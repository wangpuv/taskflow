package org.blue.taskflow.rest.codetable;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: blue
 * Date: 2010-9-13
 * Time: 10:54:07
 */
public class CodeTableData implements Serializable, Comparable<CodeTableData> {
    private String code;
    private String name;
    private String tag;
    private boolean choose;

    public CodeTableData() {
    }

    public CodeTableData(String code, String name, boolean choose) {
        this.code = code;
        this.name = name;
        this.choose = choose;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isChoose() {
        return this.choose;
    }

    public void setChoose(boolean choose) {
        this.choose = choose;
    }

    public int compareTo(CodeTableData o) {
        return new CompareToBuilder().append(getCode(), o.getCode()).toComparison();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
