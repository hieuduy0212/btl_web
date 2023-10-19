<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/view/admin" var="url"/>
<div class="quixnav">
    <div class="quixnav-scroll">
        <ul class="metismenu" id="menu">
            <li class="nav-label first">Main Menu</li>
            <li><a aria-expanded="false" href="<c:url value="/admin/home"/>">
                <i class="fa fa-home"></i><span class="nav-text">Homepage</span></a>
            </li>
            <c:if test="${sessionScope.account == null }">
            	<li><a aria-expanded="false" href="<c:url value="/admin/login"/>">
                	 <i class='fa fa-sign-in'></i><span class="nav-text">Login</span></a>
            	</li> 	
            </c:if>
            <c:if test="${sessionScope.account != null }">
            	<li><a aria-expanded="false" href="<c:url value="/admin/logout"/>">
               	  	<i class="fa fa-sign-out"></i><span class="nav-text">Logout</span></a>
            	 </li>
            </c:if>
        </ul>
    </div>
</div>