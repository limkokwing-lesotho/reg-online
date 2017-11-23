<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Faculties</h1> 
<table>
	<tr>
		<th>Code</th>
		<th>Name</th>
		<th>Action</th>
	</tr>
	<c:forEach var="faculty" items="${faculties}" varStatus="counter">
	<tr>
		<td><c:out value="${faculty.code}" /></td>
		<td><c:out value="${faculty.name}" /></td>
		<td>
		<c:url value="./" var="delete_url">
			<c:param name="d" value="${faculty.code}" />
		</c:url>
		<a href="edit.jsp?id=${faculty.code}">Edit</a>
		<a href="${delete_url}">Delete</a>
		</td>
	</tr>
	</c:forEach>
</table>
