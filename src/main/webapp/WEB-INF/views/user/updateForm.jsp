<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div id="container">
<form>
  <input type="hidden" id="id" value="${principal.user.id }" />
  <div class="form-group m-2">
    <label for="username">username</label>
    <input type="text" name="text" class="form-control" id="username" value="${principal.user.username }" readonly="readonly">
  </div>
  <div class="form-group m-2">
    <label for="pwd">Password</label>
    <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
  </div>
  <div class="form-group m-2">
    <label for="re-pwd">Password</label>
    <input type="password" name="re-password" class="form-control" placeholder="Enter password" id="re-password">
  </div>
  <div class="form-group m-2">
    <label for="email">E-mail</label>
    <input type="text" name="email" class="form-control" placeholder="Enter E-mail" id="email" value="${principal.user.email }">
  </div>
</form>
</div>
<button id="btn-update" class="btn btn-primary m-2">수정</button>

<script src="/js/user/user.js"></script>

<%@ include file="../layout/footer.jsp" %>