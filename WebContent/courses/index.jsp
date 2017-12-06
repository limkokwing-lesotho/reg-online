<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Courses</h1>
<a href="new.jsp">New</a>
<table>
	<tr>
		<th>Code</th>
		<th>Name</th>
		<th>Action</th>
	</tr>
	<c:forEach var="course" items="${all}" varStatus="counter">
	<tr>
		<td><c:out value="${course.code}" /></td>
		<td><c:out value="${course.name}" /></td>
		<td>
		<c:url value="./" var="delete_url">
			<c:param name="d" value="${course.code}" />
		</c:url>
		<a href="./?e=${course.code}">Edit</a>
		<a href="${delete_url}">Delete</a>
		</td>
	</tr>
	</c:forEach>
</table>
