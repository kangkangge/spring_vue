package com.tbc.demo.catalog.excel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Label;
import jxl.write.biff.CellValue;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.util.FileCopyUtils;

//import java.awt.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
public class Jxl {


    private static String filePath = "C:\\Users\\gekang\\Desktop\\标签管理导入.xls";
    private static String msg = "1. *号显示的都为必填项；\n" +
            "2. 标签分类必须已经在平台的标签管理应用中存在；\n" +
            "3. 标签名称最多10个字符；\n" +
            "\n" +
            "注意：\n" +
            "1. 本操作为覆盖操作，成功导入后会以当前表格内容为准；\n" +
            "2. 请保持模板中的单元格格式为“文本”";
    private String msg1 = "第四行开始为内容填写区域，请删除样例信息后填写：";


    @Test
    public void test() throws IOException, WriteException, BiffException {
        File file = new File(filePath);
        file.mkdir();
        WritableWorkbook writableWorkbook = null;
        WritableWorkbook workbook = null;
        try {
            workbook = Workbook.createWorkbook(file, Workbook.getWorkbook(file));
            writableWorkbook = setTemplateStyle(workbook, msg, msg1);
            WritableSheet sheet = writableWorkbook.getSheet(0);
            List<ImportTemplate> data = getData();
            for (int row = 4; row < data.size(); row++) {
                for (int col = 0; col < 3; col++) {
                    switch (col) {
                        case 0:
                            sheet.addCell(new Label(col, row, data.get(row - 4).getTagName()));
                            break;
                        case 1:
                            sheet.addCell(new Label(col, row, data.get(row - 4).getTagCategreName()));
                            break;
                        case 2:
                            sheet.addCell(new Label(col, row, data.get(row - 4).getTagDesc()));
                            break;
                    }

                }
            }
            writableWorkbook.write();
            writableWorkbook.close();
        } catch (Exception e) {
            if (writableWorkbook != null) {
                try {
                    writableWorkbook.close();
                } catch (Exception e1) {
                    log.error(e.getMessage());
                }
            }
        }
    }




    public void getTempla(File xlsFile) throws IOException, BiffException, WriteException {
        WritableWorkbook workbook = Workbook.createWorkbook(xlsFile, Workbook.getWorkbook(xlsFile));
        WritableSheet sheet = workbook.getSheet(0);
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 5; col++) {
                // 向工作表中添加数据
                if (row < 4) {
                    continue;
                } else {
                    switch (col) {
                        case 0:
                            sheet.addCell(new Label(col, row, "cheshi" + row + new Date()));
                            break;
                        case 1:
                            sheet.addCell(new Label(col, row, "cheshi" + row + new Date()));
                            break;
                        case 2:
                            sheet.addCell(new Label(col, row, "cheshi" + row + new Date()));
                            break;
                        case 3:
                            sheet.addCell(new Label(col, row, "cheshi" + row + new Date()));
                            break;
                        case 4:
                            sheet.addCell(new Label(col, row, Clock.systemDefaultZone().instant().toString()));
                            break;
                    }

                }
            }
        }
        workbook.write();
        workbook.close();
    }


    public WritableWorkbook setTemplateStyle(WritableWorkbook workbook, String title, String title2) throws IOException, WriteException {
        if (workbook == null) {
            return null;
        }
        WritableSheet sheet = workbook.getSheet(0);
        //重写颜色实现自定义颜色
        Colour green = setMsgStyle(Colour.GREEN, "#c6e0b4", workbook);
        Colour gray = setMsgStyle(Colour.GRAY_80, "#a896a8", workbook);
        //设置单元格样式
        WritableCellFormat wcf = new WritableCellFormat(new WritableFont(WritableFont.createFont(msg), 12, WritableFont.NO_BOLD));
        //自动换行
        wcf.setWrap(true);
        WritableFont writableFont = new WritableFont(WritableFont.createFont(msg), 12, WritableFont.NO_BOLD);
        writableFont.setColour(gray);
        WritableCellFormat wcf1 = new WritableCellFormat(writableFont);
        wcf.setBackground(green);
        //合并单元格
        sheet.mergeCells(0, 0, 30, 0);
        sheet.mergeCells(0, 1, 30, 1);
        //设置行高
        sheet.setRowView(0, 2500);
        //写入指定行列数据
        sheet.addCell(new Label(0, 0, title, wcf));
        sheet.addCell(new Label(0, 1, title2, wcf1));
        return workbook;
    }


    @Test
    public void writeExcel() throws IOException, WriteException, BiffException {
        File xlsFile = new File(filePath);
        // 创建一个工作簿
        WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
        // 创建一个工作表
        WritableSheet sheet = workbook.createSheet("失败邮件详情", 0);
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 5; col++) {
                // 向工作表中添加数据
                if (row < 3) {
                    continue;
                } else {
                    switch (col) {
                        case 0:
                            sheet.addCell(new Label(col, row, "cheshi" + row + new Date()));
                            break;
                        case 1:
                            sheet.addCell(new Label(col, row, "cheshi" + row + new Date()));
                            break;
                        case 2:
                            sheet.addCell(new Label(col, row, "cheshi" + row + new Date()));
                            break;
                        case 3:
                            sheet.addCell(new Label(col, row, "cheshi" + row + new Date()));
                            break;
                        case 4:
                            sheet.addCell(new Label(col, row, Clock.systemDefaultZone().instant().toString()));
                            break;
                    }

                }
            }
        }
        workbook.write();
        workbook.close();
    }


    public static void setTitle(WritableSheet sheet) throws WriteException {
        WritableCellFormat writableCellFormat = new WritableCellFormat();
        //设置背景色
        writableCellFormat.setBackground(Colour.GREEN);
        //设置字体
        WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
        writableCellFormat.setFont(font);
        //设置边框
        writableCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        //设置列宽
        sheet.getSettings().setDefaultColumnWidth(40);
        sheet.addCell(new Label(0, 0, "邮件标题", writableCellFormat));
        sheet.addCell(new Label(1, 0, "接收人姓名", writableCellFormat));
        sheet.addCell(new Label(2, 0, "邮箱账号", writableCellFormat));
        sheet.addCell(new Label(3, 0, "发送状态", writableCellFormat));
        sheet.addCell(new Label(4, 0, "发送状态", writableCellFormat));
    }

    private Colour setMsgStyle(Colour colour, String value, WritableWorkbook book) throws WriteException {
        Color color = Color.decode(value); // 自定义的颜色
        book.setColourRGB(colour, color.getRed(),
                color.getGreen(), color.getBlue());
        return colour;

    }

    private List<ImportTemplate> getData() {
        List<ImportTemplate> list = new ArrayList<>();
        for (int i = 0; i < 99; i++) {
            ImportTemplate importTemplate = new ImportTemplate();
            importTemplate.setTagCategreName("分类名称" + i);
            importTemplate.setTagName("分类名称" + i);
            importTemplate.setTagDesc("这里是描述" + i);
            list.add(importTemplate);
        }
        return list;
    }


}
