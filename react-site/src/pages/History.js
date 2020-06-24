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
        accountIndex: 0
    };



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
                <h1 id="accountTitle">Account</h1>

                {/*}
                <input
                  class="adminTool"
                  type="sample"
                  name="sample"
                  placeholder="Sample"
                  value={this.state.sample}
                  onChange={this.handleChange}
                  required
                />
                */}



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
