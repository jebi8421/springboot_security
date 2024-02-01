<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div id="container">
<form>
  <div class="form-group m-2">
    <label for="username">Username</label>
    <input type="text" class="form-control" placeholder="Enter username" id="username">
  </div>
  <div class="form-group m-2">
    <label for="pwd">Password</label>
    <input type="password" class="form-control" placeholder="Enter password" id="password">
  </div>
  <div class="form-group m-2">
    <label for="pwd">Email</label>
    <input type="email" class="form-control" placeholder="Enter Email" id="email">
  </div>
</form>
</div>
<button id="btn-save" class="btn btn-primary m-2">가입</button>

<script src="/js/user/user.js"></script>

<%@ include file="../layout/footer.jsp" %>