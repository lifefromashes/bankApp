import React, { Component } from "react";
import {Link} from 'react-router-dom';
import {saveTokenInCookie, readCookie, logout, setCookieHeader} from "../cookieUtil";
import {parseBankUser, parseUserByID, parseCDO, parseHistoryAdmin} from "../parseBankUser";
import {createNewAccount, createNewCDO} from "../adminFeedback";
import {server} from "../webAddress";
import {apiCall} from "../netcode";

export default class Admin extends Component {
  constructor(props) {
    super(props);

    this.state = {
        sample: "",
        userID: 1,
        amount: 0,
        accountTypeSelected: 1,
        CDONum: 0,
        cdoRate: .01,
        cdoTerm: 1,
        transAccount: 0,
        transAmmount: 0,
        transNote: "Admin Action",
        searchType: 1
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleChangeA = this.handleChangeA.bind(this);
    this.handleChangeS = this.handleChangeS.bind(this);
    this.getUsers = this.getUsers.bind(this);
    this.getUserByID = this.getUserByID.bind(this);
    this.createAccount = this.createAccount.bind(this);
    this.createCDO = this.createCDO.bind(this);
    this.createTransaction = this.createTransaction.bind(this);
    this.getHistory = this.getHistory.bind(this);


    var req = apiCall(null, 'GET', "direct", true);
    req.addEventListener('load', () => {
      if(req.responseText != "/admin"){ window.location = "/"; }
    })


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

  handleChangeS(event) {
    this.setState({
      searchType: document.getElementById("searchType").value
    })
    //console.log(document.getElementById("searchType").value);
  }



  getUsers() {

    var req = apiCall(null, 'GET', "Admin/Users/" + document.getElementById("searchType").value, true);

    req.addEventListener('load', () => {

      if(req.status >= 200 && req.status < 400){
        var str = parseBankUser(req);
        var t = document.getElementById("printout");
        t.innerHTML = "<p>" + str + "</p>";

      } else {
        document.getElementById("printout").innerHTML = "<p>Unable to connect.</p>";
      }
    })
  }

  getUserByID() {
    var id = parseInt(this.state.userID);
    var req = apiCall(null, 'GET', "Admin/Users/" + id + "/" + document.getElementById("searchType").value, true);

    req.addEventListener('load', () => {

      if(req.status >= 200 && req.status < 400){
        //console.log(req.responseText);
        var str = parseUserByID(req);
        var t = document.getElementById("printout");
        t.innerHTML = "<p>" + str + "</p>";

      } else {
        document.getElementById("printout").innerHTML = "<p>Unable to find user " + id + "</p>";
      }
    })
  }

  createCDO() {

    var body = {
      interestRate: this.state.cdoRate,
      term: this.state.cdoTerm
    };
    var req = apiCall(body, 'POST', "Admin/CDOfferings", true);

    req.addEventListener('load', () => {
      if(req.status >= 200 && req.status < 400){
        var str = createNewCDO();
        var t = document.getElementById("printout");
        t.innerHTML = "<p>" + str + "</p>";
      } else {
        document.getElementById("printout").innerHTML = "<p>Unable to create CD Offering. </p>";
      }
    })

  }

  getCDOs() {

    var req = apiCall(null, 'GET', "CDOfferings", true);
    req.addEventListener('load', () => {

      if(req.status >= 200 && req.status < 400){
        var str = parseCDO(req);
        var t = document.getElementById("printout");
        t.innerHTML = "<p>" + str + "</p>";

      }
    })
  }

  getHistory() {

    var req = apiCall(null, 'GET', "Admin/Transaction/" + this.state.transAccount + "/" + document.getElementById("searchType").value, true);

    req.addEventListener('load', () => {
      if(req.status >= 200 && req.status < 400){
        var str = parseHistoryAdmin(req);
        var t = document.getElementById("printout");
        t.innerHTML = "<p>" + str + "</p>";
      } else {
        document.getElementById("printout").innerHTML = "<p>Unable to find Account #" + this.state.transAccount + "</p>";
      }
    })



  }

  createTransaction() {




    var body = {
      sourceAccount: this.state.transAccount,
      targetAccount: this.state.transAccount,
      amount: this.state.transAmmount,
      transactionMemo: this.state.transNote
    }
    var req = apiCall(body, 'POST', "Admin/Transaction", true);

    req.addEventListener('load', () => {
      console.log(req.status);
      if(req.status >= 200 && req.status < 400){
        console.log("success create transaction");

        var str = "Transaction for account #" + this.state.transAccount + " submitted.";
        var t = document.getElementById("printout");
        t.innerHTML = "<p>" + str + "</p>";
      } else {
        document.getElementById("printout").innerHTML = "<p>Unable to find Account #" + this.state.transAccount + "</p>";
      }
    })


  }

  createAccount() {

    var t = document.getElementById("accountType").value;
    var tString = "fail";
    if(t == 1){ tString = "CheckingAccount"; }
    if(t == 2){ tString = "DBACheckingAccount"; }
    if(t == 3){ tString = "CDAccount"; }
    if(t == 4){ tString = "RegularIRA"; }
    if(t == 5){ tString = "RothIRA"; }
    if(t == 6){ tString = "RolloverIRA"; }

    var id = parseInt(this.state.userID);

    var urlString = "Admin/" + id + "/" + tString;

    if(t == 3){
      urlString = "Admin/" + id + "/" + tString + "/" + this.state.CDONum;
    }

    console.log(urlString);


    var body = { balance: this.state.amount };
    var req = apiCall(body, 'POST', urlString, true);

    req.addEventListener('load', () => {
      if(req.status >= 200 && req.status < 400){
        var str = createNewAccount();
        var t = document.getElementById("printout");
        t.innerHTML = "<p>" + str + "</p>";
      } else {
        document.getElementById("printout").innerHTML = "<p>Unable to create account.</p>";
      }
    })

  }



  render() {



    return (
      <>
      <head>
        <title>MERIT BANK</title>
        <link rel="stylesheet" type="text/css" href= "../css/Admin.css" />
      </head>
      <body>

        <header>
          <div class="main">
            <div class="logo">
              <img />
            </div>
            <ul>
              <li><a href="/login">Sign Off</a></li>
            </ul>
          </div>


          <div>


              {/*<button onClick={this.loginRequest}>Login</button>*/}

          </div>

      <br></br><br></br><br></br>

      &nbsp; &nbsp;<br /> User Account Number:
      <input
        className="userAN"
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
      <button className="userGet" onClick={this.getUsers}>Get All Users</button>
      &nbsp; &nbsp;
      <button className="userGA" onClick={this.getUserByID}>Get User by User Account Number</button>

      &nbsp; &nbsp;
      <select id="searchType"
          value={this.state.seachType}
          onChange={this.handleChangeS}
        >

          <option value="0">Search Active Accounts</option>
          <option value="1">Search Inactive Account</option>
        </select>




      <br></br>
      <div>
        &nbsp; &nbsp;
        <button className="create" onClick={this.createAccount}>Create New</button>
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
          className="start"
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
          className="cdo"
          hidden={this.state.accountTypeSelected != 3}
          id="CDO box"
          size="10"
          name="CDONum"
          value={this.state.CDONum}
          onChange={this.handleChange}
          onFocus={this.handleFocus}
          required
        />

<br />

      </div>

      <div>
        &nbsp; &nbsp;
        <button className="cdoGet" onClick={this.getCDOs}>Get All CDOs</button>
        &nbsp; &nbsp;
        <button className="cdoCreate" onClick={this.createCDO}>Create New CDO</button>
        &nbsp; with an interest rate of &nbsp;
        <input
          className="int"
          size="10"
          name="cdoRate"
          value={this.state.cdoRate}
          onChange={this.handleChange}
          onFocus={this.handleFocus}
          required
        />
        &nbsp; and a term of &nbsp;
        <input
          className="term"
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
        &nbsp; &nbsp; &nbsp; &nbsp;
        Bank Account Number:
        <input
            className="acct"
            size="10"
            name="transAccount"
            value={this.state.transAccount}
            onChange={this.handleChange}
            onFocus={this.handleFocus}
          />


        &nbsp; &nbsp;
        <button className="adj" onClick={this.createTransaction}>Adjust Funds</button>
        &nbsp; by $  &nbsp;

        <input
            className="adjamt"
            size="10"
            name="transAmmount"
            value={this.state.transAmmount}
            onChange={this.handleChange}
            onFocus={this.handleFocus}
          />

        &nbsp; Memo: &nbsp;

        <input
            className="note"
            name="transNote"
            value={this.state.transNote}
            onChange={this.handleChange}
            onFocus={this.handleFocus}
          />

          <br />
        <button className="admin2" onClick={this.getHistory}>See Activity History</button>

      </div>

      <br></br>


        <div id="printout" classname="consoleAdminTool"></div>


      </header>
      </body>

      </>
    );
  }
}
