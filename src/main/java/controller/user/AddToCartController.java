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
import dao.CartDao;
import model.Account;
import model.Cart;
import model.CartItem;

@WebServlet(urlPatterns = "/user/addToCart")
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CartDao cartDao = new CartDao();
		BookDao bookDao = new BookDao();

		HttpSession session = req.getSession();
		Account account = (Account) session.getAttribute("account");

		if (account == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.append("redirect", req.getContextPath() + "/user/login");
			PrintWriter pr = resp.getWriter();
			resp.setContentType("application/json");
			pr.print(jsonObject);
			pr.flush();
		} else {
			if (account.getIsAdmin() == 0) {
				String bidS = req.getParameter("bid");
				String qtyS = req.getParameter("qty");
				int qty = Integer.parseInt(qtyS);

				if (bidS != null) {
					Cart cart = (Cart) session.getAttribute("cart");
					int bid = Integer.parseInt(bidS);
					CartItem cartItem = cart.getCartItemById(bid);
					if (cartItem == null) {
						cartItem = new CartItem(cart.getId(), 
								bid, 
								qty, 
								bookDao.getBookById(bid));
						cart.addCartItem(cartItem);
						cartDao.addCartItem(cartItem);
					} else {
						cartItem.setQuantity(cartItem.getQuantity() + qty);
						cartDao.updateCartItem(cartItem);
					}
					session.setAttribute("cart", cart);
					JSONObject jsonObject = new JSONObject();
					jsonObject.append("sizecart", cart.getCartItems().size());
					PrintWriter pr = resp.getWriter();
					resp.setContentType("application/json");
					pr.print(jsonObject);
					pr.flush();
				}
			}
		}
	}
}
