<jsp:include page="/includes/head.jspf" />
<jsp:include page="/includes/nav.jspf" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="AddCar" method="post"
	style="margin: auto; padding: 20px; width: 50%;">
	<div class="form-group">
		<label for="date">Date Added:</label> <input type="date"
			class="form-control" name="date">
	</div>
	<div class="form-group">
		<label for="price">Price:</label> <input type="text"
			class="form-control" name="price">
	</div>
	<div class="form-group">
		<label for="make">Make:</label> <input type="text"
			class="form-control" name="make">
	</div>
	<div class="form-group">
		<label for="model">Model:</label> <input type="text"
			class="form-control" name="model">
	</div>
	<div class="form-group">
		<label for="carDescription">Vehicle Description:</label> <input
			type="text" class="form-control" name="carDescription">
	</div>
	<div class="form-group">
		<label for="mainImage">Main Image URL:</label> <input type="text"
			class="form-control" name="mainImage">
	</div>
	<div class="form-group">
		<label for="year">Year:</label> <input type="text"
			class="form-control" name="year">
	</div>
	<div class="form-group">
		<label for="used">New or Used:</label> <select class="form-control"
			name="used">
			<option>New</option>
			<option>Used</option>
		</select>
	</div>
	<div class="form-group">
		<label for="kilosRan">Kilos:</label> <input type="text"
			class="form-control" name="kilosRan">
	</div>
	<div class="form-group" style="padding: 5px;">
		<input type="submit" value="Submit" />
	</div>
</form>

<jsp:include page="/includes/footer.jspf" />