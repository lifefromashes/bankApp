import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import nick from '../images/nick.png';
import rufaro from '../images/rufaro.jpg';
import kristin from '../images/kristin.jpg';


export default class AboutUs extends Component {

  render() {
    return (
      <>
      <head>
        <title>ABOUT US</title>
        <link rel="stylesheet" type="text/css" href= "../css/AboutUs.css" />
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
              <a href="#kristin"><img src={kristin} /></a>
              <a href="#nick"><img src={nick} /></a>
              <a href="#rufaro"><img src={rufaro}/></a>

            </div>
            <div class="section" id="kristin">
                <span class="name">Kristin Skipper</span>
                <span class="border"></span>
                <p>
                Back End Developer</p>
                <partner> Kristin's brilliance shines when she's solving problems. Her drive for knowledge when programming makes her the best candidate for any Java related task.
                Her specialties include: problem-solving, quick reasoning, and taking calculated risks.</partner>
                <a class = "btn" href= "https://www.linkedin.com/in/kristin-skipper"> LEARN MORE </a>

            </div>
            <div class="section" id="nick">
                <span class="name">Nick R.</span>
                <span class="border"></span>

                <p>
                Tech Lead</p>
                <partner>Nick's general curiosity has made him a sponge for information
                but a machine when it comes to excecution in all his programming endeavors. His specialties include: problem-solving, forward thinking, & patience.</partner>
                <a class = "btn" href= "https://www.linkedin.com/in/nick-rekuch"> LEARN MORE </a>

            </div>
            <div class="section" id="rufaro">
                <span class="name">Rufaro Z.</span>
                <span class="border"></span>
                <p>
                Front End Developer</p>
                <partner> Kristin's brilliance shines when she's solving problems. Her drive for knowledge when programming makes her the best candidate for any Java related task.
                Her specialties include: problem-solving, quick reasoning, and taking calculated risks.</partner>
                <a class = "btn" href= "https://www.linkedin.com/in/rufarozengeni"> LEARN MORE </a>
            </div>
            </div>
          </header>
        </body>
      </>
    );
  }
}
