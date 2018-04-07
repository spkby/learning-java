<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>JSTL Sample</title>
</head>
<body>

<h1><c:out value="Helllo"/></h1>

<c:set var="salary" value="${499}"/>

<c:if test="${salary > 1000}">
    <h2><c:out value="YYYYY"/></h2>>
</c:if>

<c:choose>
    <c:when test="${salary < 500}">
        <h3>you are poor</h3>
    </c:when>
    <c:when test="${salary < 1000}">
        <h3>you are in median</h3>
    </c:when>
    <c:when test="${salary < 3000}">
        <h3>you are rich</h3>
    </c:when>
    <c:otherwise>
        <h3>Who are you?</h3>
    </c:otherwise>
</c:choose>
</body>
</html>
