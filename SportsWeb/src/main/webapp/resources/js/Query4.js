var SSS = SSS || {};
jQuery(function ($) {
		$( document ).ready(function() {
					type = "GET";
					data = {};
					SSS.App.tableLoader(SSS.Url.getAllShoes,data,type);
					SSS.Run.init();
		});			
});