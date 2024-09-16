<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CITIZEN SERVICES PORTAL</title>
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
	<div class="head text-center">
		<div class="h2 text-tertiary" style="font-family: fantasy;
		 font-variant: small-caps;">CITIZEN SERVICES PORTAL</div>
	</div>

	<div class="container mt-2"
		style="display: flex; padding-left: 3px; padding-top: 40px;">
		<div class="box-1" style="border: 2px solid grey;">
			<img alt="senior citizen" src="images/family.jfif" height="440px">
		</div>
		<div class="box-2"
			style="margin-left: 60px; width: 540px; padding-top: 70px;">
			<div class="card">
				<div class="card-header h3 text-tertiary">User Log In</div>
				<div class="card-body">
					<form action="./login" method="post" id="loginFormId"
						onsubmit="return validateForm()">
						<div class="row">
							<div class="col-6 text-center">
								<label for="userName" class="font-weight-bold"
									style="font-size: x-large;">User Name</label>
							</div>
							<div class="col-6 mb-5	">
								<input id="usrId" type="text" class="form-control"
									name="userName">
							</div>
							<div class="col-6 text-center">
								<label for="pass" class="font-weight-bold"
									style="font-size: x-large;">Password</label>
							</div>
							<div class="col-6 mb-5">
								<input id="passId" type="password" class="form-control"
									name="password">
							</div>
							<div class="col-7 text-center">
								<a href="signUp"
									style="text-decoration: none; font-size: x-large"
									class="text-primary">New User Sign Up</a>
							</div>
							<div class="col-5 text-center">
								<button type="submit" class="btn btn-primary" id="btnSubmit">LOGIN</button>
							</div>
							<div class="col-12 text-center">
								<label for="msg"
									class="font-weight-bold text-danger text-center"
									style="font-size: medium; font-family: sans-serif;
									font-variant: small-caps;">${msg}</label>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function validateForm() {
			var user = $('#usrId');
			var pass = $('#passId');
			if (user.val().trim() == '') {
				user.focus();
				Swal.fire("Please enter user name!");
				return false;
			}
			if (pass.val().trim() == '') {
				pass.focus();
				Swal.fire("Please enter password");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>