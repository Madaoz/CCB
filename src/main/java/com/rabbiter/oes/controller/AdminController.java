package com.rabbiter.oes.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.rabbiter.oes.entity.ApiResult;
import com.rabbiter.oes.entity.BpjPerson;
import com.rabbiter.oes.entity.User;
import com.rabbiter.oes.serviceimpl.AdminServiceImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * 管理端功能
 * 重置用户密码为初始密码
 * 查询得分情况
 * 导出得分情况表
 */

@RestController
public class AdminController {
    private static final Logger logger = LogManager.getLogger(AdminController.class);

    private AdminServiceImpl adminService;

    @Autowired
    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    /**
     * 管理员输入8位员工编号重置用户密码
     *
     * @param userId 用户8位员工编号
     * @return 返回成功
     */
    @GetMapping("/admin/{userId}")
    public ApiResult updatePWD(@PathVariable("userId") String userId) {
        logger.info("=============开始重置密码================");
        int a = adminService.updatePWD(userId);
        if (a == 0) {
            return ApiResultHandler.buildApiResult(10000, "8为员工编号不存在", null);
        }
        logger.info("=============重置密码结束=============");
        return ApiResultHandler.success(a);
    }

    /**
     * 分页查询所有的分情况
     *
     * @param page
     * @param size
     * @return 返回查询结果
     */
    @GetMapping("/admin1/{page}/{size}")
    public ApiResult selectAll1(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        logger.info("传入参数：" + " page = " + page + "   size = " + size);
        logger.info("===================分页查询所有被评价人员得分情况====================");
        ApiResult apiResult;
        Page<BpjPerson> bpjPersonPage = new Page<>(page, size);
        IPage<BpjPerson> all = adminService.selectAllPage(bpjPersonPage);
        List<BpjPerson> bpjPersonList = all.getRecords();
        for (BpjPerson bp : bpjPersonList) {
            setBpjperson(bp);
            logger.info("查询结果：" + bp.toString());
        }
        apiResult = ApiResultHandler.buildApiResult(200, "请求成功", all);
        logger.info("======================分页查询结束=====================");
        return apiResult;
    }

    /**
     * 管理员根据姓名查询得分信息
     * @param bpjPerson
     * @return 返回查询结果
     */
    @PostMapping("/admin2")
    public ApiResult selectByName(@RequestBody BpjPerson bpjPerson) {
        logger.info("=================通过姓名或员工编号查询====================");
        String name = bpjPerson.getName();
        String id = bpjPerson.getId();
        System.out.println("name =  " + name + "  id = " + id);
        List<BpjPerson> bpjPersonList = null;
        if (name != null && name != "" && id != null && id != "") {
            bpjPersonList = adminService.selectOne(name, id);
        } else if (name != null && name != "") {
            bpjPersonList = adminService.selectByName(name);
        } else if (id != null && id != "") {
            bpjPersonList = adminService.selectById(id);
        }

        for (int i = 0; i < bpjPersonList.size(); i++) {
            BpjPerson bp = bpjPersonList.get(i);
            setBpjperson(bp);
            logger.info("查询结果：" + bp.toString());
        }
        logger.info("===============通过姓名查询结束===============");
        return ApiResultHandler.success(bpjPersonList);
    }

