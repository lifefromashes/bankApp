import {saveTokenInCookie, readCookie, logout, setCookieHeader} from "./cookieUtil";
import {server} from "./webAddress";



export const apiCall = (body, verb, url, useToken) => {
    var urlString = server() + url;
    var req = new XMLHttpRequest();

    req.open(verb, urlString);
    req.setRequestHeader('Content-Type', 'application/json');
    if(useToken){ setCookieHeader(req); }


    
    

    if(body != null){
        body = JSON.stringify(body);
        req.send(body);
    } else {
        req.send();
    }

    return req;
}