package pers.xp.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pers.xp.bean.UmsMember;
import pers.xp.util.FlagUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

@WebFilter(urlPatterns = {"/*","/"})
public class UserFilter  implements Filter {

    public static Map<String,String> paths;

    static {
        paths = new HashMap<String, String>();
        paths.put("^/api/channel/[\\d+]$", "DELETE");
        paths.put("^/api/channel$", "POST");
        paths.put("^/api/channel$", "PUT");
        paths.put("^/api/detail$", "POST");
        paths.put("^/api/detail/[\\d+]$", "DELETE");
//        paths.put("^/api/user/login?auth_type=admin$","POST");
    }
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println(request.getServletPath()+request.getContextPath()+"?"+request.getQueryString());
        System.out.println(request.getMethod());
        if (!FlagUtil.flag) {
            FlagUtil.flag = true;
            FlagUtil.session = request.getSession(true);
        }

        String path = request.getServletPath().toLowerCase();
        String method = request.getMethod().toUpperCase();
        boolean isAdmin = FlagUtil.token==null?false:true;
        if (!isAdmin) {
            for (Map.Entry<String, String> entry : paths.entrySet()) {
                boolean isMatch = Pattern.matches(entry.getKey(), path);
                if (entry.getValue().equals(method) && isMatch) {
                    return;
                }
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {

    }
}
