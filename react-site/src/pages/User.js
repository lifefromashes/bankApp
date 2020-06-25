import React, { Component } from "react";
import {Link} from 'react-router-dom';
import {saveTokenInCookie, readCookie, logout, setCookieHeader} from "../cookieUtil";
import {parseBankUser, parseUserByID, parseAccounts} from "../parseBankUser";

export default class User extends Component {
  constructor(props) {
    super(props);

    this.state = {
        sample: "",
        username: "",
        password: "",
        loginErrors: "",
        accounts: [],
        accountIndex: 0,
        accountTypeSelected: 1,
        cdoType: 1,
        amount: 0,
        obj: null
    };



    var req = new XMLHttpRequest();
    var urlString = "http://localHost:8080/User";
    req.open('GET', urlString);
    req.setRequestHeader('Content-Type', 'application/json');
    var jwt = readCookie("jwt");
    setCookieHeader(req);
    req.send();
    req.addEventListener('load', () => {
      if(req.status >= 200 && req.status < 400){
        //document.getElementById("userTitle").title = JSON.parse(req.responseText).username;
        var t = document.getElementById("userTitle");
        var s = "";
        s += "<p>" + JSON.parse(req.responseText).firstName + " ";
        s += JSON.parse(req.responseText).lastName + " ";
        s += "Account Holder #" + JSON.parse(req.responseText).id + "</p>";
        t.innerHTML = s;

        var al = document.getElementById("accountList");
        al.innerHTML = parseAccounts(req);

        var accts = JSON.parse(req.responseText).bankAccounts.length;
        //if(b != null){
          for(let i=0; i<accts; i++){
            let b = document.getElementById("accountID" + i);
            this.state.accounts[this.state.accountIndex] = b;
            this.state.accountIndex ++;
            b.addEventListener('click', () => {

              window.location = "history/" + JSON.parse(req.responseText).bankAccounts[i].accountNumber;
            });

          }
        //}

      
          var req2 = new XMLHttpRequest();
          var urlString2 = "http://localHost:8080/CDOfferings";
      
      
          req2.open('GET', urlString2);
          req2.setRequestHeader('Content-Type', 'application/json');
          setCookieHeader(req2);
          req2.send();
      
          req2.addEventListener('load', () => {
            if(req2.status >= 200 && req2.status < 400){
              

              var obj = JSON.parse(req2.responseText);
              this.state.obj = obj;
              var s = "";
              var t = document.getElementById("cdoType");

              //<option value="4">Regular IRA Account</option>
              for(let i=0; i<obj.length; i++){
                s += '<option value="' + obj[i].id + '">';
                s += 'Rate ' + obj[i].interestRate + ' for ' + obj[i].term + ' years';
                s += '</option>';
              }
              t.innerHTML = s;

              
            }
          })
      
      


      }
    })

    this.handleChange = this.handleChange.bind(this);
    this.handleChangeA = this.handleChangeA.bind(this);
    this.handleChangeC = this.handleChangeC.bind(this);
    this.createAccount = this.createAccount.bind(this);
    this.handleFocus = this.handleFocus.bind(this);

  }

  handleFocus = (event) => event.target.select();


  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  handleChangeA(event) {
    this.setState({
      accountTypeSelected: document.getElementById("accountType").value
    })
  }

  handleChangeC(event) {
    this.setState({
      accountTypeSelected: document.getElementById("accountType").value
    })
  }


  createAccount() {

    var req = new XMLHttpRequest();
    var t = document.getElementById("accountType").value;
    var tString = "fail";
    if(t == 1){ tString = "CheckingAccount"; }
    if(t == 2){ tString = "DBACheckingAccount"; }
    if(t == 3){ tString = "CDAccount"; }
    if(t == 4){ tString = "RegularIRA"; }
    if(t == 5){ tString = "RothIRA"; }
    if(t == 6){ tString = "RolloverIRA"; }
    

    var urlString = "http://localHost:8080/User/" + tString;

    var body = '{"balance": "' + this.state.amount + '"'; 
    if(t == 3){
      body += ', "interestRate": "' + this.state.obj[this.state.cdoType].interestRate + '", ';
      body += '"term": ' + this.state.obj[this.state.cdoType].term;
    }
    body += '}';

    req.open('POST', urlString);
    req.setRequestHeader('Content-Type', 'application/json');
    setCookieHeader(req);
    req.send(body);

    req.addEventListener('load', () => {
      if(req.status >= 200 && req.status < 400){
        window.location.reload();
        console.log(req.responseText);
      } else {
        alert("Failed to new create account. You may only have up to 1 personal checking account, 3 DBA checking accounts, and 1 of each IRA account.");
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
              <li><a href="/login">Sign Off</a></li>
              <li><a href="/user">Welcome, USER</a></li>
              <li><a href="#">Mobile</a></li>
            </ul>
          </div>

        

      <br></br> <br></br> <br></br>

      <div id="userTitle" class="userTitle"></div>

      <div>
        <button class="adminTool2" onClick={this.createAccount}>Create New</button>

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

        &nbsp; &nbsp;
        <select id="cdoType"
          hidden={this.state.accountTypeSelected != 3}
          value={this.state.cdoType}
          onChange={this.handleChangeC}
        >
        </select>
        with a starting balance of $
        <input
          class = "adminTool2"
          name="amount"
          value={this.state.amount}
          onFocus={this.handleFocus}
          onChange={this.handleChange}
          required
        />

      </div>

      <div id="accountList"></div>



      </header>
      </body>
      </>
    );
  }
}