    /**
     * 管理员下载勾稽关系表模板
     *
     * @param response
     * @throws IOException
     */
    @GetMapping("/down1")
    public void down(HttpServletResponse response) throws IOException {
        System.out.println("管理员下载模板");
        logger.info("==============管理员开始下载模板====================");
        XSSFWorkbook hs = new XSSFWorkbook("/home/ap/ccb/filepath/file.xlsx");
//        XSSFWorkbook hs = new XSSFWorkbook("filepath/file.xlsx");
//        XSSFWorkbook hs = new XSSFWorkbook("C:\\Users\\Administrator\\Desktop\\path\\勾稽关系模板\\导入模板.xlsx");


        try (OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename" + URLEncoder.encode("resoult.xlsx", "UTF-8"));
            response.setCharacterEncoding("utf-8");
            hs.write(outputStream);
            outputStream.flush();
            outputStream.close();
            hs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("====================下载模板结束==================");
    }

    /**
     * 管理员导出三级评价查询结果
     *
     * @param response
     */
    @GetMapping("/download1")
    public void downloadExcel1(HttpServletResponse response) {
        System.out.println("管理员导出查询结果");
        logger.info("===========================管理员导出查询结果==========================");
        List<BpjPerson> bpjPersonList = adminService.dwonloadExcel();
        BpjPerson bp = new BpjPerson();
        logger.info("=============开始生成excle==============");
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
        sheet.setColumnWidth(0, 15 * 256);
        sheet.setColumnWidth(1, 15 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 15 * 256);
        sheet.setColumnWidth(4, 15 * 256);
        sheet.setColumnWidth(5, 15 * 256);
        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 20 * 256);
        sheet.setColumnWidth(8, 20 * 256);
        sheet.setColumnWidth(9, 20 * 256);
        sheet.setColumnWidth(10, 20 * 256);
        sheet.setColumnWidth(11, 20 * 256);
        List<String> titleList = new LinkedList<>();
        titleList.add("姓名");
        titleList.add("员工编号");
        titleList.add("所在部门");
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
            cell.setCellValue(bp.getId());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(2);
            cell.setCellValue(bp.getInstName());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(3);
            cell.setCellValue(bp.getSelfevaluation());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(4);
            cell.setCellValue(bp.getTotalscore());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(5);
            cell.setCellValue(bp.getTotalNm());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(6);
            cell.setCellValue(bp.getSuperior());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(7);
            cell.setCellValue(bp.getSuperiorNm());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(8);
            cell.setCellValue(bp.getEqual());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(9);
            cell.setCellValue(bp.getEqualNm());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(10);
            cell.setCellValue(bp.getSubordinate());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(11);
            cell.setCellValue(bp.getSubordinateNm());
            cell.setCellStyle(xssfCellStyle2);
        }
        logger.info("===============excel生成结束===============");
        try (OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename" + URLEncoder.encode("resoult.xlsx", "UTF-8"));
            response.setCharacterEncoding("utf-8");
            hs.write(outputStream);
            outputStream.flush();
            outputStream.close();
            hs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("=======================管理员excel导出结束=====================");
    }

    /**
     * 管理员导出不同类型题目得分查询结果
     *
     * @param response
     */
    @GetMapping("/download2")
    public void downloadExcel2(HttpServletResponse response) {
        System.out.println("管理员导出查询结果");
        logger.info("===========================管理员导出查询结果==========================");
        List<BpjPerson> bpjPersonList = adminService.dwonloadExcel();
        BpjPerson bp = new BpjPerson();
        logger.info("=============开始生成excle==============");
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
        sheet.setColumnWidth(0, 15 * 256);
        sheet.setColumnWidth(1, 15 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 25 * 256);
        sheet.setColumnWidth(4, 25 * 256);
        sheet.setColumnWidth(5, 25 * 256);
        sheet.setColumnWidth(6, 25 * 256);
        sheet.setColumnWidth(7, 25 * 256);

        List<String> titleList = new LinkedList<>();
        titleList.add("姓名");
        titleList.add("员工编号");
        titleList.add("所在部门");
        titleList.add("’以身作则‘得分");
        titleList.add("’共启愿景‘得分");
        titleList.add("’挑战现状‘得分");
        titleList.add("’使众人行‘得分");
        titleList.add("’激励人心‘得分");
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
            cell.setCellValue(bp.getId());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(2);
            cell.setCellValue(bp.getInstName());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(3);
            cell.setCellValue(bp.getScoreA());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(4);
            cell.setCellValue(bp.getScoreB());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(5);
            cell.setCellValue(bp.getScoreC());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(6);
            cell.setCellValue(bp.getScoreD());
            cell.setCellStyle(xssfCellStyle2);

            cell = headerRow.createCell(7);
            cell.setCellValue(bp.getScoreE());
            cell.setCellStyle(xssfCellStyle2);

        }
        logger.info("===============excel生成结束===============");
        try (OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename" + URLEncoder.encode("resoult.xlsx", "UTF-8"));
            response.setCharacterEncoding("utf-8");
            hs.write(outputStream);
            outputStream.flush();
            outputStream.close();
            hs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("=======================管理员excel导出结束=====================");
    }

