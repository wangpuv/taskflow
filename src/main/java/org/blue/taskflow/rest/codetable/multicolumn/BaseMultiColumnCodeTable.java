package org.blue.taskflow.rest.codetable.multicolumn;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.blue.taskflow.rest.cache.adapt.CacheAdapter;
import org.blue.taskflow.rest.codetable.multicolumn.annotation.CodeTableColumn;
import org.blue.taskflow.rest.codetable.multicolumn.annotation.CodeTableName;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-13
 * Time: 11:42:49
 */
@SuppressWarnings(value = "unchecked")
public abstract class BaseMultiColumnCodeTable {
    private static Log log = LogFactory.getLog(BaseMultiColumnCodeTable.class);
    private DataSource dataSource;
    private String interfaceTarget;
    private String dataTarget;
    private Class interfaceClass;
    private Class dataClass;
    private Map<String, String> columnMap;
    private List<String> primaryKeyList;
    private CacheAdapter cacheAdapter;

    public BaseMultiColumnCodeTable() {
        this.columnMap = new HashMap();
        this.primaryKeyList = new ArrayList();
    }

    public CacheAdapter getCacheAdapter() {
        return this.cacheAdapter;
    }

    public void setCacheAdapter(CacheAdapter cacheAdapter) {
        this.cacheAdapter = cacheAdapter;
    }

    private void init(Class i, Class d) {
        this.interfaceClass = i;
        this.dataClass = d;

        Field[] fields = this.dataClass.getDeclaredFields();
        this.primaryKeyList.clear();
        for (Field field : fields) {
            this.columnMap.put(field.getName(), ((CodeTableColumn) field.getAnnotation(CodeTableColumn.class)).field());
            if (((CodeTableColumn) field.getAnnotation(CodeTableColumn.class)).isPk())
                this.primaryKeyList.add(((CodeTableColumn) field.getAnnotation(CodeTableColumn.class)).field());
        }
    }

    private void initData() {
        if (this.cacheAdapter.get(this.interfaceTarget, false) != null) return;
        try {
            if (this.dataTarget == null)
                this.dataTarget = this.interfaceTarget + "Data";
            init(Class.forName(this.interfaceTarget), Class.forName(this.dataTarget));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        reload();
    }

    protected List getDataMapValues() {
        initData();
        return ((List) this.cacheAdapter.get(this.interfaceTarget, true));
    }

    protected Object getCodeTableClass(String field, String value) {
        initData();
        List dataList = (List) this.cacheAdapter.get(this.interfaceTarget, true);
        for (Object o : dataList) {
            try {
                String currentValue = BeanUtils.getProperty(o, field);
                if (value.equals(currentValue))
                    return o;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected List getChoosedDataMapValues() {
        List list = getDataMapValues();
        List result = new ArrayList();
        for (Object o : list) {
            Field[] fields = o.getClass().getDeclaredFields();
            for (Field f : fields) {
                if (!(((CodeTableColumn) f.getAnnotation(CodeTableColumn.class)).field().equalsIgnoreCase("CHOOSE")))
                    continue;
                try {
                    String v = BeanUtils.getProperty(o, f.getName());
                    if ((v.equalsIgnoreCase("Y")) || (v.equals("1")))
                        result.add(o);
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    protected List<Object> getCodeTableList(String field, String value) {
        initData();
        List dataList = (List) this.cacheAdapter.get(this.interfaceTarget, true);

        List result = new ArrayList();
        for (Object o : dataList) {
            try {
                String currentValue = BeanUtils.getProperty(o, field);
                if (value.equals(currentValue))
                    result.add(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    protected void updateChooseSign(boolean choose, String[] pkValues) {
        initData();
        if (pkValues.length != this.primaryKeyList.size()) {
            throw new RuntimeException("传入主键值个数与主键个数不一致！");
        }
        JdbcTemplate template = new JdbcTemplate(this.dataSource);
        template.execute(getUpdateChooseSql(choose, pkValues));
        log.info("更新多值代码表: sql : " + getUpdateChooseSql(choose, pkValues));
        this.cacheAdapter.remove(this.interfaceTarget);
    }

    private String getLoadSql() {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        List fields = new ArrayList(this.columnMap.values());
        for (int i = 0; i < fields.size(); ++i) {
            sql.append((String) fields.get(i));
            if (i < fields.size() - 1) {
                sql.append(", ");
            }
        }
        sql.append(" FROM ").append(((CodeTableName) this.dataClass.getAnnotations()[0]).value());

        return sql.toString();
    }

    private String getUpdateChooseSql(boolean choose, String[] pkValues) {
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE ").append(((CodeTableName) this.dataClass.getAnnotations()[0]).value()).append(" set ").append(getChooseSignCol()).append(" = ").append((choose) ? 1 : 0).append(" WHERE ");

        for (int i = 0; i < this.primaryKeyList.size(); ++i) {
            sql.append(this.primaryKeyList.get(i)).append(" = '").append(pkValues[i]).append("'");

            if (i < this.primaryKeyList.size() - 1) {
                sql.append(" and ");
            }
        }
        return sql.toString();
    }

    private void reload() {
        JdbcTemplate template = new JdbcTemplate(this.dataSource);
        final List dataList = new ArrayList();
        template.query(getLoadSql(), new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                Object o = null;
                try {
                    o = BaseMultiColumnCodeTable.this.dataClass.newInstance();
                    for (String attribute : BaseMultiColumnCodeTable.this.columnMap.keySet())
                        BeanUtils.setProperty(o, attribute, rs.getString((String) BaseMultiColumnCodeTable.this.columnMap.get(attribute)));
                }
                catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                dataList.add(o);
            }
        });
        log.info("加载多值代码表, sql : " + getLoadSql());
        this.cacheAdapter.put(this.interfaceTarget, dataList);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataTarget(String dataTarget) {
        this.dataTarget = dataTarget;
    }

    public void setInterfaceTarget(String interfaceTarget) {
        this.interfaceTarget = interfaceTarget;
    }

    private String getChooseSignCol() {
        Field[] fields = this.dataClass.getDeclaredFields();
        for (Field temp : fields) {
            if (temp.getName().equals("chooseSign")) {
                return temp.getAnnotation(CodeTableColumn.class).field();
            }
        }
        return "CHOOSE";
    }
}
