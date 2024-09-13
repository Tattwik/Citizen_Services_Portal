<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DOWNLOAD BIRTH CERTIFICATE</title>
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

	<div class="container"
		style="border: 5px solid grey; height: 100vh; width: 50vw; border-radius: 2%">
		<div class="h2 text-center mt-2">
			<label
				style="font-family: cursive; font-variant: small-caps; font-weight: bold; text-decoration: underline;">BIRTH
				CERTIFICATE</label>
		</div>
		<div class="h3 text-center">
			<label
				style="font-family: cursive; font-style: italic;">This is to certify that</label>
		</div>
		<div class="h4 text-center">
			<label
				style="font-family: cursive; font-style: italic; font-variant: small-caps;
				text-decoration: underline;"> ${bc.childName} </label>
		</div>
		<div class="h3 text-center">
			<label
				style="font-family: cursive; font-style: italic;">was born to</label>
		</div>
		<div class="h4 text-center">
			<label
				style="font-family: cursive; font-style: italic; font-variant: small-caps;
				text-decoration: underline;"> ${bc.motherName} </label>&nbsp;
			<label class="h3"
				style="font-family: cursive; font-style: italic;">and</label>&nbsp;
			<label
				style="font-family: cursive; font-style: italic; font-variant: small-caps;
				text-decoration: underline;"> ${bc.fatherName} </label>
		</div>
		<div class="h3" style="margin-left: 280px;">
			<label
				style="font-family: cursive; font-style: italic;">on</label>
		</div>
		<div class="h4" style="margin-left: 235px;">
			<label
				style="font-family: cursive; font-style: italic; font-variant: small-caps;
				text-decoration: underline;"> ${bc.dob} </label>
		</div>
	</div>

</body>
</html>