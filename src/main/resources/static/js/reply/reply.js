let index = {
	init: function() {
		$("#reply-save").on("click", ()=> {
			this.save();
		});
		
		$(".reply-del").on("click", (e)=> {
			this.del(e);
		})
	},
	
	
	save: function() {
		const id = $("#boardId").val();
		
		let data = {
			content: $("#content").val(),
		}
		
		fetch(`/api/board/${id}/reply`, {
			method: "POST",
			headers:{
				"Content-Type": "application/json"
			},
			body: JSON.stringify(data)
		})
		.then(res=>res.json())
		.then(data=>{
			if(data.data == 1) {
				alert("등록되었습니다.")
				location.href=`/board/${id}`;
			}
		})
	},
	
	del: function(e) {
		const id = e.target.dataset.id;
		
		fetch(`/api/board/${id}/reply`, {
			method: "DELETE",
			headers: {
				"Content-Type": "application/json"
			}
		})
		.then(res => res.json())
		.then(data => {
			if(data.data == 1) {
				alert("삭제했습니다.");
				window.location.reload();
			}
		})
	}
}

index.init();