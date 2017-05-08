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
					<li class="active"><a href="${pageContext.request.contextPath}/selectShoe" data-toggle="tab"><spring:message code="selectShoe" /></a></li>
					<li><a href="${pageContext.request.contextPath}/addShoe"><spring:message code="addShoe" /></a></li>
					<li><a href="${pageContext.request.contextPath}/editShoe"><spring:message code="editShoe" /></a></li>
					<li><a href="${pageContext.request.contextPath}/manageUser"><spring:message code="manageUser" /></a></li>
 					<li><a href="${pageContext.request.contextPath}/fileUpload"><spring:message code="upload" /></a></li>
					<li><a href="${pageContext.request.contextPath}/multiFileUpload"><spring:message code="multiUpload" /></a></li>

				</ul>
			</div>

			<div class="border-content" style="overflow: auto">
				<div class="panel panel-default panelWidth">
					  <div class="panel-heading">
					    <h3 class="panel-title">Shoe Views</h3>
					  </div>
					  <div class="panel-body">
	   						<table>
								<tr>
									<td>Select Shoe Filter </td>
									<td>
										<select id = "selectReport" class="reportTypes"  name="reportType">
											<option value="0" selected>---Please Select----</option>
											<option value="getAllShoes">View All Shoes</option>
											<option value="getShoesByBrand">View Shoes by Brand</option>
											<option value="getShoesByBrandAndPrice">View Shoes by Brand and Price</option>
											<option value="getShoesByBrandOrPrice">View Shoes by Brand or Price</option>
										</select>
									</td>
								</tr>

								<tr id = "divBrand" class = "hideit">
									<td>Select Brand</td>
									<td>
										<select id = "brandName" class="reportTypes"  name="reportType">
											<option value="0" selected><spring:message code="pleaseSelect" /></option>
											<option value="Nike"><spring:message code="Nike" /></option>
											<option value="Asics"><spring:message code="Asics" /></option>
											<option value="Puma"><spring:message code="Puma" /></option>
											<option value="Reebok"><spring:message code="Reebok" /></option>
											<option value="Adidas"><spring:message code="Adidas" /></option>
										</select>
									</td>
								</tr>

								<tr id = "divPrice" class = "hideit">
									<td><span>Enter Lower Price</span> <input type="text" id = "lowerPrice"></td>
									<td><span>Enter Upper Price</span> <input type="text" id = "upperPrice"></td>
								</tr>

							</table>

							<div id = "shoesView" align ="center"></div>
					</div>

					<div class="form-actions">
						<button id="refresh-report" class="btn btn-primary btn-large" >Generate</button>
					</div>

				</div>
			</div>

			<div class="table-container hideit">
     					<h3>Transactions</h3>
					<br><table id="table_id" class="table bordered rounded"></table>
			</div>

		</div>
</div>

</body>
</html>