    /**
     * 将数据库中值为0的数据，在导出excel的时候转换为null
     *
     * @param bpjperson
     */
    private void setBpjperson(BpjPerson bpjperson) {
        if (bpjperson.getSelfevaluation() != null && bpjperson.getSelfevaluation().equals("0")) {
            bpjperson.setSelfevaluation(null);
        }
        if (bpjperson.getEqual() != null && bpjperson.getEqual().equals("0")) {
            bpjperson.setEqual(null);
        }
        if (bpjperson.getSubordinate() != null && bpjperson.getSubordinate().equals("0")) {
            bpjperson.setSubordinate(null);
        }
        if (bpjperson.getSuperior() != null && bpjperson.getSuperior().equals("0")) {
            bpjperson.setSuperior(null);
        }
        if (bpjperson.getTotalscore() != null && bpjperson.getTotalscore().equals("0")) {
            bpjperson.setTotalscore(null);
        }
        if (bpjperson.getEqualNm() != null && bpjperson.getEqualNm().equals("0")) {
            bpjperson.setEqualNm(null);
        }
        if (bpjperson.getSubordinateNm() != null && bpjperson.getSubordinateNm().equals("0")) {
            bpjperson.setSubordinateNm(null);
        }
        if (bpjperson.getSuperiorNm() != null && bpjperson.getSuperiorNm().equals("0")) {
            bpjperson.setSuperiorNm(null);
        }
        if (bpjperson.getTotalNm() != null && bpjperson.getTotalNm().equals("0")) {
            bpjperson.setTotalNm(null);
        }
        if (bpjperson.getScoreA() != null && bpjperson.getScoreA().equals("0")) {
            bpjperson.setScoreA("");
        }
        if (bpjperson.getScoreB() != null && bpjperson.getScoreB().equals("0")) {
            bpjperson.setScoreB("");
        }
        if (bpjperson.getScoreC() != null && bpjperson.getScoreC().equals("0")) {
            bpjperson.setScoreC("");
        }
        if (bpjperson.getScoreD() != null && bpjperson.getScoreD().equals("0")) {
            bpjperson.setScoreD("");
        }
        if (bpjperson.getScoreE() != null && bpjperson.getScoreE().equals("0")) {
            bpjperson.setScoreE("");
        }
        if (bpjperson.getMarkTotalScore() != null && bpjperson.getMarkTotalScore().equals("0")) {
            bpjperson.setMarkTotalScore("");
        }
    }

