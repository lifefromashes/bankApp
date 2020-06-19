import React, { Component } from "react";
import Hero from '../components/Hero';
import Banner from "../components/Banner";
import {Link} from 'react-router-dom';
import {saveTokenInCookie, readCookie, logout, setCookieHeader} from "../cookieUtil";
import {parseBankUser, parseUserByID} from "../parseBankUser";

export default class Login extends Component {
  constructor(props) {
    super(props);

    this.state = {
        sample: "",
        userID: 1
    };

    this.handleChange = this.handleChange.bind(this);
    this.getUsers = this.getUsers.bind(this);
    this.getUserByID = this.getUserByID.bind(this);
  }


  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  getUsers() {

    var req = new XMLHttpRequest();
    var urlString = "http://localHost:8080/Admin/Users";
    req.open('GET', urlString);
    req.setRequestHeader('Content-Type', 'application/json');
    setCookieHeader(req);
    req.send();

    req.addEventListener('load', () => {
      
      if(req.status >= 200 && req.status < 400){
        //console.log(req.responseText);
        var str = parseBankUser(req);
        var t = document.getElementById("printout");
        t.innerHTML = "<p>" + str + "</p>";
        
      }
    })
  }

  getUserByID() {

    var req = new XMLHttpRequest();
    var id = 1;
    var urlString = "http://localHost:8080/Admin/Users/" + id;
    req.open('GET', urlString);
    req.setRequestHeader('Content-Type', 'application/json');
    setCookieHeader(req);
    req.send();

    req.addEventListener('load', () => {
      
      if(req.status >= 200 && req.status < 400){
        //console.log(req.responseText);
        var str = parseUserByID(req);
        var t = document.getElementById("printout");
        t.innerHTML = "<p>" + str + "</p>";
        
      }
    })
  }

  

  render() {
    return (
      <>
      <Hero hero="accountsHero">
        <Banner title="Admin Page">
          
          <div>
            
              

            

              {/*<button onClick={this.loginRequest}>Login</button>*/}
            
          </div>
        </Banner>
      </Hero>

      &nbsp; &nbsp; User Account Number:
      <input
        type="userID"
        name="userID"
        placeholder="ID#"
        value={this.state.userID}
        onChange={this.handleChange}
        required
      />
      <br></br>
      &nbsp; &nbsp; 
      <button onClick={this.getUsers}>Get All Users</button>
      
      &nbsp; &nbsp; 
      <button onClick={this.getUserByID}>Get User by User Account Number</button>

      <div id="printout"></div>

      </>
    );
  }
}
