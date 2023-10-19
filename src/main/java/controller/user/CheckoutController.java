package controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import dao.BillDao;
import dao.CartDao;
import model.Account;
import model.Bill;
import model.BillDetail;
import model.Cart;
import model.CartItem;
import tools.Validation;

@WebServlet(urlPatterns = "/user/checkout")
public class CheckoutController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/user/checkout.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CartDao cartDao = new CartDao();
		BillDao billDao = new BillDao();
		
		req.setCharacterEncoding("utf-8");
		
		String receiver = req.getParameter("receiver");
		String telReceiver = req.getParameter("telReceiver");
		String addressReceiver = req.getParameter("addressReceiver");
		String emailReceiver = req.getParameter("emailReceiver");
		String zipcode = req.getParameter("zipcode");
		
		HttpSession session= req.getSession();
		Account account = (Account) session.getAttribute("account");
		
		Bill bill = new Bill();
		java.util.Date currentDate = new Date();
		java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
		bill.setSaleday(sqlDate);
		
		bill.setReceiver(receiver);
		bill.setTelReceiver(telReceiver);
		bill.setAddressReceiver(addressReceiver);
		bill.setEmailReceiver(emailReceiver);
		bill.setZipcode(zipcode);
		bill.setAccount(account);

		if(Validation.isBlank(receiver)) {
			sendMsgToAjax(resp, "invalidReceiver");return;
		}
		if(Validation.isBlank(telReceiver) || !Validation.isPhoneNumber(telReceiver)) {
			sendMsgToAjax(resp, "invalidTelReceiver");return;
		}
		if(Validation.isBlank(addressReceiver)) {
			sendMsgToAjax(resp, "invalidAddressReceiver");return;
		}
		if(Validation.isBlank(emailReceiver) || !Validation.isEmail(emailReceiver)) {
			sendMsgToAjax(resp, "invalidEmailReceiver");return;
		}
		if(Validation.isBlank(zipcode) || !Validation.isZipcode(zipcode)) {
			sendMsgToAjax(resp, "invalidZipcode");return;
		}
		
		billDao.createBill(bill);
		
		if(session.getAttribute("buynow") != null) {
			BillDetail billDetail = new BillDetail();
			CartItem cartItem = (CartItem) session.getAttribute("buynow");
			billDetail.setBillid(bill.getId());
			billDetail.setBook(cartItem.getBook());
			billDetail.setQuantity(cartItem.getQuantity());
			billDao.addBillDetail(billDetail);
			
			session.removeAttribute("buynow");
		}else {		
			Cart cart = (Cart) session.getAttribute("cart");
			for(CartItem ci : cart.getCartItems()) {
				BillDetail billDetail = new BillDetail();
				billDetail.setQuantity(ci.getQuantity());
				billDetail.setBook(ci.getBook());
				billDetail.setBillid(bill.getId());
				billDao.addBillDetail(billDetail);
				cartDao.removeCartItem(ci); 
			}
			cart.getCartItems().clear(); 
			session.setAttribute("cart", cart);
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.append("msg", "successNewBill");
		jsonObject.append("redirect", req.getContextPath() + "/user/home");
		resp.setContentType("application/json");
		PrintWriter pr = resp.getWriter();
		pr.print(jsonObject);
		pr.flush();
	}
	private static void sendMsgToAjax( HttpServletResponse resp, String msg) throws ServletException, IOException  {
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("msg", msg);
		resp.setContentType("application/json");
		PrintWriter pr = resp.getWriter();
		pr.print(jsonObject);
		pr.flush();
	}
}
