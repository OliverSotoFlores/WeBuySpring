function showAddModal() {
  // Get modal
  var modal = document.getElementById("addModal");

  // When the user clicks the button, open the modal
  modal.style.display = "block";

  // Get button that opens the modal
  var btn = document.getElementById("p-add");
  // Get <span> element that closes the modal
  var span = document.getElementsByClassName("close")[0]; // Get the first tag with a close class

  // When the user clicks on <span> (x), close the modal
  span.onclick = function () {
    modal.style.display = "none";
  };

  // When the user clicks anywhere outside of the modal, close it
  window.onclick = function (event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  };
}

function editModalAdminProduct(t, id, name, description, price, company) {
  let modal = document.getElementById("editModal");

  modal.style.display = "block";

  let span = document.getElementsByClassName("close")[1];

  span.onclick = function () {
    modal.style.display = "none";
  };
  window.onclick = function (event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  };

  modal.querySelector("#name").value = name;
  modal.querySelector("#price").value = price;
  modal.querySelector("#description").value = description;
  modal.querySelector("#company").value = company;
  modal.querySelector("#id").value = +id;
}

function showAddModalAdminProducts() {
  let modal = document.getElementById("addModal");

  modal.style.display = "block";

  let span = document.getElementsByClassName("close")[0];

  span.onclick = function () {
    modal.style.display = "none";
  };
  window.onclick = function (event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  };
}

function showEditModal(e) {
  // Get clicked edit button
  var editButton = e.target;

  // Get its children's hidden input values
  var elementId = editButton.childNodes[1].value;
  var elementName = editButton.childNodes[3].value;

  // Insert hidden values into edit modal form
  document.getElementById("edit-element-id").value = elementId;
  document.getElementById("c-name").value = elementName;

  // Get modal
  var modal = document.getElementById("editModal");

  // When the user clicks the button, open the modal
  modal.style.display = "block";

  // Get button that opens the modal
  var btn = document.getElementById("p-add");
  // Get <span> element that closes the modal
  var span = document.getElementsByClassName("close")[1]; // Get the second tag with a close class

  // When the user clicks on <span> (x), close the modal
  span.onclick = function () {
    modal.style.display = "none";
  };

  // When the user clicks anywhere outside of the modal, close it
  window.onclick = function (event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  };
}

function showEditModalPromotion(e) {
  // Get clicked edit button
  var editButton = e.target;
  alert("A");
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

  // Get modal
  var modal = document.getElementById("editModal");

  // When the user clicks the button, open the modal
  modal.style.display = "block";

  // Get button that opens the modal
  var btn = document.getElementById("p-add");
  // Get <span> element that closes the modal
  var span = document.getElementsByClassName("close")[1]; // Get the second tag with a close class

  // When the user clicks on <span> (x), close the modal
  span.onclick = function () {
    modal.style.display = "none";
  };

  // When the user clicks anywhere outside of the modal, close it
  window.onclick = function (event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  };
}

function showCouponDataModal() {
  // Get modal
  var modal = document.getElementById("couponDataModal");

  // When the user clicks the button, open the modal
  modal.style.display = "block";
  // Get <span> element that closes the modal
  var span = document.getElementsByClassName("close")[2]; // Get the second tag with a close class

  // When the user clicks on <span> (x), close the modal
  span.onclick = function () {
    modal.style.display = "none";
  };

  // When the user clicks anywhere outside of the modal, close it
  window.onclick = function (event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  };
}
