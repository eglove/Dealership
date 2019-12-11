<jsp:include page="/includes/head.jspf" />
<jsp:include page="/includes/nav.jspf" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h1 style="text-align: center; color: red;">Crazy Carl's Crazy Cars</h1>
<p style="text-align: center; font-weight: bold;">${msg}</p>

<div class="container" style="margin: auto;">
	<div class="row row-cols-3">
		<c:forEach var="vehicle" items="${inventory}">
			<div class="col align-self-end" style="text-align: center;">
				<a href="/Dealership/Details?vin=${vehicle.vin}"> <img
					width="auto" src="${vehicle.mainImage}" class="img-fluid rounded"
					alt="Picture of ${vehicle.year} ${vehicle.make} ${vehicle.model}."/>
					<br> ${vehicle.year} ${vehicle.make} ${vehicle.model} <br>
					<fmt:formatNumber type="currency" value="${vehicle.price}" /> | <c:if
						test="${vehicle.used == 'Used'}">
						<fmt:formatNumber type="number" maxFractionDigits="2"
							value="${vehicle.kilosRan}" /> kilometers.
					</c:if> <c:if test="${vehicle.used == 'New'}">New!</c:if>
				</a>
				<hr>
			</div>
		</c:forEach>
	</div>
</div>

<%@ include file="/includes/footer.jspf"%>