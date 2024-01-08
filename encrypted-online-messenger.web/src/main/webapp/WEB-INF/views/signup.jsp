<%@ taglib prefix="shop" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="chat" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Sign Up</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/main.css">

</head>
<body>
	<header id="header" class="top-head">
		<chat:header />
	</header>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form validate-form" action="signup"
					method="POST">
					<span class="login100-form-title p-b-26"> Registration 1 </span>
					<div class="wrap-input100 validate-input">
						Nick Name<input class="input100" type="text" name="nickName">
						<span class="focus-input100" data-placeholder="Nick Name"></span>
					</div>

					<div class="wrap-input100 validate-input">
						Phone<input class="input100" type="text" name="phone"> <span
							class="focus-input100" data-placeholder="Phone"></span>
					</div>

					<div class="wrap-input100 validate-input">
						Password<input class="input100" type="password" name="password">
						<span class="focus-input100" data-placeholder="Password"></span>
					</div>

					<div class="wrap-input100 validate-input"
						data-validate="Repeat password">
						Repeat Password<input class="input100" type="password"
							name="repeatPassword"> <span class="focus-input100"
							data-placeholder="Repeat Password"></span>
					</div>

					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button class="login100-form-btn">Sign Up</button>
						</div>
					</div>

					<c:if test="${errMsg != null}">
						<div class="container-login100-form-btn">
							<span class="txtErr">${errMsg}</span>
						</div>
					</c:if>
					<c:remove var="errMsg" />



				</form>
				<div class="text-center p-t-115">
					<span class="txt1"> Already have an account? </span> <a
						class="txt2" href="signin"> Sign In </a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>