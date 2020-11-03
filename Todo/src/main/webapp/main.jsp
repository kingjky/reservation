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
	document.addEventListener("DOMContentLoaded", function(){
		var list_items = this.querySelectorAll("li");
		list_items.forEach(el => {
			var item_button = el.childNodes[3].childNodes[7];
			if(item_button != null){
				var item_id = el.childNodes[5].textContent;
				var item_type = el.childNodes[7].textContent;
				item_button.addEventListener("click", function() {
					updateType(item_id, item_type);
				})
			}
		});
	});
	
	function updateType(id, type) {
		var oReq = new XMLHttpRequest();
		oReq.addEventListener("load", function() {
			if (oReq.responseText === "success")
				moveTodo(id, type);
		});
		oReq.open("PUT", "http://localhost:8080/Todo/todoType?id=" + id + "&type=" + type);
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

		itemsArr.sort(function(prev, next) {
			var prev_id = Number(prev.childNodes[5].textContent);
			var next_id = Number(next.childNodes[5].textContent);

			if (prev_id === next_id) {
				return 0;
			} else {
				if (prev_id > next_id)
					return 1;
				else
					return -1;
			}
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
				<c:forEach items="${todoList }" var="todo">
					<c:if test="${todo.type == 'DONE'}">
						<li class="section_item">
							<h3>${todo.title}</h3>
							<p>
								<span>등록날짜: ${todo.regDate }</span>, <span>${todo.name }</span>,
								<span>우선순위 ${todo.sequence }</span>
							</p>
							<span id="item_id" class="item_hidden">${todo.id }</span>
							<span id="item_type" class="item_hidden">${todo.type }</span>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</section>

		<section>
			<div class="section_title">DOING</div>
			<ul class="section_list" id="list_DOING">
				<c:forEach items="${todoList }" var="todo">
					<c:if test="${todo.type == 'DOING'}">
						<li class="section_item" value="DOING${todo.id}">
							<h3>${todo.title}</h3>
							<p>
								<span>등록날짜: ${todo.regDate }</span>, <span>${todo.name }</span>,
								<span>우선순위 ${todo.sequence }</span>
								<input type="button"></input>
							</p>
							<span id="item_id" class="item_hidden"><p>${todo.id }</p></span>
							<span id="item_type" class="item_hidden"><p>${todo.type }</p></span>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</section>

		<section>
			<div class="section_title">TODO</div>
			<ul class="section_list" id="list_TODO">
				<c:forEach items="${todoList }" var="todo">
					<c:if test="${todo.type == 'TODO'}">
						<li class="section_item" value="TODO${todo.id}">
							<h3>${todo.title}</h3>
							<p>
								<span>등록날짜: ${todo.regDate }</span>, <span>${todo.name }</span>,
								<span>우선순위 ${todo.sequence }</span>
								<input type="button"></input>
							</p>
							<span id="item_id" class="item_hidden">${todo.id }</span>
							<span id="item_type" class="item_hidden">${todo.type }</span>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</section>
	</article>
</body>
</html>