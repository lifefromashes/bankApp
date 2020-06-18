import React, {Component} from 'react';
import Hero from '../components/Hero';
import Banner from "../components/Banner";
import {Link} from 'react-router-dom';

const Login = () =>{
  return (
    <>
    <Hero hero="accountsHero">
      <Banner title="Login">
        <Link to="/"  className="btn-primary">
        I NEED TO DO SOMETHING HERE.
      </Link>
    </Banner>
  </Hero>
  </>
);
};

export default Login;
