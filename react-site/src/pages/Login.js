import React, { Component } from "react";

import {Link} from 'react-router-dom';
import axios from "axios";
import {saveTokenInCookie, readCookie, logout, setCookieHeader} from "../cookieUtil";
import {server} from "../webAddress";
import {apiCall} from "../netcode";
import {sterilizeString} from "../sterilize";

export default class Login extends Component {
  constructor(props) {
    super(props);

    this.state = {
      username: "",
      password: "",
      loginErrors: ""
    };

    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.loginRequest = this.loginRequest.bind(this);
  }

  loginRequest(){
    console.log("creating login request...");

    var body = {
      username: sterilizeString(this.state.username),
      password: sterilizeString(this.state.password)
    }

    var req = apiCall(body, 'POST', "authenticate", false);

    req.addEventListener('load', () => {
      console.log("got response");

        if(req.status >= 200 && req.status < 400){

          saveTokenInCookie(req);

          var req2 = apiCall(null, 'GET', "direct", true);
          req2.addEventListener('load', () => {
            if(req.status >= 200 && req.status < 400){
              window.location = req2.responseText;
            }
          })






        } else {
          console.log(req.status);
          console.log(this.state.username + " " + this.state.password + ".");

          window.alert("Invalid username / password combo.");


        }



    })


  }




  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  handleSubmit(event) {
    const { username, password } = this.state;

    {/*}
    axios
      .post(
        "http://localhost:3000/sessions",
        {
          user: {
            email: email,
            password: password
          }
        },
        { withCredentials: true }
      )
      .then(response => {
        if (response.data.logged_in) {
          this.props.handleSuccessfulAuth(response.data);
        }
      })
      .catch(error => {
        console.log("login error", error);
      });
    event.preventDefault();
    */}
  }

  render() {
    return (
      <>
      <head>
        <title>LOGIN MERIT BANK</title>
          <link rel="stylesheet" type="text/css" href= "../css/Login.css" />
      </head>
      <body>

      <header>
        <div class="main">
          <div class="logo">
            <img />
          </div>
          <ul>
            <li><a href="/">Home</a></li>
            <li class="login"><a href="/login">Login</a></li>
            <li><a href="/services">Services</a></li>
            <li><a href="/aboutus">About Us</a></li>
            <li><a href="/contactus">Contact Us</a></li>
          </ul>
          </div>
      </header>

      <div class = "loginbox">
        <img class="avatar" />
          <h1>Access Account</h1>
            {/*<form onSubmit={this.handleSubmit}> */}
            <p>Username</p>
              <input
                type="username"
                name="username"
                placeholder="Username"
                value={this.state.username}
                onChange={this.handleChange}
                required
              />
            <p>Password</p>
              <input
                type="password"
                name="password"
                placeholder="Password"
                value={this.state.password}
                onChange={this.handleChange}
                required
              />

              <button onClick={this.loginRequest}>Login</button>
              <br />
              <br />
            {/* </form> */}
            {/*<a href="/register">Forgot Passowrd?</a><br />*/}
            <a href="/register">Create an account!</a><br />
          </div>


      <div id="loginOutput"></div>
      </body>
      </>
    );
  }
}
