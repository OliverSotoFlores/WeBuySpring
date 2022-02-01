/*
---------------------------------------NOTES-----------------------------------
.closest() looks for parent class
.find() looks for children class
.closest().find() used to locate somewhat like a brother class, at same level


LOGIC EXAMPLE:
<div class="father"> 
	<div class="center-div">
		...
	</div>
	<div class="right-div">
		...
	</div>
</div>

center-div is like a brother of right-div, so in order to find clases inside right-div when a class inside
center-div is clicked, we need to go to parent class with .closest(".father") and then go to it's child with
.find(".right-div")
*/
var clickedID;
var oPrice;
var nPrice;
var nQuantity;

$('.minus').click(function () {
	if(clickedID == null){
		$(this).closest('.module').find('.right-div').find('.saveChanges').prop("hidden", false);
		$(this).closest('.module').find('.right-div').find('.cancelChanges').prop("hidden", false);
		$(this).closest('.module').find('.right-div').find('.deleteProduct').prop("hidden", true);
		clickedID = $(this).closest(".module-border-wrap").data("detid");
		var cost = $(this).closest(".module-border-wrap").data("cost");
		var quantity = $(this).parent().find('input').val();
		oPrice = cost/quantity;
		substracion($(this).parent().find('input'));
		updatePrice($(this).closest('.module').find('.right-div').find('.oPrice'), 
					($(this).parent().find('input').val()*oPrice)
					);
	}else{
		if(clickedID == $(this).closest(".module-border-wrap").data("detid")){
			$(this).closest('.module').find('.right-div').find('.saveChanges').prop("hidden", false);
			$(this).closest('.module').find('.right-div').find('.cancelChanges').prop("hidden", false);
			$(this).closest('.module').find('.right-div').find('.deleteProduct').prop("hidden", true);
			substracion($(this).parent().find('input'));
			updatePrice($(this).closest('.module').find('.right-div').find('.oPrice'), 
						($(this).parent().find('input').val()*oPrice)
						);
		}else{
			alert("Please save the changes before updating a new item.");
		}
	} 
	
});
$('.plus').click(function () {
	if(clickedID == null){
		$(this).closest('.module').find('.right-div').find('.saveChanges').prop("hidden", false);
		$(this).closest('.module').find('.right-div').find('.cancelChanges').prop("hidden", false);
		$(this).closest('.module').find('.right-div').find('.deleteProduct').prop("hidden", true);
		clickedID = $(this).closest(".module-border-wrap").data("detid");
		var cost = $(this).closest(".module-border-wrap").data("cost");
		var quantity = $(this).parent().find('input').val();
		oPrice = cost/quantity;
		addition($(this).parent().find('input'));
		updatePrice($(this).closest('.module').find('.right-div').find('.oPrice'), 
									($(this).parent().find('input').val()*oPrice)
									);
	}else{
		if(clickedID == $(this).closest(".module-border-wrap").data("detid")){
			$(this).closest('.module').find('.right-div').find('.saveChanges').prop("hidden", false);
			$(this).closest('.module').find('.right-div').find('.cancelChanges').prop("hidden", false);
			$(this).closest('.module').find('.right-div').find('.deleteProduct').prop("hidden", true);
			addition($(this).parent().find('input'));
			updatePrice($(this).closest('.module').find('.right-div').find('.oPrice'), 
						($(this).parent().find('input').val()*oPrice)
						);
		}else{
			alert("Please save the changes before updating a new item.");
		}
	} 
});

$('.saveChanges').click(function(){
	$(this).prop("hidden", true);
	$(this).closest('.right-div').find('.deleteProduct').prop("hidden", false);
	$(this).closest('.right-div').find('.cancelChanges').prop("hidden", true);
	$("#idProductDetail").val(clickedID);
	$("#quantityProductDetail").val(nQuantity);
	$("#priceProductDetail").val(nPrice);
	clickedID = null;
	$("#update").submit();
});

$(".cancelChanges").click(function(){
	var cost = $(this).closest(".module-border-wrap").data("cost");
	var quantity = $(this).closest(".module-border-wrap").data("quantity");
	$(this).closest(".module").find('.quantity').val(quantity);
	$(this).closest(".right-div").find('.oPrice').text("Price: $" + cost);
	$(this).prop("hidden", true);
	$(this).closest('.right-div').find('.deleteProduct').prop("hidden", false);
	$(this).closest('.right-div').find('.saveChanges').prop("hidden", true);
});

$(".deleteProduct").click(function(){
	$("#idProductDetailDelete").val($(this).closest(".module-border-wrap").data("detid"));
	$("#delete").submit();
});


function updatePrice(clicked, price){
	nPrice = price;
	clicked.text("Price: $" + price);
}

function substracion(input){
	var $input = input;
	var count = parseInt($input.val()) - 1;
	count = count < 1 ? 1 : count;
	$input.val(count);
	setNewQuantity(count);
	$input.change();
	return false;
}

function addition(input){
	var $input = input;
	var newVal = parseInt($input.val()) + 1;
	$input.val(newVal);
	setNewQuantity(newVal);
	$input.change();
	return false;
}

function setNewQuantity(q){
	nQuantity=q;
}
