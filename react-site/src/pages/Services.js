import React, {Component} from 'react';
import {Link} from 'react-router-dom';



export default class Services extends Component {

  render() {
    return (
      <>
      <head>
        <title>LEARN MORE</title>
        <link rel="stylesheet" type="text/css" href= "../css/Services.css" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css" integrity="sha384-Bfad6CLCknfcloXFOyFnlgtENryhrpZCe29RTifKEixXQZ38WheV+i/6YWSzkz3V" crossOrigin="anonymous"/>
      </head>
        <body>

        <header>
          <div className="main">
            <div className="logo">
              <img  />
            </div>
            <ul>
              <li><a href="/">Home</a></li>
              <li><a href="/login">Login</a></li>
              <li clasName= "services"><a href="/services">Services</a></li>
              <li><a href="/aboutus">About Us</a></li>
              <li><a href="/contactus">Contact Us</a></li>
            </ul>
          </div>

          <div className="services-section">
          <div className="inner-width">
          <div>
              <h1>WHAT CAN MERIT DO FOR YOU?</h1><br />
              <h4 className="section-title">Merit Bank is a business dedicated to the success and welfare of our customers.<br />
            Below are some of the opportunities benefited to our clients: </h4>
          </div><br />
              <div className="border"></div>
              <div className="services-container">
                <div className="service-box">
                  <div className="service-icon">
                    <i className="fas fa-globe"></i>
                  </div>
                  <div className="service-title">SECURED VIRTUAL BANKING</div>
                  <div className="service-desc">Your entire banking experience all online! <br />
                  Your success is in your hands & we are here to help you manage! </div>
                </div>
                <div className="service-box">
                  <div className="service-icon">
                    <i className="fas fa-user-shield"></i>
                  </div>
                  <div className="service-title"> SECURED PERSONAL ACCOUNTS</div>
                  <div className="service-desc">Create secured Checking & Savings Accounts at your fingertips </div>
                </div>
                <div className="service-box">
                  <div className="service-icon">
                    <i className="fas fa-briefcase"></i>
                  </div>
                  <div class="service-title"> SECURED BUSINESS ACCOUNTS</div>
                  <div class="service-desc">Have a business? We got you covered with our DBA accounts! Great for Entrepreneurs with multiple streams of income. </div>
                </div>
                <div class="service-box">
                  <div class="service-icon">
                    <i class="fas fa-hand-holding-usd"></i>
                  </div>
                  <div class="service-title">INVESTING</div>
                  <div class="service-desc">Many opportunities to secure your future:
                  Regular, Roth, Rollover IRA & Certification of Deposit</div>
                </div>
                <div class="service-box">
                  <div class="service-icon">
                    <i class="fas fa-headset"></i>
                  </div>
                  <div class="service-title">24/7 CHAT & SUPPORT</div>
                  <div class="service-desc">Need help? Stuck with bad a bad connection? We can help!
                    We're available 24/7 live chat or call us! </div>
                </div>
              </div>
            </div>
          </div>
        </header>
        </body>
      </>
    );
  }
}
