<jsp:include page="/includes/head.jspf" />
<jsp:include page="/includes/nav.jspf" />

<h1 style="margin: auto; text-align: center;">Administrator Page</h1>

<form action="Login" method="post"
	style="margin: auto; text-align: center; padding: 20px;">
	<div style="padding: 5px;">
		Username: <input type="text" name="username" />
	</div>
	<div style="padding: 5px;">
		Password: <input type="password" name="password" />
	</div>
	<div style="padding: 5px;">
		<input type="submit" value="Login" />
	</div>
</form>

<jsp:include page="/includes/footer.jspf" />