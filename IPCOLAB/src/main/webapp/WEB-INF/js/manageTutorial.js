function editContainer(id) {
		var form = document.getElementById("editForm");
		form.action = "editActivityContainer.action";
		form.children.namedItem("id").value = id;
		form.submit();
	}
	function renameTopic(button) {
		var topicName = button.name;
		var topicId = button.id;
		$('#renameTopic input[name=renameTopicName]').val(topicName);
		$('#renameTopic input[name=renameTopicId]').val(topicId);
		$("#renameTopic").modal("toggle");
	}
	
	function addContainer(button) {
		var topicId = button.id.split("-")[1];
		$('#topicId').val(topicId);
	}
	
	function deleteTopic(deletedTag){
		
		var deleteId = deletedTag.id.split("_")[1];
		var topicNotEmpty = $("#topicNotEmpty_"+deleteId).val();
		
		if(topicNotEmpty=="true"){
			$("#warningDialog").modal("toggle");
		}else{
			var form = document.getElementById("confirmationForm");
			form.action = "deleteTopic.action";
			$("#deletableId").val(deleteId);
			$("#confirmationDialog").modal("toggle");
		}
	}
	
	function deleteActivityContainer(deletedTag){
		
		var deleteId = deletedTag.id.split("_")[1];
		var form = document.getElementById("confirmationForm");
		form.action = "deleteActivityContainer.action";
		$("#deletableId").val(deleteId);
	}
	$(document).ready(function() {
// 		Ajax for renaming the topic name
		$("#changeTopicName").click(function() {
			topicName = $('#renameTopic input[name=renameTopicName]').val();
			topicId = $('#renameTopic input[name=renameTopicId]').val();
			$("#loadingDiv").modal("toggle");
			$("#renameTopic").modal("toggle");
			$.ajax({
				type : "POST",
				url : "renameTopic.action",
				data : "topicName=" + topicName + "&topicId=" + topicId,
				success : function(data) {
					$("#loadingDiv").modal("toggle");
					$("#topic_name_" + topicId)[0].innerHTML = topicName;
					$('#'+topicId).attr('name', topicName);
				}
			});
		});
		
		$(".goBack").on("click",function(e) {
		    e.preventDefault(); // cancel the link itself
		    $("#editForm").attr('action', this.href);
			$("#editForm").submit();
		  });
		
	});