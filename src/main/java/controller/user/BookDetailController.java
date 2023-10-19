package controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.ReviewDao;
import model.Book;
import model.Review;

@WebServlet(urlPatterns = "/user/detail")
public class BookDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookDao bookDao = new BookDao();
		ReviewDao reviewDao = new ReviewDao();
		int bid = Integer.parseInt(req.getParameter("bid"));
		Book book = bookDao.getBookById(bid);
		req.setAttribute("book", book);
		List<Review> reviews = reviewDao.getReviewsByBookIdEach3(bid, 0);
		req.setAttribute("reviews", reviews);
		
		float meanstar = reviewDao.getMeanStarByBookid(bid);
		req.setAttribute("meanstar", meanstar);
		
		int reviewCount = reviewDao.getReviewCountByBookid(bid);
		req.setAttribute("reviewCount", reviewCount);
		
		req.setAttribute("nav", "detail");
		req.getRequestDispatcher("/view/user/detail.jsp").forward(req, resp);
	}
	
}
