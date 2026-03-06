<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h1>用户注册</h1>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <form action="${pageContext.request.contextPath}/user/doRegister" method="post" class="form">
        <div class="form-group">
            <label for="username">用户名:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">密码:</label>
            <input type="password" id="password" name="password" required minlength="6">
        </div>
        <button type="submit" class="btn">注册</button>
    </form>
    <a href="${pageContext.request.contextPath}/user/toLogin" class="link">已有账号？去登录</a>
</div>
</body>
</html>