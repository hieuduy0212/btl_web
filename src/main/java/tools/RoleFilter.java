package tools;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
@WebFilter(value= {"/admin/*", "/user/*"})
public class RoleFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		Account account = (Account)session.getAttribute("account");
		String uri = req.getServletPath();
		if(account != null) {
			if((account.getIsAdmin() == 1 && uri.contains("user"))
					|| (account.getIsAdmin() == 0 && uri.contains("admin"))) {
				String pos = (account.getIsAdmin() == 1) ? "admin" : ((account.getIsAdmin() == 0) ? "user" : ""); 
				resp.sendRedirect(req.getContextPath() + "/page403?pos=" + pos);
			}else {
				chain.doFilter(request, response);
			}
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {		
	}

}
