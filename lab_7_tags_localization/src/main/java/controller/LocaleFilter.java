package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

@WebFilter(filterName = "LanguageFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {

    private String attrLocale = "userLocale";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        if (req.getSession().getAttribute(attrLocale) == null) {
            req.getSession().setAttribute(attrLocale, req.getLocale());
        }
        String lang = req.getParameter("lang");
        if (lang != null) {
            Locale locale = new Locale(lang);
            req.getSession().setAttribute(attrLocale, locale);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
