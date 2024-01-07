<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Chat with ${friend.nickName}</title>


</head>
<body>
	<header id="header" class="top-head">
		<div class="right-nav">
			<div class="login-sr">
				<div class="login-signup">
					<ul>
						<c:if test="${loggedInUser != null}">
							Welcome, ${loggedInUser.nickName}
							<li><a class="custom-b" href="signout">Sign Out</a></li>
						</c:if>
						<c:if test="${loggedInUser == null}">
							<li><a href="signin">Sign In</a></li>
							<li><a class="custom-b" href="signup">Sign up /></a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
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


