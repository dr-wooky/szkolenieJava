<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <meta content="text/html;charset=UTF-8">
</head>
<body>
    <h1>Dodawanie klietna</h1>
    <sf:form method="POST" modelAttribute="client">
        <table>
            <tr>
                <td>Login:</td>
                <td><sf:input path="login"/></td>
                <td><sf:errors path="login"/></td>
            </tr>
            <tr>
                <td>Hasło:</td>
                <td><sf:input path="password"/></td>
                <td><sf:errors path="password"/></td>
            </tr>
            <tr>
                <td>Imię:</td>
                <td><sf:input path="firstName"/></td>
                <td><sf:errors path="firstName"/></td>
            </tr>
            <tr>
                <td>Nazwisko:</td>
                <td><sf:input path="lastName"/></td>
                <td><sf:errors path="lastName"/></td>
            </tr>
            <c:forEach items="${client.addresses}" varStatus="info">
                <tr>
                    <td colspan="3">Adres: ${info.index + 1}</td>
                </tr>
                <tr>
                    <td>Ulica:</td>
                    <td><sf:input path="addresses[${info.index}].line1"/></td>
                    <td><sf:errors path="addresses[${info.index}].line1"/></td>
                </tr>
                <tr>
                    <td>Kod:</td>
                    <td><sf:input path="addresses[${info.index}].postalCode"/></td>
                    <td><sf:errors path="addresses[${info.index}].postalCode"/></td>
                </tr>
                <tr>
                    <td>Miasto:</td>
                    <td><sf:input path="addresses[${info.index}].city"/></td>
                    <td><sf:errors path="addresses[${info.index}].city"/></td>
                </tr>
                <tr>
                    <td>Krajo:</td>
                    <td><sf:input path="addresses[${info.index}].country"/></td>
                    <td><sf:errors path="addresses[${info.index}].country"/></td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit" name="Dodaj"/>
    </sf:form>
</body>
</html>