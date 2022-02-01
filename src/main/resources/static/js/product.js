$(".product-item").click(function(){
	let id = $(this).data("id");
	let categoryId = $(this).data("idcategory");
	let $sR = $("#promotionCodeSelect").empty();
	$.ajax({
		url: 'productdetails',
        data: {id:id},
        contentType: 'application/json; charset=utf-8',
        success: function (data, textStatus, xhr) {
			$.each(data, function(index, item){
				$("#productID").val(item.id);
				$("#imgDetails").prop("src", item.imagePath);
				$("#headerModalDetails").text(item.name);
				$("#headerModalCompany").text(item.company);
				$("#modalCompany").text(item.company);
				$("#priceDetails").text(item.price);
				$("#descriptionDetails").text(item.description);
				setTotal(item.price);
			});
			$.ajax({
				url: 'availablecoupons',
		        data: {id:categoryId},
		        contentType: 'application/json; charset=utf-8',
		        success: function (data, textStatus, xhr) {
					$.each(data, function (index, value){
						if(index == 0){
							$sR.append($("<option></option>").attr({"value":0,
																"data-discount":0}).text("No promotion"));
						}
						$sR.append($("<option></option>").attr({"value":value.coupon_id,
																"data-discount":value.coupon_discount}).text(value.coupon_name + " - " + value.coupon_discount + "%"));
					});
		        },error: function (data, textStatus, xhr) {
					$sR.append($("<option></option>").attr({"value":0,
															"data-discount":0}).text("No promotions available"));
				}
			});
            $("#modalAddCart").css("display", "flex");
        },
	});
	
	
});

$('.minus').click(function () {
	var $input = $(this).parent().find('input');
	var count = parseInt($input.val()) - 1;
	count = count < 1 ? 1 : count;
	$input.val(count);
	multiplyQuantity(count);
	$input.change();
	return false;
});
$('.plus').click(function () {
	var $input = $(this).parent().find('input');
	var newVal = parseInt($input.val()) + 1;
	$input.val(newVal);
	multiplyQuantity(newVal)
	$input.change();
	return false;
});

$("#promotionCodeSelect").change(function(){
	let option = $('option:selected', this).data('discount');
	$("#quantity").val(1);
	if(option>0){
		$("#priceDetails").addClass("line-through");
		let originalPrice = $("#priceDetails").text();
		let newPrice = originalPrice-((option / 100)*originalPrice);
		$("#newPriceDetails").text(newPrice);
		setTotal(newPrice);
		$("#arrowPrice").prop("hidden",false);
		$("#newPriceDetails").prop("hidden",false);
	}else{
		setTotal($("#priceDetails").text());
		removeLineThrough();
		eraseNewPrice();
	}
});

$("#btnAddCart").click(function(){
	
	let id = $("#productID").val();
	let q = $("#quantity").val();
	let oPrice = $("#priceDetails").text();
	let fPrice = $("#totalPriceDetails").text();
	
	$("#formID").val(id);
	$("#formQuantity").val(q);
	$("#formOriginal").val(oPrice);
	$("#formFinal").val(fPrice);
	$("#formAddProduct").submit();
});

$("#modalAddCart").on('hidden.bs.modal',function(){
	removeLineThrough();
	eraseNewPrice();
});

$(".close").click(function() {
	removeLineThrough();
	eraseNewPrice();
	$("#modalAddCart").css("display", "none");
});

function eraseNewPrice(){
	$("#newPriceDetails").text("");
	$("#arrowPrice").prop("hidden",true);
	$("#newPriceDetails").prop("hidden",true);
}
function removeLineThrough(){
	if($("#priceDetails").hasClass("line-through")){
		$("priceSign").removeClass("line-through");
		$("#priceDetails").removeClass("line-through");
	}
}
function multiplyQuantity(q){
	if(! $("#priceDetails").hasClass("line-through")){
		setTotal($("#priceDetails").text()*q);
	}else{
		setTotal($("#newPriceDetails").text()*q);
	}
	
}

function setTotal(total){
	$("#totalPriceDetails").text(total);
}

var products = document.querySelectorAll('.product-image');
var productBg = document.querySelector('.productBackground');

let x = window.matchMedia("(max-width: 1000px)");

function changeHeight(){
	if(x.matches) {
		let productHeight = products[0].offsetHeight;
		productBg.style.height = `${productHeight * 0.9}px`;
	}
	else {
		productBg.style.height = "475px";
	}
}

changeHeight();

window.addEventListener('resize', changeHeight);