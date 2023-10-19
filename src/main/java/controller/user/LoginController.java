package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;
import model.Account;
import tools.MD5;
@WebServlet(urlPatterns = "/user/login")
public class LoginController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/user/login.jsp").forward(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AccountDao accountDao = new AccountDao();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Account account = new Account();
		if(username.contains("@")) {
			account.setEmail(username);
		}else {
			account.setUsername(username);
		}
		
		account.setPassword(MD5.getMD5(password));
		
		boolean successLogin = accountDao.checkLogin(account);
		if(successLogin && account.getIsAdmin() == 0) { 
			HttpSession session = req.getSession();
			session.setAttribute("account", account);
			resp.sendRedirect(req.getContextPath() + "/user/home");
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("msg", "loginError");
			resp.sendRedirect(req.getContextPath() + "/user/login");
		}
	}

}
