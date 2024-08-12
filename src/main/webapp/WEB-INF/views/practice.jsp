<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagination Practice</title>
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

	<div class="container">
		<button id="getBtn" class="btn btn-success" onclick="getdata()">GET
			DATA</button>
		<div class="card">
			<div class="card-body">
				<table class="table table-bordered table-striped" id="dataTbl">
					<thead>
						<tr>
							<th>Id</th>
							<th>NAME</th>
							<th>EMAIL</th>
						</tr>
					</thead>
					<tbody id="dataTableId">

					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function getdata() {
			$.ajax({
				url : 'getdata',
				type : 'GET',
				data : {},

				success : function(response) {
					var html = "";
					for (let i = 0; i < response.data.length; i++) {
						html = html + "<tr><td>" + response.data[i].id
								+ "</td><td>" + response.data[i].name
								+ "</td><td>" + response.data[i].email
								+ "</td></tr>";
					}
					$('#dataTableId').empty().append(html);
					//ui pagination
					$("#dataTbl").dataTable({
						"lengthMenu" : [ 3, 5, 10, 15 ]
					});
				},
				error : function(error) {
					console.log(error);
				}
			});
		}
	</script>
</body>
</html>
