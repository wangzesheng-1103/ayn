<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>四则运算练习系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h1>欢迎, ${username}!</h1>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <div class="button-group">
        <form action="${pageContext.request.contextPath}/exercise/generate" method="get" class="form-inline">
            <div class="form-group">
                <label for="count">题目数量 (1-50):</label>
                <input type="number" id="count" name="count" value="10" min="1" max="50" required>
            </div>
            <button type="submit" class="btn">开始练习</button>
        </form>
        <a href="${pageContext.request.contextPath}/exercise/history" class="btn">查看历史记录</a>
        <a href="${pageContext.request.contextPath}/user/logout" class="btn btn-logout">退出登录</a>
    </div>
</div>
</body>
</html>