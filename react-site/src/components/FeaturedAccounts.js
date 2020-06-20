import React, {Component} from 'react';
import {AccountContext} from '../context';
import Account from './Account';
import Title from './Title';

export default class FeaturedAccounts extends Component{
  static contextType = AccountContext;

  render() {
    let {loading, featuredAccounts: accounts } = this.context;

    accounts = accounts.map(account => {
      return <Account key={account.id} account={account} />;
    });
    return(
      <section className="featured-accounts">
        <Title title="featured accounts" />
        <div className="featured-accounts-center">
        </div>
      </section>
    );
  }
}
