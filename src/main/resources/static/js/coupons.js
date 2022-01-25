$("#createCouponForm").submit(function(e){
	e.preventDefault();
	let data = $(this).serialize();
	$.ajax({
		url: 'createCoupon',
        data: data,
        contentType: 'application/json; charset=utf-8',
        success: function (data, textStatus, xhr) {
			location.reload();
        },
        error: function (data, textStatus, xhr) {
			$("#addCouponModal").modal("hide");
            showError();
        }
	});
});

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
            showNoData();
        }
	});
});

$(".editCoupon").click(function(){
	let id = $(this).data("id");
	$.ajax({
		url: 'couponSearch',
        data: {id:id},
        contentType: 'application/json; charset=utf-8',
        success: function (data, textStatus, xhr) {
			$("#couponIdE").val(data.coupon_id);
			$("#couponNameE").val(data.coupon_name);
			$("#couponDiscountE").val(data.coupon_discount);
			$("#couponPromotionE").val(data.promotion_event_id);
			$("#couponCategoryE").val(data.product_category_id);
			$("#couponTypeE").val(data.coupon_type);
            showEditCouponModal();
        }
    });
});

function showAddCoupon(){
	$("#addCouponModal").modal("show");
}

function showCouponDataModal(){
	$("#couponDataModal").modal("show");
}

function showEditCouponModal(){
	$("#editCouponModal").modal("show");
}

function showNoData(){
	$("#modalNoCouponData").modal("show");
}

function showError(){
	$("#modalError").modal("show");
}