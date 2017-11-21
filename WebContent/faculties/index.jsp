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
		<c:url value="./" var="myURL">
			<c:param name="id" value="${faculty.code}" />
		</c:url>
		<a href="${myURL}">Delete</a>
		</td>
	</tr>
	</c:forEach>
</table>
