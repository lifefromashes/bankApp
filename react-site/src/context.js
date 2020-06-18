import React, {Component} from 'react';
import items from './data';



const AccountContext = React.createContext();


export default class AccountProvider extends Component {
  state={
    accounts: [],
    sortedAccounts: [],
    featuredAccounts:[],
    loading: true,
    type: "all",
    capacity: 1,
    price: 0,
    minPrice: 0,
    maxPrice: 0,
    minSize: 0,
    maxSize: 0,
    breakfast: false,
    pets: false
  };

componentDidMount() {
    // this.getData();
    let accounts = this.formatData(items);
    let featuredAccounts = accounts.filter(account => account.featured === true);
    //
    let maxPrice = Math.max(...accounts.map(item => item.price));
    let maxSize = Math.max(...accounts.map(item => item.size));
    this.setState({
      accounts,
      featuredAccounts,
      sortedAccounts: accounts,
      loading: false,
      //
      price: maxPrice,
      maxPrice,
      maxSize
    });
  }

formatData(items){
  let tempItems = items.map(item => {
    let id = item.sys.id;
    let images = item.fields.images.map(image => image.fields.file.url);
    let account = {...item.fields, images, id};
    return account;
  });
  return tempItems;
}

getAccount = slug => {
    let tempAccounts = [...this.state.accounts];
    const account = tempAccounts.find(account => account.slug === slug);
    return account;
  };
  handleChange = event => {
    const target = event.target;
    const value = target.type === "checkbox" ? target.checked : target.value;
    const name = target.name;
    console.log(name, value);

    this.setState(
      {
        [name]: value
      },
      this.filterAccounts
    );
  };
  filterAccounts = () => {
    let {
      accounts,
      type,
      capacity,
      price,
      minSize,
      maxSize,
      breakfast,
      pets
    } = this.state;

    let tempAccounts = [...accounts];
    // transform values
    // get capacity
    capacity = parseInt(capacity);
    price = parseInt(price);
    // filter by type
    if (type !== "all") {
      tempAccounts = tempAccounts.filter(account => account.type === type);
    }
    // filter by capacity
    if (capacity !== 1) {
      tempAccounts = tempAccounts.filter(account => account.capacity >= capacity);
    }
    // filter by price
    tempAccounts = tempAccounts.filter(account => account.price <= price);
    //filter by size
    tempAccounts = tempAccounts.filter(
      account => account.size >= minSize && account.size <= maxSize
    );
    //filter by breakfast
    if (breakfast) {
      tempAccounts = tempAccounts.filter(account => account.breakfast === true);
    }
    //filter by pets
    if (pets) {
      tempAccounts = tempAccounts.filter(account => account.pets === true);
    }
    this.setState({
      sortedAccounts: tempAccounts
    });
  };

  render(){
    return ( <AccountContext.Provider value={{...this.state, getAccount: this.getAccount, handleChange: this.handleChange}}>
    {this.props.children}
    </AccountContext.Provider>
  );
  }
}

const AccountConsumer = AccountContext.Consumer;

export{AccountProvider, AccountConsumer, AccountContext};

export function withAccountConsumer(Component) {
  return function ConsumerWrapper(props){
    return (
      <AccountConsumer>
      {value => <Component{...props} context={value} />}
      </AccountConsumer>
    );
  };
}
