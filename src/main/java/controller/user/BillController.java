package controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import dao.BillDao;
import model.Account;
import model.Bill;

@WebServlet(urlPatterns = "/user/bills")
public class BillController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BillDao billDao = new BillDao();
		HttpSession session = req.getSession();
		Account account = (Account) session.getAttribute("account");
		if (account != null && account.getIsAdmin() == 0) {
			List<Bill> bills = billDao.getBillsByAccountId(account.getId());
			session.setAttribute("bills", bills);
		}
		req.getRequestDispatcher("/view/user/bills.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BillDao billDao = new BillDao();
		String billidS = req.getParameter("billid");
		int billid = Integer.parseInt(billidS);
		
		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		List<Bill> bills = (List<Bill>) session.getAttribute("bills");
		Bill bill = null;
		for (Bill b : bills) {
			if (b.getId() == billid) {
				bill = b;
				break;
			}
		}
		
		String action = req.getParameter("action");
		if(action.equals("post")) {
			req.setAttribute("bill", bill);
			req.getRequestDispatcher("/view/user/billdetail.jsp").forward(req, resp);
		}else if(action.equals("delete")) {
			billDao.cancelBill(billid);
			bills.remove(bill);
			session.setAttribute("bills", bills);
			JSONObject jsonObject = new JSONObject();
			jsonObject.append("redirect", req.getContextPath() + "/user/bills");
			resp.setContentType("application/json");
			PrintWriter pr = resp.getWriter();
			pr.print(jsonObject);
			pr.flush();
		}
	}
}
