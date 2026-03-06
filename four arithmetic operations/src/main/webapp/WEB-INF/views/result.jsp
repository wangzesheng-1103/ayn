<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>练习结果</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h1>练习结果</h1>
    <div class="summary">
        <p>得分: ${result.score}/${result.questions.size()}</p>
        <p>正确率: <fmt:formatNumber value="${result.accuracy}" pattern="0.00"/>%</p>
    </div>
    <div class="questions">
        <c:forEach items="${result.questions}" var="question" varStatus="status">
            <div class="question ${question.correct ? 'correct' : 'incorrect'}">
                <span class="question-number">${status.index + 1}.</span>
                <span class="expression">${question.expression}</span>
                <span class="answer">你的答案: ${question.userAnswer}</span>
                <span class="answer">正确答案: ${question.correctAnswer}</span>
                <span class="status">${question.correct ? '✓' : '✗'}</span>
            </div>
        </c:forEach>
    </div>
    <div class="button-group">
        <a href="${pageContext.request.contextPath}/exercise/main" class="btn">返回主页</a>
        <a href="${pageContext.request.contextPath}/exercise/history" class="btn">查看历史</a>
    </div>
</div>
</body>
</html>