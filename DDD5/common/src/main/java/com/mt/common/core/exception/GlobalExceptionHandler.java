package com.mt.common.core.exception;

import com.mt.common.core.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 * Created by wangfan on 2018-02-22 11:29
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public JsonResult errorHandler(Exception ex, HttpServletResponse response) {
        cross(response);  // 支持跨域

        //把异常写入到文件中，每个异常一个文件
        BusinessException.logInFile(ex);

        // 对不同错误进行不同处理
        if (ex instanceof BusinessException) {
            return JsonResult.error(((IException) ex).getCode(), ex.getMessage()).setData(((BusinessException) ex).getExtendedData());
        } else if (ex instanceof IException) {
            return JsonResult.error(((IException) ex).getCode(), ex.getMessage());
        } else if (ex instanceof AccessDeniedException) {
            return JsonResult.error(403L, "没有访问权限");
        }
        logger.error(ex.getMessage(), ex);
        return JsonResult.error("系统错误").put("error", ex.toString());
    }

    private void cross(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, X-Custom-Header, Authorization");
    }

}
