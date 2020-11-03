<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="./css/todoForm.css">
<script type="text/javascript">
	function clear() {
		console.log("clear")
	}
</script>
</head>
<body>
	<header>
		<h1>할일 등록</h1>
	</header>
	<article>
		<section>
			<form action="/Todo/todoAdd" method="post">
				<label for="form_title" class="label_text">어떤일인가요?</label>
				<input type="text" name="title" id="form_title"
					placeholder="swift 공부하기(24자까지)" maxlength="24" required>
				<label for="form_name" class="label_text">누가 할일인가요?</label>
				<input type="text" name="name" id="form_name" placeholder="홍길동(12자까지)" maxlength="12" required>
				<div>우선순위를 선택하세요</div>
				<input type="radio" id="sequence1" name="sequence" value="1" required><label for="sequence1" class="label_radio">1순위</label>
				<input type="radio" id="sequence2" name="sequence" value="2"><label for="sequence2" class="label_radio">2순위</label>
				<input type="radio" id="sequence3" name="sequence" value="3"><label for="sequence3" class="label_radio">3순위</label>
				<div id="buttons">
					<a href="/Todo/main" class="buttons_left">＜ 이전</a>
					<input type="reset" class="buttons_right" value="내용지우기">
					<input type="submit" class="buttons_right" >
				</div>
			</form>
		</section>
	</article>
</body>
</html>