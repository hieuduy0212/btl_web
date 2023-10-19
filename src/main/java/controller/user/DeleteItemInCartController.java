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

import dao.CartDao;
import model.Cart;
@WebServlet(urlPatterns = "/user/deteleItemInCart")
public class DeleteItemInCartController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CartDao cartDao = new CartDao();
		
		int bid = Integer.parseInt(req.getParameter("bid"));
		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		cartDao.removeCartItem(cart.getCartItemById(bid));
		cart.removeCartItem(bid);
		session.setAttribute("cart", cart);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("sizecart", cart.getCartItems().size());
		resp.setContentType("application/json");
		PrintWriter pr =  resp.getWriter();
		pr.print(jsonObject);
		pr.flush();		
	}
}
