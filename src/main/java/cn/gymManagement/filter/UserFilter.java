package cn.gymManagement.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用于拦截会员登录去请求
 */
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化用户拦截器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        //获取请求对象
        HttpServletRequest clientRequest = (HttpServletRequest) servletRequest;
        //获取响应对象
        HttpServletResponse clientResponse = (HttpServletResponse) servletResponse;
        //得到包含工程名的当前页面全路径
        String currentURL = clientRequest.getRequestURI();
        //得到工程名
        String ctxPath = clientRequest.getContextPath();
        //截取全路径除工程名外的路径
        String targetURL = currentURL.substring(ctxPath.length());
        //若存在已登录（会话）则返回该session（会话），否则返回NULL
        HttpSession session = clientRequest.getSession(false);

        //非登录页面拦截
        if (!("/clientView/userLogin.html".equals(targetURL))) {
            if (session == null || session.getAttribute("userSession") == null) {
                //跳回登录界面
                clientResponse.sendRedirect("/clientView/userLogin.html");
                System.out.println("---拦截非登录界面---跳回用户登录界面");
                return;
            } else {
                chain.doFilter(clientRequest, clientResponse);
                System.out.println("---存在该用户，通过拦截---");
                return;
            }
        } else {
            chain.doFilter(clientRequest, clientResponse);
            System.out.println("---通过拦截，属于登录界面或者主页（非登录主页）---");
        }
    }

    @Override
    public void destroy() {
        System.out.println("销毁拦截器");
    }
}
