package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDao;
import model.Cart;
import model.CartItem;
@WebServlet(urlPatterns = "/user/updateCart")

public class UpdateCartController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CartDao cartDao = new CartDao();
		
		int bid = Integer.parseInt(req.getParameter("bid"));
		int qty = Integer.parseInt(req.getParameter("qty"));
		
		HttpSession session = req.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		CartItem ci = cart.getCartItemById(bid);
		ci.setQuantity(qty);
		cartDao.updateCartItem(ci);
	}
}
