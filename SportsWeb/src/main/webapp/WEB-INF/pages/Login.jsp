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
         <h2> Total number of shoes       : {{rowCount}} </h2>
         <h2> Total Price of all shoes    : {{sum}} </h2>
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
         <h2> First Name	     : {{firstName}} </h2>
         <h2> Last Name			 : {{lastName}} </h2>
         <h2> City				 : {{city}} </h2>
         	<h2> State		     : {{state}} </h2>
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
                     <div id="my_container">
                        <form method="post" class="shoeform" name="shoeForm" action="selectShoe">
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
                     </div>
                  </td>
                  <td width="30%">
                  </td>
               </tr>
            </table>
         </div>
         <section class="notice">
         <p>
         <strong>Notice</strong><br>
         Built using Spring Boot 1.2.7
         <br><br>
         A Simple CRUD App for managing inventory of a fictional Shoe store.
         </p>
         <br><br>
         </section>
         <div class="signinbutton">
         <form method="post" action="selectShoe"></form>
         </div>
      </section>
      <!-- Footer -->
      <footer id="loginFooter" class="body">
         <section class="Copyright">
            Arun
         </section>
      </footer>
   </body>
</html>