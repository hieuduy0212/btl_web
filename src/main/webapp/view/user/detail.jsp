<%@page import="model.Review"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <link href="${url}/css/star.css" rel="stylesheet">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>

<body>
    <jsp:include page="header.jsp"/>

    <!-- Products Start -->
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-lg-12">
            	<!-- Shop Detail Start -->
			    <div class="container-fluid py-5">
			        <div class="row px-xl-5">
			            <div class="col-lg-5 pb-5">
			                <div id="product-carousel" class="carousel slide" data-ride="carousel">
			                    <div class="carousel-inner border">
			                        <div class="carousel-item active text-center">
			                            <img style="width: 500px" src="${pageContext.request.contextPath}/${book.cover}" alt="Image">
			                        </div>
			                    </div>
			                </div>
			            </div>
			
			            <div class="col-lg-7 pb-5">
			                <h3 class="font-weight-semi-bold">Title : ${book.title}</h3>
			                <h3 class="font-weight-semi-bold">Author : ${book.author}</h3>
			                <h3 class="font-weight-semi-bold">Pages : ${book.pages}</h3>
			                <h3 class="font-weight-semi-bold">Release date : ${book.releasedate}</h3>
			                <h3 class="font-weight-semi-bold">Category : ${book.category.name}</h3>
			                
			                <div class="d-flex mb-3">
			                    <div class="text-primary mr-2">
			                        <h5 class="font-weight-semi-bold">${meanstar}/5</h5>
			                    </div>
			                    <small class="pt-1">(${reviewCount} Reviews)</small>
			                </div>
			                <p class="mb-4">${book.description }</p>
			                <div class="d-flex align-items-center mb-4 pt-2">
			                    <div class="input-group quantity mr-3" style="width: 130px;">
			                        <div class="input-group-btn">
			                            <button class="btn btn-primary btn-minus" >
			                            <i class="fa fa-minus"></i>
			                            </button>
			                        </div>
			                        <input type="text" class="form-control bg-secondary text-center" value="1" id="quantity" name="quantity">
			                        <div class="input-group-btn">
			                            <button class="btn btn-primary btn-plus">
			                                <i class="fa fa-plus"></i>
			                            </button>
			                        </div>
			                    </div>
			                    <button class="btn btn-primary px-3" onclick="addToCart(${book.id}, -1)"><i class="fa fa-shopping-cart mr-1"></i> Add To Cart</button>
			                    <button class="btn btn-primary px-3 ml-3" onclick="buyNow(${book.id})"> Mua ngay</button>
			                </div>
			            </div>
			        </div>
			        <div class="row px-xl-5">
			            <div class="col">
			                <div class="nav nav-tabs justify-content-center border-secondary mb-4">
			                    <a class="nav-item nav-link active" data-toggle="tab" href="#tab-pane-1">Reviews</a>
			                </div>
			                <div class="tab-content">
			                    <div class="tab-pane fade show active" id="tab-pane-1">
			                        <div class="row">
			                            <div class="col-md-6">
			                            <c:if test="${fn:length(reviews) == 0 }">
			                            	<h6 class="text-center m-5">Chưa có đánh giá nào</h6>
			                            </c:if>
			                            <c:if test="${fn:length(reviews) != 0 }">
			                            	<div id="reviews">
			                            	
			                            	
				                            	<c:forEach var="review" items="${reviews}">
					                            	 <div class="media mb-4 review">
					                                    <div class="media-body row">
					                                        <h6 class="col-8">${review.account.fullname}<small> - <i>${review.created }</i></small></h6> 
					                                        <div class="text-primary mb-2 copl-4">
					                                        	<c:forEach var="i" begin="1" end="5">
					                                        		
					                                        		<c:choose>
					                                        			<c:when test="${i <= review.star }">
					                                        				<i class="bx bxs-star"></i>
					                                        			</c:when>
				                                        				<c:when test="${i - review.star == 0.5}">
					                                        				<i class="bx bxs-star-half"></i>
					                                        			</c:when>
						                                        		<c:otherwise>
						                                        			<i class="bx bx-star"></i>	
						                                        		</c:otherwise>
					                                        		</c:choose>
					                                        		
					                                        	</c:forEach>
					                                        	
												           </div>
					                                        
					                                        <p class="col-12">${review.content}</p>
					                                    </div>
					                                </div>
				                                    <hr width="100%" size="5px">
				                            	</c:forEach>
			                            	</div>
			                            	<button id="btnmore" class="btn btn-primary" onclick="loadMoreReview(${book.id})">Xem thêm</button>
			                            </c:if>
		                            	   
			                            </div>
			                            
			                            <c:if test="${sessionScope.account.isAdmin == 0}">
				                            <div class="col-md-6">
				                                <h4 class="mb-4">Để lại đánh giá của bạn</h4>
				                                <div class="d-flex my-3">
				                                    <p class="mb-0 mr-2">Your Rating * :</p>
				                                    <div class="text-primary star">
				                                        <i class='bx bx-star'><div></div><div></div></i>
				                                    	<i class='bx bx-star'><div></div><div></div></i>
				                                    	<i class='bx bx-star'><div></div><div></div></i>
				                                    	<i class='bx bx-star'><div></div><div></div></i>
				                                    	<i class='bx bx-star'><div></div><div></div></i>
				                                    </div>
				                                </div>
			                                    <div class="form-group">
			                                        <label for="content">Your Review *</label>
			                                        <textarea id="content" cols="30" rows="5" class="form-control" name="content"></textarea>
			                                    </div>
			                                    <div class="form-group">
			                                        <button class="btn btn-primary" onclick="addReview(${book.id})" >Thêm đánh giá</button>
			                                    </div>
				                            </div>
			                            </c:if>
			                            <c:if test="${sessionScope.account== null}">
			                            	<h6 class="text-center m-5">Bạn cần đăng nhập <a href="<c:url value="/user/login"/>">tại đây</a> để thêm đánh giá</h6>
			                            </c:if>
			                        </div>
			                    </div>
			                </div>
			            </div>
			        </div>
			    </div>
			    <!-- Shop Detail End -->          
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
    <script src="${url}/js/star.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    
</body>

</html>