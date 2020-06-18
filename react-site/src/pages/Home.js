import React from "react";
import Hero from "../components/Hero";
import Banner from '../components/Banner';
import {Link} from 'react-router-dom';
import Services from '../components/Services';
import FeaturedAccounts from '../components/FeaturedAccounts';


const Home = () => {
  return(
    <>
    <Hero>
      <Banner title="Secured Accounts" subtitle="Complete virtual access">
        <Link to="/accounts" className="btn-primary">
          Accounts
        </Link>
      </Banner>
    </Hero>
    <Services />
    <FeaturedAccounts />
    </>

);

};

export default Home;
