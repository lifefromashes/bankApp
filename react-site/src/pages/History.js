import React, { Component } from "react";
//import Hero from '../components/Hero';
//import Banner from "../components/Banner";
import {Link} from 'react-router-dom';
import {saveTokenInCookie, readCookie, logout, setCookieHeader} from "../cookieUtil";
import {parseBankUser, parseUserByID, parseAccounts, parseHistory} from "../parseBankUser";

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
        amount: 0
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleChangeA = this.handleChangeA.bind(this);
    this.handleFocus = this.handleFocus.bind(this);
    this.makeTrans = this.makeTrans.bind(this);



    var req = new XMLHttpRequest();

    let url = window.location;
    let urlSplit = url.toString().split("/");
    let actID = urlSplit[urlSplit.length - 1];
    var urlString = "http://localHost:8080/User/Transaction/" + actID;
    req.open('GET', urlString);
    req.setRequestHeader('Content-Type', 'application/json');
    var jwt = readCookie("jwt");
    setCookieHeader(req);
    req.send();
    req.addEventListener('load', () => {
      if(req.status >= 200 && req.status < 400){
        //document.getElementById("userTitle").title = JSON.parse(req.responseText).username;
        
        var s = "";
        s += JSON.parse(req.responseText).accountName + " #";
        s += JSON.parse(req.responseText).accountNumber;
        document.getElementById("accountTitle").innerHTML = s;

        s = "";
        s += "$" + JSON.parse(req.responseText).balance;
        document.getElementById("bal").innerHTML = s;

        
        var al = document.getElementById("historyList");
        al.innerHTML = parseHistory(req);

        



        

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


  makeTrans() {
    
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


    var body = '{"amount": "' + amt + '", ';
    body += '"sourceAccount": ' + actID + ', ';
    body += '"targetAccount": ' + actID + ', ';
    body += '"transactionMemo": "' + mem + '"}';

    var urlString = "http://localHost:8080/User/Transaction";
    req.open('POST', urlString);
    req.setRequestHeader('Content-Type', 'application/json');
    var jwt = readCookie("jwt");
    setCookieHeader(req);
    req.send(body);

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
              <li><a href="/login">Sign Off</a></li>
              <li><a href="/user">Welcome, USER</a></li>
              <li><a href="#">Mobile</a></li>
            </ul>
          </div>


        


        <div>
          <div title="Transaction History">
          <div id="userTitle"></div>

          

            <div>
                <h1 id="accountTitle" class="accountTitle">Account</h1>
                <h1 id="bal" class="userBalance"></h1>

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
      </header>
      </body>
      
      </>
    );
  }
}
