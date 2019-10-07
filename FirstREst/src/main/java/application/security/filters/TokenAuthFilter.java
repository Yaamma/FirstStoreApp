package application.security.filters;

import application.security.token.TokenAuntetication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class TokenAuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServlet = (HttpServletRequest) request;
        String token = httpServlet.getParameter("token");
        TokenAuntetication tokenAuntetication = new TokenAuntetication(token);
        if(token != null){
            SecurityContextHolder.getContext().setAuthentication(tokenAuntetication);
            // фильтры переходят в спринг и он теперь знает что есть такая аунтефикация
        } else  {
            tokenAuntetication.setAuthenticated(false);
        }
        filterChain.doFilter(request,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
