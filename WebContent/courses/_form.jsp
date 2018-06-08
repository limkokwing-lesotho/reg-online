<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="regonline.datasource.DAO" %>
<%@ page import="regonline.faculty.Faculty" %>

<%
	List<Faculty> faculties = new DAO<>(Faculty.class).all();
	request.setAttribute("faculties", faculties);
%>

<c:if test="${course != null}">
	<c:set var="action" scope="request" value="?u=${course.id}"/>
</c:if>
<form action="./${action}" method="POST">
  <c:if test="${course == null}">
	  <div class="form-group">
	    <label for="id">Code</label>
	    <input type="text" class="form-control" id="id" name="id" value="${course.id}" required="required">
	  </div>
  </c:if>
  <div class="form-group">
    <label for="name">Name</label>
    <input type="text" class="form-control" id="name" name="name" value="${course.name}" required="required">
  </div>
  <div class="form-group">
    <label for="credits">Credits</label>
    <input type="number" min="0" step="1" class="form-control" id="credits" name="credits" value="${course.credits}" required="required">
  </div>
   <div class="form-group">
    <label for="type">Type</label>
    <select class="form-control" id="type" name="type">
      <option>Minor</option>
      <option>Core</option>
      <option>Major</option>
    </select>
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