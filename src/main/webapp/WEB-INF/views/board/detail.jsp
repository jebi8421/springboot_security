<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
  <button class="btn btn-secondary" onclick="history.back();">돌아가기</button>
  <c:if test="${principal.username eq board.user.username }">
  <button id="btn-update-form" class="btn btn-warning">수정</button>
  <button id="btn-delete" class="btn btn-danger">삭제</button>
  </c:if>
  <br/><br/>
  <div>
  	글번호 : <span id="id"><i>${board.id }</i></span> / 
  	작성자 : <span id="username"><i>${board.user.username }</i></span> /
  	작성일 : <span id="createDate"><i><fmt:formatDate value="${board.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></i></span> /
  	조회수 : <span><i>${board.count }</i></span>
  </div>
  <br/>
  <div class="form-group m-2">
    <h3>${board.title}</h3>
  </div>
  <hr />
  <div class="form-group m-2">
    <div>${board.content }</div>
  </div>
  <br/>
  <div class="card">
  	<div class="card-body"><textarea class="form-control" id="content" rows="1"></textarea></div>
  	<div class="card-footer"><button id="reply-save" class="btn btn-primary" style="float:right;">댓글등록</button></div>
  </div>
  <br/>
  <div class="card">
  	<div class="card-header">댓글리스트</div>
  	<ul class="list-group">
      <li class="list-group-item">First Item</li>
  	</ul>
  </div>
  <br/>
</div>

<script src="/js/reply/reply.js"></script>
<%@ include file="../layout/footer.jsp" %>