package com.stewart.building.config.component;

import com.stewart.building.common.R;
import com.stewart.building.common.ResultStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 1：验证前端传入的参数
 * 2：参数的转换
 * 3:数据库sql语句的错误
 * 4：运行时的业务逻辑的错误
 *
 * @author Stewart
 * @create 2021/12/19
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     *  校验错误拦截处理
     *
     * @param exception 错误信息集合
     * @return 错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R validationBodyException(MethodArgumentNotValidException exception){
        AtomicReference<String> message = new AtomicReference<>("");
        BindingResult result = exception.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();

            errors.forEach(p ->{

                FieldError fieldError = (FieldError) p;
                logger.error("Data check failure : object{"+fieldError.getObjectName()+"},field{"+fieldError.getField()+
                        "},errorMessage{"+fieldError.getDefaultMessage()+"}");
                message.set(fieldError.getDefaultMessage());

            });

        }
        return R.error(ResultStatus.ERROR);
    }

    /**
     * 参数类型转换错误
     *
     * @param exception 错误
     * @return 错误信息
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    public R parameterTypeException(HttpMessageConversionException exception){

        logger.error(exception.getCause().getLocalizedMessage());
        return R.error(ResultStatus.TYPE_ERROR);

    }

    /**
     * 数据库出现错误
     * @param e
     * @return
     */
    @ExceptionHandler(SQLException.class)
    public R mySqlException(SQLException e){
        if (e instanceof SQLIntegrityConstraintViolationException){
            return R.error(ResultStatus.ASSOCIATED_DATA_ERROR);
        }
        return R.error(ResultStatus.MYSQL_ERROR);
    }

    /**
     * 项目业务层出现错误
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public R runTimeException(RuntimeException e){
        logger.error("出错的原因==========>"+e.getMessage());
        return R.error(ResultStatus.BUSINESS_ERROR);
    }
}

