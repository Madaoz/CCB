package com.rabbiter.oes.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.oes.entity.ApiResult;
import com.rabbiter.oes.entity.BpjPerson;
import com.rabbiter.oes.serviceimpl.AdminServiceImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 管理端功能
 * 重置用户密码为初始密码
 * 查询得分情况
 * 导出得分情况表
 */

@RestController
public class AdminController {

    private AdminServiceImpl adminService;
    @Autowired
    public AdminController(AdminServiceImpl adminService){
        this.adminService = adminService;
    }

    /**
     * 管理员输入8位员工编号重置用户密码
     * @param userId
     * @return
     */
    @GetMapping("/admin/{userId}")
    public ApiResult updatePWD(@PathVariable("userId") String userId){
        int a = adminService.updatePWD(userId);
        if(a == 0){
            return ApiResultHandler.buildApiResult(10000,"8为员工编号不存在",null);
        }
        return ApiResultHandler.success(a);
    }

    /**
     * 分页查询所有的分情况
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/admin1/{page}/{size}")
    public ApiResult selectAll1(@PathVariable("page") Integer page,@PathVariable("size") Integer size){
        System.out.println("分页查询所有被评价人员得分情况");
        ApiResult apiResult;
        Page<BpjPerson> bpjPersonPage = new Page<>(page,size);
        IPage<BpjPerson> all = adminService.selectAllPage(bpjPersonPage);
        List<BpjPerson> bpjPersonList = all.getRecords();
        for(BpjPerson bp : bpjPersonList){
            if(bp.getSelfevaluation() != null && bp.getSelfevaluation().equals("0")){
                bp.setSelfevaluation("");
            }
            if(bp.getSuperior() != null && bp.getSuperior().equals("0")){
                bp.setSuperior("");
            }
            if(bp.getSuperiorNm() != null && bp.getSuperiorNm().equals("0")){
                bp.setSuperiorNm("");
            }
            if(bp.getEqual() != null && bp.getEqual().equals("0")){
                bp.setEqual("");
            }
            if(bp.getEqualNm() != null && bp.getEqualNm().equals("0")){
                bp.setEqualNm("");
            }
            if(bp.getSubordinate() != null && bp.getSubordinate().equals("0")){
                bp.setSubordinate("");
            }
            if(bp.getSubordinateNm() != null && bp.getSubordinateNm().equals("0")){
                bp.setSubordinateNm("");
            }
            if(bp.getTotalscore() != null && bp.getTotalscore().equals("0")){
                bp.setTotalscore("");
            }
            if(bp.getTotalNm() != null && bp.getTotalNm().equals("0")){
                bp.setTotalNm("");
            }
        }
        apiResult = ApiResultHandler.buildApiResult(200,"请求成功",all);
        return apiResult;
    }

    /**
     * 管理员根据姓名查询得分信息
     * @param name
     * @return
     */
    @GetMapping("/admin1/{name}")
    public ApiResult selectByName(@PathVariable("name") String name){
        System.out.println("查询所有被评价人员得分情况");
        List<BpjPerson> bpjPersonList = adminService.selectByName(name);
        for(int i = 0;i < bpjPersonList.size();i++){
            BpjPerson bp = bpjPersonList.get(i);
            if(bp.getSelfevaluation() != null && bp.getSelfevaluation().equals("0")){
                bp.setSelfevaluation("");
            }
            if(bp.getSuperior() != null && bp.getSuperior().equals("0")){
                bp.setSuperior("");
            }
            if(bp.getSuperiorNm() != null && bp.getSuperiorNm().equals("0")){
                bp.setSuperiorNm("");
            }
            if(bp.getEqual() != null && bp.getEqual().equals("0")){
                bp.setEqual("");
            }
            if(bp.getEqualNm() != null && bp.getEqualNm().equals("0")){
                bp.setEqualNm("");
            }
            if(bp.getSubordinate() != null && bp.getSubordinate().equals("0")){
                bp.setSubordinate("");
            }
            if(bp.getSubordinateNm() != null && bp.getSubordinateNm().equals("0")){
                bp.setSubordinateNm("");
            }
            if(bp.getTotalscore() != null && bp.getTotalscore().equals("0")){
                bp.setTotalscore("");
            }
            if(bp.getTotalNm() != null && bp.getTotalNm().equals("0")){
                bp.setTotalNm("");
            }
        }
        return ApiResultHandler.success(bpjPersonList);
    }


