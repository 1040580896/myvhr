package com.th.vhr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.th.vhr.bean.RespBean;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 验证码过滤器
 */
public class VerificationCodeFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if("POST".equals(req.getMethod())&&"/doLogin".equals(req.getServletPath())){
            // 登陆请求
            String code = req.getParameter("code");
            String verify_code = ((String) req.getSession().getAttribute("verify_code"));
            chain.doFilter(req,resp);
            if(verify_code==null||verify_code==null||!verify_code.equalsIgnoreCase(code)){
                // 验证码不正确
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write(new ObjectMapper().writeValueAsString(RespBean.error("验证码错误")));
                out.flush();;
                out.close();
            }else {
                chain.doFilter(req,resp);
            }

        }else {
            chain.doFilter(req,resp);
        }



    }
}
