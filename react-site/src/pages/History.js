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

        //t.innerHTML = "<p>" + JSON.parse(req.responseText).firstName;
        //t += "" + "</p>";


        var al = document.getElementById("historyList");
        al.innerHTML = parseHistory(req);

        //var his = JSON.parse(req.responseText).length;
        //for(let i=0; i<his; i++){
          //let b = document.getElementById("accountID" + i);
          //this.state.accounts[this.state.accountIndex] = b;
          //this.state.accountIndex ++;
          //b.addEventListener('click', () => {
            //window.location = "history/" + JSON.parse(req.responseText).bankAccounts[i].accountNumber;
          //});
        //}



        console.log("transactions: " + req.responseText);

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
      <div>
        <div title="Transaction History">
        <div id="userTitle"></div>

          <div>

              <input
                type="sample"
                name="sample"
                placeholder="Sample"
                value={this.state.sample}
                onChange={this.handleChange}
                required
              />



              {/*<button onClick={this.loginRequest}>Login</button>*/}

          </div>

        </div>

      </div>
      <div id="historyList"></div>
      </>
    );
  }
}
