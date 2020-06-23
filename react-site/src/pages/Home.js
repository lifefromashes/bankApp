import React from "react";

import {Link} from 'react-router-dom';


const Home = () => {
  return(
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
            <li class="home"><a href="/">Home</a></li>
            <li><a href="/login">Login</a></li>
            <li><a href="/services">Services</a></li>
            <li><a href="/aboutus">About Us</a></li>
            <li><a href="/contactus">Contact Us</a></li>
            <li><a href="#">Mobile</a></li>
          </ul>
        </div>
        <div class="title">
          <h1>WELCOME TO MERIT BANK</h1>
          <h2>Investing in your future</h2>
        </div>
        <div class="button">
          <a class ="btn" href="/services">LEARN MORE</a>
        </div>
      </header>
    </body>

    </>

);

};

export default Home;
