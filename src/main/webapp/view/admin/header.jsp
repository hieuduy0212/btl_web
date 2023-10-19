<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/view/admin" var="url"/>
<div class="nav-header">
    <a href="index.html" class="brand-logo">
        <img class="logo-abbr" src="${url}/images/logo.png" alt="">
        <img class="logo-compact" src="${url}/images/logo-text.png" alt="">
        <img class="brand-title" src="${url}/images/logo-text.png" alt="">
    </a>

    <div class="nav-control">
        <div class="hamburger">
            <span class="line"></span><span class="line"></span><span class="line"></span>
        </div>
    </div>
</div>
<div class="header">
    <div class="header-content">
        <nav class="navbar navbar-expand">
            <div class="collapse navbar-collapse justify-content-between">
                <div class="header-left"></div>
                <ul class="navbar-nav header-right">
                    <li class="nav-item dropdown header-profile">
                        <a class="nav-link" href="#" role="button" data-toggle="dropdown">
                            <i class="mdi mdi-account"></i>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right">
                        	<a href="#" class="dropdown-item">
	                            <i class="fa fa-user-o"></i>
	                            <span class="ml-2">Hello ${sessionScope.account.fullname}</span>
	                        </a>
	                        <c:if test="${sessionScope.account == null }">
		                            <a href="<c:url value="/admin/login"/>" class="dropdown-item">
		                                <i class="icon-key"></i>
		                                <span class="ml-2">Login</span>
		                            </a>
				           </c:if>
				           <c:if test="${sessionScope.account != null }">
	                            <a href="<c:url value="/admin/logout"/>" class="dropdown-item">
	                                <i class="icon-key"></i>
	                                <span class="ml-2">Logout</span>
	                            </a>
				           </c:if>                        
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</div>