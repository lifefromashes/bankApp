document.getElementById('btnLogin').addEventListener("click", login );

function login() {
	var userName = document.getElementById('username').value; 
	var passWord = document.getElementById('password').value;
	
	// construct an HTTP request with the user info in the body
	var req = new XMLHttpRequest();
	var body = '{"username": "' + userName + '", ';
	body += '"password": "' + passWord + '"}';
	
	var urlString = "http://localHost:8080/authenticate";
	req.open('POST', urlString);
	req.setRequestHeader('Content-Type', 'application/json');
	req.send(body);
	
	req.onload = function() { 
		
		if(req.status >= 200 && req.status < 400){
			// when we receive a valid response save the response as a cookie 
			// NOTE: in Chrome, this will only work once the site is hosted, Chrome does
			// not allow local sites to store cookies
			
			var cvalue = JSON.parse(req.responseText).jwt;
			var d = new Date();
			d.setTime(d.getTime() + (1 * 60 * 60 * 1000));
			var expires = "; expires=";
			var cookie = "jwt=" + cvalue + "; " + expires + d + '; path=/';
		
			document.cookie = cookie;
			var c = document.cookie;
			
			// create a new HTTP request with the cookie to get a redirect
			var req2 = new XMLHttpRequest();
			var urlString = "http://localHost:8080/direct";
			req2.open('GET', urlString);
			req2.setRequestHeader('Content-Type', 'application/json');
			var jwt = readCookie("jwt");
			req2.setRequestHeader('Authorization', 'Bearer ' + jwt);
			req2.send();
			req2.onload = function() { 
				// when we receive a valid response go to the url provided
				if(req.status >= 200 && req.status < 400){
					window.location = req2.responseText;
				}
			}
			
		} else {
			// bad username / password echo printing will go here
		}
	}
}