    /**
     * 管理员上传文件，批量导入用户信息
     * 且更新leaderinfo用户信息以及score_manage表勾稽关系
     * @param file
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @PostMapping("/upload")
    public ApiResult uploadFile(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws ServletException, IOException {
        logger.info("==================管理员上传模板开始===================");

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getLastRowNum();
            System.out.println(rows + "============");
            for (int i = 1; i <= rows; i++) {
                if (sheet.getRow(i).getCell(0) == null || sheet.getRow(i).getCell(0).toString().equals("")) {
                    break;
                }
                CellStyle stringStyle = workbook.createCellStyle();
                stringStyle.setDataFormat(workbook.createDataFormat().getFormat("\"text\""));

                String pjName = (sheet.getRow(i).getCell(1) == null ? null : sheet.getRow(i).getCell(1).toString());
                Cell cell0 = sheet.getRow(i).getCell(0);
                cell0.setCellType(CellType.STRING);
                cell0.setCellStyle(stringStyle);

                String pjId = (cell0 == null ? null : cell0.toString());
                if(pjId.length() != 8){
                    return ApiResultHandler.buildApiResult(10000, "该人员"+pjName+"的员工编号有误"+",错误编号为："+pjId, null);
                }
                String pjUass = (sheet.getRow(i).getCell(2) == null ? null : sheet.getRow(i).getCell(2).toString());
                String pjInstname = (sheet.getRow(i).getCell(3) == null ? null : sheet.getRow(i).getCell(3).toString());
                Cell cell1 = sheet.getRow(i).getCell(4);
                cell1.setCellType(CellType.STRING);
                cell1.setCellStyle(stringStyle);
                String bpjName = (sheet.getRow(i).getCell(5) == null ? null : sheet.getRow(i).getCell(5).toString());
                String bpjId = (cell1 == null ? null : cell1.toString());
                if(bpjId.length() != 8){
                    return ApiResultHandler.buildApiResult(10000, "该人员"+bpjName+"的员工编号有误"+",错误编号为："+bpjId, null);
                }
                String bpjUass = (sheet.getRow(i).getCell(6) == null ? null : sheet.getRow(i).getCell(6).toString());
                String bpjInstname = (sheet.getRow(i).getCell(7) == null ? null : sheet.getRow(i).getCell(7).toString());
                Cell cell2 = sheet.getRow(i).getCell(8);
                cell2.setCellType(CellType.STRING);
                cell2.setCellStyle(stringStyle);
                String level = (cell2 == null ? null : cell2.toString());

                //是否更新userinfo表的机构信息标志
                boolean updateUser = false;
                //是否更新leaderinfo表中机构信息标志
                boolean updateLeader = false;

                //将评价人信息导入userinfo()
                //查询是否存在，若不存在进行新增，若存在，对比instname是否需要更新
                User user = adminService.findUserInfo(pjId);
                if (user == null) {
                    logger.info("评价人信息不存在，插入评价人信息：pjID = " + pjId + "  pjName = " + pjName + "  pjUass = " + pjUass + "  pjInstname = " + pjInstname);
                    adminService.insertUserInfo(pjId, pjName, pjUass, pjInstname);
                } else if (user != null) {
                    boolean a = user.getUserInstName().equals(pjInstname);
                    if (a == false) {
                        updateUser = true;
                        adminService.updateUserInstName(pjId, pjInstname);
                        logger.info("评价人信息存在，更新评价人机构号：pjId = " + pjId + "  pjInstname = " + pjInstname);
                    }
                }


                //将被评价人信息导入leaderinfo
                //查询是否存在，若不存在进行新增，若存在，对比instname是否需要更新
                BpjPerson bpjPerson1 = adminService.findBpjPerson(bpjId);
                if (bpjPerson1 == null) {
                    logger.info("被评价人信息不存在，插入被评价人信息：bpjId = " + bpjId + "  bpjName = " + bpjName + "bpjUass = " + bpjUass + "  bpjInstname = " + bpjInstname);
                    adminService.insertLeaderInfo(bpjId, bpjName, bpjUass, bpjInstname);
                } else if (bpjPerson1 != null) {
                    boolean b = bpjPerson1.getInstName().equals(bpjInstname);
                    if (b == false) {
                        updateLeader = true;
                        adminService.updateleaderInfo(bpjId, bpjInstname);
                        logger.info("被评价人信息存在，更新机构信息，bpjId = " + bpjId + "   bpjInstname = " + bpjInstname);
                    }
                }

                //将评价关系导入score_manager
                BpjPerson bpjPerson2 = adminService.findScoreManage(pjId, bpjId);
                if (bpjPerson2 == null) {
                    logger.info("勾稽关系不存在，插入新的勾稽关系：pjId = " + pjId + "  pjName = " + pjName + " pjUass = " + pjUass + "  pjInstname = " + pjInstname + "  bpjId = " + bpjId + "  bpjName = " + bpjName + "  bpjUass = " + bpjUass + "  bpjInstname = " + bpjInstname + "  level = " + level);
                    adminService.insertScoreManage(pjId, pjName, pjUass, pjInstname, bpjId, bpjName, bpjUass, bpjInstname, level);
                } else if (bpjPerson2 != null) {
                    if (updateUser = true) {
                        logger.info("更新评价人的机构信息");
                        adminService.updateScorePJInstName(pjId, pjInstname);
                    }
                    if (updateLeader = true) {
                        logger.info("更新被评价人的机构信息");
                        adminService.updateScoreBPJInstName(bpjId, bpjInstname);
                    }
                }
            }
            logger.info("================上传模板结束=================");
            return ApiResultHandler.buildApiResult(200, "上传成功，评价已发布!", null);
        } catch (IOException e) {
            logger.error("读取文件失败", e);
            return ApiResultHandler.buildApiResult(10000, "读取文件失败", null);
        }
    }

    /**
     *
     * @param rowOut 生成的excel表的行
     * @param cStyle 单元格格式
     * @param id      评价人id
     * @param name      评价人姓名
     * @param uass      评价人uass
     * @param part      评价人所属机构
     * @param idX       被评价人id
     * @param nameX     被评价人姓名
     * @param uassX     被评价人uass
     * @param partX     被评价人所属机构
     * @param level     评价人和被评价人勾稽关系
     */
    public void creatCellX(XSSFRow rowOut, XSSFCellStyle cStyle, String id, String name, String uass, String part, String idX,
                           String nameX, String uassX, String partX, String level) {
        Cell fcell = null;
        fcell = rowOut.createCell(0);
        fcell.setCellValue(new XSSFRichTextString(id));
        fcell.setCellStyle(cStyle);
        fcell = rowOut.createCell(1);
        fcell.setCellValue(new XSSFRichTextString(name));
        fcell.setCellStyle(cStyle);
        fcell = rowOut.createCell(2);
        fcell.setCellValue(new XSSFRichTextString(uass));
        fcell.setCellStyle(cStyle);
        fcell = rowOut.createCell(3);
        fcell.setCellValue(new XSSFRichTextString(part));
        fcell.setCellStyle(cStyle);
        fcell = rowOut.createCell(4);
        fcell.setCellValue(new XSSFRichTextString(idX));
        fcell.setCellStyle(cStyle);
        fcell = rowOut.createCell(5);
        fcell.setCellValue(new XSSFRichTextString(nameX));
        fcell.setCellStyle(cStyle);
        fcell = rowOut.createCell(6);
        fcell.setCellValue(new XSSFRichTextString(uassX));
        fcell.setCellStyle(cStyle);
        fcell = rowOut.createCell(7);
        fcell.setCellValue(new XSSFRichTextString(partX));
        fcell.setCellStyle(cStyle);
        fcell = rowOut.createCell(8);
        fcell.setCellValue(new XSSFRichTextString(level));
        fcell.setCellStyle(cStyle);
    }

