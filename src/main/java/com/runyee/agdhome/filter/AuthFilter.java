package com.runyee.agdhome.filter;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jensen on 2017/4/11.
 */
@Component
public class AuthFilter implements Filter {


    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        req.setAttribute("encrypt","encrypt");

        HttpServletResponse response = (HttpServletResponse) res;
        //ystem.out.println("filter:: servletpath::"+request.getServletPath() +"  uri::"+request.getRequestURI()+" context::"+ request.getContextPath());
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}

