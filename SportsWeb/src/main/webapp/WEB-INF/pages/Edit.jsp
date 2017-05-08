<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="./header.jsp">
	<jsp:param name="pageTitle" value="Arun's Sports Store"/>
</jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/Query4.js"></script>
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
					<li class="active"><a href="${pageContext.request.contextPath}/editShoe" data-toggle="tab"><spring:message code="editShoe" /></a></li>
					<li><a href="${pageContext.request.contextPath}/manageUser"><spring:message code="manageUser" /></a></li>
 					<li><a href="${pageContext.request.contextPath}/fileUpload"><spring:message code="upload" /></a></li>
					<li><a href="${pageContext.request.contextPath}/multiFileUpload"><spring:message code="multiUpload" /></a></li>

				</ul>
			</div>

			<div class="border-content" style="overflow: auto">
				<div class="panel panel-default panelWidth">
					  <div class="panel-heading">
					    <h3 class="panel-title">Update Shoes</h3>
					  </div>
					  <div class="panel-body">
	   						<div class="table-container hideit">
									<table id="table_id" class="table bordered rounded"></table>
							</div>
					  </div>
				<sec:authorize access="hasRole('Admin')">
					<div align="right">
						<button id = "editShoeDetails">Edit</button>
						<button id = "updateShoeDetails">Update</button>
					</div>
				</sec:authorize>

				</div>
			</div>
		</div>
 	</div>

</body>
</html>