import React, { Component } from "react";
import {Link} from 'react-router-dom';
import {saveTokenInCookie, readCookie, logout, setCookieHeader} from "../cookieUtil";
import {parseBankUser, parseUserByID, parseAccounts} from "../parseBankUser";
import {server} from "../webAddress";
import {apiCall} from "../netcode";
import {sterilizeString} from "../sterilize";

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
        stateName: "",
        zip: ""
        
    };

    var req = apiCall(null, 'GET', "User", true);

    req.addEventListener('load', () => {
        console.log(123);
        if(req.status >= 200 && req.status < 400){
            var user = JSON.parse(req.responseText);

            this.state.username = user.username;
            document.getElementById("username").value = user.username;

            this.state.firstname = user.firstName;
            document.getElementById("firstname").value = user.firstName;

            this.state.lastname = user.lastName;
            document.getElementById("lastname").value = user.lastName;

            this.state.ssn = user.ssn;
            document.getElementById("ssn").value = user.ssn;

            this.state.email = user.email;
            document.getElementById("email").value = user.email;

            this.state.phone = user.phone;
            document.getElementById("phone").value = user.phone;

            this.state.address = user.address;
            document.getElementById("address").value = user.address;

            this.state.city = user.city;
            document.getElementById("city").value = user.city;

            this.state.stateName = user.state;
            document.getElementById("stateName").value = user.state;

            this.state.zip = user.zip;
            document.getElementById("zip").value = user.zip;

            document.getElementById("welcomeUser").innerHTML = "Welcome " + user.firstName;
            
        }
    })





    this.handleFocus = this.handleFocus.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.updateInfo = this.updateInfo.bind(this);
    this.closeAccount = this.closeAccount.bind(this);

}

handleFocus = (event) => event.target.select();


handleChange(event) {
  this.setState({
    [event.target.name]: event.target.value
  });
}

updateInfo(){

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

    var req = apiCall(body, 'POST', "Contact", true);

    req.addEventListener('load', () => {

      if(req.status >= 200 && req.status < 400){
        
        window.location = "/User";

      } else {
        //handle bad input

      }
    })

}

closeAccount(){

    var req = apiCall(null, 'GET', "quickFunds", true);

    req.addEventListener('load', () => {

      if(req.status >= 200 && req.status < 400){
        var tot = req.responseText;

        if(window.confirm("Are you sure you want withdraw your $" + tot + " available funds and permanently delete your User Account, forefiting all investments?")){

            var req2 = apiCall(null, 'PUT', "User/Quit", true);
        
            req2.addEventListener('load', () => {
        
              if(req2.status >= 200 && req2.status < 400){
                
                window.location = "/";
        
              } else {
                //handle bad input
        
              }
            })

        }
        
      } else{

      }
    })

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
              <li><a href="/user" id="welcomeUser">Welcome, USER</a></li>
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
                  id="username"
                  value={this.state.username}
                  disabled
                /><br />

                

                <label>First Name:</label>
                <input
                  class="staticInfo"
                  name="firstname"
                  id="firstname"
                  value={this.state.firstname}
                  disabled
                /><br />

                <label>Last Name:</label>
                <input
                  name="lastname"
                  id="lastname"
                  class="staticInfo"
                  value={this.state.lastname}
                  disabled
                /><br />

                <label>SSN:</label>
                <input
                  name="ssn"
                  id="ssn"
                  class="staticInfo"
                  value={this.state.ssn}
                  disabled
                /><br />

                <label>Email:</label>
                <input
                  name="email"
                  id="email"
                  value={this.state.email}
                  onChange={this.handleChange}
                  onFocus={this.handleFocus}
                  required
                /><br />

                <label>Phone #:</label>
                <input
                  name="phone"
                  id="phone"
                  value={this.state.phone}
                  onChange={this.handleChange}
                  onFocus={this.handleFocus}
                  required
                /><br />

                <label>Address:</label>
                <input
                  name="address"
                  id="address"
                  value={this.state.address}
                  onChange={this.handleChange}
                  onFocus={this.handleFocus}
                  required
                /><br />

                <label>City:</label>
                <input
                  name="city"
                  id="city"
                  value={this.state.city}
                  onChange={this.handleChange}
                  onFocus={this.handleFocus}
                  required
                /><br />

                <label>State:</label>
                <input
                  name="stateName"
                  id="stateName"
                  value={this.state.stateName}
                  onChange={this.handleChange}
                  onFocus={this.handleFocus}
                  required
                /><br />

                <label>Zip Code:</label>
                <input
                  name="zip"
                  id="zip"
                  value={this.state.zip}
                  onChange={this.handleChange}
                  onFocus={this.handleFocus}
                  required
                /><br />

                <button onClick={this.updateInfo}>Update Contact Info</button>
                <br></br><br></br>
                <button onClick={this.closeAccount}>Close Account</button>

            </div>
            </div>
            </section>
          </header>
          </body>
          
        </>
    );
  }
}
