var SSS = SSS || {};
jQuery(function ($) {
	
	SSS.App = {
		tableLoader: function (url,data,type) {
		var table = $('#table_id').dataTable(
				{
					"bJQueryUI" : false,
					"sPaginationType" : "full_numbers",
					"sAjaxSource" : url,
					"bSort": true,
					"fnServerData": function ( sSource, aoData, fnCallback, oSettings ) {
						oSettings.jqXHR = $.ajax( {
							"dataType": 'json',
							"type": type,
							"url": sSource,
							"data": data,
							"success": fnCallback,
							"error" : function() {
								$(".dataTables_empty").text("Network Error");
							},
							"headers" :{
								"Accept":"application/json",
							},
							"accepts" : "application/json",
							"contentType" : "application/json",
							"async" : true,
							"processData" : false
						} );
						
					},
					
					"fnRowCallback": function( nRow, aData, iDisplayIndex ) {
						 $('.shoeIdClass', nRow).append('<input type="hidden" value="false" class="edit"/><img src="./resources/images/end_assignment@2x.png" title="Remove" alt="deleteAssignment" class="removeShoeIcon deleteIcon hideit">');
					},
					
					"aoColumns" : [
					               {
					            	   "sTitle" : "<div><p>Shoe ID</p></div>",
					            	   "mData" : "shoeId",
					            	   "sClass": "shoeIdClass",
					            	   "sWidth" : "43px"
					               },
					               {
					            	   "sTitle" : "<div><p>Shoe Name</p></div>",
					            	   "mData" : "shoeName",
					            	   "sClass": "shoeNameClass",
					            	   "sWidth" : "83px"
					               },
					               {
					            	   "sTitle" : "<div><p>Brand Name</p></div>",
					            	   "mData" : "brandName",
					            	   "sClass": "brandNameClass",
					            	   "sWidth" : "83px"
					               },
					               {
					            	   "sTitle" : "<div><p>Price</p></div>",
					            	   "mData" : "price",
					            	   "sClass": "priceClass",
					            	   "sWidth" : "83px"
					               }
					               ],
					               "bDestroy" : true,
					               "bAutoWidth" : false,
					               "aaSorting" : [ [ 1, "desc" ] ]
					               
							
					            	  
			 });
		
		$('.table-container').removeClass('hideit');
		$(".table-container").show();

		},
		
		
		postData : function (url, data) {
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
		},
		
		 getData : function(url, params) {
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
		}
	
	}
	
	SSS.Run = {
			init: function () {
				this.cacheElements();
			    this.bindEvents();
			},
			
			cacheElements: function () {
	            this.shoeListTemplate = Handlebars.compile($("#shoe-list").html());
	            this.userDetailsTemplate = Handlebars.compile($("#userDetails").html());
	        },
				
			bindEvents: function () {
				$('body')
					.on('change','#selectReport', $.proxy(this.selectReport, this))
					.on('click', '#refresh-report', $.proxy(this.generateReport, this))
					.on('click', '#signup', $.proxy(this.getRegisterPage, this))
					.on('click', '#registerUser', $.proxy(this.registerUser, this))
					.on('click', '#getAllShoes', $.proxy(this.getAllShoes, this))
					.on('click', '#getShoesByBrand', $.proxy(this.getShoesByBrand, this))
					.on('click', '#addShoe', $.proxy(this.addShoe, this))
					.on('click', '#editShoeDetails', $.proxy(this.editShoeDetails, this))
					.on('click', '#updateShoeDetails', $.proxy(this.updateShoeDetails, this))
					.on('click', '#getShoesByBrandAndPrice', $.proxy(this.getShoesByBrandAndPrice, this))
					.on('click', '#getShoesByBrandOrPrice', $.proxy(this.getShoesByBrandOrPrice, this))
					.on('click', '#getShoeProjections', $.proxy(this.getShoeProjections, this))
					.on('click', '#getUserDetails', $.proxy(this.getUserDetails, this))
					.on('change', '#table_id tbody tr td.shoeNameClass input', $.proxy(this.rowEdited, this))
					.on('change', '#table_id tbody tr td.brandNameClass select', $.proxy(this.rowEdited, this))
					.on('change', '#table_id tbody tr td.priceClass input', $.proxy(this.rowEdited, this))
					.on('click', '#table_id tbody tr td.shoeIdClass .removeShoeIcon', $.proxy(this.rowDeleted, this))
					
					
			},
			
			selectReport: function () {
				
				if ($("#selectReport option:selected").val() === "getShoesByBrand") {
					$('#divBrand').removeClass('hideit');
					$('#divPrice').addClass('hideit');
					$('.table-container').addClass('hideit');
					$("#aggregatesView").hide();
				} else if ($("#selectReport option:selected").val() === "getShoesByBrandAndPrice" || $("#selectReport option:selected").val() === "getShoesByBrandOrPrice") {
					$('#divBrand').removeClass('hideit');
					$('#divPrice').removeClass('hideit');
					$('.table-container').addClass('hideit');
					$("#aggregatesView").hide();
				} else if ($("#selectReport option:selected").val() === "getAllShoes" || $("#selectReport option:selected").val() === "getShoeAggregates") {
					$('#divBrand').addClass('hideit');
					$('#divPrice').addClass('hideit');
					$('.table-container').addClass('hideit');
					$("#aggregatesView").hide();
				} 	
			},
			
			generateReport: function () {
				if ($("#selectReport option:selected").val() === "getAllShoes") {
					this.getAllShoes();
				} else if ($("#selectReport option:selected").val() === "getShoesByBrand") {
					this.getShoesByBrand();
				} else if ($("#selectReport option:selected").val() === "getShoesByBrandAndPrice") {
					this.getShoesByBrandAndPrice();
				} else if ($("#selectReport option:selected").val() === "getShoesByBrandOrPrice") {
					this.getShoesByBrandOrPrice();
				} else if ($("#selectReport option:selected").val() === "getShoeAggregates") {
					this.getShoeAggregates();
				}
			},
			
			registerUser: function () {
				
				var registerUser = {
						'userId'	: $('#userId').val(),
						'password' 	: $('#password').val(),
						'firstName'	: $('#firstName').val(),
						'lastName' 	: $('#lastName').val(),
						'role'  	: $('#userRole').val(),
						'city' 		: $('#city').val(),
						'state' 	: $('#state').val(),
						'country' 	: $('#country').val(),
						'zipCode' 	: $('#zipCode').val(),
						'phoneOne' 	: $('#phone1').val(),
						'phoneTwo' 	: $('#phone2').val(),
						'emailOne' 	: $('#email1').val(),
						'emailTwo' 	: $('#email2').val()
					   };
		
				var	registerUserResponse = SSS.App.postData(SSS.Url.registerUser,registerUser);
				registerUserResponse.success(function (data) {
					if(data.statusMessage == "Success") {
						alert("Registration Successful");
					}
					window.location = "./login"
				});
			},
			
			getAllShoes: function () {
				type = "GET";
				data = {};
				SSS.App.tableLoader(SSS.Url.getAllShoes,data,type);
			},
			
			getRegisterPage: function () {
				window.location = "./addUser";
			},
			
			getShoesByBrand: function () {
				type = "GET";
				data = {};
				url = SSS.Url.getShoesByBrand + "?brandName="+ $('#brandName').val();
				SSS.App.tableLoader(url,data,type);
			},
			
			addShoe: function () {
				var addShoe = {
						'shoeName'	: $('#shoeName').val(),
						'brandName' : $('#brandName').val(),
						'price' 	: $('#price').val()
					   };
		
				var	addShoeResponse = SSS.App.postData(SSS.Url.addShoe,addShoe);
	
				addShoeResponse.success(function (data) {
					if(data.statusMessage == "Success") {
						alert("Shoe Added Successfully");
					}
					window.location = "./selectShoe"
				});
				
			},
			
			editShoeDetails: function () {
				$('.removeShoeIcon').removeClass('hideit');
				$('#table_id tbody tr td.brandNameClass').each(function () {
					 var oldBrandName = $(this).text();
					 $(this).empty();
					 $(this).append('<select id="brandName"><option value="0" selected>'+oldBrandName+'</option><option value="Nike">Nike</option><option value="Asics">Asics</option></select>');
				 });
				  $('#table_id tbody tr td.shoeNameClass').each(function () {
					 var oldShoeName = $(this).text();
					 $(this).empty();
					 $(this).append('<input type="text" value="'+oldShoeName+'"></input>');
				 });
				 $('#table_id tbody tr td.priceClass').each(function () {
					 var oldPrice = $(this).text();
					 $(this).empty();
					 $(this).append('<input type="text" value="'+oldPrice+'"></input>');
					});
			},
			
			updateShoeDetails: function () {
				type = "POST";
				data = {};
				var saveAssignmentArray = [];
				 $('#table_id tbody tr').each(function () {
					 var isEdited = $(this).find('td.shoeIdClass .edit').val();
					 if (isEdited === 'true') {
						 saveRow = {
								 'shoeId': $(this).find('td.shoeIdClass').text(),
								 'shoeName': $(this).find('td.shoeNameClass input').val(),
								 'brandName': $(this).find('td.brandNameClass select option:selected').text(),
								 'price': $(this).find('td.priceClass input').val()
								 };
						 saveAssignmentArray.push(saveRow);
					 }
				 });
				 var updateShoeResponse = SSS.App.postData(SSS.Url.updateShoes,saveAssignmentArray);
				 
				 updateShoeResponse.success(function (data) {
						if(data.statusMessage == "Success") {
						alert("Shoe Updated Successfully");
						}
						SSS.App.tableLoader(SSS.Url.getAllShoes,data,"GET");
				});
			},
			
			getShoesByBrandAndPrice: function () {
				type = "GET";
				data = {};
				url = SSS.Url.getShoesByBrandAndPrice + "?brandName=" + $('#brandName').val() + "&lowerPrice=" + $('#lowerPrice').val() + "&upperPrice=" + $('#upperPrice').val();
				SSS.App.tableLoader(url,data,type);
			},
			
			getShoesByBrandOrPrice: function () {
				type = "GET";
				data = {};
				url = SSS.Url.getShoesByBrandOrPrice + "?brandName=" + $('#brandName').val() + "&lowerPrice=" + $('#lowerPrice').val() + "&upperPrice=" + $('#upperPrice').val();
				SSS.App.tableLoader(url,data,type);
			},
			
			getShoeProjections: function () {
				$("#aggregatesView").hide();
				var self = this;
				var shoeList = SSS.App.postData(SSS.Url.getShoeProjections,{});
				shoeList.success(function (data) {
					console.log(data);
//					$("#shoesView").html(self.shoeListTemplate(data));
//					$("#shoesView").show();
				});
			},
			
			getUserDetails: function () {
				var self = this;
				var url = "./register/fetchUser?userId=" + $('#userId').val();
				var userDetails = SSS.App.getData(url,{});
				userDetails.success(function (data) {
					console.log(data);
					$("#userDetailsView").html(self.userDetailsTemplate(data));
					$("#userDetailsView").show();
				});
			},
			
			rowEdited: function (evt) {
				 var $el = $(evt.target).closest('tr');
		         $el.find('td.shoeIdClass .edit').val("true");
		         $el.find('td').addClass('rowHighlight');
			},
			
			rowDeleted: function (evt) {
				 var $el = $(evt.target).closest('tr');
				 
		         $el.find('td.shoeIdClass .edit').val("true");
		         $el.find('td').addClass('rowHighlight');
		         deleteRow = {
						 'shoeId': $el.find('td.shoeIdClass').text(),
						 'shoeName': $el.find('td.shoeNameClass input').val(),
						 'brandName': $el.find('td.brandNameClass select option:selected').text(),
						 'price': $el.find('td.priceClass input').val()
						 };
		         
		         var deleteShoeResponse = SSS.App.postData(SSS.Url.deleteShoes,deleteRow);
					deleteShoeResponse.success(function (data) {
							if(data.statusMessage == "Success") {
							alert("Shoe Deleted Successfully");
							}
							SSS.App.tableLoader(SSS.Url.getAllShoes,data,"GET");
					});
		         
			}
		
		}
	    	
});