    /**
     * 行领导uass信息
     * @param name
     * @return
     */
    public String getUass(String name) {
        String uass = null;
        switch (name) {
            case "杨军":
                uass = "yangjun.sd";
                break;
            case "包建军":
                uass = "baojianjun.sd";
                break;
            case "冯汝臣":
                uass = "fengruchen.sd";
                break;
            case "焦守铭":
                uass = "jiaoshouming.sd";
                break;
            case "陆滨":
                uass = "lubin.sd";
                break;
            case "肖鹏":
                uass = "xiaopeng.sd";
                break;
            case "刘春龙":
                uass = "liuchunlong.sd";
                break;
            case "王云鹏":
                uass = "wangyunpeng.sd";
                break;
            case "范传东":
                uass = "fanchuandong.sd";
                break;
            case "王宪明":
                uass = "wangxianming.sd";
                break;
        }
        return uass;
    }

    /**
     * 行领导8位编号
     * @param name
     * @return
     */
    public String getID(String name) {
        String id = null;
        switch (name) {
            case "杨军":
                id = "86573790";
                break;
            case "包建军":
                id = "22530592";
                break;
            case "冯汝臣":
                id = "63221757";
                break;
            case "焦守铭":
                id = "29910285";
                break;
            case "陆滨":
                id = "56657099";
                break;
            case "肖鹏":
                id = "02021830";
                break;
            case "刘春龙":
                id = "97358011";
                break;
            case "王云鹏":
                id = "68033051";
                break;
            case "范传东":
                id = "32177397";
                break;
            case "王宪明":
                id = "58880128";
                break;
        }
        return id;
    }

