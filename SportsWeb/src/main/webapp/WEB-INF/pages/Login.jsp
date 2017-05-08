<!DOCTYPE html>
<html lang="en">
<head>
<title >Arun's Sports Store</title>
<meta charset="utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/handlebars.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/urls.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/init.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css" type="text/css" media="all" />
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
	<h2><u>User Details</u></h2>
	<h2> User ID			 : {{userId}} </h2>
	<h2> First Name			 : {{firstName}} </h2>
	<h2> Last Name			 : {{lastName}} </h2>
	<h2> City				 : {{city}} </h2>
 	<h2> State				 : {{state}} </h2>
	<h2> Country			 : {{country}} </h2>
	<h2> Zip Code			 : {{zipCode}} </h2>
	<h2> Phone One			 : {{phoneOne}} </h2>
	<h2> Phone Two			 : {{phoneTwo}} </h2>
	<h2> Email One			 : {{emailOne}} </h2>
	<h2> Email Two			 : {{emailTwo}} </h2>

</script>
	<div>
		&nbsp;&nbsp;
	</div>
	<section id="loginCon" class="body">

	<header >
		<%-- <div class="myStore_logo"><span><img src="${pageContext.request.contextPath}/resources/images/logo.png"></span></div> --%>
		<div id="logo">
			<span><img src="${pageContext.request.contextPath}/resources/images/unnamed.png" width = "150px" alt="Logo" /></span>
		</div>
	</header>

	<hr>

<!-- Form -->
	<div id="login">
		<div class="title-bar">
			<h2>Login</h2>
		</div>

		<table width="100%">
			<tr>
				<td width="30%">
				</td>
				<td>

						<script>
	function dsfocus(){ document.appleConnectForm.theAccountPW.focus(); }
