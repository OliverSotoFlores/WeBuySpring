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
				$("#priceDetails").text(item.price);
				$("#descriptionDetails").text(item.description);
			});
			$.ajax({
				url: 'availablecoupons',
		        data: {id:categoryId},
		        contentType: 'application/json; charset=utf-8',
		        success: function (data, textStatus, xhr) {
					$.each(data, function (index, value){
						$sR.append($("<option></option>").attr("value", value.coupon_id).text(value.coupon_name + " - " + value.coupon_discount + "%"));
					});
		        },error: function (data, textStatus, xhr) {
					$sR.append($("<option></option>").attr("value", 0).text("No coupons available"));
				}
			});
            $("#modalAddCart").modal("show");
        },
	});
	
	
});

$('.minus').click(function () {
	var $input = $(this).parent().find('input');
	var count = parseInt($input.val()) - 1;
	count = count < 1 ? 1 : count;
	$input.val(count);
	$input.change();
	return false;
});
$('.plus').click(function () {
	var $input = $(this).parent().find('input');
	$input.val(parseInt($input.val()) + 1);
	$input.change();
	return false;
});