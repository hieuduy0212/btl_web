package controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import dao.ReviewDao;
import model.Review;
@WebServlet(urlPatterns = "/loadmorereview")
public class LoadMoreReview extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ReviewDao reviewDao = new ReviewDao();
		String bookidS = req.getParameter("bookid");
		String existsS = req.getParameter("exists");
		List<Review> more = reviewDao.getReviewsByBookIdEach3(Integer.parseInt(bookidS), Integer.parseInt(existsS));
		JSONArray jsonArray = new JSONArray(more);
		resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pr = resp.getWriter();
        pr.println(jsonArray);
        pr.flush();
	}
}
