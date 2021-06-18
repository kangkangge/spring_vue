package com.tbc.demo.catalog.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.annotation.write.style.OnceAbsoluteMerge;
import lombok.Data;
import org.apache.poi.ss.usermodel.FillPatternType;

import java.util.Date;

/**
 * 导入模板
 */
public class ImportTemplate {

    private String tagName;
    private String tagCategreName;
    private String tagDesc;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagCategreName() {
        return tagCategreName;
    }

    public void setTagCategreName(String tagCategreName) {
        this.tagCategreName = tagCategreName;
    }

    public String getTagDesc() {
        return tagDesc;
    }

    public void setTagDesc(String tagDesc) {
        this.tagDesc = tagDesc;
    }
}
