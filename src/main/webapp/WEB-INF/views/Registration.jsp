<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>USER REGISTRATION</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" />

<script src="https://code.jquery.com/jquery-2.2.4.js"
	integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.18.5/xlsx.full.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
	<div class="text-center mt-2 text-success">
		<h2>New User Sign Up Form</h2>
	</div>
	<div class="container">
		<div class="card">
			<form action="./register" method="post" id="registerFormId">
				<div class="card-body">
					<div class="row">
						<div class="col-4 mb-2">
							<label for="usrName" style="font-weight: bold;">User Name<span
								class="text-danger">*</span></label> <input id="usrId"
								class="form-control" type="text" name="userName">
						</div>
						<div class="col-4 mb-2">
							<label for="pass" style="font-weight: bold;">Password<span
								class="text-danger">*</span></label> <input id="passId"
								class="form-control" type="password" name="password">
						</div>
						<div class="col-4 mb-2">
							<label for="rPass" style="font-weight: bold;">Repeat
								Password<span class="text-danger">*</span>
							</label> <input id="rPassId" class="form-control" type="password">
						</div>
						<div class="col-4 mb-2">
							<label for="phone" style="font-weight: bold;">Phone No:<span
								class="text-danger">*</span></label> <input id="phnId"
								class="form-control" type="text" name="phoneNo">
						</div>
						<div class="col-4 mb-2">
							<label for="dob" style="font-weight: bold;">Date of Birth<span
								class="text-danger">*</span></label> <input id="dobId"
								class="form-control" type="date" name="dob">
						</div>
						<div class="col-4 mb-2 mt-4">
							<label for="gender" style="font-weight: bold;">Sex:<span
								class="text-danger">*</span></label>
							<div>
								<input id="genIdM" type="radio" name="sex" value="Male">&nbsp;Male&nbsp;
								<input id="genIdF" type="radio" name ="sex" value="female">&nbsp;Female
							</div>
						</div>
						<div class="col-4 mb-2">
							<label for="email" style="font-weight: bold;">Email<span
								class="text-danger">*</span></label> <input id="emailId"
								class="form-control" type="email" name="email">
						</div>
						<div class="col-4 mb-2">
							<label for="addrsId" style="font-weight: bold;">Address<span
								class="text-danger">*</span></label>
							<textarea id="addrsId" class="form-control" name="address" autocomplete="off"></textarea>
						</div>
						<div class="col-12 mt-5 text-center">
							<button type="submit" class="btn btn-success" id="btnSubmit">Apply</button>
							<button type="button" id="resBtn" class="btn btn-warning" onclick="reloadPage()">Reset</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	function validateForm(event) {

		event.preventDefault();
		
		var userName = document.getElementById("usrId");
		var password = document.getElementById("passId");
		var rPassword = document.getElementById("rPassId");
		var phone = document.getElementById("phnId");
		var dob = document.getElementById("dobId");
		var genM = document.getElementById("genIdM");
		var genF = document.getElementById("genIdF");
		var email = document.getElementById("emailId");
		var address = document.getElementById("addrsId");

		var forbiddenSymbols = /^[a-zA-Z0-9]+(\.[a-zA-Z0-9]+)*$/;
		var passwordValidator = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+{}\[\]:;"'<>?,.\/\\~`|]).{8,}$/;
		var phoneValidator = /^\d{10}$/;
		var emailValidator = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

		if (userName.value == '') {
			userName.focus();
			Swal.fire("Please fill user name!");
			return false;
		} else if (userName.value.length < 3) {
			userName.focus();
			Swal.fire("User Name length should be more than 3 characters!");
			return false;
		} else if (!forbiddenSymbols.test(userName.value)) {
			userName.focus();
			Swal.fire("No symbols allowed except ' . '");
			return false;
		}

		if (password.value == '') {
			password.focus();
			Swal.fire("Please fill password!");
			return false;
		} else if (!passwordValidator.test(password.value)) {
			password.focus();
			Swal.fire("Invalid password. It must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long.");
			return false;
		}

		if (rPassword.value !== password.value) {
			rPassword.focus();
			Swal.fire("Passwords don't match!");
			return false;
		}

		if (phone.value == '') {
			phone.focus();
			Swal.fire("Please enter phone number!");
			return false;
		} else if (!phoneValidator.test(phone.value)) {
			phone.focus();
			Swal.fire("Please enter a valid phone number!");
			return false;
		}

		if (!genM.checked && !genF.checked) {
			Swal.fire("Please select a gender!");
			return false;
		}
   
		if (email.value == '') {
     email.focus();
     Swal.fire("Please enter email id!");
     return false;
		} else if (!emailValidator.test(email.value)) {
      email.focus();
      Swal.fire("Please enter a valid email address!");
      return false;
		}
		
		if (address.value == '') {
      address.focus();
      Swal.fire("Please enter the complete address!");
      return false;
		}
		
		Swal.fire({
			title: "Confirm Registration?",
			showDenyButton: true,
			showCancelButton: true,
			confirmButtonText: "YES",
			denyButtonText: "NO"
		}).then((result) => {
			if (result.isConfirmed) {
				Swal.fire("Registered Successfully!", "", "success").then(() => {
					document.getElementById("registerFormId").submit();
				});
			} else if (result.isDenied) {
				Swal.fire("Registration Cancelled!", "", "info");
			}
		});

		return false;
	}
	document.getElementById("registerFormId").addEventListener("submit", validateForm);
	
	function reloadPage(){
     location.reload();
	}
</script>
</html>