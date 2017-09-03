<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
%>
<%@ taglib
	prefix="s"
	uri="/struts-tags"
%>
<!DOCTYPE html>
<html>
<head>
<link
	rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"
></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
></script>
<script
	type="text/javascript"
	src="/ImageIng/js/custom.js"
></script>
<style type="text/css">
label>input { /* HIDE RADIO */
	visibility: hidden; /* Makes input not-clickable */
	position: absolute; /* Remove input from document flow */
}

label>input+img { /* IMAGE STYLES */
	cursor: pointer;
	border: 2px solid transparent;
}

label>input:checked+img { /* (RADIO CHECKED) IMAGE STYLES */
	border: 2px solid #f00;
}
</style>
<meta
	http-equiv="Content-Type"
	content="text/html; charset=ISO-8859-1"
>
<title>Insert title here</title>
</head>

<body>
	<a
		class="btn"
		href="task"
	>Create</a>
	<br>
	<input
		type="hidden"
		id="taskId"
		value='<s:property value="%{task.taskId}"/>'
	>
	<input
		type="hidden"
		id="opponent"
		value='<s:property value="%{task.opponent}" />'
	>
	<button
		class="btn btn-primary"
		type="button"
		id="submit"
	>Submit</button>
	<button
		class="btn btn-danger"
		type="button"
		id=""
	>Exit</button>
	<a
		class="btn"
		href="logout"
	>Logout</a>
	<br>
	<div class="container-fluid">
		<s:iterator
			value="%{task.questions}"
			var="question"
		>
			<form>
				<div
					class="container"
					id="questions"
				>
					<img
						class="question"
						value=""
						alt="<s:property value='%{#question.qId}' />"
						src="/ImageIng/images/<s:property value='%{#question.qId}' />.jpg"
						width="200px"
						height="200px"
					>
				</div>
				<div class="row">
					<s:iterator
						value="%{#question.answers}"
						var="sub"
					>
						<div class="col-sm-3">
							<label> <input
								type="radio"
								name="fb"
								value="<s:property value='%{#sub.ansId}'/>"
							/> <img
								src="/ImageIng/images/<s:property value='%{#sub.ansId}'/>.jpg"
								width="100px"
								height="100px"
							>
							</label>
						</div>
					</s:iterator>
				</div>
			</form>

		</s:iterator>

	</div>


</body>
</html>