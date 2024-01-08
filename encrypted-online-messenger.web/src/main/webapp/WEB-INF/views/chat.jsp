<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="chat" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Chat with ${friend.nickName}</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/main.css">

</head>
<body>
	<header id="header" class="top-head">
		<chat:header />
	</header>

	<div class="friends-list">
		Chat with ${friend.nickName}:
		<ul>
			<c:if test="${loggedInUser != null}">
				<c:forEach items="${messages}" var="message">
					(${message.time}) ${message.sender.nickName}: ${message.body}
					</br>
				</c:forEach>
			</c:if>
			<c:if test="${loggedInUser == null}">
				<li>Log-In to see Chat</li>
			</c:if>
		</ul>
		<form class="draft" action="draft"
			method="POST">
			<div class="wrap-input100">
			 	<input type="hidden" name="receiver" value="${friend.phone}" />
				<input class="input100" type="text" name="draft">
			</div>

			<div class="wrap-login100-form-btn">
				<button class="login100-form-btn">Send</button>
			</div>

		</form>
	</div>




</body>
</html>


