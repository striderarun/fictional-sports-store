<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<jsp:include page="./header.jsp">
	<jsp:param name="pageTitle" value="Arun's Sports Store"/>
</jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/init.js"></script>

</head>
<body>
	<div class="container">
		<nav class="navbar navbar-default navalign">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="#">
		        <span class="appName"><spring:message code="arun.sports.store" /></span>
		      </a>
		    </div>
		    <ul class="nav navbar-nav navbar-right">
      			<li><a href="${pageContext.request.contextPath}/logout" class="logoutBtn">Logout</a></li>
			</ul>
		  </div>
		</nav>
		<div class="body">
			<div>
				<ul class="nav nav-pills nav-justified">
					<li><a href="${pageContext.request.contextPath}/selectShoe"><spring:message code="selectShoe" /></a></li>
					<li><a href="${pageContext.request.contextPath}/addShoe"><spring:message code="addShoe" /></a></li>
					<li><a href="${pageContext.request.contextPath}/editShoe"><spring:message code="editShoe" /></a></li>
					<li><a href="${pageContext.request.contextPath}/manageUser" ><spring:message code="manageUser" /></a></li>
 					<li><a href="${pageContext.request.contextPath}/fileUpload"><spring:message code="upload" /></a></li>
					<li class="active"><a href="${pageContext.request.contextPath}/multiFileUpload" data-toggle="tab"><spring:message code="multiUpload" /></a></li>

				</ul>
			</div>

			<div class="border-content" style="overflow: auto">
				<div class="panel panel-default panelWidth">
					  <div class="panel-heading">
					    <h3 class="panel-title">Upload File</h3>
					  </div>
					  <div class="panel-body">
	   						<form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/shoes/multiUpload">
								File 1 to upload: <input type="file" name="file"><br />
								Name of File 1: <input type="text" name="name"><br /> <br />
								File 2 to upload: <input type="file" name="file"><br />
								Name of File 2: <input type="text" name="name"><br /> <br />
								<input type="submit" value="Upload"> Press here to upload the file
							</form>
					  </div>
				</div>
			</div>
			
			<div>
				<img src="${pageContext.request.contextPath}/shoes/fetchShoeImage?shoeId=2">
			</div>
		</div>
	</div>

</body>
</html>