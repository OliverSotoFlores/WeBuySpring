function showAddModal(){

    // Get modal
    var modal = document.getElementById("addModal");

    // When the user clicks the button, open the modal
    modal.style.display = "block";

    // Get button that opens the modal
    var btn = document.getElementById("p-add");
    // Get <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0]; // Get the first tag with a close class

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal){
            modal.style.display = "none";
        }
    }

}

function showEditModal(){

    // Get modal
    var modal = document.getElementById("editModal");

    // When the user clicks the button, open the modal
    modal.style.display = "block";

    // Get button that opens the modal
    var btn = document.getElementById("p-add");
    // Get <span> element that closes the modal
    var span = document.getElementsByClassName("close")[1]; // Get the second tag with a close class

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal){
            modal.style.display = "none";
        }
    }

}
