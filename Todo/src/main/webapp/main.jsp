<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>main</title>
<link rel="stylesheet" href="./css/main.css">
<script>
	function updateType(id, type) {
		console.log(id, type);
		var oReq = new XMLHttpRequest();
		 oReq.addEventListener("load", function() {
		   console.log(this.responseText);
		 });
		 oReq.open("POST", "http://localhost:8080/Todo/todoType?id="+id+"&type="); 
		 oReq.send();
	}
</script>
</head>
<body>
	<header>
		<div id="header_title">나의 해야할 일들</div>
		<a href="./todoForm.jsp" id="header_new-todo">새로운 TODO 등록</a>
	</header>
	<article>
		<section>
			<div class="section_title">DONE</div>
			<c:forEach items="${todos }" var="todo">
				<c:if test="${todo.type == 'DONE'}">
					<div class="section_item">
						<h3>${todo.title}</h3>
						<p>
							<span>등록날짜: ${todo.regDate }</span>, 
							<span>${todo.name }</span>, 
							<span>우선순위 ${todo.sequence }</span>
							<button onclick="updateType('${todo.id}', '${todo.type}')"></button>
						</p>
					</div>
				</c:if>
			</c:forEach>
		</section>
		
		<section>
			<div class="section_title">DOING</div>
			<c:forEach items="${todos }" var="todo">
				<c:if test="${todo.type == 'DOING'}">
					<div class="section_item">
						<h3>${todo.title}</h3>
						<p>
							<span>등록날짜: ${todo.regDate }</span>, 
							<span>${todo.name }</span>, 
							<span>우선순위 ${todo.sequence }</span>
							<button onclick="updateType('${todo.id}', '${todo.type}')"></button>
						</p>
					</div>
				</c:if>
			</c:forEach>
		</section>
		
		<section>
			<div class="section_title">TODO</div>
			<c:forEach items="${todos }" var="todo">
				<c:if test="${todo.type == 'TODO'}">
					<div class="section_item">
						<h3>${todo.title}</h3>
						<p>
							<span>등록날짜: ${todo.regDate }</span>, 
							<span>${todo.name }</span>, 
							<span>우선순위 ${todo.sequence }</span>
							<button onclick="updateType('${todo.id}', '${todo.type}')"></button>
						</p>
					</div>
				</c:if>
			</c:forEach>
		</section>
	</article>
</body>
</html>