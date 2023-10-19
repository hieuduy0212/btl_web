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
                <div class="d-flex justify-content-center align-items-center">
	                <main>
	                    <div class="container">
	                    <c:if test="${sessionScope.account == null }">
	                    	<h5>You need a admin account. Please login <a href="<c:url value="/admin/login"/>">here</a></h5>
	                    </c:if>
	                    <c:if test="${sessionScope.account.isAdmin == 1 }">
	                    	<div class="row justify-content-center">
	                            <div class="col-lg-12">
	                                <div class="card shadow-lg border-0 rounded-lg mt-5">
	                                    <div class="card-header"><h3 class="font-weight-light my-4">Information book</h3></div>
	                                    <div class="card-body">
	                                        <form id="submitFormBook" method="post" enctype="multipart/form-data">
	                                        	<input class="form-control p-1" id="bid" name="bid" type="hidden" value="${bid}"/>
	                                            <div class="row mb-3">
	                                            	<div class="col-md-6 row">
	                                            		<div class="col-md-6">
	                                                        <label for="title">Title</label><span style="color:red"> *</span>
		                                                    <div class="form-group mb-3 mb-md-0">
		                                                        <input ${status=='view'?"disabled":"" } class="form-control p-1" id="title" name="title" type="text" value="${book.title}"/>
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-6">
	                                                        <label for="author">Author</label><span style="color:red"> *</span>
		                                                    <div class="form-group">
		                                                        <input ${status=='view'?"disabled":"" } class="form-control p-1" id="author" name="author" type="text" value="${book.author}"/>
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-12 mt-10">
		                                                    <div class="form-group">
		                                                        <label for="description">Description</label>
		                                                        <textarea ${status=='view'?"disabled":"" } class="form-control" id="description" name="description" 
		                                                        rows="10" style="width: 100%; height:100px">${book.description}</textarea>
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-6">
		                                                    <div class="form-group mb-3 mb-md-0 mt-3">
		                                                        <label for="releasedate">Release date</label><span style="color:red"> *</span>
		                                                        <input ${status=='view'?"disabled":"" } class="form-control p-1" id="releasedate" name="releasedate" type="date" value="${book.releasedate}"/>
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-6">
		                                                    <div class="form-group mt-3">
		                                                        <label for="pages">Pages</label>
		                                                        <input ${status=='view'?"disabled":"" } class="form-control p-1" id="pages" name="pages" type="number" value="${book.pages}"/>
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-12">
		                                                    <div class="form-group mt-3">
		                                                   		<label for="category">Category</label>
		                                                        <select class="form-control" name="category" id="category" ${status=='view'?"disabled":"" }>
		                                                        	<option disabled value="0">Chọn một danh mục</option>
		                                                        	<c:forEach var="cate" items="${categories}">
		                                                        		<c:choose>
		                                                        			<c:when test="${book.category.id == cate.id}">
		                                                        				<option selected value="${cate.id}">${cate.name}</option>
		                                                        			</c:when>
		                                                        			<c:otherwise>
		                                                        				<option value="${cate.id}">${cate.name}</option>
		                                                        			</c:otherwise>
		                                                        		</c:choose>
		                                                        	</c:forEach>
																</select>
		                                                    </div>
		                                                </div>
	                                            	</div>
	                                            	
	                                            	<div class="col-md-6 row">
	                                            		<div class="col-md-12 form-group">
	                                            			<label class="form-label">Cover of book</label>
														  	<input ${status=='view'?"disabled":"" } class="form-control" type="file" id="cover" name="cover" accept="image/png, image/jpeg, image/jpg">
	                                            		</div>
	                                            		<h3></h3>
	                                            		<div class="col-md-12 form-group d-flex justify-content-center align-items-center" id="showcover" style="border: 1px solid #ced4da; height: 80%;border-radius: 0.375rem;">
	                                            			<img id="cover_image" style="max-width:200px; max-height:400px !important" src="${pageContext.request.contextPath}/${book.cover}">
	                                            		</div>
	                                            	</div>
	                                            </div>
	                                            <div class="mt-4 mb-0 text-center">
	                                                <c:if test="${status=='add'}"><button class="btn btn-success p-3" onclick="return confirm('Are you sure to add this book?')">Add</button></c:if>
	                                                <button class="btn btn-success p-3" style="display : none" id="btnSave" onclick="return confirm('Are you sure to update this book?')">Save</button>
	                                            </div>
	                                        </form>
	                                        <div class="mt-4 mb-0 text-center">
	                                                <c:if test="${status=='view'}"><button id="btnEdit" class="btn btn-primary p-3" onclick="clickedit(this)">Edit</button></c:if>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </c:if>
	                    </div>
	                </main>
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
    <script src="${url}/js/upload-image.js"></script>
    <script src="${url}/js/submit-form-book.js"></script>
    <script>
    	function clickedit(element){
    		let form = document.querySelector('form');
    		let inputs = form.getElementsByTagName('input');
    		for(let i = 0 ; i < inputs.length ; i++){
    			inputs[i].disabled = false;
    		}
    		form.querySelector('textarea').disabled = false;
    		form.querySelector('select').disabled = false;
    		document.getElementById('btnSave').style.display = 'inline';
    		document.getElementById('btnEdit').style.display = 'none';
    	}
    </script>
</body>

</html>