    /**
     * 管理员下载生成勾稽关系表模板
     *
     * @param response
     * @throws IOException
     */
    @GetMapping("/down2")
    public void downExcel(HttpServletResponse response) throws IOException {
        System.out.println("管理员下载生成勾稽关系表模板");
        logger.info("==============管理员下载生成勾稽关系表模板====================");
        XSSFWorkbook hs = new XSSFWorkbook("/home/ap/ccb/filepath/file2.xlsx");
//        XSSFWorkbook hs = new XSSFWorkbook("C:\\Users\\Administrator\\Desktop\\path\\勾稽关系模板生成模板\\勾稽关系生成导入模板.xlsx");

        try (OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename" + URLEncoder.encode("resoult.xlsx", "UTF-8"));
            response.setCharacterEncoding("utf-8");
            hs.write(outputStream);
            outputStream.flush();
            outputStream.close();
            hs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("====================下载模板结束==================");
    }

    /**
     * 根据上传的生成勾稽关系模板，生成勾稽关系表
     * @param file
     * @param response
     * @throws IOException
     * @throws InvalidFormatException
     */
    @PostMapping("/template")
    public ApiResult templateExcle(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException, InvalidFormatException {
//        File file1 = new File("H://测评导入.xlsx");
        InputStream inputStream = file.getInputStream();
//        File file2 = new File("/home/ap/ccb/filepath/template.xlsx");
        logger.info("=============读取temple文件，生成勾稽关系表==========");
//        File file2 = new File("C:\\Users\\Administrator\\Desktop\\path\\勾稽关系模板\\导入模板.xlsx");
        File file2 = new File("/home/ap/ccb/filepath/file.xlsx");


        FileOutputStream fileOutputStream = null;
        FileInputStream fis = null;
        fis = new FileInputStream(file2);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheetOut = wb.getSheetAt(0);
        XSSFCellStyle cStyle = wb.createCellStyle();
        cStyle.setWrapText(true);


        cStyle.setDataFormat(wb.createDataFormat().getFormat("\"text\""));

        int index = 1;
        XSSFRow rowOut = sheetOut.createRow(index);

        Workbook workbook = new XSSFWorkbook(inputStream);


//        Workbook workbook = WorkbookFactory.create(file1);
        Sheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getLastRowNum();
        for (int i = 1; i <= rows; i++) {

            String name = (sheet.getRow(i).getCell(0) == null ? null : sheet.getRow(i).getCell(0).toString());
            Cell cell0 = sheet.getRow(i).getCell(1);
            cell0.setCellType(CellType.STRING);
//            cell0.setCellStyle(cStyle);
            cell0.getCellStyle().cloneStyleFrom(cStyle);

            String id = (cell0 == null ? null : cell0.toString());
            logger.info("id = "+id + "id的长度" + id.length());
            if(id.length() != 8){
                return ApiResultHandler.buildApiResult(10000, "该人员"+ name +"的员工编号有误"+",错误编号为："+ id, null);
            }
            String part = (sheet.getRow(i).getCell(3) == null ? null : sheet.getRow(i).getCell(3).toString());
            String level = (sheet.getRow(i).getCell(7) == null ? null : sheet.getRow(i).getCell(7).toString());
            String uass = (sheet.getRow(i).getCell(2) == null ? null : sheet.getRow(i).getCell(2).toString());
            Cell fcell = null;
            if (level.equals("A") && level != null) {
                //1代表部门老总，
                //增加行领导评价
                String tx = (sheet.getRow(i).getCell(4) == null ? null : sheet.getRow(i).getCell(4).toString());
                String leader1 = (sheet.getRow(i).getCell(5) == null ? null : sheet.getRow(i).getCell(5).toString());
                String leader2 = (sheet.getRow(i).getCell(6) == null ? null : sheet.getRow(i).getCell(6).toString());
                creatCellX(rowOut, cStyle, getID("杨军"), "杨军", getUass("杨军"), "行领导", id, name, uass, part,"2");
                index++;
                rowOut = sheetOut.createRow(index);
                if (leader1 != null) {
                    creatCellX(rowOut, cStyle, getID(leader1), leader1, getUass(leader1), "行领导", id, name, uass, part,"2");
                    index++;
                    rowOut = sheetOut.createRow(index);
               }
                if (leader2 != null) {
                    creatCellX(rowOut, cStyle, getID(leader2), leader2, getUass(leader2), "行领导",id, name, uass, part, "2");
                    index++;
                    rowOut = sheetOut.createRow(index);
                }
                for (int k = 1; k <= rows; k++) {
                    //给其他部门老总打分
                    if (k != i) {
                        String nameX = sheet.getRow(k).getCell(0).toString();

                        Cell cell1 = sheet.getRow(k).getCell(1);
                        cell1.setCellType(CellType.STRING);
//                        cell1.setCellStyle(cStyle);
                        cell1.getCellStyle().cloneStyleFrom(cStyle);
                        String idX = cell1.toString();

                        String partX = sheet.getRow(k).getCell(3).toString();
                        String levelX = sheet.getRow(k).getCell(7).toString();
                        String uassX = sheet.getRow(k).getCell(2).toString();
                        String tx2 = (sheet.getRow(k).getCell(4) == null ? null : sheet.getRow(k).getCell(4).toString());
                        if (level.equals(levelX) && tx.equals(tx2)) {
                            creatCellX(rowOut, cStyle, id, name, uass, part, idX, nameX, uassX, partX, "1");
                            index++;
                            rowOut = sheetOut.createRow(index);
                        }
                    }
                }
                for (int k = 1; k <= rows; k++) {
                    //给本部门副总打分
                    if (k != i) {
                        String nameX = sheet.getRow(k).getCell(0).toString();
                        Cell cell1 = sheet.getRow(k).getCell(1);
                        cell1.setCellType(CellType.STRING);
//                        cell1.setCellStyle(cStyle);
                        cell1.getCellStyle().cloneStyleFrom(cStyle);
                        String idX = cell1.toString();
                        String partX = sheet.getRow(k).getCell(3).toString();
                        String levelX = sheet.getRow(k).getCell(7).toString();
                        String uassX = sheet.getRow(k).getCell(2).toString();
                        if (levelX.equals("B") && partX.equals(part)) {
                            creatCellX(rowOut, cStyle, id, name, uass, part, idX, nameX, uassX, partX, "2");
                            index++;
                            rowOut = sheetOut.createRow(index);
                        }
                    }
                }
            }
            if (level.equals("B")) {
                //2代表部门副总
                for (int k = 1; k <= rows; k++) {
                    //给本部门老总打分
                    if (k != i) {
                        String nameX = sheet.getRow(k).getCell(0).toString();
                        Cell cell1 = sheet.getRow(k).getCell(1);
                        cell1.setCellType(CellType.STRING);
//                        cell1.setCellStyle(cStyle);
                        cell1.getCellStyle().cloneStyleFrom(cStyle);
                        String idX = cell1.toString();
                        String partX = sheet.getRow(k).getCell(3).toString();
                        String levelX = sheet.getRow(k).getCell(7).toString();
                        String uassX = sheet.getRow(k).getCell(2).toString();
                        if (levelX.equals("A") && partX.equals(part)) {
                            creatCellX(rowOut, cStyle, id, name, uass, part, idX, nameX, uassX, partX, "0");
                            index++;
                            rowOut = sheetOut.createRow(index);
                        }
                    }
                }
                for (int k = 1; k <= rows; k++) {
                    //给本部门其他副总打分
                    if (k != i) {
                        String nameX = sheet.getRow(k).getCell(0).toString();
                        Cell cell1 = sheet.getRow(k).getCell(1);
                        cell1.setCellType(CellType.STRING);
//                        cell1.setCellStyle(cStyle);
                        cell1.getCellStyle().cloneStyleFrom(cStyle);
                        String idX = cell1.toString();
                        String partX = sheet.getRow(k).getCell(3).toString();
                        String levelX = sheet.getRow(k).getCell(7).toString();
                        String uassX = sheet.getRow(k).getCell(2).toString();
                        if (levelX.equals("B") && partX.equals(part)) {
                            creatCellX(rowOut, cStyle, id, name, uass, part, idX, nameX, uassX, partX, "1");
                            index++;
                            rowOut = sheetOut.createRow(index);
                        }
                    }
                }
            }
            if (level.equals("C")) {
                //3代表部门科长
                for (int k = 1; k <= rows; k++) {
                    //给本部门其他副总打分
                    if (k != i) {
                        String nameX = sheet.getRow(k).getCell(0).toString();
                        Cell cell1 = sheet.getRow(k).getCell(1);
                        cell1.setCellType(CellType.STRING);
//                        cell1.setCellStyle(cStyle);
                        cell1.getCellStyle().cloneStyleFrom(cStyle);
                        String idX = cell1.toString();
                        String partX = sheet.getRow(k).getCell(3).toString();
                        String levelX = sheet.getRow(k).getCell(7).toString();
                        String uassX = sheet.getRow(k).getCell(2).toString();
                        if (levelX.equals("B") && partX.equals(part)) {
                            creatCellX(rowOut, cStyle, id, name, uass, part, idX, nameX, uassX, partX, "0");
                            index++;
                            rowOut = sheetOut.createRow(index);
                        }
                    }
                }


            }
        }
//        fileOutputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\path\\勾稽关系生成\\勾稽关系表.xlsx");
        fileOutputStream = new FileOutputStream("/home/ap/ccb/filepath/勾稽关系表.xlsx");

        wb.write(fileOutputStream);
        fileOutputStream.close();
        fis.close();
        return ApiResultHandler.buildApiResult(200, "成功！", null);
    }

    /**
     * 管理员下载生成的勾稽关系表
     * @param response
     * @throws IOException
     */
    @GetMapping("/down3")
    public void downExcel2(HttpServletResponse response) throws IOException {
        System.out.println("管理员下载生成的勾稽关系表");
        logger.info("==============管理员下载生成勾稽关系表模板====================");
//        XSSFWorkbook hs = new XSSFWorkbook("/home/ap/ccb/filepath/file2.xlsx");
//        XSSFWorkbook hs = new XSSFWorkbook("C:\\Users\\Administrator\\Desktop\\path\\勾稽关系生成\\勾稽关系表.xlsx");
        XSSFWorkbook hs = new XSSFWorkbook("/home/ap/ccb/filepath/勾稽关系表.xlsx");

        try (OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename" + URLEncoder.encode("resoult.xlsx", "UTF-8"));
            response.setCharacterEncoding("utf-8");
            hs.write(outputStream);
            outputStream.flush();
            outputStream.close();
            hs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("====================下载结束==================");
    }


    /**
     * 管理员重置leaderinfo表和score_manage表
     *
     * @return
     */
    @GetMapping("/resetting")
    public ApiResult Resetting() {
        logger.info("===============管理员重置得分信息=================");
        //重置leaderinfo表得分数据
        logger.info("--------重置leaderinfo表得分数据-------");
        int i = adminService.resetLeaderInfo();
        //重置score_manage表得分数据
        logger.info("--------重置score_manage表得分数据------");
        int j = adminService.resetScoreManage();
        logger.info("=============重置结束================");
        return ApiResultHandler.buildApiResult(200, "重置成功!", "leaderinfo表重置了：" + i + "行数据，scor_manage表重置了：" + j + "行数据");
    }

}
