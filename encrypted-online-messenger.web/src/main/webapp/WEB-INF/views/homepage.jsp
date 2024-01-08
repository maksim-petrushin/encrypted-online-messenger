<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="chat" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Encrypted Messenger</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/main.css">

</head>
<body>

	<header id="header" class="top-head">
		<chat:header />
	</header>
	<main>
		<aside class="friends-list">
			<c:if test="${loggedInUser != null}">
				<div class="body-container">
					<div class="chats">
						<c:forEach items="${friends}" var="friend">
							<a href="chat?phone=${friend.phone}">
								<div class="chat-box">
									<div class="chat-container">
										<div class="friend-line">
											<h3>${friend.nickName}</h3>
											<span>06:04 PM</span>
										</div>
										<div class="last-message">
											<p>Last Message asdasd</p>
										</div>
									</div>
								</div>
							</a>
						</c:forEach>
					</div>

				</div>
			</c:if>
			<c:if test="${loggedInUser == null}">
				<h3>Log-In to view Friends</h3>
			</c:if>
		</aside>
		<section class="content">
			<c:if test="${loggedInUser != null}">
				<div class="container" id="chatBox">

					<div class="content-header">
						<div class="details">
							<c:if test="${friend != null}">
								<h3>${friend.nickName}</h3>
							</c:if>
						</div>
					</div>


					<div class="chat-with-friend-container">

						<div class="chat-with-friend-window">
							<c:if test="${messages != null}">
								<c:forEach items="${messages}" var="message">

									<c:if test="${message.sender.phone == friend.phone}">
										<div class="message-block  friends-text">
											<div class="chat-msg">
												<p>${message.body}</p>
												<span class="time">${message.time}</span>
											</div>
										</div>
									</c:if>
									<c:if test="${message.sender.phone != friend.phone}">
										<div class="message-block  my-text">
											<div class="chat-msg">
												<p>${message.body}</p>
												<span class="time">${message.time}</span>
											</div>
										</div>
									</c:if>
								</c:forEach>
							</c:if>
						</div>

					</div>

					<div>
						<c:if test="${friend != null}">
							<form class="message-box" action="draft" method="POST" autocomplete="off">
								<div class="message-content">
									<input type="hidden" name="receiver" value="${friend.phone}" />
									<input type="text" placeholder="Message" name="draft">
								</div>
								<input class="send-message-button" type="submit" value="Send" />
							</form>
						</c:if>
					</div>
				</div>
			</c:if>

			<c:if test="${loggedInUser == null}">
				<h3>Log-In to see Chat</h3>
			</c:if>
		</section>
	</main>
</body>
</html>


