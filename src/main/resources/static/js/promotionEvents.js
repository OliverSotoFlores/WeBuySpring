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

function addPromotionEvent(){
	$("#addPromotionModal").modal("show");
}

function editPromotion(e){
	var editButton = e.target;

  // Get its children's hidden input values
  var elementId = editButton.childNodes[1].value;
  var elementAdminId = editButton.childNodes[3].value;

  // Insert hidden values into edit modal form
  document.getElementById("edit-element-id").value = elementId;
  document.getElementById("edit-admin-id").value = elementAdminId;

  // Get edit button's parent tags
  let prevTableElement = editButton.parentNode;
  let prevParent = prevTableElement.parentNode;

  // Get edit button's sibling HTML values
  let promoName = prevParent.childNodes[1].innerHTML;
  let promoDesc = prevParent.childNodes[3].innerHTML;
  let promoStartDate = prevParent.childNodes[4].innerHTML;
  let promoEndDate = prevParent.childNodes[6].innerHTML;
  let promoStatus = prevParent.childNodes[8].innerHTML;

  // Insert values from the list into the modal's inputs
  document.getElementById("promo-name").value = promoName;
  document.getElementById("promo-desc").value = promoDesc;
  document.getElementById("promo-start-date").value = promoStartDate;
  document.getElementById("promo-end-date").value = promoEndDate;

  // Get select tag
  let statusList = document.getElementById("status-dropdown");

  // Add selected attribute to according Promotion Event Status option tag inside the select tag.
  Array.from(statusList.options).forEach(function (option_element) {
    // Add selected attribute if promoStatus value is the same as the option
    if (promoStatus == option_element.innerHTML) {
      option_element.selected = true;
    }
  });
  
  $("#editPromotionModal").modal("show");
}