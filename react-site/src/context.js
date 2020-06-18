import React, {Component} from 'react';
import items from './data';


const AccountsContext=React.createContext();

// <RoomContext.Provider value={}

export default class AccountsProvider extends Component {
  state={
    accounts: [],
    sortedAccounts: [],
    featuredAccounts:[],
    loading: true

  };
// if netlify  getData but no netlify


componentDidMount(){
  // this.getData
  let accounts = this.formatData(items);
let featuredAccounts = accounts.filter(account => account.featured ===true);
this.setState({
  accounts,featuredAccounts,sortedAccounts:accounts, loading:false
});
}

formatData(items){
  let tempItems = items.map(item => {
    let id = item.sys.id;
    let images = item.fields.images.map(image => image.fields.file.url);
    let account ={...item.fields,images:images,id}
    return (account);
  });
  return (tempItems);
}


  render(){
    return ( <AccountsContext.Provider value="{{...this.state}}">
    {this.props.children}
    </AccountsContext.Provider>
  );
  }
}

const AccountsConsumer = AccountsContext.Consumer;

export{AccountsProvider, AccountsConsumer, AccountsContext};
