<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="../WEB-INF/urls.tld" prefix="ex"%>
<h1>Courses</h1>
<a href="new.jsp">New</a>
<table>
	<tr>
		<th>Code</th>
		<th>Name</th>
		<th>Type</th>
		<th>Faculty</th>
		<th>Action</th>
	</tr>
	<c:forEach var="course" items="${all}" varStatus="counter">
	<tr>
		<td><c:out value="${course.code}" /></td>
		<td><c:out value="${course.name}" /></td>
		<td><c:out value="${course.type}" /></td>
		<td><c:out value="${course.faculty}" /></td>
		<td>
			<ex:url type="Edit" id="${course.id}"/>
			<ex:url type="Delete" id="${course.id}"/>
		</td>
	</tr>
	</c:forEach>
</table>
