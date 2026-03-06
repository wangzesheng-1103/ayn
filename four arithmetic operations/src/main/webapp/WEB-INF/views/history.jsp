<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>历史记录</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
  <h1>练习历史记录</h1>
  <c:if test="${empty history}">
    <p>暂无练习记录</p>
  </c:if>
  <c:if test="${not empty history}">
    <div class="history-list">
      <c:forEach items="${history}" var="exercise">
        <div class="history-item">
          <div class="history-header">
            <span class="date">练习时间: ${exercise.timestamp}</span>
            <span class="score">得分: ${exercise.score}/${exercise.questions.size()}</span>
            <span class="accuracy">正确率: <fmt:formatNumber value="${exercise.accuracy}" pattern="0.00"/>%</span>
          </div>
        </div>
      </c:forEach>
    </div>
  </c:if>
  <div class="button-group">
    <a href="${pageContext.request.contextPath}/exercise/main" class="btn">返回主页</a>
  </div>
</div>
</body>
</html>