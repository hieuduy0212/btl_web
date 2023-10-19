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
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Datatable</h4>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
	                                <c:if test="${sessionScope.account.isAdmin == 1 }">
	                              		<div class="col-12 text-center" ><a class="btn btn-primary" href="<c:url value="/admin/view?bid=0"/>">Add Book</a></div>
	                                </c:if>
                                    <table id="example2" class="display" style="width:100%">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
	                                            <th>Title</th>
	                                            <th>Author</th>
	                                            <th>Category</th>
	                                            <th>Release date</th>
	                                            <th>Pages</th>
	                                            <th>Sales amount</th>
	                                            <c:if test="${sessionScope.account.isAdmin == 1 }">
	                                            	<th>Action</th>
	                                            </c:if>
                                            </tr>
                                        </thead>
                                        <tbody>
	                                    	<c:forEach var="book" items="${books}">
		                                    	<tr id="tr${book.id }">
		                                            <td>${book.id }</td>
		                                            <td>${book.title }</td>
		                                            <td>${book.author }</td>
		                                            <td>${book.category.name }</td>
		                                            <td>${book.releasedate }</td>
		                                            <td>${book.pages }</td>
		                                            <td>${book.salesAmount }</td>
		                                            <c:if test="${sessionScope.account.isAdmin == 1 }">
		                                            	<td>
		                                            		<a href="<c:url value="/admin/view?bid=${book.id}"/>" class="btn btn-primary">View</a>
		                                            		<button class="btn btn-danger" onclick="deletebook(${book.id })">Delete</button>
		                                            	</td>
		                                            </c:if>
		                                        </tr>
	                                    	</c:forEach>
	                                    </tbody>
                                        <tfoot>
	                                        <tr>
	                                            <th>ID</th>
	                                            <th>Title</th>
	                                            <th>Author</th>
	                                            <th>Category</th>
	                                            <th>Release date</th>
	                                            <th>Pages</th>
	                                            <th>Sales amount</th>
	                                            <c:if test="${sessionScope.account.isAdmin == 1 }">
	                                            	<th>Action</th>
	                                            </c:if>
	                                        </tr>
	                                    </tfoot>
                                    </table>
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
    <!-- Datatable -->
    <script src="${url}/vendor/datatables/js/jquery.dataTables.min.js"></script>
    <script src="${url}/js/plugins-init/datatables.init.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script>
       	function deletebook(bid){
       		let context = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
       		
       		swal({
       		  title: "Are you sure?",
       		  text: "Once deleted, you will not be able to recover this book!",
       		  icon: "warning",
       		  buttons: true,
       		  dangerMode: true,
       		})
       		.then((willDelete) => {
       		  if (willDelete) {
       			let trd = document.querySelector('#tr'+bid);
       			trd.remove();
       			$.ajax({
       				url : context + "/admin/delete",
       				method : 'get',
       				data : {
       					bid : bid
       				},
       				success: function(response){
       					let msg = response.msg;
       					if(msg){
       						swal({title : 'Delete book successfully!', icon : 'success'});
       					}
       				},
       				error : function(err){
       					console.log(err);
       				}
       			});
       		  }
       		});
       	}
       </script>
</body>

</html>