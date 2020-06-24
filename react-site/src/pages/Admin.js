import React, { Component } from "react";
import {Link} from 'react-router-dom';
import {saveTokenInCookie, readCookie, logout, setCookieHeader} from "../cookieUtil";
import {parseBankUser, parseUserByID, parseCDO, parseHistoryAdmin} from "../parseBankUser";
import {createNewAccount, createNewCDO} from "../adminFeedback";

export default class Admin extends Component {
  constructor(props) {
    super(props);

    this.state = {
        sample: "",
        userID: 1,
        amount: 0,
        accountTypeSelected: 1,
        CDONum: 0,
        cdoRate: .0001,
        cdoTerm: 1,
        transAccount: 0,
        transAmmount: 0,
        transNote: "Admin Action"
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleChangeA = this.handleChangeA.bind(this);
    this.getUsers = this.getUsers.bind(this);
    this.getUserByID = this.getUserByID.bind(this);
    this.createAccount = this.createAccount.bind(this);
    this.createCDO = this.createCDO.bind(this);
    this.createTransaction = this.createTransaction.bind(this);
    this.getHistory = this.getHistory.bind(this);
  }
  handleFocus = (event) => event.target.select();

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

  createCDO() {
    console.log("enter create cdo");

    var req = new XMLHttpRequest();
    var urlString = "http://localHost:8080/Admin/CDOfferings";

    var body = '{"interestRate": "' + this.state.cdoRate + '", ';
    body += '"term": "' + this.state.cdoTerm + '"}';

    req.open('POST', urlString);
    req.setRequestHeader('Content-Type', 'application/json');
    setCookieHeader(req);
    req.send(body);

    req.addEventListener('load', () => {
      if(req.status >= 200 && req.status < 400){
        var str = createNewCDO();
        var t = document.getElementById("printout");
        t.innerHTML = "<p>" + str + "</p>";
      }
    })

  }

  getCDOs() {
    console.log("enter getCDOs");

    var req = new XMLHttpRequest();
    var urlString = "http://localHost:8080/CDOfferings";
    req.open('GET', urlString);
    req.setRequestHeader('Content-Type', 'application/json');
    setCookieHeader(req);
    req.send();

    req.addEventListener('load', () => {

      if(req.status >= 200 && req.status < 400){
        //console.log(req.responseText);
        var str = parseCDO(req);
        var t = document.getElementById("printout");
        t.innerHTML = "<p>" + str + "</p>";

      }
    })
  }

  getHistory() {
    console.log("enter get history admin");






    var req = new XMLHttpRequest();

    var urlString = "http://localHost:8080/Admin/Transaction/" + this.state.transAccount;
    req.open('GET', urlString);
    req.setRequestHeader('Content-Type', 'application/json');
    var jwt = readCookie("jwt");
    setCookieHeader(req);
    req.send();

    req.addEventListener('load', () => {
      if(req.status >= 200 && req.status < 400){
        var str = parseHistoryAdmin(req);
        var t = document.getElementById("printout");
        t.innerHTML = "<p>" + str + "</p>";
      }
    })














  }

  createTransaction() {
    

    var req = new XMLHttpRequest();
    var urlString = "http://localHost:8080/Admin/Transaction";
    

    var body = '{"sourceAccount": ' + this.state.transAccount + ', ';
    body += '"targetAccount": ' + this.state.transAccount + ', ';
    body += '"amount": "' + this.state.transAmmount + '", ';
    body += '"transactionMemo": "' + this.state.transNote + '"}'

    console.log(body);

    req.open('POST', urlString);
    req.setRequestHeader('Content-Type', 'application/json');
    setCookieHeader(req);
    req.send(body);

    req.addEventListener('load', () => {
      console.log(req.status);
      if(req.status >= 200 && req.status < 400){
        console.log("success create transaction");

        var str = "Account " + this.state.transAccount + " adjusted.";
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

    if(t == 3){
      urlString = "http://localHost:8080/Admin/" + id + "/" + tString + "/" + this.state.CDONum;
    }

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
              <li><a href="/user">Welcome, ADMIN</a></li>
              <li><a href="#">Mobile</a></li>
            </ul>
          </div>


          <div>


              {/*<button onClick={this.loginRequest}>Login</button>*/}

          </div>

      <br></br><br></br><br></br>

      &nbsp; &nbsp; User Account Number:
      <input
        className="adminTool" 
        type="userID"
        name="userID"
        placeholder="ID#"
        value={this.state.userID}
        onChange={this.handleChange}
        onFocus={this.handleFocus}
        required
      />
      <br></br>
      &nbsp; &nbsp;
      <button className="adminTool" onClick={this.getUsers}>Get All Users</button>

      &nbsp; &nbsp;
      <button className="adminTool2" onClick={this.getUserByID}>Get User by User Account Number</button>

      <br></br>
      <div>
        &nbsp; &nbsp;
        <button className="adminTool" onClick={this.createAccount}>Create New</button>
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
          className="adminTool" 
          size="10"
          type="amount"
          name="amount"
          placeholder="amount"
          value={this.state.amount}
          onChange={this.handleChange}
          onFocus={this.handleFocus}
          required
        />
        &nbsp;
        <label id="CDO lab" hidden={this.state.accountTypeSelected != 3}>  from CDO # </label>
        &nbsp;
        <input
          className="adminTool" 
          hidden={this.state.accountTypeSelected != 3}
          id="CDO box"
          size="10"
          name="CDONum"
          value={this.state.CDONum}
          onChange={this.handleChange}
          onFocus={this.handleFocus}
          required
        />



      </div>

      <div>
        &nbsp; &nbsp;
        <button className="adminTool" onClick={this.getCDOs}>Get All CDOs</button>
        &nbsp; &nbsp;
        <button className="adminTool" onClick={this.createCDO}>Create New CDO</button>
        &nbsp; with an interest rate of &nbsp;
        <input
          className="adminTool" 
          size="10"
          name="cdoRate"
          value={this.state.cdoRate}
          onChange={this.handleChange}
          onFocus={this.handleFocus}
          required
        />
        &nbsp; % and a term of &nbsp;
        <input
          className="adminTool" 
          size="4"
          name="cdoTerm"
          value={this.state.cdoTerm}
          onChange={this.handleChange}
          onFocus={this.handleFocus}
          required
        />
        &nbsp; years &nbsp;


      </div>

      <div>
        &nbsp; &nbsp;
        Bank Account Number:
        <input
            className="adminTool" 
            size="10"
            name="transAccount"
            value={this.state.transAccount}
            onChange={this.handleChange}
            onFocus={this.handleFocus}
          />
          

        &nbsp; &nbsp;
        <button className="adminTool" onClick={this.createTransaction}>Adjust Funds</button>
        &nbsp; by $  &nbsp;
        
        <input
            className="adminTool" 
            size="10"
            name="transAmmount"
            value={this.state.transAmmount}
            onChange={this.handleChange}
            onFocus={this.handleFocus}
          />
        
        &nbsp; Memo: &nbsp;

        <input
            className="adminTool" 
            name="transNote"
            value={this.state.transNote}
            onChange={this.handleChange}
            onFocus={this.handleFocus}
          />

        
        <button className="adminTool2" onClick={this.getHistory}>See Activity History</button>

      </div>

      <br></br>


      <div id="printout" classname="consoleAdminTool"></div>
      </header>
      </body>

      </>
    );
  }
}
