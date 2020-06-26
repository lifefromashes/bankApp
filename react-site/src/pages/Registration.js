import React, { Component } from "react";

import {Link} from 'react-router-dom';
import axios from "axios";
import {saveTokenInCookie, readCookie, logout, setCookieHeader} from "../cookieUtil";
import {server} from "../webAddress";

export default class Registration extends Component {
  constructor(props) {
    super(props);

    this.state = {
      username: "",
      email: "",
      password: "",
      password_confirmation: "",
      firstname: "",
      lastname: "",
      ssn: "",
      phone: "",
      address: "",
      city: "",
      stateName: "",
      zip: "",
      registrationErrors: ""
    };

    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.submitNewUser = this.submitNewUser.bind(this);
  }

  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  submitNewUser() {
    console.log("trying to create new user");

    var body = '{"username": "' + this.state.username + '", ';
    body += '"password": "' + this.state.password + '", ';
    body += '"firstName": "' + this.state.firstname + '", ';
    body += '"lastName": "' + this.state.lastname + '", ';
    body += '"ssn": "' + this.state.ssn + '", ';
    body += '"email": "' + this.state.email + '", ';
    body += '"phone": "' + this.state.phone + '", ';
    body += '"address": "' + this.state.address + '", ';
    body += '"city": "' + this.state.city + '", ';
    body += '"state": "' + this.state.stateName + '", ';
    body += '"zip": "' + this.state.zip + '"}';

    var req = new XMLHttpRequest();
    var urlString = server() + "NewUser";
    req.open('POST', urlString);
    req.setRequestHeader('Content-Type', 'application/json');
    setCookieHeader(req);
    req.send(body);

    req.addEventListener('load', () => {

      if(req.status >= 200 && req.status < 400){
        console.log(req.responseText);
        window.location = "/login";

      } else {
        //handle bad input

      }
    })

  }

  handleSubmit(event) { /*
    const { email, password, password_confirmation } = this.state;

    axios
      .post(
        "http://localhost:3000/registrations",
        {
          user: {
            email: email,
            password: password,
            password_confirmation: password_confirmation
          }
        },
        { withCredentials: true }
      )
      .then(response => {
        if (response.data.status === "created") {
          this.props.handleSuccessfulAuth(response.data);
        }
      })
      .catch(error => {
        console.log("registration error", error);
      });
    event.preventDefault();
    */
  }

  render() {
    return (
      <>
        <head>
          <title>REGISTER</title>
          <link rel="stylesheet" type="text/css" href= "../App.css" />
        </head>
          <body>

          <header>
            <div class="main">
              <div class="logo">
                <img  />
              </div>
              <ul>
                <li><a href="/">Home</a></li>
                <li class ="register"><a href="/register">Register</a></li>
                <li><a href="/services">Services</a></li>
                <li><a href="/aboutus">About Us</a></li>
                <li><a href="/contactus">Contact Us</a></li>
                <li><a href="#">Mobile</a></li>
              </ul>
            </div>
          <section id="register-section">
              <div class="registercontainer">
            <Link to="/login" className="btn-primary">
            Already have an Account?
            </Link>
            <div class="register-form">

                <label>Username:</label>
                <input
                  name="username"
                  placeholder="Desired Username"
                  value={this.state.username}
                  onChange={this.handleChange}
                  required
                /><br />

                <label>Password:</label>
                <input
                  type="password"
                  name="password"
                  placeholder="Password"
                  value={this.state.password}
                  onChange={this.handleChange}
                  required

                /><br />

                <label>Confirm:</label>
                <input
                  type="password"
                  name="password_confirmation"
                  placeholder="Password confirmation"
                  value={this.state.password_confirmation}
                  onChange={this.handleChange}
                  required
                /><br />

                <label>First Name:</label>
                <input
                  name="firstname"
                  placeholder="First Name"
                  value={this.state.firstname}
                  onChange={this.handleChange}
                  required
                /><br />

                <label>Last Name:</label>
                <input
                  name="lastname"
                  placeholder="Last Name"
                  value={this.state.lastname}
                  onChange={this.handleChange}
                  required
                /><br />

                <label>SSN:</label>
                <input
                  name="ssn"
                  placeholder="SSN"
                  value={this.state.ssn}
                  onChange={this.handleChange}
                  required
                /><br />

                <label>Email:</label>
                <input
                  name="email"
                  placeholder="Email"
                  value={this.state.email}
                  onChange={this.handleChange}
                  required
                /><br />

                <label>Phone #:</label>
                <input
                  name="phone"
                  placeholder="Phone Number"
                  value={this.state.phone}
                  onChange={this.handleChange}
                  required
                /><br />

                <label>Address:</label>
                <input
                  name="address"
                  placeholder="Address"
                  value={this.state.address}
                  onChange={this.handleChange}
                  required
                /><br />

                <label>City:</label>
                <input
                  name="city"
                  placeholder="City"
                  value={this.state.city}
                  onChange={this.handleChange}
                  required
                /><br />

                <label>State:</label>
                <input
                  name="stateName"
                  placeholder="State"
                  value={this.state.stateName}
                  onChange={this.handleChange}
                  required
                /><br />

                <label>Zip Code:</label>
                <input
                  name="zip"
                  placeholder="Zip Code"
                  value={this.state.zip}
                  onChange={this.handleChange}
                  required
                /><br />

                <button onClick={this.submitNewUser}>Register</button>

            </div>
            </div>
            </section>
          </header>
          </body>
          
        </>
    );
  }
}
