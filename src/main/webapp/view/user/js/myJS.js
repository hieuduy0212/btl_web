function addToCart(bookid, quantity){
	let context = window.location.pathname
	context = context.substring(0, context.indexOf("/", 2))
	
	if(quantity == '-1'){
		quantity = document.getElementById('quantity').value;
		if(quantity == 0){
			return;
		}
	}
	$.ajax({
		url : context + "/user/addToCart",
		type : 'get',
		data : {
			bid : bookid ,
			qty : quantity
		},
		success : function(response){
			if(response.redirect){
				swal({
					text : 'You need to login to add this to cart!',
					icon : 'warning',
					buttons : true,
					dangerMode: true,
				}).then((ok) => {
					if(ok){
						window.location.href = response.redirect	
					}
				});			
			}
			document.getElementById('sizecart').textContent = response.sizecart
		}, 
		error : function (err){
			console.log(err)
		}
	});
}


function deleteItem(bookid){
	if(confirm("Are you sure you want to remove this book from your cart?")){
		let context = window.location.pathname
		context = context.substring(0, context.indexOf("/", 2))
		$.ajax({
			url : context + "/user/deteleItemInCart",
			type : 'post',
			data : {
				bid : bookid
			},
			success : function(response){
				let row = document.getElementById(bookid);
				row.remove();				
				document.getElementById('sizecart').textContent = response.sizecart
			}, 
			error : function(err){
				console.log(err)
			}
			
		});
	}
}

function updateCart(bookid){
	let row = document.getElementById(bookid);
	let td2 = row.getElementsByTagName('td')[2];
	let quantity = td2.querySelector('input').value

	if(quantity == '0'){
		deleteItem(bookid)
	}else{
		let context = window.location.pathname
		context = context.substring(0, context.indexOf("/", 2))
		
		$.ajax({
			url : context + "/user/updateCart",
			type : 'post',
			data : {
				bid : bookid,
				qty : quantity
			},
			success : function (){},
			error : function(err){
				console.log(err)
			}
		});
	}	
}

function checkout(){
	swal({
		text : 'Are you sure to order?',
		icon : 'warning',
		buttons : true,
		dangerMode: true,
	}).then((ok) => {
		if(ok){
			let context = window.location.pathname
			context = context.substring(0, context.indexOf("/", 2))
			
			let receiver = document.getElementById('receiver').value;
			let telReceiver = document.getElementById('telReceiver').value;
			let addressReceiver = document.getElementById('addressReceiver').value;
			let emailReceiver = document.getElementById('emailReceiver').value;
			let zipcode = document.getElementById('zipcode').value;
			$.ajax({
				async : false,
				url : context + '/user/checkout',
				type : 'post',
				data : {
					receiver : receiver,
					telReceiver : telReceiver,
					addressReceiver : addressReceiver,
					emailReceiver : emailReceiver,
					zipcode : zipcode
				},
				success : function(response){
					if(response.msg == 'successNewBill'){
						swal({
							text : "Create new bill successfully. Please continue to shop",
							icon : 'success',
							buttons : true,
						}).then((ok) => {
							if(ok){
								if(response.redirect){
									window.location.href = response.redirect;
								}	
							}
						});
					} else if(response.msg == 'invalidReceiver'){
						swal("Invalid receiver name");
					} else if(response.msg == 'invalidTelReceiver'){
						swal("Phone number has 10 digits, no spaces");
					} else if(response.msg == 'invalidAddressReceiver'){
						swal("Invalid address");
					} else if(response.msg == 'invalidEmailReceiver'){
						swal("Invalid email");
					}else if(response.msg == 'invalidZipcode'){
						swal("Zipcode has 6 digits, no spaces");
					}
				},
				error : function(err){
					console.log(err)
				}
			});
		}
	});	
}

function showBill(billid){
	let context = window.location.pathname
	context = context.substring(0, context.indexOf("/", 2))
	
	$.ajax({
		url : context + '/user/bills',
		type : 'post',
		data : {
			billid : billid,
			action : 'post'
		},
		success : function(billdetail){
			document.getElementById('content').innerHTML = billdetail
		},
		error : function(error){
			console.log(error)
		}
	});
}

function cancelBill(billid){
	if(confirm('Are you sure to cancel this bill?')){
		let context = window.location.pathname
		context = context.substring(0, context.indexOf("/", 2))
		
		$.ajax({
			url : context + "/user/bills",
			type : 'post',
			data : {
				billid : billid,
				action : 'delete'
			},
			success : function(response){
				if(response.redirect){
					window.location.href =response.redirect 
				}
			},
			error : function(loi){
				console.log(loi)
			}
		})
	}
}

function loadMoreReview(bookid){
	let exists = document.querySelectorAll('.review').length;
	
	let context = window.location.pathname
	context = context.substring(0, context.indexOf("/", 2))
	
	$.ajax({
		url : context + "/loadmorereview",
		type : 'post',
		data : {
			bookid : bookid,
			exists : exists
		},
		success : function(data){
			let content = ``;
			if(data.length != 0){
				$.each(data, function(index, element){
					content = `<div class="media mb-4 review">
                                    <div class="media-body row">
                                        <h6 class="col-8">${element.account.fullname}<small> - <i>${element.created }</i></small></h6> 
                                        <div class="text-primary mb-2 copl-4">
                                        `; 
                                        
                	for(let i = 1; i <= 5 ; i++){
						if(i <= element.star){
							content += `<i class="bx bxs-star"></i>
							`
						}else{
							if(i - element.star == 0.5){
								content += `<i class="bx bxs-star-half"></i>
								`
							}else{
								content += `<i class="bx bx-star"></i>
								`
							}
						}
					}
                                        	
				    content += `</div>
                            <p class="col-12">${element.content}</p>
                        </div>
                    </div>
                    <hr width="100%" size="5px">`;
					
					$('#reviews').append(content); 
					content = ``
				});
			}else{
				$('#btnmore').text("Seen all").attr("disabled", true);
			}
		}
	})
}

function buyNow(bookid){
	let quantity = document.getElementById('quantity').value;
	if(quantity > 0){
		let context = window.location.pathname
		context = context.substring(0, context.indexOf("/", 2))	
		$.ajax({
			url : context + "/user/buynow",
			type : 'post',
			data : {
				bid : bookid,
				qty : quantity
			},
			success : function(resp){
				if(resp.redirect){
					if(confirm("You need to be logged in to order")){
						window.location.href = resp.redirect
					}
				}
				if(resp.redirectCheckout){
					window.location.href = resp.redirectCheckout
				}
			},
			error : function (err){
				console.log(err)
			}			
		});
	}else{
		alert('Please select the quantity to buy')
	}
}
