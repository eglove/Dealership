<jsp:include page="/includes/head.jspf" />
<jsp:include page="/includes/nav.jspf" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1 style="text-align: center;">Cray Carl's Crazy Ledger</h1>

<div class="table-responsive">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th scope="col">Transaction Date</th>
				<th scope="col">VIN</th>
				<th scope="col">First Name</th>
				<th scope="col">Last Name</th>
				<th scope="col">Phone Number</th>
				<th scope="col">Email</th>
				<th scope="col">Street Address</th>
				<th scope="col">City</th>
				<th scope="col">State</th>
				<th scope="col">ZipCode</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="transaction" items="${ledger}">
				<tr>
					<th scope="row">${transaction.transactionDate}</th>
					<td>${transaction.vin}</td>
					<td>${transaction.p.firstName}</td>
					<td>${transaction.p.lastName}</td>
					<td>${transaction.p.phoneNumber}</td>
					<td>${transaction.p.email}</td>
					<td>${transaction.p.address.streetAddress}</td>
					<td>${transaction.p.address.city}</td>
					<td>${transaction.p.address.state}</td>
					<td>${transaction.p.address.zipCode}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<%@ include file="/includes/footer.jspf"%>