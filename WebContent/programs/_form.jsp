<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="regonline.datasource.DAO" %>
<%@ page import="regonline.faculty.Faculty" %>

<%
	List<Faculty> faculties = new DAO<>(Faculty.class).all();
	request.setAttribute("faculties", faculties);
%>

<c:if test="${program != null}">
	<c:set var="action" scope="request" value="?u=${program.id}"/>
</c:if>
<form action="./${action}" method="POST">
  <c:if test="${program == null}">
	  <div class="form-group">
	    <label for="id">Code</label>
	    <input type="text" class="form-control" id="id" name="id" value="${program.id}">
	  </div>
  </c:if>
  <div class="form-group">
    <label for="name">Name</label>
    <input type="text" class="form-control" id="name" name="name" value="${program.name}">
  </div>
  <div class="form-group">
    <label for="faculty">Faculty</label>
    <select class="form-control" id="faculty" name="faculty">
      <c:forEach items="${faculties}" var="faculty">
      <option value="${faculty.id}">${faculty}</option>
      </c:forEach>
    </select>
  </div>
  <button type="submit" class="btn btn-default">Submit</button>
</form> 