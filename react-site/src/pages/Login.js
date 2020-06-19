import React, { Component } from "react";
import Hero from '../components/Hero';
import Banner from "../components/Banner";
import {Link} from 'react-router-dom';
import axios from "axios";
import {saveTokenInCookie, readCookie, logout, setCookieHeader} from "../cookieUtil";

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
    
    var urlString = "http://localHost:8080/authenticate";

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
          urlString = "http://localHost:8080/direct";
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
      <Hero hero="accountsHero">
        <Banner title="Login">
          <Link to="/register" className="btn-primary">
          JOIN US
          </Link>
          <div>
            {/*<form onSubmit={this.handleSubmit}> */}
              <input
                type="username"
                name="username"
                placeholder="Username"
                value={this.state.username}
                onChange={this.handleChange}
                required
              />

              <input
                type="password"
                name="password"
                placeholder="Password"
                value={this.state.password}
                onChange={this.handleChange}
                required
              />

              <button onClick={this.loginRequest}>Login</button>
            {/* </form> */}
          </div>

          

          
          
          


        </Banner>
        
      </Hero>
      <div id="loginOutput"></div>
      </>
    );
  }
}
