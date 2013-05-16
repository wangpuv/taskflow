package org.blue.taskflow.rest.codetable;

import org.blue.taskflow.rest.cache.adapt.CacheAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-13
 * Time: 10:56:19
 */
@SuppressWarnings(value = "unchecked")
abstract class AbstractCodeTableTemplate {

    @Autowired
    protected CacheAdapter cacheAdapter;

    protected abstract void init();

    protected List<CodeTableData> getAllCode(String key) {
        init();
        List<CodeTableData> result = (List<CodeTableData>) this.cacheAdapter.get(key, true);
        Collections.sort(result);
        return result;
    }

    protected List<CodeTableData> getAllChoosedCode(String key) {
        init();
        List result = new ArrayList();
        for (CodeTableData codeTable : (List<CodeTableData>) this.cacheAdapter.get(key, true)) {
            if (codeTable.isChoose())
                result.add(codeTable);
        }
        Collections.sort(result);
        return result;
    }

    protected String getNameByCode(String code, String key) {
        init();
        for (CodeTableData codeTable : (List<CodeTableData>) this.cacheAdapter.get(key, false)) {
            if (codeTable.getCode().equals(code))
                return codeTable.getName();
        }
        return null;
    }

    protected List<String> getNameListByCode(String code, String key) {
        init();
        List result = new ArrayList();
        for (CodeTableData codeTable : (List<CodeTableData>) this.cacheAdapter.get(key, false)) {
            if (codeTable.getCode().equals(code))
                result.add(codeTable.getName());
        }
        Collections.sort(result);
        return result;
    }

    protected List<String> getCodeByName(String name, String key) {
        init();
        List result = new ArrayList();
        for (CodeTableData codeTable : (List<CodeTableData>) this.cacheAdapter.get(key, false)) {
            if (codeTable.getName().equals(name))
                result.add(codeTable.getCode());
        }
        Collections.sort(result);
        return result;
    }

    protected String getTagByCode(String code, String key) {
        init();
        for (CodeTableData codeTable : (List<CodeTableData>) this.cacheAdapter.get(key, false)) {
            if (codeTable.getCode().equals(code))
                return codeTable.getTag();
        }
        return null;
    }
}
