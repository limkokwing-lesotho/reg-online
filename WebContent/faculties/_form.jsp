<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${faculty != null}">
	<c:set var="action" scope="request" value="?u=${faculty.code}"/>
</c:if>
<form action="./${action}" method="POST">
  <c:if test="${faculty == null}">
	  <div class="form-group">
	    <label for="code">Code</label>
	    <input type="text" class="form-control" id="code" name="code" value="${faculty.code}">
	  </div>
  </c:if>
  <div class="form-group">
    <label for="name">Name</label>
    <input type="text" class="form-control" id="name" name="name" value="${faculty.name}">
  </div>
  <button type="submit" class="btn btn-default">Submit</button>
</form> 