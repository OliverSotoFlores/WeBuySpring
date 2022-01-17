$("#promotionEventSearchForm").submit(function(e){
	e.preventDefault();
	let id = $("#searchInput").val();
	$.ajax({
		url: 'searchPromotionEvent',
		data: {id:id},
		contentType: 'application/json; charset="utf-8"',
		success: function (data, textStatus, xhr) {
			$("#promotionEventNameS").val(data.promotion_event_name);
			$("#promotionEventDescS").val(data.promotion_event_description);
			$("#promotionEventStartDateS").val(data.promotion_event_start_date);
			$("#promotionEventEndDateS").val(data.promotion_event_end_date);
			$("#promotionEventStatusS").val(data.promotion_event_status);
			
			showPromotionEventDataModal();
		},
		error: function (data, textStatus, xhr) {
			alert("No data found");
		}
	});
});