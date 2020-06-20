import React from "react";

import {Link} from 'react-router-dom';
import AccountsContainer from "../components/AccountsContainer";

const Accounts = () =>{
  return (
    <>
    
          <Link to="/"  className="btn-primary">
          Return Home
          </Link>

      <AccountsContainer />
    </>
  );
};

export default Accounts;
