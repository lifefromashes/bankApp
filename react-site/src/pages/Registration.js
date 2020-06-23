import React, { Component } from "react";

import {Link} from 'react-router-dom';
import axios from "axios";
import {saveTokenInCookie, readCookie, logout, setCookieHeader} from "../cookieUtil";

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
    var urlString = "http://localHost:8080/NewUser";
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

          <Link to="/login" className="btn-primary">
          Already have an Account?
          </Link>
            <div>

                <label>Username:</label>
                <input
                  name="username"
                  placeholder="Desired Username"
                  value={this.state.username}
                  onChange={this.handleChange}
                  required
                />

                <label>Password:</label>
                <input
                  type="password"
                  name="password"
                  placeholder="Password"
                  value={this.state.password}
                  onChange={this.handleChange}
                  required

                />
                <label>Confirm Password:</label>
                <input
                  type="password"
                  name="password_confirmation"
                  placeholder="Password confirmation"
                  value={this.state.password_confirmation}
                  onChange={this.handleChange}
                  required
                />

                <label>First Name:</label>
                <input
                  name="firstname"
                  placeholder="First Name"
                  value={this.state.firstname}
                  onChange={this.handleChange}
                  required
                />

                <label>Last Name:</label>
                <input
                  name="lastname"
                  placeholder="Last Name"
                  value={this.state.lastname}
                  onChange={this.handleChange}
                  required
                />

                <label>SSN:</label>
                <input
                  name="ssn"
                  placeholder="SSN"
                  value={this.state.ssn}
                  onChange={this.handleChange}
                  required
                />

                <label>Email:</label>
                <input
                  name="email"
                  placeholder="Email"
                  value={this.state.email}
                  onChange={this.handleChange}
                  required
                />

                <label>Phone Number:</label>
                <input
                  name="phone"
                  placeholder="Phone Number"
                  value={this.state.phone}
                  onChange={this.handleChange}
                  required
                />

                <label>Address:</label>
                <input
                  name="address"
                  placeholder="Address"
                  value={this.state.address}
                  onChange={this.handleChange}
                  required
                />

                <label>City:</label>
                <input
                  name="city"
                  placeholder="City"
                  value={this.state.city}
                  onChange={this.handleChange}
                  required
                />

                <label>State:</label>
                <input
                  name="stateName"
                  placeholder="State"
                  value={this.state.stateName}
                  onChange={this.handleChange}
                  required
                />

                <label>Zip Code:</label>
                <input
                  name="zip"
                  placeholder="Zip Code"
                  value={this.state.zip}
                  onChange={this.handleChange}
                  required
                />

                <button onClick={this.submitNewUser}>Register</button>

            </div>
    
        </>
    );
  }
}
