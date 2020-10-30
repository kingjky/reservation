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
		var oReq = new XMLHttpRequest();
		oReq.addEventListener("load", function() {
			moveTodo(id, type);
		});
		oReq.open("PUT", "http://localhost:8080/Todo/todoType?id=" + id
				+ "&type=" + type);
		oReq.send();
	}
	function moveTodo(id, type) {
		var value = type + id;
		var li = document.querySelector("li[value='" + value + "']");

		var newType;
		if (type === "TODO")
			newType = "DOING";
		else if (type === "DOING")
			newType = "DONE";
		li.setAttribute("value", newType + id);

		var btn = li.childNodes[3].childNodes[7];
		if (newType === "DONE")
			btn.remove();
		else
			btn.setAttribute("onclick", "updateType('" + id + "', '" + newType + "')");

		var ul = document.querySelector("#list_" + newType);
		ul.appendChild(li);

		var items = ul.childNodes;
		var itemsArr = [];
		for ( var i in items) {
			if (items[i].nodeType == 1) {
				itemsArr.push(items[i]);
			}
		}

		itemsArr.sort(function(a, b) {
			var a_id = Number(a.childNodes[5].innerHTML);
			var b_id = Number(b.childNodes[5].innerHTML);
			return a_id == b_id ? 0 : (a_id > b_id ? 1 : -1);
		});

		for (i = 0; i < itemsArr.length; ++i) {
			ul.appendChild(itemsArr[i]);
		}
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
			<ul class="section_list" id="list_DONE">
				<c:forEach items="${todos }" var="todo">
					<c:if test="${todo.type == 'DONE'}">
						<li class="section_item">
							<h3>${todo.title}</h3>
							<p>
								<span>등록날짜: ${todo.regDate }</span>, <span>${todo.name }</span>,
								<span>우선순위 ${todo.sequence }</span>
							</p>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</section>

		<section>
			<div class="section_title">DOING</div>
			<ul class="section_list" id="list_DOING">
				<c:forEach items="${todos }" var="todo">
					<c:if test="${todo.type == 'DOING'}">
						<li class="section_item" value="DOING${todo.id}">
							<h3>${todo.title}</h3>
							<p>
								<span>등록날짜: ${todo.regDate }</span>, <span>${todo.name }</span>,
								<span>우선순위 ${todo.sequence }</span>
								<button onclick="updateType('${todo.id}', '${todo.type}')"></button>
							</p>
							<span id="item_id">${todo.id }</span>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</section>

		<section>
			<div class="section_title">TODO</div>
			<ul class="section_list" id="list_TODO">
				<c:forEach items="${todos }" var="todo">
					<c:if test="${todo.type == 'TODO'}">
						<li class="section_item" value="TODO${todo.id}">
							<h3>${todo.title}</h3>
							<p>
								<span>등록날짜: ${todo.regDate }</span>, <span>${todo.name }</span>,
								<span>우선순위 ${todo.sequence }</span>
								<button onclick="updateType('${todo.id}', '${todo.type}')"></button>
							</p>
							<span id="item_id">${todo.id }</span>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</section>
	</article>
</body>
</html>