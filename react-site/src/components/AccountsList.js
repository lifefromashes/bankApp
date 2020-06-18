
import React from "react";
import Account from "./Account";
const AccountsList = ({ accounts }) => {
  if (accounts.length === 0) {
    return (
      <div className="empty-search">
        <h3>unfortunately no accounts matched your search parameters</h3>
      </div>
    );
  }
  return (
    <section className="accountslist">
      <div className="accountslist-center">
        {accounts.map(item => {
          return <Account key={item.id} account={item} />;
        })}
      </div>
    </section>
  );
};

export default AccountsList;
