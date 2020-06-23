import React, { Component } from "react";
import {Link} from 'react-router-dom';
import {saveTokenInCookie, readCookie, logout, setCookieHeader} from "../cookieUtil";
import {parseBankUser, parseUserByID} from "../parseBankUser";
import {createNewAccount} from "../adminFeedback";

export default class Admin extends Component {
  constructor(props) {
    super(props);

    this.state = {
        sample: "",
        userID: 1,
        amount: 0,
        accountTypeSelected: 1,
        CDONum: 0
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleChangeA = this.handleChangeA.bind(this);
    this.getUsers = this.getUsers.bind(this);
    this.getUserByID = this.getUserByID.bind(this);
    this.createAccount = this.createAccount.bind(this);
  }


  handleChange(event) {

    this.setState({
      [event.target.name]: event.target.value
    });

    //{console.log(document.getElementById("accountType").value)}
  }

  handleChangeA(event) {
    this.setState({
      accountTypeSelected: document.getElementById("accountType").value
    })
  }



  getUsers() {
    console.log("enter getUsers");

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
    console.log("enter getUserByID");

    var req = new XMLHttpRequest();
    var id = parseInt(this.state.userID);
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

  createAccount() {

    console.log("enter createAccount");

    //createNewAccount(document.getElementById("accountType").value, this.state.amount);
    var req = new XMLHttpRequest();
    var t = document.getElementById("accountType").value;
    var tString = "fail";
    if(t == 1){ tString = "CheckingAccount"; }
    if(t == 2){ tString = "DBACheckingAccount"; }
    if(t == 3){ tString = "CDAccount"; }
    if(t == 4){ tString = "RegularIRA"; }
    if(t == 5){ tString = "RolloverIRA"; }
    if(t == 6){ tString = "RothIRA"; }
    var id = parseInt(this.state.userID);

    var urlString = "http://localHost:8080/Admin/" + id + "/" + tString;

    var body = '{"balance": "' + this.state.amount + '"}';

    req.open('POST', urlString);
    req.setRequestHeader('Content-Type', 'application/json');
    setCookieHeader(req);
    req.send(body);

    req.addEventListener('load', () => {

      if(req.status >= 200 && req.status < 400){


        var str = createNewAccount();
        var t = document.getElementById("printout");
        t.innerHTML = "<p>" + str + "</p>";



      }
    })

  }



  render() {



    return (
      <>


          <div>


              {/*<button onClick={this.loginRequest}>Login</button>*/}

          </div>
    

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

      <br></br>
      <div>
        &nbsp; &nbsp;
        <button onClick={this.createAccount}>Create New</button>
        &nbsp; &nbsp;
        <select id="accountType"
          value={this.state.accountTypeSelected}
          onChange={this.handleChangeA}
        >
          
          <option value="1">Checking Account</option>
          <option value="2">DBA Checking Account</option>
          <option value="3">CD Account</option>
          <option value="4">Regular IRA Account</option>
          <option value="5">Roth IRA Account</option>
          <option value="6">Rollover IRA Account</option>
        </select>

        &nbsp; with a starting balance of &nbsp;
        <input
          size="10"
          type="amount"
          name="amount"
          placeholder="amount"
          value={this.state.amount}
          onChange={this.handleChange}
          required
        />
        &nbsp;
        <label id="CDO lab" hidden={this.state.accountTypeSelected != 3}>  from CDO # </label>
        &nbsp;
        <input
          hidden={this.state.accountTypeSelected != 3}
          id="CDO box"
          size="10"
          name="amount"
          value={this.state.CDONum}
          onChange={this.handleChange}
          required
        />

        

      </div>

      <div>
        &nbsp; &nbsp;
        <button onClick={this.createAccount}>Get All CDOs</button>
        &nbsp; &nbsp;
        <button onClick={this.createAccount}>Create New CDO</button>
        &nbsp; with an interest rate of &nbsp;
        <input
          size="10"
          name="amount"
          placeholder="amount"
          value={this.state.amount}
          onChange={this.handleChange}
          required
        />
        &nbsp; % and a term of &nbsp;
        <input
          size="4"
          name="amount"
          placeholder="amount"
          value={this.state.amount}
          onChange={this.handleChange}
          required
        />
        &nbsp; years &nbsp;


      </div>

      

      <div id="printout"></div>

      </>
    );
  }
}
