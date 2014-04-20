<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>


<%@include file="/template/header.jsp"%>


<div class="row">
	<div class="hero-unit">
		<h1 style="font-size: 40px;">SUPINFO Another Url Shortener</h1>
	</div>
</div>
<div class="row well">
	<div class="span12">
		<form class="form-inline" action="addUrl" method="post"
			style="text-align: center;">
			<input class="input-large" type="text" placeholder="Name" id="name"
				name="name"> <input class="input-large" type="text"
				placeholder="Url" id="url" name="url">
			<button type="submit" class="btn">Generate</button>
		</form>


	</div>
</div>
<div class="row well">

	<table class="table table-bordered">
		<tr style="background-color: #EEE;">
			<td><b>Name</b></td>
			<td><b>Url</b></td>
			<td><b>Url générée</b></td>
			<td><b>Click</b></td>
			<td><b>Date</b></td>
			<td><b>Action</b></td>
		</tr>
		<c:forEach items="${urls}" var="p">
			<tr>
				<td><a href="showUrl?id=${p.id}">${p.name}</a></td>
				<td><a target="_blank" href="${p.url}">${p.url}</a></td>
				<td><a target="_blank" href="http://localhost:8080/project/r?id=${p.urlGenere}">http://localhost:8080/project/r?id=${p.urlGenere}</a></td>
				<sql:query var="result">
					select * from stats where url_id = ?
					<sql:param value="${p.id}" />
				</sql:query>
				<td><a href="showUrl?id=${p.id}">${result.rowCount}</a></td>
				<td>${p.date}</td>
				<td>
				<a  style="margin-right: 10px;" title="Statistique" href="showUrl?id=${p.id}"><i	class="icon-eye-open"></i></a>
				<c:if test="${p.enable == false}">
						<a  style="margin-right: 10px;" title="Activer" href="enableUrl?id=${p.id}&enable=1"><i class="icon-ok"></i>
						</a>
					</c:if> 
					<c:if test="${p.enable == true}">
						<a  style="margin-right: 10px;" title="Desactiver" href="enableUrl?id=${p.id}&enable=0"><i class="icon-off"></i></a>
					</c:if> 
					<a title="Supprimer" href="removeUrl?id=${p.id}"><i	class="icon-remove"></i></a>
					</td>
			</tr>
		</c:forEach>
	</table>

</div>
<%@include file="/template/footer.jsp"%>
