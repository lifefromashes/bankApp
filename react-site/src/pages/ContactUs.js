import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {saveTokenInCookie, readCookie, logout, setCookieHeader} from "../cookieUtil";



export default class ContactUs extends Component {

  constructor(props) {
    super(props);

    this.state = {
      firstname: "First Name",
      lastname: "Last Name",
      email: "Email@Somewhere.com",
      subject: "Subject",
      message: "Message"
    }

    this.submitFeedback = this.submitFeedback.bind(this);
    this.handleFocus = this.handleFocus.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleFocus = (event) => event.target.select();


  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  submitFeedback(){
    
    var req = new XMLHttpRequest();
    var urlString = "http://localHost:8080/Feedback";

    var body = '{"firstname": "' + this.state.firstname + '", ';
    body += '"lastname": "' + this.state.lastname + '", ';
    body += '"email": "' + this.state.email + '", ';
    body += '"subject": "' + this.state.subject + '", ';
    body += '"message": "' + this.state.message + '"}';

    req.open('POST', urlString);
    req.setRequestHeader('Content-Type', 'application/json');
    setCookieHeader(req);
    req.send(body);

    req.addEventListener('load', () => {
      if(req.status >= 200 && req.status < 400){
        alert("Thanks! We'll review your message and get back to you.");
        //document.getElementById("myForm").reset();
        document.getElementById("firstname").value = "";
      }
    })

  }

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
                    <div class="form" id="myForm">
                        <input type="text" name="firstname" id="firstname" value={this.state.firstname} onFocus={this.handleFocus} onChange={this.handleChange} required/>
                        <input type="text" name="lastname" id="lastname" value={this.state.lastname} onFocus={this.handleFocus} onChange={this.handleChange} required/>
                        <input type="Email" name="email" id="email" value={this.state.email} onFocus={this.handleFocus} onChange={this.handleChange} required/>
                        <input type="text" name="subject" id="subject" value={this.state.subject} onFocus={this.handleFocus} onChange={this.handleChange} required/>
                        <textarea name="message" rows="5" id="message" value={this.state.message} onFocus={this.handleFocus} onChange={this.handleChange} required></textarea>
                        <button class="submit" onClick={this.submitFeedback}>Send Message</button>
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
