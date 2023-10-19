package controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDao;
import dao.CartDao;
import model.Account;
import model.Book;
import model.Cart;

@WebServlet(urlPatterns = { "/user", "/user/home", "/user/" })
public class HomePageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookDao bookDao = new BookDao();
		CartDao cartDao = new CartDao();
		List<Book> books = bookDao.getAllBook();
		req.setAttribute("books", books);
		HttpSession session = req.getSession();
		Account account = (Account) session.getAttribute("account");
		if (account != null) {
			Cart cart = cartDao.getCartById(account.getId());
			session.setAttribute("cart", cart);
		}
		req.setAttribute("nav", "home");
		req.getRequestDispatcher("/view/user/home.jsp").forward(req, resp);
	}
}
