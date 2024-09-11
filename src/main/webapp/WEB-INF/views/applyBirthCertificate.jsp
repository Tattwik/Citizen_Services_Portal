<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>APPLY BIRTH CERTIFICATE</title>
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
			${userName} </label> <a href="apply"
			style="text-decoration: none; font-size: x-large; font-family: sans-serif; margin-left: 120px; color: black;"
			class="text-primary"><label style="color: black;">Apply
				Birth Certificate</label></a> <a href="view"
			style="text-decoration: none; font-size: x-large; font-family: sans-serif; margin-left: 120px; color: black;"
			class="text-primary"><label style="color: black;">View
				Birth Certificate Application Status</label></a>
		<button onclick="logout()" class="btn btn-danger"
			style="margin-left: 120px;">Log Out</button>
		<div style="margin-left: 70px;">
			<small
				style="font-variant: small-caps; font-family: cursive; font-weight: bold; font-size: medium;">
				${user} </small>
		</div>
	</div>

	<div class="container mt-3">
		<div class="card">
			<form action="./applyBC" method="post" enctype="multipart/form-data"
				onsubmit="return validateForm();">
				<div class="card-header h2 text-success">Birth Certificate
					Application Form</div>
				<div class="card-body">
					<div class="row">
						<div class="col-4 mb-2">
							<label for="childNameId" class="font-weight-bold"
								style="font-family: sans-serif; font-variant: small-caps;">Child
								Name: <span class="text-danger">*</span>
							</label> <input type="text" id="childNameId" name="childName"
								class="form-control">
						</div>
						<div class="col-4 mb-2">
							<label for="fatherNameId" class="font-weight-bold"
								style="font-family: sans-serif; font-variant: small-caps;">Father
								Name: <span class="text-danger">*</span>
							</label> <input type="text" id="fatherNameId" name="fatherName"
								class="form-control">
						</div>
						<div class="col-4 mb-2">
							<label for="motherNameId" class="font-weight-bold"
								style="font-family: sans-serif; font-variant: small-caps;">Mother
								Name: <span class="text-danger">*</span>
							</label> <input type="text" id="motherNameId" name="motherName"
								class="form-control">
						</div>
						<div class="col-4 mb-2">
							<label for="pobId" class="font-weight-bold"
								style="font-family: sans-serif; font-variant: small-caps;">Place
								of Birth: <span class="text-danger">*</span>
							</label> <input type="text" id="pobId" name="placeOfBirth"
								class="form-control">
						</div>
						<div class="col-4 mb-2">
							<label for="dobId" class="font-weight-bold"
								style="font-family: sans-serif; font-variant: small-caps;">Date
								of Birth: <span class="text-danger">*</span>
							</label> <input type="date" id="dobId" name="dob" class="form-control">
						</div>
						<div class="col-4 mb-2">
							<label for="genId" class="font-weight-bold"
								style="font-family: sans-serif; font-variant: small-caps;">Sex:
								<span class="text-danger">*</span>
							</label>
							<div>
								<input type="radio" name="gender" id="genMId" value="Male">
								<label style="font-family: sans-serif;">Male</label>&nbsp;&nbsp;
								<input type="radio" name="gender" id="genFId" value="Female">
								<label style="font-family: sans-serif;">Female</label>
							</div>
						</div>
						<div class="col-12 mb-2">
							<label for="perAddId" class="font-weight-bold"
								style="font-family: sans-serif; font-variant: small-caps;">Permanent
								Address of Parent: <span class="text-danger">*</span>
							</label>
						</div>
						<div class="col-2 mb-2">
							<label for="stateId" class="font-weight-bold"
								style="font-family: sans-serif; font-variant: small-caps;">State:
								<span class="text-danger">*</span>
							</label>&nbsp;&nbsp; <select id="stateId" class="form-control"
								name="state" onchange="getDistrictByState()">
								<option value="0">--Select--</option>
								<c:forEach items="${stateList}" var="state">
									<option value="${ state.stateId }"
										<c:if test="${state.stateId eq rege.state.stateId }">selected="selected"</c:if>>${state.stateName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-2 mb-2">
							<label for="districtId" class="font-weight-bold"
								style="font-family: sans-serif; font-variant: small-caps;">District:
								<span class="text-danger">*</span>
							</label> <select id="districtId" class="form-control"
								name="district">
								<c:if test="${rege ne null}">
									<c:forEach items="${districtList}" var="district">
										<option value="${district.districtId}"
											<c:if test="${district.districtId eq rege.district.districtId}">selected="selected"</c:if>>${district.districtName}</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
						<div class="col-4 mb-2">
							<label for="houseId" class="font-weight-bold"
								style="font-family: sans-serif; font-variant: small-caps;">House
								No-Locality-Zip: <span class="text-danger">*</span>
							</label>
							<textarea id="houseId" class="form-control"
								name="houseNoLocalityZip" onchange="addressInp()">
							</textarea>
						</div>
						<div class="col-4 mb-2">
							<label for="addrsId" class="font-weight-bold"
								style="font-family: sans-serif; font-variant: small-caps;">Upload
								Address Proof: <span class="text-danger">*</span>
							</label> <input type="file" id="addrsId" name="file"
								class="form-control">
						</div>
						<div class="col-12 mt-4 mb-2 text-center">
							<button type="submit" id="btnSubmit" class="btn btn-success"
								style="font-family: sans-serif; font-variant: small-caps;">Apply</button>
							<button type="reset" id="btnReset" class="btn btn-warning"
								style="font-family: sans-serif; font-variant: small-caps;">Reset</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script type="text/javascript">
	function addressInp() {
    var res = $('#houseId').val().trim();
    $('#houseId').val(res);
	}
	
function validateForm() {

   var res = true;
   if (res) {
	   console.log("hitting");
	   var namePattern = /^[A-Za-z. ]+$/;
	   var addressPattern = /^[A-Za-z0-9\s,.'\-\/]+$/;
	   
	   var child = $('#childNameId');
	   var father = $('#fatherNameId');
	   var mother = $('#motherNameId');
	   var pob = $('#pobId');
	   var dob = $('#dobId');
	   var state = $('#stateId');
	   var district = $('#districtId');
	   var house = $('#houseId');
	   var address = $('#addrsId');
	  /*  var male = $('#genMId');
	   var female = $('#genFId'); */
	   var gender = document.getElementsByName('gender');
	   
	   if (child.val().trim() == '') {
	      child.focus();
	      Swal.fire("Please fill child name!");
	      return false;
	   }
	   
	   if (!namePattern.test(child.val())) {
	      child.focus();
	      child.val('');
	      Swal.fire("No symbols allowed for child name!");
	      return false;
	   }
	   
	   if (father.val().trim() == '') {
        father.focus();
        Swal.fire("Please fill father name!");
        return false;
	   }
	   
	   if (!namePattern.test(father.val())) {
        father.focus();
        father.val('');
        Swal.fire("No symbols allowed for father name!");
        return false;
	   }
	   
	   if (mother.val().trim() == '') {
	        mother.focus();
	        Swal.fire("Please fill mother name!");
	        return false;
		   }
		   
		  if (!namePattern.test(mother.val())) {
	        mother.focus();
	        mother.val('');
	        Swal.fire("No symbols allowed for mother name!");
	        return false;
		   }
		  
		  if (pob.val().trim() == '') {
         pob.focus();
         Swal.fire("Please fill place of birth!");
         return false;
		  }
		  
		  if (!addressPattern.test((pob).val().trim())) {
         pob.focus();
         pob.val('');
         Swal.fire("Invalid address!");
         return false;
		  }
		  
		  if (dob.val() == '') {
         dob.focus();
         Swal.fire("Please fill in date of birth of child!");
         return false;
		  }
		  var isSelected = false;
		  for (const gen of gender) {
		        if (gen.checked) {
		            isSelected = true;
		            break;
		        }
		    }
		  
		  if (!isSelected) {
        Swal.fire("Please select gender");
        return false;
		  }
		  
		  if (state.val() === '0') {
         state.focus();
         Swal.fire("Please select state!");
         return false;
		  }
		  
		  if (district.val() === '0') {
		      district.focus();
		      Swal.fire("Please select district!");
		      return false;
			}
		  
		  if (house.val().trim() == '') {
         house.focus();
         house.val('');
         Swal.fire("Please fill house no-locality-zip");
         return false;
		  }
		  
		  if (address.val() == '') {
         address.focus();
         Swal.fire("Please upload address proof!");
         return false;
		  }
		  
		  var index = address.val().lastIndexOf('.');
		  var s = address.val().substring(index);
		  console.log(s);
		  
		  if (!(s === '.jpg' || s === '.jpeg' || s === '.png' || s === '.pdf' || s === '.jfif')) {
         address.focus();
         address.val('');
         Swal.fire("Only jpg, jpeg, png, pdf and jfif files are allowed");
         return false;
		  }
		  
   }
   return res;
}
</script>

	<script type="text/javascript">
function getDistrictByState() {
   var stateId = $('#stateId').val();
   console.log(stateId);
   if (stateId != 0) {
	    $.ajax({
			   url : 'getDistrictByState',
			   type : 'GET',
			   data : {
				    stateId : stateId
			   },

			   success : function(response) {
				   $("#districtId").html(response);
			   },
			   error : function(error) {
			 	   console.log(error)
			  }
		  });
   } else {
     $('#districtId').html('');
   }
  
}
</script>

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