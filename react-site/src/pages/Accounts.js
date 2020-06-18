import React from "react";
import Hero from '../components/Hero'
import Banner from "../components/Banner";
import {Link} from 'react-router-dom';
import AccountsContainer from "../components/AccountsContainer";

const Accounts = () =>{
  return (
    <>
      <Hero hero="accountsHero">
        <Banner title="Accounts">
          <Link to="/"  className="btn-primary">
          Return Home
          </Link>
        </Banner>
      </Hero>;
      <AccountsContainer />
    </>
  );
};

export default Accounts;
