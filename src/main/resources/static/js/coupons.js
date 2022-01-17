$("#couponSearchForm").submit(function(e){
	e.preventDefault();
	let id = $("#searchInput").val();
	$.ajax({
		url: 'couponSearch',
        data: {id:id},
        contentType: 'application/json; charset=utf-8',
        success: function (data, textStatus, xhr) {
			$("#couponNameS").val(data.coupon_name);
			$("#couponDiscountS").val(data.coupon_discount + "%");
			$("#couponPromotionS").val(data.promotion_event_name);
			$("#couponCategoryS").val(data.category_name);
			$("#couponTypeS").val(data.coupon_type);
            showCouponDataModal();
        },
        error: function (data, textStatus, xhr) {
            alert("No data found");
        }
	});
});