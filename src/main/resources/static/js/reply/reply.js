let index = {
	init: function() {
		$("#reply-save").on("click", ()=> {
			this.save();
		});
	},
	
	
	save: function() {
		let content = $("#content").val();
		
		fetch("/api/reply", {
			headers:{
				"Content-type": "application/json"
			},
			
		})
	}
}

index.init();