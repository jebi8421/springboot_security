let index = {
	init : function() {
		$("#btn-save").on("click", ()=>{
			this.save();
		});
		
		$("#btn-update-form").on("click", ()=>{
			const id = $("#id").text();
			location.href=`/board/${id}/updateForm`;
		});
		
		$("#btn-update").on("click", ()=>{
			this.update();
		});
		
		$("#btn-delete").on("click", ()=>{
			this.delete();
		})
	},
	
	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		
		fetch("/api/board", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(data)
		})
		.then(res=>res.json())
		.then(data=>{
			if(data.data == 1) {
				alert("작성되었습니다.")
				location.href="/board";
			}
		});
	},

	update: function() {
		const id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		}
		
		fetch(`/api/board/${id}`, {
			method: "PUT",
			headers: {
				"Content-type": "application/json"
			},
			body: JSON.stringify(data)
		})
		.then(res => res.json())
		.then(data=>{
			if(data.data == 1) {
				alert("수정되었습니다.");
				location.href="/board";
			}
		});
	},
	
	delete: function() {
		const id = $("#id").text();
		
		let data = {
			username: $("#username").text()
		}
		
		fetch("/api/board/"+id, {
			method: "DELETE",
			headers: {
				"Content-type": "application/json"
			},
			body: JSON.stringify(data)
		})
		.then(res => res.json())
		.then(data => {
			if(data.data == 1) {
				alert("삭제되었습니다.");
				location.href="/board";
			} else {
				alert(data.status);
				location.href="/board";
			}
		})
	}
}

index.init();