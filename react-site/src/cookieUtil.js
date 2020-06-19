export const readCookie = (name) => {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}


export const logout = ()  => {
	document.cookie = "jwt=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
	window.location = "index.html";
}


export const setCookieHeader = (req) => {

    var jwt = readCookie("jwt");
    req.setRequestHeader('Authorization', 'Bearer ' + jwt);

}

export const saveTokenInCookie = (req) => {
    var cvalue = JSON.parse(req.responseText).jwt;
    var d = new Date();
	d.setTime(d.getTime() + (1 * 60 * 60 * 1000));
	var expires = "; expires=";
	var cookie = "jwt=" + cvalue + "; " + expires + d + '; path=/';
		
	document.cookie = cookie;
}



