package com.stewart.building.Listener;

/**
 * @author 陈鸿杰
 * @create 2022/1/8
 * @Description
 */

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.stewart.building.mbg.service.IUserService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析监听器，
 * 每解析一行会回调invoke()方法。
 * 整个excel解析结束会执行doAfterAllAnalysed()方法
 * 有个很重要的点 DemoDataListener 不能被spring管理，
 * 要每次读取excel都要new,然后里面用到spring可以构造方法传进去
 *
 * @author Steart
 */
public class ExcelListener extends AnalysisEventListener {


    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelListener.class);

    /**
     * 添加的最大数量
     * private static final int BATCH_COUNT = 5;
     */
    @Resource
    private IUserService userService;
    /**
     * 自定义用于暂时存储data。
     * 可以通过实例获取该值
     */

    private int clazzId;
    private List<Object> datas = new ArrayList<>();
    private Boolean flag;
    public ExcelListener( ) {
    }

    public ExcelListener(Integer clazzId, IUserService userService) {
        this.clazzId = clazzId;
        this.userService = userService;
    }


    /**
     * 这个每一条数据解析都会来调用
     * @param object
     * @param context
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        datas.add(object);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
//        if (datas.size() >= BATCH_COUNT) {
//            saveData(datas);
//            // 存储完成清理 list
//            datas.clear();
//            LOGGER.info("这里？？？？？？？");
//        }
//        saveData(datas);
//        datas.clear();
        LOGGER.info("一条数据结束");
    }


    /**
     * 所有数据解析完成了 都会来调用
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData(datas);
        datas.clear();//解析结束销毁不用的资源
    }

    /**
     * 加上存储数据库
     * @param datas
     */
    private void saveData(List<Object> datas) {
        LOGGER.info(datas+"");
        //一次性插入多条记录,需要使用mybatis的批量添加
        //mybatis-plus的批量添加底层是for循环
        setFlag(userService.batchInsert(clazzId, datas));
    }


    /**
     * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
     * @param exception
     * @param context
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) {
        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合invokeHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
            LOGGER.error("第{}行，第{}列解析异常", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex());
        }
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
