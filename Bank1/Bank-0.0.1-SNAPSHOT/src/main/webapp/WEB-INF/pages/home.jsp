<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title></title>
    <meta content="text/html;charset=UTF-8">
</head>
<body>
    <h1><s:message code="homeTitle"/></h1>
    <sec:authorize ifAllGranted="ROLE_ADMIN">
        Witaj Adminie.
    </sec:authorize>
    <ul>
        <li><a href="addClient.html"><s:message code="addClient"/></a></li>
        <li><a href="showClients.html"><s:message code="showClients"/></a></li>
        <li><a href="logout">Wyloguj <sec:authentication property="principal.username"/></a></li>
    </ul>
</body>
</html>