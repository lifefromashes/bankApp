import React from 'react';
import './App.css';
import './css/Login.css';
import './css/Services.css';
import './css/AboutUs.css';
import './css/Home.css';

import Home from './pages/Home';
import Accounts from './pages/Accounts';
import CheckingAccount from './pages/CheckingAccount';
import Login from './pages/Login';
import Error from './pages/Error';
import Registration from './pages/Registration';
import Admin from './pages/Admin';
import User from './pages/User';
import History from './pages/History';
import Services from './pages/Services';
import AboutUs from './pages/AboutUs';
import ContactUs from './pages/ContactUs';
import ContactInfo from './pages/ContactInfo';

import {Route, Switch} from 'react-router-dom';


function App() {
  return (
  <>

    <Switch>
      <Route exact path= "/" component = {Home}/>
      <Route exact path= "/accounts" component = {Accounts}/>
      <Route exact path= "/accounts/:slug" component = {CheckingAccount}/>
      <Route exact path= "/login" component = {Login}/>
      <Route exact path= "/admin" component = {Admin}/>
      <Route exact path= "/user" component = {User}/>
      <Route exact path= "/contactinfo" component = {ContactInfo}/>
      <Route exact path= "/history/*" component = {History}/>
      <Route exact path= "/register" component ={Registration} />
      <Route exact path= "/services" component = {Services} />
      <Route exact path= "/aboutus" component = {AboutUs}/>
      <Route exact path= "/contactus" component = {ContactUs} />
      <Route component ={Error} />
    </Switch>
  </>

  );
}

export default App;
