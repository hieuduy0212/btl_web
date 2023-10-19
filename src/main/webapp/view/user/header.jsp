<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<c:url value="/view/user" var="url"/>

<!-- Topbar Start -->
    <div class="container-fluid">
        <div class="row align-items-center py-3 px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a href="" class="text-decoration-none">
                    <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">BOOK</span>SHOP</h1>
                </a>
            </div>
            <div class="col-lg-6 col-6 text-left">
            </div>
            <div class="col-lg-3 col-6 text-right">
                <a href="<c:url  value="/user/cart"/>" class="btn border">
                    <i class="fas fa-shopping-cart text-primary"></i>
                    <span class="badge" id="sizecart">${fn:length(sessionScope.cart.cartItems)}</span>
                </a>
            </div>
        </div>
    </div>
    <!-- Topbar End -->


    <!-- Navbar Start -->
    <div class="container-fluid">
        <div class="row border-top px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
            </div>
            <div class="col-lg-9">
                <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
                    <a href="" class="text-decoration-none d-block d-lg-none">
                        <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">BOOK</span>SHOP</h1>
                    </a>
                    <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                        <div class="navbar-nav mr-auto py-0">
                            <a href="<c:url  value="/user/home"/>" class="nav-item nav-link ${nav == 'home' ? 'active' : ''}">Home</a>
                            <c:if test="${nav == 'detail'}">
                            	<a href="#" class="nav-item nav-link ${nav == 'detail' ? 'active' : ''}">Shop Detail</a>
                            </c:if>
                            <a href="<c:url  value="/user/cart"/>" class="nav-item nav-link ${nav == 'cart' ? 'active' : ''}">Shopping Cart</a>
                            <a href="<c:url value="/user/bills"/>" class="nav-item nav-link">Bill List</a>
                        </div>
                        <div class="navbar-nav ml-auto py-0">
                        	
                        	<c:if test="${sessionScope.account == null }">
                        		<a href="<c:url value="/user/login"/>" class="nav-item nav-link">Login</a>
                            	<a href="<c:url value="/user/register"/>" class="nav-item nav-link">Register</a>
                        	</c:if>
                        
                            <c:if test="${sessionScope.account.isAdmin == 0 }">
                            	<a href="#" class="nav-item nav-link">Hello ${sessionScope.account.fullname}</a>
                        		<a href="<c:url value="/user/logout"/>" class="nav-item nav-link">Logout</a>
                        	</c:if>
                            
                        </div>
                    </div>
                </nav>
                
            </div>
        </div>
    </div>
    <!-- Navbar End -->