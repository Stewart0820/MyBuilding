package com.stewart.building.config.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stewart.building.common.R;
import com.stewart.building.common.ResultStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 当访问接口没有权限时，自定义的返回结果
 * Created by macro on 2018/4/26.
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * 当用户没有访问权限时的处理器，用于返回JSON格式的处理结果；
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        R bean = R.error(ResultStatus.NOT_PERMISSION);
        out.write(new ObjectMapper().writeValueAsString(bean));
        out.close();
        out.flush();
    }
}
