package com.rabbiter.oes.controller;

import com.rabbiter.oes.entity.Admin;
import com.rabbiter.oes.entity.ApiResult;
import com.rabbiter.oes.entity.BpjPerson;
import com.rabbiter.oes.serviceimpl.AdminServiceImpl;
import com.rabbiter.oes.util.ApiResultHandler;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


/**
 * 管理端功能
 * 重置用户密码为初始密码
 *
 */

@RestController
public class AdminController {

    private AdminServiceImpl adminService;
    @Autowired
    public AdminController(AdminServiceImpl adminService){
        this.adminService = adminService;
    }
    //管理员输入8位员工编号重置用户密码
    @PutMapping("/admin/{userId}")
    public ApiResult updatePWD1(@PathVariable("userId") String userId){
        return ApiResultHandler.success(adminService.updatePWD1(userId));
    }

    //管理员输入uass编号重置用户密码
    @PutMapping("/admin/{userUass}")
    public ApiResult updatePWD2(@PathVariable("userUass") String userUass){
        return ApiResultHandler.success(adminService.updatePWD2(userUass));
    }

    //管理员查询所有得分信息
    @GetMapping("/admin1")
    public ApiResult selectAll(){
        System.out.println("查询所有被评价人员得分情况");
        return ApiResultHandler.success(adminService.selectAll());
    }

//    //管理员导出查询结果
//    @GetMapping("/admin2")
//    public ApiResult download(){
//        System.out.println("管理员导出查询结果");
//        List<BpjPerson> bpjPersonList = adminService.selectAll();
//        BpjPerson bp = new BpjPerson();
//        String filePath = "C:\\Users\\Administrator\\Desktop\\path";
//        String fileName = System.currentTimeMillis() + ".xls";
//        bp.setFileName(fileName);
//        bp.setFilePath(filePath);
//        Excel(filePath + "/" + fileName, bpjPersonList);
//        return ApiResultHandler.success(bp);
//    }

//    public void Excel(String file,List<BpjPerson> bpjPersonList){
//        FileOutputStream outputStream = null;
//        HSSFWorkbook hs = new HSSFWorkbook;
//        try{
//            System.out.println("生成excle");
//            outputStream = new FileOutputStream(new File(file));
//            Sheet sheet = hs.createSheet("shee1");
//            Row headerRow = sheet.createRow(0);
//            Cell cell = headerRow.createCell(0);
//            //设置字段名
//            headerRow.createCell(0).setCellValue("姓名");
//            headerRow.createCell(1).setCellValue("8位员工编号");
//            headerRow.createCell(2).setCellValue("uass编号");
//            headerRow.createCell(3).setCellValue("自评得分");
//            headerRow.createCell(4).setCellValue("上级评价得分");
//            headerRow.createCell(5).setCellValue("上级评价人数");
//            headerRow.createCell(6).setCellValue("同级评价得分");
//            headerRow.createCell(7).setCellValue("同级评价人数");
//            headerRow.createCell(8).setCellValue("下级评价得分");
//            headerRow.createCell(9).setCellValue("下级评价人数");
//            headerRow.createCell(10).setCellValue("总得分");
//            headerRow.createCell(11).setCellValue("总评价人数");
//            int count = bpjPersonList.size();
//            for(int i = 0; i <= count; i++){
//
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    @GetMapping("/admins")
    public ApiResult findAll(){
        System.out.println("查询全部");
        return ApiResultHandler.success(adminService.findAll());
    }

    @GetMapping("/admin/{adminId}")
    public ApiResult findById(@PathVariable("adminId") Integer adminId){
        System.out.println("根据ID查找");
        return ApiResultHandler.success(adminService.findById(adminId));
    }

    @DeleteMapping("/admin/{adminId}")
    public ApiResult deleteById(@PathVariable("adminId") Integer adminId){
        adminService.deleteById(adminId);
        return ApiResultHandler.success();
    }

    @PutMapping("/admin/{adminId}")
    public ApiResult update(@PathVariable("adminId") Integer adminId, Admin admin){
        return ApiResultHandler.success(adminService.update(admin));
    }

    @PostMapping("/admin")
    public ApiResult add(Admin admin){
        return ApiResultHandler.success(adminService.add(admin));
    }

    @GetMapping("/admin/resetPsw/{adminId}/{oldPsw}/{newPsw}")
    public ApiResult resetPsw(@PathVariable("adminId") Integer adminId, @PathVariable("newPsw") String newPsw, @PathVariable("oldPsw") String oldPsw) {
        return ApiResultHandler.success(adminService.resetPsw(adminId, newPsw, oldPsw));
    }
}
