package org.blue.taskflow.rest.codetable;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-13
 * Time: 10:53:11
 */
public interface CodeTable {
    void init();

    List<CodeTableData> getAllCode();

    List<CodeTableData> getAllChoosedCode();

    String getNameByCode(String code);

    List<String> getNameListByCode(String code);

    List<String> getCodeByName(String name);

    String getTagByCode(String code);

    String getTableName();
}
