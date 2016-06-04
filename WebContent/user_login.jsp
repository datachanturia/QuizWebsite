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
		// initialize and setupe facebook js sdk	
		window.fbAsyncInit = function() {
			FB.init({
				appId : '1248881405129739',
				xfbml : true,
				version : 'v2.6'
			});
			FB
					.getLoginStatus(function(response) {
						if (response.status === 'connected') {
							document.getElementById('status').innerHTML = 'we are connected';
							document.getElementById('login').style.visibility = 'hidden';
						} else if (response.status === 'not_authorized') {
							document.getElementById('status').innerHTML = 'we are not logged in';
						} else {
							document.getElementById('status').innerHTML = 'you are not logged in Facebook';
						}
					});
		};

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

		function login() {
			FB
					.login(
							function(response) {
								if (response.status === 'connected') {
									document.getElementById('status').innerHTML = 'we are connected';
									document.getElementById('login').style.visibility = 'hidden';
								} else if (response.status === 'not_authorized') {
									document.getElementById('status').innerHTML = 'we are not logged in';
								} else {
									document.getElementById('status').innerHTML = 'you are not logged in Facebook';
								}
							},
							{
								scope : 'email, user_birthday, user_location, user_hometown'
							});
		}

		function getInfo() {
			console.log('Welcome!  Fetching your information.... ');
			FB.api('/me', 'GET', {fields: 'first_name, last_name, name, id, picture.width(150).height(150)'}, function(response) {
				console.log('Good to see you, ' + response.name + '.'
						+ ' Email: ' + response.email + ' Facebook ID: '
						+ response.id);
				var userfirstName = response.first_name;
				var lastName = response.last_name;
				var useremail = response.email;
				var usersex = response.gender;
				var userbithday = response.birthday;
				var hometown = response.hometown; 
				var location = response.location;
				document.getElementById('status').innerHTML = "<img src='" + response.picture.data.url + "'>";
				//alert(hometown);

			});
		}
	</script>
	<div id="status"></div>

	<button onclick="getInfo()">Get Info</button>
	<button onclick="login()" id="login">Login</button>

</body>
</html>