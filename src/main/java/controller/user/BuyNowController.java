package controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import dao.BookDao;
import model.Account;
import model.CartItem;

@WebServlet(urlPatterns = "/user/buynow")
public class BuyNowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookDao bookDao = new BookDao();
		HttpSession session = req.getSession();
		Account account =  (Account) session.getAttribute("account");
		if(account == null) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.append("redirect", req.getContextPath() + "/user/login");
			resp.setContentType("application/json");
			PrintWriter pr = resp.getWriter();
			pr.print(jsonObject);
			pr.flush();
		}else {
			String bidS = req.getParameter("bid");
			String qtyS = req.getParameter("qty");
			
			CartItem cartItem = new CartItem();
			cartItem.setQuantity(Integer.parseInt(qtyS));
			cartItem.setBook(bookDao.getBookById(Integer.parseInt(bidS)));
			
			session.setAttribute("buynow", cartItem);
			
			JSONObject jsonObject=new JSONObject();
			jsonObject.append("redirectCheckout", req.getContextPath() + "/user/checkout");
			resp.setContentType("application/json");
			PrintWriter pr = resp.getWriter();
			pr.print(jsonObject);
			pr.flush();
			
		}
		
		
		
	}
}
