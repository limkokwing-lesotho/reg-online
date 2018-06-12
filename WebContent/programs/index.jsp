<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="../WEB-INF/urls.tld" prefix="ex"%>

<h1>Programs</h1>
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
			<ex:url type="Update" id="${program.id}"/>
			<ex:url type="Delete" id="${program.id}"/>
		</td>
	</tr>
	</c:forEach>
</table>
