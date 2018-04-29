<html>
<body>
	<div
		style="position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
		<h1 style="color: blue">
			Sorry for the inconvenience! <br /> server encountered a problem ...
		</h1>
		<br />
		<h2 style="color: blue">more details can be found bellow</h2>
		<h4 style="color: blue">
			<%
				out.println("Requested source: " + pageContext.getErrorData().getRequestURI());
				out.println("<br/>");
				out.println(pageContext.getErrorData().getStatusCode() + " - " + pageContext.getException());
				out.println("<br/>");
			%>
		</h4>
	</div>
</body>
</html>


