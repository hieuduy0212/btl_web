package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import model.BookStat;

@WebServlet(urlPatterns = {"/admin", "/admin/home", "/admin/"})
public class HomePageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookDao bookDao = new BookDao();
		List<BookStat> bookList = bookDao.getBookStat();
		req.setAttribute("books", bookList);
		req.setAttribute("sidebar", "home");
		req.getRequestDispatcher("/view/admin/home.jsp").forward(req, resp);
	}
}
