function showNAddModal(){
	$("#addProductModal").modal("show");
}

function editNModalAdminProduct(t, id, name, description, price, company, category){
	$("#id").val(id);
	$("#e-name").val(name);
	$("#e-category").val(category);
	$("#e-company").val(company);
	$("#e-price").val(price);
	$("#e-description").val(description);
	$("#editProductModal").modal("show");
	
}