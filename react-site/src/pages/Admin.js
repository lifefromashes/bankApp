import React, { Component } from "react";
import Hero from '../components/Hero';
import Banner from "../components/Banner";
import {Link} from 'react-router-dom';
import {saveTokenInCookie, readCookie, logout, setCookieHeader} from "../cookieUtil";

export default class Login extends Component {
  constructor(props) {
    super(props);

    this.state = {
        sample: "",
        username: "",
        password: "",
        loginErrors: ""
    };
  }




  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  

  render() {
    return (
      <>
      <Hero hero="accountsHero">
        <Banner title="Admin Page">
          
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
        </Banner>
      </Hero>
      </>
    );
  }
}
