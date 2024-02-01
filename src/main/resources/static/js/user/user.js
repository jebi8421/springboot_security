let index = {
	init: function() {
		$('#btn-save').on('click', ()=>{
			this.save();
		});
		
		$('#btn-login').on('click', ()=>{
			this.login();
		});
		
		$('#btn-update').on("click",()=>{
			this.update();
		});
	},
	
	save: function(){
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		}
		
		if(data.username.length < 1) {
			alert("username을 입력하세요.");
			$("#username").focus();
			return;
		}
		if(data.password.length < 1) {
			alert("password를 입력하세요.");
			$("#password").focus();
			return;
		}
		if(data.email.length < 1) {
			alert("email을 입력하세요.");
			$("#email").focus();
			return;
		}
	
		fetch("/auth/joinProc", {
			method: "POST",
			headers: {
		    	"Content-Type": "application/json",
		  	},
			body: JSON.stringify(data)
		})
		.then(res => res.json())
		.then(data => {
			if(data.data == 1) {
				alert("회원가입이 완료되었습니다.");
				location.href="/auth/loginForm";
			}
		});
	}, 
	
	login: function(){
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		}
		
		if(data.username.length < 1) {
			alert("username을 입력하세요.");
			$("#username").focus();
			return;
		}
		if(data.password.length < 1) {
			alert("password를 입력하세요.");
			$("#password").focus();
			return;
		}
	
		fetch("/api/user/login", {
			method: "POST",
			headers: {
		    	"Content-Type": "application/json",
		  	},
			body: JSON.stringify(data)
		})
		.then(res => res.json())
		.then(data => {
			if(data.data == 1) {
				alert("로그인이 되었습니다.");
				location.href="/";
			} else {
				alert("사용자 정보가 올바르지 않습니다.");
				location.href="/auth/loginForm";
			}
		})
	},
	
	update: function() {
		const id = $('#id').val();
		const password = $('#password').val();
		const re_password = $('#re-password').val();
		
		if(password != re_password) {
			alert("비밀번호를 확인해 주세요.");
			$('#password').focus();
			return null;
		}
		
		let data = {
			username: $('#username').val(),
			password: password,
			email: $('#email').val()
		}
		
		fetch(`/api/user/${id}`, {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(data)
		})
		.then(res => res.json())
		.then(data => {
			if(data.data == 1) {
				alert("수정되었습니다.");
				location.href="/board";
			}
		})
	}
}

index.init();