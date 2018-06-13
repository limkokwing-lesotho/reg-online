<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="../WEB-INF/urls.tld" prefix="ex"%>
<h1>Users</h1>
<a href="new.jsp">New</a>
<table>
	<tr>
		<th>Username</th>
		<th>First Name</th>
		<th>Last Name</th>
	</tr>
	<c:forEach var="user" items="${all}" varStatus="counter">
	<tr>
		<td><c:out value="${user.username}" /></td>
		<td><c:out value="${user.firstName}" /></td>
		<td><c:out value="${user.lastName}" /></td>
		<td>
			<ex:url type="Edit" id="${user.id}"/>
			<ex:url type="Delete" id="${user.id}"/>
		</td>
	</tr>
	</c:forEach>
</table>
