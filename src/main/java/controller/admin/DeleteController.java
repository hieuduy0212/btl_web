package controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.BookDao;
import model.Book;
@WebServlet(urlPatterns = "/admin/delete")
public class DeleteController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookDao bookDao = new BookDao();
		String bidS = req.getParameter("bid");
		//delete image from disk
		String realpath = req.getServletContext().getRealPath("/");
		Book bookdel = bookDao.getBookById(Integer.parseInt(bidS));
		String imagepath = realpath + bookdel.getCover();
		File file = new File(imagepath);
		file.delete(); 
		
		bookDao.deleteBookById(Integer.parseInt(bidS));
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("msg", "delete");
		resp.setContentType("application/json");
		PrintWriter pr = resp.getWriter();
		pr.println(jsonObject);
		pr.flush();
	}
}
