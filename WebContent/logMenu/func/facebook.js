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
							getInfo();
						} else if (response.status === 'not_authorized') {
							
						} else {
							
						}
					},
					{
						scope : 'email, user_birthday, user_location, user_hometown'
					});
}

function getInfo() {
	console.log('Welcome!  Fetching your information.... ');
	FB.api('/me?fields=name, email, id, picture.width(150).height(150)', 'GET',
			function(response) {
				console.log('Good to see you, ' + response.name + '.'
						+ ' Email: ' + response.email + ' Facebook ID: '
						+ response.id + ' Facebook Photo: '
						+ response.picture.data.url);
				var userfirstName = response.first_name;
				var lastName = response.last_name;
				var useremail = response.email;
				var usersex = response.gender;
				var userbithday = response.birthday;
				var hometown = response.hometown;
				var location = response.location;

				// alert(hometown);

			});
}