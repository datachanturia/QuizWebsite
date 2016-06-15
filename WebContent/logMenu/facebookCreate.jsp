<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id)) {
				return;
			}
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
		
		// initialize and setupe facebook js sdk	
		window.fbAsyncInit = function() {
			FB.init({
				appId : '1248881405129739',
				xfbml : true,
				version : 'v2.6'
			});

			FB.getLoginStatus(function(response) {
				if (response.status === 'connected') {
					getInfo();
				} else if (response.status === 'not_authorized') {
					login();

				} else {

				}
			});

		};

		function login() {
			FB.login(function(response) {
				if (response.status === 'connected') {
					getInfo();
				} else if (response.status === 'not_authorized') {
					login();
				} else {
					login();
				}
			}, {
				scope : 'email, user_birthday, user_location, user_hometown'
			});
		}
		function getInfo() {
			console.log('Welcome!  Fetching your information.... ');
			FB
					.api(
							'/me?fields=name, email, id, picture.width(150).height(150)',
							'GET',
							function(response) {
								console.log('Good to see you, ' + response.name
										+ '.' + ' Email: ' + response.email
										+ ' Facebook ID: ' + response.id
										+ ' Facebook Photo: '
										+ response.picture.data.url);
								//	var userfirstName = response.first_name;
								//	var lastName = response.last_name;
								//	var useremail = response.email;
								//	var usersex = response.gender;
								//	var userbithday = response.birthday;
								//	var hometown = response.hometown;
								//	var location = response.location;

								document.getElementById('username').value = response.name;
								document.getElementById('password').value = response.id;
								document.getElementById('email').value = response.email;
								document.getElementById('photo').value = response.picture.data.url;
								//alert(hometown);

							});
		}
	</script>
	<div id="status"></div>
	<br>

	<form action="../ServletCreateFacebook" method="get" name="register"
		id="myForm">
		<input name="username" type="hidden" id="username" /> <input
			name="password" type="hidden" id="password" /> <input name="email"
			type="hidden" id="email" /> <input name="photo" type="hidden"
			id="photo" />

		<button onclick="getInfo()">login</button>
	</form>
	<button onclick="login()">bla</button>

</body>
</html>