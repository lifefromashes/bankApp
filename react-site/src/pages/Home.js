import React from "react";

import {Link} from 'react-router-dom';


const Home = () => {
  return(
    <>
    <head>
      <title>MERIT BANK</title>
      <link rel="stylesheet" type="text/css" href= "../css/Home.css" />
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
          </ul>
        </div>
        <div class="title">
          <h1>WELCOME TO MERIT BANK</h1>
          <div>
          <h2>Investing in your Future</h2><br />
          </div>
          <div>
          <a class = "btn" href= "/services"> LEARN MORE </a>
          </div>
        </div>


      </header>
    </body>

    </>

);

};

export default Home;
