function freshMessage() {
	$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath }/message/messageAction_getUserMessageCount.action"
			})
}
freshMessage();