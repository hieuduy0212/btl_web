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
        <div class="text-center mb-4">
            <h2 class="section-title px-5"><span class="px-2">Products</span></h2>
        </div>
        <div class="row px-xl-5 pb-3">
        	
        	<c:forEach var="book" items="${requestScope.books}">
	        	<div class="col-lg-3 col-md-6 col-sm-12 pb-1">
	                <div class="card product-item border-0 mb-4">
	                    <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0 text-center" >
	                        <img class="img-fluid" style="width: 192px; height: 262px" src="${pageContext.request.contextPath}/${book.cover}" alt="">
	                    </div>
	                    <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
	                        <h6 class="text-truncate">${book.title}</h6>
	                        <h6 class="text-truncate">${book.author}</h6>
	                    </div>
	                    <div class="card-footer d-flex justify-content-between bg-light border">
	                        <a href="<c:url value="/user/detail?bid=${book.id}"/>" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>View Detail</a>
	                        <button class="btn btn-sm text-dark p-0" onclick="addToCart(${book.id}, 1)" ><i class="fas fa-shopping-cart text-primary mr-1"></i>Add To Cart</button>
	                    </div>
	                </div>
	            </div>
        	</c:forEach>
        	
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