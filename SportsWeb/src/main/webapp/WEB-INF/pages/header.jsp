<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/handlebars.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/urls.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.dataTables.css" type="text/css" media="all" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" type="text/css" media="all" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bank.css" type="text/css" media="all" />


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title >${param.pageTitle}</title>
</head>
<body>

<script id="aggregate-outputs" type="text/x-handlebars-template">
	<h2><u>Aggregate Details</u></h2>
	<h2> Total number of shoes    : {{rowCount}} </h2>
	<h2> Total Price of all shoes : {{sum}} </h2>
	<h2> Maximum shoe price		  : {{max}} </h2>
	<h2> Minimum shoe price		  : {{min}} </h2>
 	<h2> Average shoe price		  : {{avg}} </h2>

</script>

<script id="shoe-list" type="text/x-handlebars-template">
	<h2><u>List of Shoes</u></h2>

    {{#each shoeListResponse}}
           <h2>{{shoeName}}</h2>
    {{/each}}
</script>

<script id="userDetails" type="text/x-handlebars-template">
	<ul class="list-group">
  		<li class="list-group-item">
    		<span class="badge">{{userId}}</span>
    		User Name
  		</li>
		<li class="list-group-item">
    		<span class="badge">{{firstName}}</span>
    		First Name
  		</li>
		<li class="list-group-item">
    		<span class="badge">{{lastName}}</span>
    		Last Name
  		</li>
		<li class="list-group-item">
    		<span class="badge">{{city}}</span>
    		City
  		</li>
		<li class="list-group-item">
    		<span class="badge">{{state}}</span>
    		State
  		</li>
		<li class="list-group-item">
    		<span class="badge">{{country}}</span>
    		Country
  		</li>
		<li class="list-group-item">
    		<span class="badge">{{zipCode}}</span>
    		Zip Code
  		</li>
		<li class="list-group-item">
    		<span class="badge">{{phoneOne}}</span>
    		Phone One
  		</li>
		<li class="list-group-item">
    		<span class="badge">{{phoneTwo}}</span>
    		Phone Two
  		</li>
		<li class="list-group-item">
    		<span class="badge">{{emailOne}}</span>
    		Email One
  		</li>
		<li class="list-group-item">
    		<span class="badge">{{emailTwo}}</span>
    		Email Two
  		</li>
</ul>
</script>

</body>
</html>