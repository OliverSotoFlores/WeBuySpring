// Get DOM's elements
var passInput = document.getElementById("pass");
var confirmInput = document.getElementById("confirm-pass");
var expandable = document.getElementById("expandable");
var expandable2 = document.getElementById("expandable2");
var letter = document.getElementById("letter");
var capital = document.getElementById("capital");
var number = document.getElementById("number");
var length = document.getElementById("length");
var passMessage = document.getElementById("same-pass");
var passNotEmpty = document.getElementById("pass-not-empty");

// Check whether both password and confirm password fields match
function matchPassword() {
	if(passInput.value != confirmInput.value) {
		alert("Passwords did not match!")
		return false;
	} else {
		return true
	}
}

// Display password requirements when clicking on password field
passInput.onfocus = function() {
	document.getElementById("req-title").style.display = "block";
	letter.style.display = "block";
	capital.style.display = "block";
	number.style.display = "block";
	length.style.display = "block";
	expandable.classList.remove("not-expanded");
	expandable.classList.add("expanded");
}

// Hide password requirements when clicking outside of the password field
passInput.onblur = function() {
	expandable.classList.remove("expanded");
	expandable.classList.add("not-expanded");
	document.getElementById("req-title").style.display = "none";
	letter.style.display = "none";
	capital.style.display = "none";
	number.style.display = "none";
	length.style.display = "none";
}

// Display password requirements when clicking on password field
confirmInput.onfocus = function() {
	
	if(passInput.value != "") {
		passNotEmpty.classList.remove("invalid");
		passNotEmpty.classList.add("valid");
	} else {
		passNotEmpty.classList.remove("valid");
		passNotEmpty.classList.add("invalid");
	}
	
	// Check if confirm password input is empty
	if(confirmInput.value != "") {
		// Check whether both password and confirm password fields match
		if(passInput.value == confirmInput.value) {
			passMessage.classList.remove("invalid");
			passMessage.classList.add("valid");
		} else {
			passMessage.classList.remove("valid");
			passMessage.classList.add("invalid");
		}
	}
	
	passNotEmpty.style.display= "block";
	passMessage.style.display = "block";
	expandable2.classList.remove("not-expanded");
	expandable2.classList.add("expanded");
}

// Display password requirements when clicking on password field
confirmInput.onblur = function() {
	expandable2.classList.remove("expanded");
	expandable2.classList.add("not-expanded");
	passNotEmpty.style.display = "none";
	passMessage.style.display = "none";
}

// When user starts typing inside password field
passInput.onkeyup = function() {
	
	// Validate lowercase letters
	var lowerCaseLetters = /[a-z]/g;
	if(passInput.value.match(lowerCaseLetters)) {
		letter.classList.remove("invalid");
		letter.classList.add("valid");
	} else {
		letter.classList.remove("valid");
		letter.classList.add("invalid");
	}
	
	// Validate capital letters
	var upperCaseLetters = /[A-Z]/g
	if(passInput.value.match(upperCaseLetters)) {
		capital.classList.remove("invalid");
		capital.classList.add("valid");
	} else {
		capital.classList.remove("valid");
		capital.classList.add("invalid");
	}
	
	// Validate numbers
	var numbers = /[0-9]/g;
	if(passInput.value.match(numbers)) {
		number.classList.remove("invalid");
		number.classList.add("valid");
	} else {
		number.classList.remove("valid");
		number.classList.add("invalid");
	}
	
	// Validate length
	if(passInput.value.length >= 8) {
		length.classList.remove("invalid");
		length.classList.add("valid");
	} else {
		length.classList.remove("valid");
		length.classList.add("invalid");
	}
}

confirmInput.onkeyup = function() {
	// Check whether both password and confirm password fields match
	if(passInput.value == confirmInput.value) {
		passMessage.classList.remove("invalid");
		passMessage.classList.add("valid");
	} else {
		passMessage.classList.remove("valid");
		passMessage.classList.add("invalid");
	}
}