package com.js.common.util.excel;

import com.js.common.exception.SystemException;
import com.js.enums.program.ProgramDateEnum;
import com.js.form.broadcast.mistake.BroadcastMistakeForm;
import com.js.vo.broadcast.BroadcastMistakeExport;
import com.js.vo.system.SysConfigCommon;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @Author: 姜爽
 * @Description: excle导出错误
 * @Date: 2020/5/10 14:15
 */
public class MistakeExcelUtil {

    public static void export(HttpServletResponse response, List<BroadcastMistakeExport> broadcastMistakeExportList, SysConfigCommon sysConfigCommon, BroadcastMistakeForm broadcastMistakeForm) {
        //声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        sheet.setColumnWidth(0,500);
        HSSFHeader header = sheet.getHeader();
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        //字体大小
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short)20);
        //什么字体
        headerFont.setFontName("微软雅黑");
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        header.setCenter("黑龙江大学有线广播台播音事故与签到情况汇总");

        Row timeArang = sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(1,1,0,6));
        //合并单元格，数字代表的依次为起始行，截至行，起始列， 截至列
        timeArang.createCell(1).setCellValue(sysConfigCommon.getAcademicYear() + "年度第" + sysConfigCommon.getAcademicTerm() + "学期第" + broadcastMistakeForm.getTeachingWeek() + "教学周放音错误报告");

        //设置副标题
        Row topic = sheet.createRow(1);
        topic.createCell(0).setCellValue("序号");
        topic.createCell(1).setCellValue("时间段");
        topic.createCell(2).setCellValue("节目名称");
        topic.createCell(3).setCellValue("事故内容");
        topic.createCell(4).setCellValue("应扣分数");
        topic.createCell(5).setCellValue("备注");
        for (int rowNum = 2;rowNum < broadcastMistakeExportList.size() + 2; rowNum ++){
            Row mainRow=sheet.createRow(rowNum);
            mainRow.setHeight(new Short("35"));
            BroadcastMistakeExport broadcastMistakeExport = broadcastMistakeExportList.get(rowNum-2);
            mainRow.createCell(0).setCellValue(rowNum - 2);
            mainRow.createCell(1).setCellValue(ProgramDateEnum.valueOf(broadcastMistakeExport.getPeriod()).getMsg());
            mainRow.createCell(2).setCellValue(broadcastMistakeExport.getProgramName());
            mainRow.createCell(3).setCellValue(broadcastMistakeExport.getDetail());
        }



        OutputStream os = null;
        //清空response
        response.reset();
        try{
            response.addHeader("Content-Disposition", "attachment;filename="+sysConfigCommon.getAcademicYear() + "年度第" +
                    sysConfigCommon.getAcademicTerm() + "学期第" + broadcastMistakeForm.getTeachingWeek() + "教学周监听报告.xls");
            os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            //将excel写入到输出流中
            workbook.write(os);
            os.flush();
            os.close();
        }catch (Exception e){
            throw new SystemException("导出出现异常");
        }
    }
}
