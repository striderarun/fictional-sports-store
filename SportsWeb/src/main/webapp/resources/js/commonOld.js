//jQuery(function ($) {
//	alert("It works")
//});

jQuery(function ($) {
	
		$("#my").click(function(){
			
			obj = {
					'user' : $('#t1').val(),
					'password' : $('#t2').val()
			};
			
			response = postData("./login",obj );

			
			});
		
		$("#my").click(function(){
			
			obj = {
					'user' : $('#t1').val(),
					'password' : $('#t2').val()
			};
			
			response = postData("./login",obj );

			
			});
			
//		$("#my").click(function(){
//			
//			getData('./login?userName='+ $('#t1').val() + '&password=' + $('#t2').val());
//			var redirect = '${redirectUrl}';
//			console.log(redirect);
//			if (redirect) {
//			    window.location.replace(redirect);
//			}
//			});
		
		$("#my2").click(function(){
			console.log("button2");
			tableLoader();
			
			});
		
	
		function postData(url, data) {
	            console.log('postData', url, JSON.stringify(data));
	           
	            var options = {
	                    url: url,
	                    type: 'POST',
	                    headers: {
	                        'Accept': 'application/json; charset=utf-8',
	                        'Content-Type': 'application/json; charset=UTF-8',
	                        
	                    }
	                };

	                options.data = JSON.stringify(data)
	                return $.ajax(options);
	        };
	        
	        function getData(url, params) {
	            console.log('getData', url, params);
	            data: params || {};
	            var options = {
	                    url: url,
	                    type: 'GET',
	                    data: params,
	                    headers: {
	                        'Accept': 'application/json; charset=utf-8',
	                        'Content-Type': 'application/json; charset=UTF-8',
	                        
	                    }
	                };
	            
	                return $.ajax(options);
	        };

	        
	        function tableLoader() {
	        	
	    		var table = $('#table_id').dataTable(
	    				{
	    					"bJQueryUI" : false,
	    					"sPaginationType" : "full_numbers",
	    					"sAjaxSource" : "/MyProject/fetch",
	    					"fnServerData": function ( sSource, aoData, fnCallback, oSettings ) {
	    						oSettings.jqXHR = $.ajax( {
	    							"dataType": 'json',
	    							"type": "POST",
	    							"url": sSource,
	    							"success": fnCallback,
	    							"error" : function() {
	    								$(".dataTables_empty").text("Network Error");
	    							},
	    							"data":'{"user":"arun","password" : "50"}',
	    							"headers" :{
	    								"Accept":"application/json",
	    							},
	    							"accepts" : "application/json",
	    							"contentType" : "application/json",
	    							"async" : true,
	    							"processData" : false
	    						} );
	    						
	    					},
	    					"aoColumns" : [
	    					               {
	    					            	   "sTitle" : "<div><p>Phrase</p></div>",
	    					            	   "mData" : "phrase",
	    					            	   "sWidth" : "455px"
	    					               },
	    					               {
	    					            	   "sTitle" : "<div><p>Occurrence</p></div>",
	    					            	   "mData" : "count",
	    					            	   "sWidth" : "83px"
	    					               }
	    					               ],
	    					               "bDestroy" : true,
	    					               "bAutoWidth" : false,
	    					               "aaSorting" : [ [ 1, "desc" ] ]
	    					               
	    							
	    					            	  
	    			 });
	    		
	    		$(".table-container").show();

	    		};
	    	
	
	});

<%--
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pager.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/app.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/moment.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/picker.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/chosen.jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/date.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.blockui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.contactlist.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.inputmask.js"></script> 
--%>

.on('click', '.removeShoeIcon', $.proxy(this.removeShoe, this))
