<%--
  Created by IntelliJ IDEA.
  User: Zagir
  Date: 05.03.2020
  Time: 5:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='UTF-8'>
    <title>Hello page</title></head>
<body>
<br>
<form method="post">
    <fieldset>
        <legend>Sign up</legend>
        <div>
            <label>Name</label>
            <input type="text" name="name" required>
        </div>
        <div>
            <label>Email</label>
            <input type="text" name="email" required>
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password" required>
        </div>
        <button type="submit"> Send</button>
    </fieldset>
</form>
</body>
</html>