</script>



		<div id="ds_container">














	<script type="text/javascript">
		function placeHolderFieldAnimation() {

			function getStyle(el, strCssRule){
				var oElm = document.getElementById(el);
				var strValue = "";
				if(document.defaultView && document.defaultView.getComputedStyle){
					strValue = document.defaultView.getComputedStyle(oElm, "").getPropertyValue(strCssRule);
				}
				else if(oElm.currentStyle){
					strCssRule = strCssRule.replace(/\-(\w)/g, function (strMatch, p1){
						return p1.toUpperCase();
					});
					strValue = oElm.currentStyle[strCssRule];
				}
				return strValue;
			}

			timeMsg();

			var inputs = document.getElementsByTagName("input");
			var len = inputs.length;
			for (var i=0; i < len; i++) {
				if ((inputs[i].getAttribute("type") == "text" || inputs[i].getAttribute("type") == "password" ) && inputs[i].getAttribute("placeholder") && inputs[i].getAttribute("placeholder").length > 0) {

					var labelid = "label" + i;

					// Create the wrapper div which makes elements relative
					var newDivTag = document.createElement("div");
					newDivTag.className ="field-container";
					newDivTag.style.position = "relative";

					// Create the label div
					var divTag = document.createElement("div");
					divTag.id = labelid;
					divTag.className = "label-text";
					divTag.innerHTML = inputs[i].getAttribute("placeholder");
					divTag.style.display = "block";
					divTag.style.color = "#aaa";
					divTag.style.position = "absolute";
					divTag.style.top = "0px";
					divTag.style.left = "0px";

					// Inherit the properties from parent
					divTag.style['width'] = getStyle(inputs[i].id, "width");
					divTag.style.paddingTop = getStyle(inputs[i].id, "padding-top");
					divTag.style.paddingBottom = getStyle(inputs[i].id, "padding-bottom");
					divTag.style.paddingLeft = getStyle(inputs[i].id, "padding-left");
					divTag.style.paddingRight = getStyle(inputs[i].id, "padding-right");
					divTag.style.textAlign = getStyle(inputs[i].id, "text-align");
					divTag.style.fontSize = getStyle(inputs[i].id, "font-size");

					// Clone the current input type element to be used later
					var currentInputElement = inputs[i].cloneNode(false);
					// Remove placeholder to override the safari honoring of HTML5 placeholder attribute
					currentInputElement.removeAttribute("placeholder");

					// Get parent node and replace input with wrapper div
					var parentNode = inputs[i].parentNode;
					parentNode.replaceChild(newDivTag, inputs[i]);

					// Start adding elements
					newDivTag.appendChild(currentInputElement);
					newDivTag.appendChild(divTag);

					// Assign events
					if (currentInputElement.value.length < 1) {
						divTag.style.display = "block";
					} else {
						divTag.style.display = "none";
					}
					divTag.onclick = function() {
						this.style.color = '#e1e1e1';
						this.parentNode.getElementsByTagName("input")[0].focus();
					}
					currentInputElement.onblur = function() {
						if (this.value.length < 1 || this.value == this.getAttribute("placeholder")) {
							this.parentNode.getElementsByTagName("div")[0].style.display = "block";
							this.parentNode.getElementsByTagName("div")[0].style.color = '#aaa';
						}
						else {
							this.parentNode.getElementsByTagName("div")[0].style.display = "none";
						}
					}
					currentInputElement.onfocus = function() {
						if (this.value.length < 1 || this.value == this.getAttribute("placeholder")) {
							this.parentNode.getElementsByTagName("div")[0].style.display = "block";
							this.parentNode.getElementsByTagName("div")[0].style.color = '#e1e1e1';
						}
						else {
							this.parentNode.getElementsByTagName("div")[0].style.display = "none";
						}
					}
					currentInputElement.onkeydown = function(evt) {
						this.parentNode.getElementsByTagName("div")[0].style.display = "none";
					}
				}
			}
		}

		function timeMsg()
		{
			var t=setTimeout("afterload()",280);
		}

		function afterload() {
			var a = document.getElementById("accountname").value ;
			var v = document.getElementById("accountpassword").value ;
			var u = document.getElementById("accountname").nextSibling;
			var p = document.getElementById("accountpassword").nextSibling;
			while (u.nodeType!=1)
			{
			  u=u.nextSibling;
			}
			while (p.nodeType!=1)
			{
			  p=p.nextSibling;
			}
			if(a!=null && a!="") {
				document.getElementById(u.id).style.display = "none";
			}
			if(v!=null && v!="") {
				document.getElementById(p.id).style.display = "none";
			}
		}
	</script>



			<form method="post" class="dsform" name="appleConnectForm" action="selectShoe">



				<div class="formrow">



					<span class="formwrap">
						<input size="30" autocapitalize="off" autocorrect="off" maxlength="128" tabindex="1" placeholder="User ID" class="input-text" id="accountname" type="text" value="" name="theAccountName" />
					</span>




				</div>
				<div class="formrow">
					<span class="formwrap">
						<input size="30" oncut="return false ;" autocorrect="off" maxlength="32" onpaste="return false ;" autocapitalize="off" tabindex="2" oncopy="return false ;" placeholder="Password" class="input-password" id="accountpassword" type="password" name="theAccountPW" />
					</span>

				</div>

				<div id="bot-nav">

							<input class="btn bigblue" id="signup" type="button" value="Sign Up" name="signInHyperLink" />

							<input class="btn bigblue" id="signInHyperLink" type="submit" value="Sign in" name="signInHyperLink" />


				</div>

				<input id="theTypeValue" type="hidden" name="theTypeValue" />

			<input type="hidden" name="wosid" value="M3ygDhvCeASZIs79TV79Kw" /></form>




</div>












				</td>
				<td width="30%">
				</td>
			</tr>
		</table>




	</div>

	<!-- End Form -->
	<!-- Notice -->
	<section class="notice">
		<p>
		<strong>Notice</strong><br>
		This is a secured-access website.
		<br><br>
		Unauthorized access of this site is prohibited. By proceeding from this point, you agree to comply with all Terms &amp; Conditions applying to use of this site.  You will be held responsible for the appropriate use of the ID and password used to access this site, as well as all information accessed on this site.
		</p>

	</section>

	<!-- End of Notice -->
	<div class="signinbutton">
		<form method="post" action="selectShoe">
		<input type="hidden" name="wosid" value="M3ygDhvCeASZIs79TV79Kw" /></form>
	</div>
</section>

<!-- Footer -->

<footer id="lgoinFooter" class="body">
	<section class="Copyright">
		Arun Inc.
	</section>
	
</footer>

<!-- End Footer -->


</body>
</html>