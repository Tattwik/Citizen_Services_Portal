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
		<div class="card">
			<div class="row">
				<div class="col-6">
					<label for="stateId">Select state : </label> <select name="state"
						id="stateId" class="form-control">
						<option value="0">--select--</option>
						<option value="1">California</option>
						<option value="2">Texas</option>
						<option value="3">New York</option>
					</select>
				</div>
				<div class="col-6" style="margin-top: 32px;">
					<button type="button" id="submitBtn" class="btn btn-success"
						onclick="callData(10,1)">GET DATA</button>
				</div>
			</div>
			<div class="card-body">
				<table class="table table-bordered table-striped" id="dataTbl">
					<thead>
						<tr>
							<th>Id</th>
							<th>NAME</th>
							<th>EMAIL</th>
							<th>STATE</th>
						</tr>
					</thead>
					<tbody id="dataTableId">

					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			callData(10, 1);
		});
	</script>

	<script type="text/javascript">
		function callData(pageSize, pageNumber) {

			var state = $("#stateId").val();
			var stateName = $('#stateId option:selected').text();

			console.log(stateName);
			$
					.ajax({
						url : 'getdataByName',
						type : 'GET',
						data : {
							"stateName" : stateName,
							"pageSize1" : pageSize,
							"pageNumber1" : pageNumber
						},

						success : function(response) {
							console.log(response);
							var html = "";
							var prev = "";
							var val = JSON.parse(response);
							var arr = val;
							if (arr.totalrowno == 0) {
								clearTable();
								return false;
							}

							$(".dataTables_info").empty().append("");
							var prevP = arr.start - 1;
							if (arr.pgno == 0) {
								prev = prev
										+ "<li class='paginate_button previous disabled' aria-controls='dynamicTable' tabindex='0' id='dynamic_previous'><a onclick='callData("
										+ pageSize + "," + arr.pgno
										+ ")' >Previous</a></li>";
							} else {
								prev = prev
										+ "<li class='paginate_button previous' aria-controls='dynamicTable' tabindex='0' id='dynamicTable_previous'><a onclick='callData("
										+ pageSize
										+ ","
										+ arr.pgno
										+ ")' style='cursor: pointer;'>Previous</a></li>";
							}
							if (arr.pgno == (arr.end - 1)) {
								next = "<li class='paginate_button next disabled' aria-controls='dynamicTable' tabindex='0' id='dynamicTable_next'><a onclick='callData("
										+ pageSize
										+ ","
										+ arr.pgno
										+ ")'>Next</a></li>";
							} else {
								next = "<li class='paginate_button next' aria-controls='dynamicTable' tabindex='0' id='dynamicTable_next'><a onclick='callData("
										+ pageSize
										+ ","
										+ (arr.pgno + 2)
										+ ")' style='cursor: pointer;'>Next</a></li>";
							}
							for (var i = arr.start; i <= arr.end; i++) {
								prev = prev
										+ "<li class='paginate_button' aria-controls='dynamicTable' tabindex='0' id='btn"
                                  + i + "'><a onclick='callData('"
										+ pageSize + "," + i
										+ ")' style='cursor: pointer;'>" + i
										+ "</a></li>";
							}
							prev = prev + next;
							$(".pagination").empty().append(prev);
							$("#btn" + pageNumber).attr("class",
									"paginate_button active");

							var showpagesize = "Showing 1 of " + pageSize
									+ " of " + arr.totalrowno + " entries";
							$(".dataTables_info").empty().append(showpagesize);
							var count = 0;
							var listDataLen = 0;

							if (val != "" || val != null) {
								$.each(val.list,
										function(index, value) {
											count = index + 1;
											html = html + "<tr><td>" + /* ((pageSize * (pageNumber - 1)) + count) */
											value.id + "</td>";

											html = html + "<td>" + value.name
													+ "</td>";
											html = html + "<td>" + value.email
													+ "</td>";
											html = html + "<td>" + value.state
													+ "</td>";
										});
							}
							$("#dataTableId").empty().append(html);
						},
						error : function(error) {
							console.log(error);
						}
					});
		}
		function clearTable() {
			var table = $('#dataTbl').DataTable();

			table.clear().draw();
		}
	</script>

</body>
</html>
