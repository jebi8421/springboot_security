<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<%@ include file="layout/header.jsp" %>

<div class="container">
	<c:forEach var="board" items="${boardList.content}">
	<div class="card m-2">
	  <div class="card-body">
	    <h4 class="card-title">${board.title}</h4>
	    <p class="card-text">${board.content}</p>
	    <a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
	  </div>
	</div>
	</c:forEach>
	<ul class="pagination justify-content-center">
	<li class="page-item ${boardList.first ? 'disabled' : '' }"><a class="page-link" href="/board?page=${boardList.number-1 }">Previous</a></li>
	<c:forEach var="i" begin="1" end="${pageNum}">
		<li class="page-item ${boardList.number == i-1 ? 'active' : '' }"><a class="page-link" href="/board?page=${i-1}">${i}</a></li>
	</c:forEach>
	<li class="page-item ${boardList.last ? 'disabled' : '' }"><a class="page-link" href="/board?page=${boardList.number+1}">Next</a></li>
	</ul>
</div>

<%@ include file="layout/footer.jsp" %>