<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="./css/todoForm.css">
<script>
	console.log('hihi');
	let el = document.querySelector("div");
	console.log(el);
</script>
</head>
<body>
	<header>
		<h1>할일 등록</h1>
	</header>
	<article>
		<section>
			<form action="/Todo/todoAdd" method="post">
				<p>어떤일인가요?</p>
				<input type="text" name="title" id="form_title"
					placeholder="swift 공부하기(24자까지)">
				<p>누가 할일인가요?</p>
				<input type="text" name="name" id="form_name" placeholder="홍길동">
				<p>우선순위를 선택하세요</p>
				<input type="radio" id="sequence1" name="sequence" value="1">
				<label for="sequence1">1순위</label> <input type="radio"
					id="sequence2" name="sequence" value="2"> <label
					for="sequence2">2순위</label> <input type="radio" id="sequence3"
					name="sequence" value="3"> <label for="sequence3">3순위</label>
				<div id="buttons">
					<a href="/Todo/main" class="footer_anchor-left">이전</a>
					<a class="footer_anchor-right" id="footer_clear">내용지우기</a>
					<input type="submit" class="footer_anchor-right" >
				</div>
			</form>
		</section>
	</article>
	<footer> </footer>
</body>
</html>