$(document).ready(() => {
	let formBook = $('#submitFormBook');
	formBook.submit((e) => {
		e.preventDefault();
		let formData = new FormData(document.getElementById('submitFormBook'));
		let context = window.location.pathname;
		context = context.substring(0, context.indexOf("/", 2));
		$.ajax({
			url : context + '/admin/view',
			type : 'post',
			data : formData,
			processData: false, //form is object -> not atempt convert form to query string
			contentType: false, // form is multipart/form-data -> if true -> ajax atempt convert to json, x-www-form-urlencoded
			success : function(response){
				let msg = response.msg;
				if(msg == 'addbookerror'){
					swal({title : 'Title or author is exists!', icon : 'warning'});
		    	} else if(msg == 'invalidTitle'){
					swal({title : 'Invalid title!', icon : 'warning'});
		    	} else if(msg == 'invalidAuthor'){
					swal({title : 'Invalid author!', icon : 'warning'});
		    	} else if(msg == 'invalidReleaseDate'){
					swal({title : 'Invalid release date!', icon : 'warning'});
		    	} else{
					swal({
						title : msg + ' book successfully', 
						icon : 'success',
						buttons : {
							cancel : "Cancel",
							home : {
								text : 'Home', value : 'home'
							}						
						}
					}).then((value) => {
						switch(value){
							case 'home':
								window.location.href = context + '/admin/home'
								break;
							default : break;
						}
					});
				}
			},
			error : function(err){
				console.log(err)
			}
		})
	});
} );
