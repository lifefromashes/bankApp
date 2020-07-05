import React, { Component } from "react";

import {Link} from 'react-router-dom';
import axios from "axios";
import {saveTokenInCookie, readCookie, logout, setCookieHeader} from "../cookieUtil";
import {server} from "../webAddress";
import {apiCall} from "../netcode";
import {sterilizeString} from "../sterilize";

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


    var body = {
      username: sterilizeString(this.state.username),
      password: sterilizeString(this.state.password),
      firstName: sterilizeString(this.state.firstname),
      lastName: sterilizeString(this.state.lastname),
      ssn: sterilizeString(this.state.ssn),
      email: sterilizeString(this.state.email),
      phone: sterilizeString(this.state.phone),
      address: sterilizeString(this.state.address),
      city: sterilizeString(this.state.city),
      state: sterilizeString(this.state.stateName),
      zip: sterilizeString(this.state.zip)
    }

    if(body.username.length < 4){
      window.alert("Usernames must be at least 4 characters.");
      return;
    }

    if(body.password.length < 4){
      window.alert("Passwords must be at least 4 characters.");
      return;
    }

    if(body.password != document.getElementById("password_confirmation").value){
      window.alert("Password must match password confirmation.");
      return;
    }


    var req = apiCall(body, 'POST', "NewUser", false);

    req.addEventListener('load', () => {

      if(req.status >= 200 && req.status < 400){
        console.log(req.responseText);
        window.location = "/login";

      } else {
        window.alert("Please select a different username.");

      }
    })

  }

  handleSubmit(event) { }

  render() {
    return (
      <>
        <head>
          <title>REGISTER</title>
          <link rel="stylesheet" type="text/css" href= "../css/Register.css" />
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
                  id="password_confirmation"
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
