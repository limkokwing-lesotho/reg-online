<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Faculties</h1>
<c:forEach var="faculty" items="${fuculties}" varStatus="counter">
    <div id="faculties">
        <p> <c:out value="${faculty}" /> </p>
    </div>
</c:forEach>
