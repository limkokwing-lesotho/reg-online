<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Faculties</h1>
<a href="new.jsp">New</a>
<table>
	<tr>
		<th>Code</th>
		<th>Name</th>
		<th>Faculty</th>
		<th>Action</th>
	</tr>
	<c:forEach var="program" items="${all}" varStatus="counter">
	<tr>
		<td><c:out value="${program.code}" /></td>
		<td><c:out value="${program.name}" /></td>
		<td><c:out value="${program.faculty}" /></td>
		<td>
		<c:url value="./" var="delete_url">
			<c:param name="d" value="${program.code}" />
		</c:url>
		<a href="./?e=${program.code}">Edit</a>
		<a href="${delete_url}">Delete</a>
		</td>
	</tr>
	</c:forEach>
</table>
