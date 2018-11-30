<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h2 class="text-center">ToDo App</h2>
		<c:if test="${ todo == null }">
			<form action="TodoServlet" method="post">
				<div class="row text-center">
					<div class="col col-md-8">
						<input type="text" class="form-control" name="name">
					</div>
					<div class="col col-md-2">
						<input type="submit" class="btn btn-primary" value="Add" name="submit">
					</div>
				</div>
			</form>
		</c:if>
		
		<c:if test="${ todo != null }">
			<form action="TodoServlet" method="post">
				<div class="row text-center">
					<div class="col col-md-8">
						<input type="hidden" value="${ todo.id }" name="id">
						<input type="text" class="form-control" name="name" value="${ todo.name }">
					</div>
					<div class="col col-md-2">
						<input type="submit" class="btn btn-primary" value="Modify" name="submit">
					</div>
				</div>
			</form>
		</c:if>
		
		<table class="table mt-5">
			<tbody>
				<c:forEach var="todo" items="${ todos }">
					<tr>
						<td>${ todo.name }</td>
						<td>
							<form action="TodoServlet" method="post">
								<input type="hidden" value="${ todo.id }" name="id">
								<input type="submit" class="btn btn-danger" value="Delete" name="submit">
								<input type="submit" class="btn btn-warning" value="Edit" name="submit">
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>