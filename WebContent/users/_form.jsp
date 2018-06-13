<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${user != null}">
	<c:set var="action" scope="request" value="?u=${user.id}"/>
</c:if>
<form action="./${action}" method="POST">
  <c:if test="${user == null}">
	  <div class="form-group">
	    <label for="id">Username</label>
	    <input type="text" class="form-control" id="id" name="id" value="${user.id}" required="required">
	  </div>
  </c:if>
  <div class="form-group">
    <label for="firstName">First Name</label>
    <input type="text" class="form-control" id="firstName" name="firstName" value="${user.firstName}" required="required">
  </div>
  <div class="form-group">
    <label for="lastName">Second Name</label>
    <input type="text" class="form-control" id="lastName" name="lastName" value="${user.lastName}" required="required">
  </div>
  <button type="submit" class="btn btn-default">Submit</button>
</form> 