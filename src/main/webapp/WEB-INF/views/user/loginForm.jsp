<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div id="container">
<form action="/auth/loginProc" method="POST">
  <div class="form-group m-2">
    <label for="username">Username</label>
    <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
  </div>
  <div class="form-group m-2">
    <label for="pwd">Password</label>
    <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
  </div>
  <!-- <div class="form-group form-check m-2">
    <label class="form-check-label">
      <input class="form-check-input" name="remember" type="checkbox"> Remember me
    </label>
  </div> -->
  <button type="submit" id="btn-login" class="btn btn-primary m-2">로그인</button>
  <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=5d15758a5c0bd030b48d7c8b8a0988dc&redirect_uri=http://localhost:8080/auth/kakao/callback" ><img src="/images/kakao.png" height="38px"/></a>
</form>
</div>

<!-- <script src="/js/user/user.js"></script> -->

<%@ include file="../layout/footer.jsp" %>