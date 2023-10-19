<%@page import="model.Account"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/view/user" var="url"/>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Book shop</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="${url}/img/favicon.ico" rel="icon">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="${url}/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="${url}/css/style.css" rel="stylesheet">
</head>

<body>
    <jsp:include page="header.jsp"/>

    <!-- Products Start -->
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-lg-5">
                <div class="card shadow-lg border-0 rounded-lg mt-5">
                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Register Form</h3></div>
                    <div class="card-body">
                    	<input type="hidden" value="${sessionScope.msg }" id="msg"/>
                    	<%
                    		String msg = (String)session.getAttribute("msg");
                    		if(msg != null){
                    			session.removeAttribute("msg");
                    		}
                    	%>
                    	<c:set var="account" value="${sessionScope.account}"/>
                    	<%
                    		Account a = (Account)session.getAttribute("account");
                    		if(a != null){
                    			session.removeAttribute("account");
                    		}
                    	%>
                        <form action="<c:url value="/user/register"/>" method="POST">
                        	<div class="form-group mb-3">
                                <input class="form-control" required type="text" placeholder="Fullname" name="fullname" value="${account.fullname }"/>
                            </div>
                            <div class="form-group mb-3">
                                <input class="form-control" required type="text" placeholder="Username" name="username" value="${account.username }"/>
                            </div>
                            <div class="row">
	                            <div class="form-group mb-3 col-6">
	                                <input class="form-control" required  type="password" placeholder="Password" name="password" />
	                            </div>
	                            <div class="form-group mb-3 col-6">
	                                <input class="form-control" required  type="password" placeholder="Repeat Password" name="re-password" />
	                            </div>
                            </div>
                            
                            <div class="form-group mb-3">
                                <input class="form-control" required type="text" placeholder="Email" name="email" value="${account.email }" />
                            </div>
                            <div class="d-flex align-items-center justify-content-center mt-4 mb-0">
                                <button class="btn btn-primary" >Sign up</button>
                            </div>
                        </form>
                        <hr>
                        <p class="text-center">Have an account? <a href="<c:url value="/user/login"/>">Log in</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Products End -->

	<!-- Footer Start -->
    <jsp:include page="footer.jsp"/>
    <!-- Footer End -->


    <!-- Back to Top -->
    <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="${url}/lib/easing/easing.min.js"></script>
    <script src="${url}/lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="${url}/mail/jqBootstrapValidation.min.js"></script>
    <script src="${url}/mail/contact.js"></script>
    <script src="${url}/js/main.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script type="text/javascript">
    	let msg = document.getElementById("msg").value;
    	if(msg == 'failRegister'){
    		swal({text: 'Tài khoản đã tồn tại' , icon : 'error'});
    	} else if(msg == 'successRegister'){
    		swal({text: 'Đăng ký thành công' , icon : 'success'});
    	} else if(msg == 'invalidFullname'){
    		swal({text: 'Tên không được để trống' , icon : 'warning'});
    	} else if(msg == 'invalidUsername'){
    		swal({text: 'Tài khoản có ít nhất 3 ký tự, có thể chứa chữ hoa, thường, số, các ký tự .-_' , icon : 'warning'});
    	} else if(msg == 'invalidPassword'){
    		swal({text: 'Mật khẩu ít nhất một ký tự' , icon : 'warning'});
    	} else if(msg == 'invalidRePassword'){
    		swal({text: 'Nhập lại mật khẩu sai' , icon : 'warning'});
    	} else if(msg == 'invalidEmail'){
    		swal({text: 'Email không hợp lệ' , icon : 'warning'});
    	}    
    </script>
</body>

</html>