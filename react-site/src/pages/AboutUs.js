import React, {Component} from 'react';
import {Link} from 'react-router-dom';



export default class AboutUs extends Component {

  render() {
    return (
      <>
      <head>
        <title>ABOUT US</title>
        <link rel="stylesheet" type="text/css" href= "../App.css" />
      </head>
      <body>

        <header>
          <div class="main">
            <div class="logo">
              <img  />
            </div>
            <ul>
              <li><a href="/">Home</a></li>
              <li><a href="/login">Login</a></li>
              <li><a href="/services">Services</a></li>
              <li class= "aboutus"><a href="/aboutus">About Us</a></li>
              <li><a href="/contactus">Contact Us</a></li>
            </ul>
          </div>

          <div class="team-section">
            <h1>MEET OUR TEAM</h1>
              <span class="border"></span>
            <div class= "ps">
              <a href="#kristin">kristin<img /></a>
              <a href="#nick">Nick<img /></a>
              <a href="#rufaro">Rufaro<img /></a>
            </div>
            <div class="section" id="kristin">
                <span class="name">Kristin S.</span>
                <span class="border"></span>
                <p>
                  Back-End Developer. She is very nice.
                </p>
            </div>
            <div class="section" id="nick">
                <span class="name">Nick R.</span>
                <span class="border"></span>
                <p>
                  Tech Leader. He is very nice.
                </p>
            </div>
            <div class="section" id="rufaro">
                <span class="name">Rufaro Z.</span>
                <span class="border"></span>
                <p>
                  Front-End Developer. She is very nice.
                </p>
            </div>
            </div>
          </header>
        </body>
      </>
    );
  }
}
