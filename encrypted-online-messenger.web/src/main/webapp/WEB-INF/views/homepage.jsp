<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Encrypted Messenger</title>


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
		Friends:
		<ul>
			<c:if test="${loggedInUser != null}">
				<c:forEach items="${friends}" var="friend">
					<li><a href="chat?phone=${friend.phone}">
							<div class="box">
								<h4>${friend.nickName}</h4>
							</div>
					</a></li>
				</c:forEach>
			</c:if>
			<c:if test="${loggedInUser == null}">
				<li>Log-In to view Friends</li>
			</c:if>
		</ul>
	</div>




</body>
</html>


