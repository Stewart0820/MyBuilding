package com.stewart.building.util;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.metadata.Sheet;
import com.stewart.building.Listener.ExcelListener;
import com.stewart.building.common.R;
import com.stewart.building.mbg.mapper.UserMapper;
import com.stewart.building.mbg.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈鸿杰
 * @create 2022/1/8
 * @Description
 */
public class ExcelUtils {

    /**
     * @param is    导入文件输入流
     * @param clazz Excel实体映射类
     * @param userService
     * @return
     */
    public static Boolean readExcel(InputStream is, Class clazz, Integer clazzId, IUserService userService) {

        BufferedInputStream bis = null;
        Boolean flag;
        try {
            bis = new BufferedInputStream(is);
            // 解析每行结果在listener中处理
            ExcelListener listener = new ExcelListener(clazzId,userService);

            ExcelReader excelReader = EasyExcelFactory.getReader(bis, listener);
            excelReader.read(new Sheet(1, 1, clazz));
            //这里有bug
            flag = listener.getFlag();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * @param os    文件输出流
     * @param clazz Excel实体映射类
     * @param data  导出数据
     * @return
     */
    public static Boolean writeExcel(OutputStream os, Class clazz, List<? extends BaseRowModel> data) {
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(os);
            ExcelWriter writer = new ExcelWriter(bos, ExcelTypeEnum.XLSX);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet1 = new Sheet(1, 0, clazz);
            writer.write(data, sheet1);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

//        public static void main(String[] args) {
//            //1.读Excel
//            FileInputStream fis = null;
//            try {
//                fis = new FileInputStream("D:\\2007.xlsx");
//                Boolean flag = ExcelUtils.readExcel(fis, ExcelEntity.class);
//                System.out.println("导入是否成功："+flag);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }finally {
//                if (fis != null){
//                    try {
//                        fis.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//
//            //2.读Excel
//            FileOutputStream fos = null;
//            try {
//                fos = new FileOutputStream("D:\\export.xlsx");
//                //FileOutputStream fos, Class clazz, List<? extends BaseRowModel> data
//                List<ExcelEntity> list = new ArrayList<>();
//                for (int i = 0; i < 5; i++){
//                    ExcelEntity excelEntity = new ExcelEntity();
//                    excelEntity.setName("我是名字"+i);
//                    list.add(excelEntity);
//                }
//                Boolean flag = ExcelUtils.writeExcel(fos,ExcelEntity.class,list);
//                System.out.println("导出是否成功："+flag);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }finally {
//                if (fos != null){
//                    try {
//                        fos.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//        }
}
