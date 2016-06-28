<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up</title>




<link rel="stylesheet" href="logMenu/css/style.css">




</head>

<body>

	<div id="wrapper">
		<div class="join">Please Try Again!</div>
		<div class="lock"></div>
		<div class="clr"></div>
		<div class="login-options">
			<p>
				the eMail
				<%
				out.println(request.getParameter("femail"));
			%>
				is already in use
			</p>
		</div>

		<a class="facebook" href="./logMenu/fbCreate.html">sign up with
			Facebook</a>

		<div class="clr">
			<hr />
		</div>
		<div class="mail-text">Or sign up using your email address.</div>
		<div class="forms">
			<form action="./ServletCreateAcc" method="post" name="register">
				<input name="username"
					placeholder="Enter your username or just name..." size="70"
					onClick="border: 1px solid #30a8da;" id="username" required /> <input
					name="email" type="text" placeholder="Enter your email address..."
					size="70" onClick="border: 1px solid #30a8da;" id="email" required />
				<input name="password" type="password"
					placeholder="Enter a password..." size="70"
					onClick="border: 1px solid #30a8da;" id="password" required />

				<button class="create-acc">Create Account</button>
				<div align="right">
					<a class="mail-text" href="./logMenu/index.html">Log In</a>
				</div>
			</form>
		</div>
	</div>





</body>
</html>
