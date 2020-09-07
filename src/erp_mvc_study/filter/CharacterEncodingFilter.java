package erp_mvc_study.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(filterName = "CharacterEncodingFilter", urlPatterns = { "/*" }, initParams = {
        @WebInitParam(name = "encoding", value = "utf-8") })
public class CharacterEncodingFilter implements Filter {

    private String encoding;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("doFilter()");
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);

        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        encoding = fConfig.getInitParameter("encoding");
        if (encoding == null) {
            encoding = "UTF-8";
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        
    }

}
