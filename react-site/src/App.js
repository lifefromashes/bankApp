import React from 'react';
import './App.css';

import Home from './pages/Home';
import Accounts from './pages/Accounts';
import CheckingAccount from './pages/CheckingAccount';
import Login from './pages/Login';
import Error from './pages/Error';
import Registration from './pages/Registration';
import Admin from './pages/Admin';
import User from './pages/User';

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
      <Route exact path= "/register" component ={Registration} />
      <Route component ={Error} />
    </Switch>
  </>

  );
}

export default App;
