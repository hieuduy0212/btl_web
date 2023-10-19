<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:url value="/view/user" var="url"/>
<!DOCTYPE html>
<html lang="en" >

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
            <div class="col-lg-12">
            
            	<c:if test="${sessionScope.account == null }">
            		<h6 class="text-center m-5">You need to <a href="<c:url value="/user/login"/>">login</a> to view cart</h6>
            	</c:if>
            	
            	
            	<c:if test="${sessionScope.account.isAdmin == 0}">
            	<!-- Cart Start -->
				    <div class="container-fluid pt-5">
				        <div class="row px-xl-5">
				            <div class="col-lg-8 table-responsive mb-5">
				                <table class="table table-bordered text-center mb-0">
				                    <thead class="bg-secondary text-dark">
				                        <tr>
				                        	<th>No</th>
				                            <th>Product</th>
				                            <th>Quantity</th>
				                            <th>Remove</th>
				                        </tr>
				                    </thead>
				                    <tbody class="align-middle">
				                    
				                    	<c:set var="stt" value="1"/>
				                    	
				                    	<c:forEach var="cartitem" items="${sessionScope.cart.cartItems}">
				                    	<tr id="${cartitem.book.id}">
				                    		<td>${stt}</td>
				                    		<c:set var="stt" value="${stt+1}"/>
				                            <td class="align-middle">
				                            	<a href="<c:url value="/user/detail?bid=${cartitem.book.id}"/>" 
				                            		style="color : black; text-decoration:none" >
					                            	<img src="${pageContext.request.contextPath}/${cartitem.book.cover}" 
					                            	alt="" style="width: 50px;"> ${cartitem.book.title }
				                            	</a>	
				                            </td>
				                            <td class="align-middle">
				                                <div class="input-group quantity mx-auto" style="width: 100px;" onclick="updateCart(${cartitem.book.id})">
				                                    <div class="input-group-btn">
				                                        <button class="btn btn-sm btn-primary btn-minus" >
				                                        <i class="fa fa-minus"></i>
				                                        </button>
				                                    </div>
				                                    <input type="text" class="form-control form-control-sm bg-secondary text-center" value="${cartitem.quantity}"  >
				                                    <div class="input-group-btn">
				                                        <button class="btn btn-sm btn-primary btn-plus">
				                                            <i class="fa fa-plus"></i>
				                                        </button>
				                                    </div>
				                                </div>
				                            </td>
				                            <td class="align-middle"><button onclick="deleteItem(${cartitem.book.id})"  class="btn btn-sm btn-primary"><i class="fa fa-times"></i></button></td>
				                        </tr>
				                    	</c:forEach>
				                    
				                        
				                    </tbody>
				                </table>
				            </div>
				            <div class="col-lg-4">
				                <div class="card border-secondary mb-5">
				                	<c:if test="${fn:length(sessionScope.cart.cartItems) != 0}">
					                    <div class="card-footer border-secondary bg-transparent">
					                        <a  href="<c:url value="/user/checkout"/>"  class="btn btn-block btn-primary my-3 py-3">Proceed to checkout</a>
					                    </div>
				                    </c:if>
				                </div>
				            </div>
				        </div>
				    </div>
				    <!-- Cart End -->
            	</c:if>                  
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
    <script src="${url}/js/myJS.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    
</body>

</html>
