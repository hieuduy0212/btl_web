<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/view/admin" var="url"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Admin Homepage</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="${url}/images/favicon.png">
    <link rel="stylesheet" href="${url}/vendor/owl-carousel/css/owl.carousel.min.css">
    <link rel="stylesheet" href="${url}/vendor/owl-carousel/css/owl.theme.default.min.css">
    <link href="${url}/vendor/jqvmap/css/jqvmap.min.css" rel="stylesheet">
    <!-- Datatable -->
    <link href="${url}/vendor/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
    <link href="${url}/css/style.css" rel="stylesheet">
</head>

<body>
    <!--*******************
        Preloader start
    ********************-->
    <div id="preloader">
        <div class="sk-three-bounce">
            <div class="sk-child sk-bounce1"></div>
            <div class="sk-child sk-bounce2"></div>
            <div class="sk-child sk-bounce3"></div>
        </div>
    </div>
    <!--*******************
        Preloader end
    ********************-->
    <div id="main-wrapper">
        <jsp:include page="sidebar.jsp"/>
        <jsp:include page="header.jsp"/>

        <!--**********************************
            Content body start
        ***********************************-->
        <div class="content-body">
            <!-- row -->
            <div class="container-fluid">
                <div class="row justify-content-center h-100 align-items-center">
	                <div class="col-md-6">
	                    <div class="authincation-content">
	                        <div class="row no-gutters">
	                            <div class="col-xl-12">
	                                <div class="auth-form">
	                                    <h4 class="text-center mb-4">Sign in your admin account</h4>
	                                    <input type="hidden" value="${sessionScope.msg }" id="msg"/>
                                       	<%
                                       		HttpSession sessiong = request.getSession();
                                       		String msg = (String)session.getAttribute("msg");
                                       		if(msg != null){
                                       			session.removeAttribute("msg");
                                       		}
                                       	%>
	                                    <form action="<c:url value="/admin/login"/>" method="POST" onsubmit="return checkLogin()">
	                                        <div class="form-group">
	                                            <label><strong>Email/Username</strong></label>
	                                            <input class="form-control" autofocus id="inputEmail" type="text" placeholder="email@gmail.com" name="username"/>
	                                        </div>
	                                        <div class="form-group">
	                                            <label><strong>Password</strong></label>
	                                            <input class="form-control" id="inputPassword" type="password" placeholder="Password" name="password" />
	                                        </div>
	                                        <div class="text-center">
	                                            <button type="submit" class="btn btn-primary btn-block">Sign me in</button>
	                                        </div>
	                                    </form>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
            </div>
        </div>
        <!--**********************************
            Content body end
        ***********************************-->


        <jsp:include page="footer.jsp"/>

        <!--**********************************
           Support ticket button start
        ***********************************-->

        <!--**********************************
           Support ticket button end
        ***********************************-->
    </div>
    <!-- Required vendors -->
    <script src="${url}/vendor/global/global.min.js"></script>
    <script src="${url}/js/quixnav-init.js"></script>
    <script src="${url}/js/custom.min.js"></script>
    <!-- Counter Up -->
    <script src="${url}/vendor/jqvmap/js/jquery.vmap.min.js"></script>
    <script src="${url}/vendor/jqvmap/js/jquery.vmap.usa.js"></script>
    <script src="${url}/vendor/jquery.counterup/jquery.counterup.min.js"></script>
    <script src="${url}/js/dashboard/dashboard-1.js"></script>
    <!-- Datatable -->
    <script src="${url}/vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="${url}/js/plugins-init/datatables.init.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script type="text/javascript">
     	$(document).ready(function(){
     		let msg = $('#msg').val();
     		if(msg=='loginError' ){
     			swal({text : "Wrong account", icon: "error" });
     		}
     	});
     	
     </script>
     <script>
	     function checkLogin(){
	   		let username = $('#inputEmail').val();
	   		let password = $('#inputPassword').val();
	   		if(username!='' && username.length > 0 && password != '' && password.length > 0){
	   			return true;
	   		}else{
	   			swal({text : "Please enter your admin account", icon: "error" });
	   			return false;
	   		}
		}	
     </script>
</body>

</html>