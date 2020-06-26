import React, { Component } from "react";
import {Link} from 'react-router-dom';
import {saveTokenInCookie, readCookie, logout, setCookieHeader} from "../cookieUtil";
import {parseBankUser, parseUserByID, parseAccounts} from "../parseBankUser";

export default class ContactInfo extends Component {
  constructor(props) {
    super(props);

    this.state = {
        sample: "",
        username: "",
        firstname: "",
        lastname: "",
        ssn: "",
        email: "",
        phone: "",
        address: "",
        city: "",
        state: "",
        zip: ""
        
    };

    this.handleFocus = this.handleFocus.bind(this);
    this.handleChange = this.handleChange.bind(this);

}

handleFocus = (event) => event.target.select();


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
              <li><a href="/user">Welcome, USER</a></li>
              <li><a href="/login">Sign Off</a></li>
            </ul>
          </div>

          




          <section id="register-section">
              <div class="registercontainer">

                  
            
            <div class="register-form">

                <label>Username: </label>
                
                <input
                  class="staticInfo"
                  name="username"
                  value={this.state.username}
                  disabled
                /><br />

                

                <label>First Name:</label>
                <input
                  class="staticInfo"
                  name="firstname"
                  value={this.state.firstname}
                  disabled
                /><br />

                <label>Last Name:</label>
                <input
                  name="lastname"
                  class="staticInfo"
                  value={this.state.lastname}
                  disabled
                /><br />

                <label>SSN:</label>
                <input
                  name="ssn"
                  class="staticInfo"
                  value={this.state.ssn}
                  disabled
                /><br />

                <label>Email:</label>
                <input
                  name="email"
                  value={this.state.email}
                  onChange={this.handleChange}
                  required
                /><br />

                <label>Phone #:</label>
                <input
                  name="phone"
                  value={this.state.phone}
                  onChange={this.handleChange}
                  required
                /><br />

                <label>Address:</label>
                <input
                  name="address"
                  value={this.state.address}
                  onChange={this.handleChange}
                  required
                /><br />

                <label>City:</label>
                <input
                  name="city"
                  value={this.state.city}
                  onChange={this.handleChange}
                  required
                /><br />

                <label>State:</label>
                <input
                  name="stateName"
                  value={this.state.stateName}
                  onChange={this.handleChange}
                  required
                /><br />

                <label>Zip Code:</label>
                <input
                  name="zip"
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
