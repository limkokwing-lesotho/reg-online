<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${course != null}">
	<c:set var="action" scope="request" value="?u=${course.code}"/>
</c:if>
<form action="./${action}" method="POST">
  <c:if test="${course == null}">
	  <div class="form-group">
	    <label for="code">Code</label>
	    <input type="text" class="form-control" id="code" name="code" value="${course.code}">
	  </div>
  </c:if>
  <div class="form-group">
    <label for="name">Name</label>
    <input type="text" class="form-control" id="name" name="name" value="${course.name}">
  </div>
  <div class="form-group">
    <label for="credits">Credits</label>
    <input type="text" class="form-control" id="credits" name="credits" value="${course.credits}">
  </div>
   <div class="form-group">
    <label for="type">Type</label>
    <input type="text" class="form-control" id="type" name="type" value="${course.type}">
  </div>
  <button type="submit" class="btn btn-default">Submit</button>
</form> 