<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>四则运算练习</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
  <h1>四则运算练习</h1>
  <form action="${pageContext.request.contextPath}/exercise/submit" method="post">
    <input type="hidden" name="username" value="${exercise.username}">
    <c:forEach items="${exercise.questions}" var="question" varStatus="status">
      <div class="question">
        <span class="question-number">${status.index + 1}.</span>
        <span class="expression">${question.expression}</span>
        <input type="number" name="questions[${status.index}].userAnswer" required>
        <input type="hidden" name="questions[${status.index}].expression" value="${question.expression}">
        <input type="hidden" name="questions[${status.index}].correctAnswer" value="${question.correctAnswer}">
      </div>
    </c:forEach>
    <button type="submit" class="btn">提交答案</button>
  </form>
</div>
</body>
</html>