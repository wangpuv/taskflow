package org.blue.taskflow.rest.codetable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-13
 * Time: 11:33:21
 */
public class StoreCodeTableTemplate extends AbstractCodeTableTemplate implements CodeTable {
    private static Log log = LogFactory.getLog(StoreCodeTableTemplate.class);
    private String tableName;

    @Autowired
    private DataSource dataSource;

    private void reload() {
        final List<CodeTableData> codeTableList = new ArrayList<CodeTableData>();
        JdbcTemplate template = new JdbcTemplate(this.dataSource);

        template.query("SELECT * FROM " + this.tableName, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                CodeTableData ctd = new CodeTableData();
                ctd.setCode(rs.getString("CODE"));
                ctd.setName(rs.getString("NAME"));
                ctd.setChoose("Y".equalsIgnoreCase(rs.getString("CHOOSE")));
                ResultSetMetaData meta = rs.getMetaData();
                for (int i = 1; i <= meta.getColumnCount(); ++i) {
                    if (meta.getColumnName(i).equalsIgnoreCase("TAG")) {
                        ctd.setTag(rs.getString("TAG"));
                        break;
                    }
                }
                codeTableList.add(ctd);
            }
        });
        log.info("初始化表数据sql : SELECT * FROM " + this.tableName);

        this.cacheAdapter.put(this.tableName, codeTableList);
    }

    public void init() {
        if (this.cacheAdapter.get(this.tableName, false) == null)
            reload();
    }

    public List<CodeTableData> getAllCode() {
        return super.getAllCode(this.tableName);
    }

    public List<CodeTableData> getAllChoosedCode() {
        return super.getAllChoosedCode(this.tableName);
    }

    public String getNameByCode(String code) {
        return super.getNameByCode(code, this.tableName);
    }

    public List<String> getNameListByCode(String code) {
        return super.getNameListByCode(code, this.tableName);
    }

    public String getTagByCode(String code) {
        return super.getTagByCode(code, this.tableName);
    }

    public String getTableName() {
        return this.tableName;
    }

    public List<String> getCodeByName(String name) {
        return super.getCodeByName(name, this.tableName);
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
