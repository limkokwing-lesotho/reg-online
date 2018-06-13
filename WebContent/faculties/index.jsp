<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="../WEB-INF/urls.tld" prefix="ex"%>
<h1>Faculties</h1>
<a href="new.jsp">New</a>
<table>
	<tr>
		<th>Code</th>
		<th>Name</th>
		<th>Action</th>
	</tr>
	<c:forEach var="faculty" items="${all}" varStatus="counter">
	<tr>
		<td><c:out value="${faculty.code}" /></td>
		<td><c:out value="${faculty.name}" /></td>
		<td>
			<ex:url type="Edit" id="${faculty.id}"/>
			<ex:url type="Delete" id="${faculty.id}"/>
		</td>
	</tr>
	</c:forEach>
</table>
