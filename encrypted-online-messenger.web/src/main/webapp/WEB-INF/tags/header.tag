<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="right-nav">
	<div class="login-sr">
		<div class="login-signup">
			<ul>
				<c:if test="${loggedInUser != null}">
					<li><a href="my-profile"><fmt:message key="welcome.lbl"
								bundle="${rb}" /> ${loggedInUser.nickName}</a></li>
					<li><a class="custom-b" href="signout"><fmt:message
								key="signout.btn" bundle="${rb}" /></a></li>
				</c:if>
				<c:if test="${loggedInUser == null}">
					<li><a href="signin">Sign In</a></li>
					<li><a class="custom-b" href="signup">Sign up /></a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>