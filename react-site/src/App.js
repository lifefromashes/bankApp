import React from 'react';
import './App.css';

import Home from './pages/Home';
import Accounts from './pages/Accounts';
import Checking from './pages/Checking';
import Error from './pages/Error';

import {Route, Switch} from 'react-router-dom';

import Navbar from './components/Navbar';
function App() {
  return (
  <>
  <Navbar/>
  <Switch>
  <Route exact path= "/" component = {Home}/>
  <Route exact path= "/accounts" component = {Accounts}/>
  <Route exact path= "/accounts/:slug" component = {Checking}/>
  <Route component ={Error} />
  </Switch>
  </>

  );
}

export default App;
