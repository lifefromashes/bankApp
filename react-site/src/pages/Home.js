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
            <li class="home"><a href="index.html">Home</a></li>
            <li><a href="menu/personal.html">Personal</a></li>
            <li><a href="menu/services.html">Services</a></li>
            <li><a href="menu/about.html">About</a></li>
            <li><a href="menu/contact.html">Contact</a></li>
            <li><a href="#">Mobile</a></li>
          </ul>
        </div>
        <div class="title">
          <h1>WELCOME TO MERIT BANK</h1>
          <h2>Investing in your future</h2>
        </div>
        <div class="button">
          <a class ="btn" href="menu/services.html">LEARN MORE</a>
        </div>
      </header>
    </body>

    </>

);

};

export default Home;
