import React, { Component } from "react";
import defaultBcg from "../images/logo.jpg";

import { Link } from "react-router-dom";
import { AccountContext } from "../context";


export default class CheckingAccount extends Component {
  constructor(props) {
    super(props);
    console.log(this.props);
    this.state = {
      slug: this.props.match.params.slug,
      defaultBcg: defaultBcg
    };
  }
  static contextType = AccountContext;

  // componentDidMount() {
  //   console.log(this.props);
  // }
  render() {
    const { getAccount } = this.context;
    const account = getAccount(this.state.slug);

    if (!account) {
      return (
        <div className="error">
          <h3> no such room could be found...</h3>
          <Link to="/accounts" className="btn-primary">
            back to rooms
          </Link>
        </div>
      );
    }
    const {
      name,
      description,
      capacity,
      size,
      price,
      extras,
      breakfast,
      pets,
      images
    } = account;
    const [main, ...defaultImages] = images;
    console.log(defaultImages);

    return (
      <>
            <Link to="/accounts" className="btn-primary">
              back to rooms
            </Link>
        <section className="checking-account">
          <div className="checking-account-images">
            {defaultImages.map((item, index) => (
              <img key={index} src={item} alt={name} />
            ))}
          </div>
          <div className="checking-account-info">
            <article className="desc">
              <h3>details</h3>
              <p>{description}</p>
            </article>
            <article className="info">
              <h3>info</h3>
              <h6>price : ${price}</h6>
              <h6>size : {size} SQFT</h6>
              <h6>
                max capacity :
                {capacity > 1 ? `${capacity} people` : `${capacity} person`}
              </h6>
              <h6>{pets ? "pets allowed" : "no pets allowed"}</h6>
              <h6>{breakfast && "free breakfast included"}</h6>
            </article>
          </div>
        </section>
        <section className="account-extras">
          <h6>extras </h6>
          <ul className="extras">
            {extras.map((item, index) => (
              <li key={index}>- {item}</li>
            ))}
          </ul>
        </section>
      </>
    );
  }
}
