import React, { Component } from "react";
//import Hero from '../components/Hero';
//import Banner from "../components/Banner";
import {Link} from 'react-router-dom';
import {saveTokenInCookie, readCookie, logout, setCookieHeader} from "../cookieUtil";
import {parseBankUser, parseUserByID, parseAccounts, parseHistory} from "../parseBankUser";
import {server} from "../webAddress";
import {apiCall} from "../netcode";

export default class History extends Component {
  constructor(props) {
    super(props);

    this.state = {
        sample: "",
        username: "",
        password: "",
        loginErrors: "",
        accounts: [],
        accountIndex: 0,
        actionTypeSelected: 1,
        amount: 0,
        account: null
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleChangeA = this.handleChangeA.bind(this);
    this.handleFocus = this.handleFocus.bind(this);
    this.makeTrans = this.makeTrans.bind(this);
    this.closeAccount = this.closeAccount.bind(this);


    let url = window.location;
    let urlSplit = url.toString().split("/");
    let actID = urlSplit[urlSplit.length - 1];

    var req = apiCall(null, 'GET', "User/Transaction/" + actID, true);

    req.addEventListener('load', () => {
      if(req.status >= 200 && req.status < 400){
        
        this.state.account = JSON.parse(req.responseText);

        var s = "";
        s += JSON.parse(req.responseText).accountName + " #";
        s += JSON.parse(req.responseText).accountNumber;
        document.getElementById("accountTitle").innerHTML = s;

        s = "";
        s += "$" + JSON.parse(req.responseText).balance;
        document.getElementById("bal").innerHTML = s;

        s = "";
        s += "Interest Rate: " + (100 * JSON.parse(req.responseText).interestRate) + "%";
        var dummyTerm = 1;
        if( JSON.parse(req.responseText).term != -1 ){
          dummyTerm = JSON.parse(req.responseText).term;
          s += " for " + JSON.parse(req.responseText).term + " years. Mature value will be $";
        } else {
          s += " In 1 year (without withdraws or deposits) balance will be $";
        }
        document.getElementById("rate").innerHTML = s;
        
        var al = document.getElementById("historyList");
        al.innerHTML = parseHistory(req);




        var body = {
          balance: JSON.parse(req.responseText).balance,
          interestRate: JSON.parse(req.responseText).interestRate,
          term: dummyTerm
        }
        var req2 = apiCall(body, 'POST', "FutureValue", true);
        req2.addEventListener('load', () => {
          if(req2.status >= 200 && req2.status < 400){
            s = req2.responseText;


            document.getElementById("rate").innerHTML = document.getElementById("rate").innerHTML + s;
          }
        })


        

      }
    })


  }




  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  handleChangeA(event) {
    this.setState({
      actionTypeSelected: document.getElementById("actionType").value
    })
  }

  handleFocus = (event) => event.target.select();

  closeAccount(){

    if(this.state.account.accountName == "Savings Account"){
      window.alert("Your Savings Account cannot be closed without closing your entire User Account. If you're sure you want to do this, you can do so from your User page.");
      return;
    }
    

    if(window.confirm("Really close this account, moving all funds to Savings?")){


      var req = apiCall(null, 'PUT', "User/Close/" + this.state.account.accountNumber, true);

      req.addEventListener('load', () => {
        if(req.status >= 200 && req.status < 400){
          window.location = "/User";
        }
      })



    }

  }

  makeTrans() {
    
    if(this.state.amount <= 0){
      document.getElementById("amount").focus();
      return;
    }


    var req = new XMLHttpRequest();
    let url = window.location;
    let urlSplit = url.toString().split("/");
    let actID = urlSplit[urlSplit.length - 1];

    let amt = this.state.amount;

    let mem = "Deposit";
    if(this.state.actionTypeSelected == 2){
      mem = "Withdraw"; 
      amt *= -1;
    }



    var body = {
      amount: amt,
      sourceAccount: actID,
      targetAccount: actID,
      transactionMemo: mem
    }


    var req = apiCall(body, 'POST', "User/Transaction", true);
    
    req.addEventListener('load', () => {
      if(req.status >= 200 && req.status < 400){
        window.location.reload();
        
        
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
              
              <li><a href="/user">Welcome, USER</a></li>
              <li><a href="/login">Sign Off</a></li>
            </ul>
          </div>


        


        <div>
          <div title="Transaction History">
          <div id="userTitle"></div>

          

            <div>
                <h1 id="accountTitle" class="accountTitle">Account</h1>
                <h1 id="bal" class="userBalance"></h1>
                <h1 id="rate" class="accountInterest"></h1>

                <div class="userTrans">
                  Make a &nbsp;

                  <select id="actionType"
                    value={this.state.actionTypeSelected}
                    onChange={this.handleChangeA}
                  >
                    <option value="1">Deposit</option>
                    <option value="2">Withdraw</option>
                  </select>


                  &nbsp; of $

                  <input
                    className="adminTool" 
                    name="amount"
                    id="amount"
                    value={this.state.amount}
                    onChange={this.handleChange}
                    onFocus={this.handleFocus}
                    required
                  />

                  <button className="adminTool" onClick={this.makeTrans}>Submit</button>

                </div>





            </div>

          </div>

        </div>
        <div id="historyList"></div>

        <br></br>
        <button 
          className={"closeButton"}
          id="closeButton" 
          onClick={this.closeAccount}
        >Close Account</button>
        
      </header>
      </body>
      
      </>
    );
  }
}
