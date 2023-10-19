package controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import dao.ReviewDao;
import model.Account;
import model.Review;

@WebServlet(urlPatterns = "/user/review")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ReviewDao reviewDao = new ReviewDao();
		
		String content = req.getParameter("content");
		int bookid = Integer.parseInt(req.getParameter("bookid"));
		float star = Float.parseFloat(req.getParameter("star"));
		
		HttpSession session = req.getSession();
		Account account = (Account)session.getAttribute("account");
		
		Review review = new Review();
		review.setContent(content);
		review.setStar(star);
		review.setAccount(account);
		review.setBookid(bookid);
		Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		review.setCreated(sqlDate);
		
		reviewDao.addReview(review);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("msg", "successAddReview");
		resp.setContentType("application/json");
		PrintWriter pr = resp.getWriter();
		pr.print(jsonObject);
		pr.flush();		
	}
}
