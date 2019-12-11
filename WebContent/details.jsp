<jsp:include page="/includes/head.jspf" />
<jsp:include page="/includes/nav.jspf" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container">
	<p style="color: red; style= bold">${msg}</p>
	<div class="row row-cols-1 justify-content-center">
		<div class="col">
			<img width="auto" src="${mainImage}" class="img-fluid rounded"
				alt="${make} ${model}" />
		</div>
		<div class="col">${year}&nbsp;${make}&nbsp;${model}</div>
		<div class="col">
			<fmt:formatNumber type="currency" maxFractionDigits="2"
				value="${price}"></fmt:formatNumber>
			|
			<fmt:formatNumber type="number" value="${kilosRan}"></fmt:formatNumber>
			kilometers.
		</div>
		<div class="col">${carDescription}</div>
		<div class="col">Days in inventory:&nbsp;${daysInInventory}</div>
	</div>

	<hr>
	<c:if test="${daysInInventory <= 120}">
		<p>Buy now:</p>
		<form action="BuyCar" method="post">
			<div class="form-group">
				<label for="firstName">First Name:</label> <input type="text"
					class="form-control" name="firstName">
			</div>
			<div class="form-group">
				<label for="lastName">Last Name:</label> <input class="form-control"
					type="text" name="lastName" />
			</div>
			<div class="form-group">
				<label for="phoneNumber">Phone Number:</label> <input
					class="form-control" type="text" name="phoneNumber" />
			</div>
			<div class="form-group">
				<label for="email">Email:</label> <input class="form-control"
					type="text" name="email" />
			</div>
			<div class="form-group">
				<label for="streetAddress">Street Address:</label> <input
					class="form-control" type="text" name="streetAddress" />
			</div>
			<div class="form-group">
				<label for="city">City:</label> <input class="form-control"
					type="text" name="city" />
			</div>
			<div class="form-group">
				<label for="state">State:</label> <input class="form-control"
					type="text" name="state" />
			</div>
			<div class="form-group">
				<label for="zipCode">Zip Code:</label> <input class="form-control"
					type="text" name="zipCode" />
			</div>
			<div class="form-group">
				<input type="hidden" class="form-control" name=vin value="${vin}">
			</div>
			<div class="form-group">
				<input type="submit" class="form-control" value="Buy Now" />
			</div>
		</form>
	</c:if>
	<c:if test="${daysInInventory > 120}">
		<p>
			Minimum Bid:
			<fmt:formatNumber type="currency" value="${discount}"></fmt:formatNumber>
		</p>
		<p>Bid Now:</p>
		<form action="BidOnCar" method="post">
			<div class="form-group">
				<label for="userBid">Your Bid:</label> <input class="form-control"
					type="text" name="userBid" />
			</div>
			<div class="form-group">
				<label for="firstName">First Name:</label> <input
					class="form-control" type="text" name="firstName" />
			</div>
			<div class="form-group">
				<label for="lastName">Last Name:</label> <input class="form-control"
					type="text" name="lastName" />
			</div>
			<div class="form-group">
				<label for="phoneNumber">Phone Number:</label> <input
					class="form-control" type="text" name="phoneNumber" />
			</div>
			<div class="form-group">
				<label for="email">Email:</label> <input class="form-control"
					type="text" name="email" />
			</div>
			<div class="form-group">
				<label for="streetAddress">Street Address:</label> <input
					class="form-control" type="text" name="streetAddress" />
			</div>
			<div class="form-group">
				<label for="city">City:</label> <input class="form-control"
					type="text" name="city" />
			</div>
			<div class="form-group">
				<label for="state">State:</label> <input class="form-control"
					type="text" name="state" />
			</div>
			<div class="form-group">
				<label for="zipCode">Zip Code:</label> <input class="form-control"
					type="text" name="zipCode" />
			</div>
			<div class="form-group">
				<input type="hidden" class="form-control" name=vin value="${vin}" />
			</div>
			<div class="form-group">
				<input type="hidden" class="form-control" name="price"
					value="${price}" />
			</div>
			<div class="form-group">
				<input type="hidden" class="form-control" name="discount"
					value="${discount}" />
			</div>
			<div class="form-group">
				<input type="submit" class="form-control" value="Buy Now" />
			</div>
		</form>
	</c:if>
</div>

<jsp:include page="/includes/footer.jspf" />