    /**
     * 管理员导入模板文件发起评价
     * @param file
     * @return
     */
    @PostMapping ("/upload")
    public ApiResult uploadFile(@RequestParam("file") MultipartFile file,HttpServletResponse response) throws ServletException, IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getLastRowNum();
        for (int i=0; i<=rows; i++){
            String pjId = sheet.getRow(i).getCell(0).toString();
            String pjName = sheet.getRow(i).getCell(1).toString();
            String pjUass = sheet.getRow(i).getCell(2).toString();
            String pjInstname = sheet.getRow(i).getCell(3).toString();
            String bpjId = sheet.getRow(i).getCell(4).toString();
            String bpjName = sheet.getRow(i).getCell(5).toString();
            String bpjUass = sheet.getRow(i).getCell(6).toString();
            String bpjInstname = sheet.getRow(i).getCell(7).toString();
            String level = sheet.getRow(i).getCell(8).toString();
            //将评价人信息导入userinfo()
            //查询是否存在，若不存在进行新增，若存在，对比instname是否需要更新

            //将被评价人信息导入leaderinfo
            //查询是否存在，若不存在进行新增，若存在，对比instname是否需要更新


            //将评价关系导入score_manager





        }
        return  ApiResultHandler.buildApiResult(200,"请求成功",null);

    }

    /**
     * 管理员导出查询结果
     * @param response
     */
    @GetMapping("/download")
    public void downloadExcel(HttpServletResponse response) {
        System.out.println("管理员导出查询结果");
        List<BpjPerson> bpjPersonList = adminService.dwonloadExcel();
        BpjPerson bp = new BpjPerson();
        System.out.println("生成excle");
        XSSFWorkbook hs = new XSSFWorkbook();
        XSSFSheet sheet = hs.createSheet("shee1");
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints((short) 60);
        XSSFCell cell = headerRow.createCell(0);
        //设置第一行格式，font字体加粗，大小15
        XSSFFont font1 = hs.createFont();
        font1.setBold(true);
        font1.setFontHeight((long) 15);
        XSSFCellStyle xssfCellStyle1 = hs.createCellStyle();
        xssfCellStyle1.setFont(font1);
        xssfCellStyle1.setAlignment(HorizontalAlignment.CENTER);
        xssfCellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
        sheet.setColumnWidth(0,15*256);
        sheet.setColumnWidth(1,15*256);
        sheet.setColumnWidth(2,15*256);
        sheet.setColumnWidth(3,15*256);
        sheet.setColumnWidth(4,15*256);
        sheet.setColumnWidth(5,20*256);
        sheet.setColumnWidth(6,20*256);
        sheet.setColumnWidth(7,20*256);
        sheet.setColumnWidth(8,20*256);
        sheet.setColumnWidth(9,20*256);
        sheet.setColumnWidth(10,20*256);
        List<String> titleList = new LinkedList<>();
        titleList.add("姓名");
        titleList.add("uass");
        titleList.add("自测得分");
        titleList.add("他测得分");
        titleList.add("他测人数");
        titleList.add("上级评价得分");
        titleList.add("上级评价人数");
        titleList.add("同级评价得分");
        titleList.add("同级评价人数");
        titleList.add("下级评价得分");
        titleList.add("下级评价人数");
        for (int i = 0; i < titleList.size(); i++) {
            cell = headerRow.createCell(i);
            cell.setCellStyle(xssfCellStyle1);
            cell.setCellValue(titleList.get(i));
        }
        //设置数据行单元格格式
        XSSFFont font2 = hs.createFont();
        font2.setFontHeight((long) 12);
        XSSFCellStyle xssfCellStyle2 = hs.createCellStyle();
        xssfCellStyle2.setFont(font2);
        xssfCellStyle2.setAlignment(HorizontalAlignment.CENTER);
        xssfCellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
        int rowNum = 1;
        //插入数据
        for (int i = 0; i < bpjPersonList.size(); i++) {
            bp = bpjPersonList.get(i);
            setBpjperson(bp);
            headerRow = sheet.createRow(rowNum++);
            headerRow.setHeightInPoints((short) 30);
            cell = headerRow.createCell(0);
            cell.setCellValue(bp.getName());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(1);
            cell.setCellValue(bp.getUass());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(2);
            cell.setCellValue(bp.getSelfevaluation());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(3);
            cell.setCellValue(bp.getTotalscore());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(4);
            cell.setCellValue(bp.getTotalNm());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(5);
            cell.setCellValue(bp.getSuperior());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(6);
            cell.setCellValue(bp.getSuperiorNm());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(7);
            cell.setCellValue(bp.getEqual());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(8);
            cell.setCellValue(bp.getEqualNm());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(9);
            cell.setCellValue(bp.getSubordinate());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(10);
            cell.setCellValue(bp.getSubordinateNm());
            cell.setCellStyle(xssfCellStyle2);
        }
        try (OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename" + URLEncoder.encode("resoult.xlsx","UTF-8"));
            response.setCharacterEncoding("utf-8");
            hs.write(outputStream);
            outputStream.flush();
            outputStream.close();
            hs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //将数据库中值为0的数据，在导出excel的时候转换为null
    private void setBpjperson(BpjPerson bpjperson) {
        if(bpjperson.getSelfevaluation() != null && bpjperson.getSelfevaluation().equals("0")) {
            bpjperson.setSelfevaluation(null);
        }
        if(bpjperson.getEqual() != null && bpjperson.getEqual().equals("0")){
            bpjperson.setEqual(null);
        }
        if(bpjperson.getSubordinate() != null && bpjperson.getSubordinate().equals("0")){
            bpjperson.setSubordinate(null);
        }
        if(bpjperson.getSuperior() != null && bpjperson.getSuperior().equals("0")){
            bpjperson.setSuperior(null);
        }
        if(bpjperson.getTotalscore() != null && bpjperson.getTotalscore().equals("0")){
            bpjperson.setTotalscore(null);
        }
        if(bpjperson.getEqualNm() != null && bpjperson.getEqualNm().equals("0")){
            bpjperson.setEqualNm(null);
        }
        if(bpjperson.getSubordinateNm() != null && bpjperson.getSubordinateNm().equals("0")){
            bpjperson.setSubordinateNm(null);
        }
        if(bpjperson.getSuperiorNm() != null && bpjperson.getSuperiorNm().equals("0")){
            bpjperson.setSuperiorNm(null);
        }
        if(bpjperson.getTotalNm() != null && bpjperson.getTotalNm().equals("0")){
            bpjperson.setTotalNm(null);
        }
    }

}
