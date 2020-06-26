import React, { Component } from "react";

import {Link} from 'react-router-dom';
import axios from "axios";
import {saveTokenInCookie, readCookie, logout, setCookieHeader} from "../cookieUtil";
import {server} from "../webAddress";

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

    var req = new XMLHttpRequest();
    var body = '{"username": "' + this.state.username + '", ';
    body += '"password": "' + this.state.password + '"}';

    var urlString = server() + "authenticate";

    //console.log(body);

    req.open('POST', urlString);
    req.setRequestHeader('Content-Type', 'application/json');
    req.send(body);

    req.addEventListener('load', () => {
      console.log("got response");

        if(req.status >= 200 && req.status < 400){
          //console.log(req.responseText);

          saveTokenInCookie(req);
          console.log("!: " + readCookie("jwt"));


          //use our newly saved cookie to request a reditect from the server
          var req2 = new XMLHttpRequest();
          urlString = server() + "direct";
          req2.open('GET', urlString);
          req2.setRequestHeader('Content-Type', 'application/json');
          setCookieHeader(req2);
          req2.send();

          req2.addEventListener('load', () => {
            if(req.status >= 200 && req.status < 400){
              window.location = req2.responseText;
            }
          })






        } else {
          console.log(req.status);
          //bad user / pass combo code goes here

          document.getElementById("loginOutput").innerHTML = "<p>~~~~Invalid Username Password~~~~</p>";


        }



    })

    //console.log(body);

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
          <link rel="stylesheet" type="text/css" href= "../App.css" />
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
            <li><a href="#">Mobile</a></li>
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
            <a href="/register">Forgot Passowrd?</a><br />
            <a href="/register">Create an account!</a><br />
          </div>


      <div id="loginOutput"></div>
      </body>
      </>
    );
  }
}
