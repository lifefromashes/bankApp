import React, {Component} from 'react';
import {Link} from 'react-router-dom';



export default class ContactUs extends Component {

  render() {
    return (
      <>
      <head>
        <title>LEARN MORE</title>
        <link rel="stylesheet" type="text/css" href= "../App.css" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css" integrity="sha384-Bfad6CLCknfcloXFOyFnlgtENryhrpZCe29RTifKEixXQZ38WheV+i/6YWSzkz3V" crossOrigin="anonymous"/>
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
              <li><a href="/aboutus">About Us</a></li>
              <li class = "contactus"><a href="/contactus">Contact Us</a></li>
              <li><a href="#">Mobile</a></li>
            </ul>
          </div>
          <section id="contact-section">
              <div class="container">
                  <h2>Contact Us</h2>
                    <p> We're available right at your finger tips! </p>
                    <div class="contact-form">

                      <div>
                        <i class="fa fa-map-marker"></i><span class="form-info"> DFW, Texas To Be decided</span><br />
                        <i class="fa fa-phone"></i><span class="form-info"> +1-999-999-9999</span><br />
                        <i class="fa fa-envelope"></i><span class="form-info"> info@meritbank.com</span><br />
                        </div>
                    <div class="form">
                        <input type="text" placeholder="Your Name" required/>
                        <input type="text" placeholder="Last Name" required/>
                        <input type="Email" placeholder="Email" required/>
                        <input type="text" placeholder="Subject of this mesage" required/>
                        <textarea name="message" placeholder="Message" rows="5" required></textarea>
                        <button class="submit">Send Message</button>
                      </div>
                      </div>
                </div>
        </section>
      </header>
      </body>

      </>
    );
  }
}
