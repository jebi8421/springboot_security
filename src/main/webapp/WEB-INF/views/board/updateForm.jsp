<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
<form>
  <input type="hidden" id="id" value="${board.id }" />
  <div class="form-group m-2">
    <label for="username">제목</label>
    <input type="text" class="form-control" placeholder="Enter title" id="title" value="${board.title }">
  </div>
  <div class="form-group m-2">
    <label for="pwd">본문</label>
    <textarea class="form-control summernote" rows="5" id="content">${board.content }</textarea>
  </div>
</form>
<button id="btn-update" class="btn btn-primary m-2">수정하기</button>
</div>

<script src="/js/board/board.js"></script>
<script>
$('.summernote').summernote({
	tabsize: 2,
	height: 300
});
</script>

<%@ include file="../layout/footer.jsp" %>