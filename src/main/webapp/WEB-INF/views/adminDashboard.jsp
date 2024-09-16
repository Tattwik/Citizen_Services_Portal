<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ADMIN DASHBOARD</title>
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
	<div class="head" style="background-color: teal;">
		<img alt="userProfilePhoto" src="images/john doe.jpg" height="70px"
			style="border-radius: 25px; margin-left: 5px;"> <label
			for="userName"
			style="font-variant: small-caps; font-family: fantasy; font-size: x-large;">
			${userName} </label> <a href="viewAdmin"
			style="text-decoration: none; font-size: x-large; font-family: sans-serif; margin-left: 370px; color: black;"
			class="text-primary"><label style="color: black;">View
				Applications</label></a>
		<button onclick="logout()" class="btn btn-danger"
			style="margin-left: 400px;">Log Out</button>
		<div style="margin-left: 70px;">
			<small
				style="font-variant: small-caps; font-family: cursive; font-weight: bold; font-size: medium;">
				${user} </small>
		</div>
	</div>
	<div class="container">
		<div class="card">
			<div class="card-header h2 text-success">All Applications</div>
			<div class="card-body">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>Sl#</th>
							<th>Application Type</th>
							<th>Applied By</th>
							<th>Applied On</th>
							<th>Child Name</th>
							<th>Father Name</th>
							<th>Gender</th>
							<th>Address Proof</th>
							<th colspan="2" class="text-center">Action</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${birthList}" var="birth" varStatus="counter">
							<tr>
								<td>${counter.count}</td>
								<td>Birth Application</td>
								<td>${birth.userRegd.userName}</td>
								<td>${birth.appliedOn}</td>
								<td>${birth.childName}</td>
								<td>${birth.fatherName}</td>
								<td>${birth.gender}</td>
								<td><a href="./downloadFile?fileName=${birth.addressProof}">${birth.addressProof}</a></td>
								<td><a href="./approve?birth=${birth}"
									class="btn btn-success m-1">Approve</a></td>
								<td><a href="./reject?birth=${birth}"
									class="btn btn-danger m-1">Reject</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script>
	function logout() {
        Swal.fire({
            title: 'Are you sure?',
            text: "You will be logged out!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, logout!'
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = "/csp/logout"; 
            }
        });
    }
	
		let inactivityTimer;
		function logoutUser() {
			window.location.href = "/csp/sessionLogout";
		}
		function resetInactivityTimer() {
			clearTimeout(inactivityTimer);
			inactivityTimer = setTimeout(logoutUser, 1800000);
		}
		function setupActivityListeners() {
			document.addEventListener('mousemove', resetInactivityTimer);
			document.addEventListener('keydown', resetInactivityTimer);
			document.addEventListener('click', resetInactivityTimer);
			document.addEventListener('scroll', resetInactivityTimer);
		}
		function initialize() {
			setupActivityListeners();
			resetInactivityTimer();
		}
		initialize();
	</script>

</body>
</html>