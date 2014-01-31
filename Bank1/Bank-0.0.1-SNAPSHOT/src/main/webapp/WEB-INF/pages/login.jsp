<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Logowanie</title>
    <meta content="text/html;charset=UTF-8">
</head>
<body>
    <h1>Logowanie</h1>
    <form method="POST" action="login">
        Login: <input type="text" name="j_username">
        Password: <input type="password" name="j_password">
        <input type="submit">
    </form>
</body>
</html>