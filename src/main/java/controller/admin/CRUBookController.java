package controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import dao.BookDao;
import dao.CategoryDao;
import model.Book;
import model.Category;
import tools.UniqueImageFileName;
import tools.Validation;

@WebServlet(urlPatterns = { "/admin/view" })
@MultipartConfig
public class CRUBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CategoryDao categoryDao = new CategoryDao();
		BookDao bookDao = new BookDao();
		
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		String bidS = req.getParameter("bid");
		List<Category> categories = categoryDao.getAllCategory();
		req.setAttribute("categories", categories);
		req.setAttribute("bid", bidS);
		if (bidS.equals("0")) { 
			req.setAttribute("status", "add");
			req.getRequestDispatcher("/view/admin/view.jsp").forward(req, resp);
		} else {
			req.setAttribute("status", "view");
			if (bidS != null) {
				Book book = bookDao.getBookById(Integer.parseInt(bidS));
				req.setAttribute("book", book);
				req.getRequestDispatcher("/view/admin/view.jsp").forward(req, resp);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CategoryDao categoryDao = new CategoryDao();
		BookDao bookDao = new BookDao();
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		String bidS = req.getParameter("bid");
		// get params and validate
		String title = req.getParameter("title");
		if(Validation.isBlank(title) || title.length() > 50) {
			sendMsgToAjax(resp, "invalidTitle"); return ;
		}
		
		String author = req.getParameter("author");
		if(Validation.isBlank(author) || author.length() > 50) {
			sendMsgToAjax(resp, "invalidAuthor"); return ;
		}
		
		String description = req.getParameter("description");
		
		String releasedate = req.getParameter("releasedate");
		if(!Validation.isDate(releasedate) || !Validation.isReleaseDate(releasedate)) {
			sendMsgToAjax(resp, "invalidReleaseDate"); return ;
		}
		
		String pages = req.getParameter("pages");
		
		// set book
		Book book = null;
		if(bidS.equals("0")){ 
			book = new Book();	
		}else { 
			book = bookDao.getBookById(Integer.parseInt(bidS));
		}
		book.setId(Integer.parseInt(bidS));
		int categoryid = Integer.parseInt(req.getParameter("category"));
		List<Category> categories = categoryDao.getAllCategory();
		Category category = categories.get(categoryid - 1);
		book.setCategory(category);
		
		book.setTitle(title);
		book.setAuthor(author);
		book.setDescription(description);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date parsedDate = dateFormat.parse(releasedate);
			Date sqlDate = new java.sql.Date(parsedDate.getTime());
			book.setReleasedate(sqlDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (pages.equals("")) {
			book.setPages(0);
		} else {
			book.setPages(Integer.parseInt(pages));
		}
		
		Part part = req.getPart("cover");
		if (part.getSubmittedFileName().length() > 0) {
			String covername = "cover" + File.separator + UniqueImageFileName.getUniqueImageFileName(part.getSubmittedFileName());
			File filepath = new File(req.getServletContext().getRealPath("/cover"));
			if (!filepath.exists()) {
				filepath.mkdir();
			}
			String realpath = req.getServletContext().getRealPath("/");
			String coverpath = realpath + covername;
			part.write(coverpath);
			book.setCover(covername);
		}
		
		if (bookDao.getBookByTitleAndAuthor(book)) { // duplicate title and author
			sendMsgToAjax(resp, "addbookerror"); return ;
		}else {
			if (bidS.equals("0")) {  
				bookDao.addNewBook(book);
				sendMsgToAjax(resp, "Add"); return ;
			}else { 
				bookDao.editBook(book);
				sendMsgToAjax(resp, "Update"); return ;
			}
		}
	}
	
	private static void sendMsgToAjax(HttpServletResponse resp, String msg) throws IOException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("msg", msg );
		resp.setContentType("application/json");
		PrintWriter pr = resp.getWriter();
		pr.print(jsonObject);
		pr.flush();
	}
}

