import React, { Component } from "react";
import {Link} from 'react-router-dom';
import {saveTokenInCookie, readCookie, logout, setCookieHeader} from "../cookieUtil";
import {parseBankUser, parseUserByID, parseAccounts} from "../parseBankUser";

export default class User extends Component {
  constructor(props) {
    super(props);

    this.state = {
        sample: "",
        username: "",
        password: "",
        loginErrors: "",
        accounts: [],
        accountIndex: 0
    };



    var req = new XMLHttpRequest();
    var urlString = "http://localHost:8080/User";
    req.open('GET', urlString);
    req.setRequestHeader('Content-Type', 'application/json');
    var jwt = readCookie("jwt");
    setCookieHeader(req);
    req.send();
    req.addEventListener('load', () => {
      if(req.status >= 200 && req.status < 400){
        //document.getElementById("userTitle").title = JSON.parse(req.responseText).username;
        var t = document.getElementById("userTitle");
        t.innerHTML = "<p>" + JSON.parse(req.responseText).firstName + "</p>";

        var al = document.getElementById("accountList");
        al.innerHTML = parseAccounts(req);

        var accts = JSON.parse(req.responseText).bankAccounts.length;
        //if(b != null){
          for(let i=0; i<accts; i++){
            let b = document.getElementById("accountID" + i);
            this.state.accounts[this.state.accountIndex] = b;
            this.state.accountIndex ++;
            b.addEventListener('click', () => {

              window.location = "history/" + JSON.parse(req.responseText).bankAccounts[i].accountNumber;
            });

          }
        //}

      }
    })


  }




  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }



  render() {
    return (
      <>
      <head>
        <title>MERIT BANK</title>
        <link rel="stylesheet" type="text/css" href= "../App.css" />
      </head>
      <body>

        <header>
          <div class="main">
            <div class="logo">
              <img />
            </div>
            <ul>
              <li><a href="/login">Sign Off</a></li>
              <li><a href="/user">Welcome, USER</a></li>
              <li><a href="#">Mobile</a></li>
            </ul>
          </div>

        <div id="userTitle"></div>

          <div>

              <input
                type="sample"
                name="sample"
                placeholder="Sample"
                value={this.state.sample}
                onChange={this.handleChange}
                required
              />



              {/*<button onClick={this.loginRequest}>Login</button>*/}

          </div>


      <div id="accountList"></div>



      </header>
      </body>
      </>
    );
  }
}
