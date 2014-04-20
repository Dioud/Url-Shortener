<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%@include file="/template/header.jsp"%>


<script type="text/javascript" src="/project/js/jquery.js"></script>
<script type="text/javascript" src="/project/js/jquery.jqplot.js"></script>
<script type="text/javascript" src="/project/js/jqplot.pieRenderer.js"></script>
<script type="text/javascript" src="/project/js/jqplot.donutRenderer.js"></script>


<sql:query var="result">
					select * from stats where url_id = ?
					<sql:param value="${url.id}" />
</sql:query>

<div class="row">
	<div class="hero-unit">
		<h1 style="font-size: 40px;">${url.name }</h1>
		<p>NB clicks :${result.rowCount}</p>
		<p>
			<a target="_blank" href="${url.url}">${url.url}</a> <i
				class="icon-arrow-right"></i> <a target="_blank"
				href="http://localhost:8080/project/r?id=${url.urlGenere}">http://localhost:8080/project/r?id=${url.urlGenere}</a>
	</div>
</div>
<div class="row well">
	<h2 style="text-align: center;">Referer</h2>
	<div id="chart2"></div>
	<table class="table table-bordered">
		<tr style="background-color: #EEE;">
			<td><b>Referer</b></td>
			<td><b>Click</b></td>
		</tr>
		<c:forEach items="${stat}" var="p">
			<tr>
				<td>${p.referer}</td>
				<sql:query var="result2">
					select * from stats where referer = ?
					<sql:param value="${p.referer}" />
				</sql:query>
				<td>${result2.rowCount}</td>

			</tr>
		</c:forEach>
	</table>

	<script type="text/javascript">
$(document).ready(function(){
  var data = [
      	<c:forEach items="${stat}" var="p">
			<sql:query var="result3">
				select * from stats where referer = ?
				<sql:param value="${p.referer}" />
			</sql:query>
      		["${p.referer}", ${result3.rowCount}],
		</c:forEach>     
   
  ];
  var plot2 = jQuery.jqplot ('chart2', [data], 
    {
      seriesDefaults: {
        renderer: jQuery.jqplot.PieRenderer, 
        rendererOptions: {
          // Turn off filling of slices.
          fill: false,
          showDataLabels: true, 
          // Add a margin to seperate the slices.
          sliceMargin: 4, 
          // stroke the slices with a little thicker line.
          lineWidth: 5
        }
      }, 
      legend: { show:true, location: 'e' }
    }
  );
});
</script>
</div>
<div class="row well">
	<h2 style="text-align: center;">Country</h2>
	<div id="chart3"></div>
	<table class="table table-bordered">
		<tr style="background-color: #EEE;">
			<td><b>Pays</b></td>
			<td><b>Click</b></td>
		</tr>
		<c:forEach items="${stat}" var="p">
			<tr>
				<td>${p.pays}</td>
				<sql:query var="result2">
					select * from stats where pays = ?
					<sql:param value="${p.pays}" />
				</sql:query>
				<td>${result2.rowCount}</td>

			</tr>
		</c:forEach>
	</table>

	<script type="text/javascript">
$(document).ready(function(){
  var data = [
      	<c:forEach items="${stat}" var="p">
			<sql:query var="result3">
				select * from stats where pays = ?
				<sql:param value="${p.pays}" />
			</sql:query>
      		["${p.pays}", ${result3.rowCount}],
		</c:forEach>     
   
  ];
  var plot2 = jQuery.jqplot ('chart3', [data], 
    {
      seriesDefaults: {
        renderer: jQuery.jqplot.PieRenderer, 
        rendererOptions: {
          // Turn off filling of slices.
          fill: false,
          showDataLabels: true, 
          // Add a margin to seperate the slices.
          sliceMargin: 4, 
          // stroke the slices with a little thicker line.
          lineWidth: 5
        }
      }, 
      legend: { show:true, location: 'e' }
    }
  );
});
</script>
</div>
<%@include file="/template/footer.jsp"%>
