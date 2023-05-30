<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title></title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/main.css">
	<!-- change to match your file/naming structure -->
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/app.js"></script>
	<!-- change to match your file/naming structure -->
	</head>
	<body>
	<div class="container m-2">
		<div class="form-group row p-3">
		    <label for="score" class="col-sm-4 col-form-label">Your Gold:</label>
		    <div class="col-sm-8">
		      <input type="text" readonly class="form-control" id="score" value="<c:out value="${gold }" />">
		    </div>
	  	</div>
	
  <div class="row">
    <div class="col-sm">
		<form class="card p-3" action="/" method="post">
			<h5 class="card-title">Farm</h5>
			<p class="card-text">(Earns 10-20 gold)</p>
			<input type="hidden" name="lugar" value="farm" />
  			<button type="submit" class="btn btn-primary">Find Gold!</button>
		</form>
    </div>
    <div class="col-sm">
      <form class="card p-3" action="/" method="post">
			<h5 class="card-title">Cave</h5>
			<p class="card-text">(Earns 5-10 gold)</p>
			<input type="hidden" name="lugar" value="cave" />
  			<button type="submit" class="btn btn-primary">Find Gold!</button>
		</form>
    </div>
    <div class="col-sm">
      <form class="card p-3" action="/" method="post">
			<h5 class="card-title">House</h5>
			<p class="card-text">(Earns 2-5 gold)</p>
			<input type="hidden" name="lugar" value="house" />
  			<button type="submit" class="btn btn-primary">Find Gold!</button>
		</form>
    </div>
    <div class="col-sm">
      <form class="card p-3" action="/" method="post">
			<h5 class="card-title">Casino</h5>
			<p class="card-text">(Earns 0-50 gold)</p>
			<input type="hidden" name="lugar" value="casino" />
  			<button type="submit" class="btn btn-primary">Find Gold!</button>
		</form>
    </div>
  </div>
  
   <div class="form-group mt-3">
    <label for="exampleFormControlTextarea1">Activities:</label>
    <div class="card p-3 Scroll">
    
       	<c:forEach var="actividad" items="${actividades }">
		<c:if test="${actividad.contains('earned') }">

			<p class="text-success">
				<c:out value="${actividad }" />
			</p>
		</c:if>
		<c:if test="${actividad.contains('lost') }">

			<p class="text-danger">
				<c:out value="${actividad }" />
			</p>
		</c:if>
	</c:forEach>
	</div>
  </div>
    <a class="btn btn-primary" href="/reset" role="button">Reset</a>
</div>

	</body>
</html>