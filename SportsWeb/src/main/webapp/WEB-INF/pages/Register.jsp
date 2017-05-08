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
					<li class="active"><a href="${pageContext.request.contextPath}/addUser" data-toggle="tab">Register User</a></li>

				</ul>
			</div>
			<div class="border-content" style="overflow: auto">
				<div class="panel panel-default panelWidth">
					  <div class="panel-heading">
					    <h3 class="panel-title">Manage User</h3>
					  </div>
					  <div class="panel-body">
									<div id="register" align="center">
							<table>
								<tr>
									<td>First Name :</td>
									<td><input id="firstName" type="text">
									</td>
								</tr>

								<tr>
									<td>Last Name :</td>
									<td><input id="lastName" type="text">
									</td>
								</tr>
								
								<tr>
									<td>User Role :</td>
									<td><input id="userRole" type="text">
									</td>
								</tr>

								<tr>
									<td>City :</td>
									<td><input id="city" type="text">
									</td>
								</tr>

								<tr>
									<td>State :</td>
									<td><input id="state" type="text">
									</td>
								</tr>

								<tr>
									<td>Country :</td>
									<td><input id="country" type="text">
									</td>
								</tr>

								<tr>
									<td>Zip Code :</td>
									<td><input id="zipCode" type="text">
									</td>
								</tr>

								<tr>
									<td>Phone No 1 :</td>
									<td><input id="phone1" type="text">
									</td>
								</tr>

								<tr>
									<td>Phone No 2 :</td>
									<td><input id="phone2" type="text">
									</td>
								</tr>

								<tr>
									<td>Email Id 1 :</td>
									<td><input id="email1" type="text">
									</td>
								</tr>

								<tr>
									<td>Email Id 2 :</td>
									<td><input id="email2" type="text">
									</td>
								</tr>

								<tr>
									<td>User Id :</td>
									<td><input id="userId" type="text">
									</td>
								</tr>

								<tr>
									<td>Password :</td>
									<td><input id="password" type="text">
									</td>
								</tr>
							</table>
							<br>


						</div>
						<br><button id="registerUser" class="btn btn-primary btn-large">Register</button>
					  </div>

				</div>
			</div>
		</div>
	</div>




</body>
</html>