import React, { Component } from "react";
import Hero from '../components/Hero';
import Banner from "../components/Banner";
import {Link} from 'react-router-dom';
import axios from "axios"; //lightweight rest http client based on angular ugh. use yarn add axios
//can also use fetch. one of the two

export default class Registration extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: "",
      password: "",
      password_confirmation: "",
      registrationErrors: ""
    };

    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  handleSubmit(event) {
    const { email, password, password_confirmation } = this.state;

    axios
      .post(
        "http://localhost:3000/registrations",
        {
          user: {
            email: email,
            password: password,
            password_confirmation: password_confirmation
          }
        },
        { withCredentials: true }
      )
      .then(response => {
        if (response.data.status === "created") {
          this.props.handleSuccessfulAuth(response.data);
        }
      })
      .catch(error => {
        console.log("registration error", error);
      });
    event.preventDefault();
  }

  render() {
    return (
      <>
      <Hero hero="accountsHero">
        <Banner title="Register">
          <Link to="/login" className="btn-primary">
          Already have an Account?
          </Link>
            <div>
              <form onSubmit={this.handleSubmit}>
                <input
                  type="email"
                  name="email"
                  placeholder="Email"
                  value={this.state.email}
                  onChange={this.handleChange}
                  required
                />

                <input
                  type="password"
                  name="password"
                  placeholder="Password"
                  value={this.state.password}
                  onChange={this.handleChange}
                  required
                />

                <input
                  type="password"
                  name="password_confirmation"
                  placeholder="Password confirmation"
                  value={this.state.password_confirmation}
                  onChange={this.handleChange}
                  required
                />

                <button type="submit">Register</button>
              </form>
            </div>
          </Banner>
        </Hero>
        </>
    );
  }
}
