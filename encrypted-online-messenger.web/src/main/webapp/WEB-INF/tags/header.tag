<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="top-head-div">
	<a class="active" href="homepage">Home</a> <a href="#">Settings</a>
	<div class="topnav-right">
		<c:if test="${loggedInUser != null}">
			<a href="#">User: ${loggedInUser.nickName}</a>
			<a href="signout">Sign Out</a>
		</c:if>
		<c:if test="${loggedInUser == null}">
			<a href="signin">Sign In</a>
			<a href="signup">Sign up</a>
		</c:if>
	</div>
</div>