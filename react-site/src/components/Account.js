import React from "react";
import { Link } from "react-router-dom";
import defaultImg from "../images/logo.jpg";
import PropTypes from "prop-types";
import { memo } from "react";
const Account = memo(({ account }) => {
  const { name, slug, images, price } = account;
  // console.log(name);
  return (
    <article className="account">
      <div className="img-container">
        <img src={images[0] || defaultImg} alt="checking account" />
        <div className="price-top">
          <h6>${price}</h6>
          <p>per night</p>
        </div>
        <Link to={`/accounts/${slug}`} className="btn-primary account-link">
          features
        </Link>
      </div>
      <p className="account-info">{name}</p>
    </article>
  );
});

Account.propTypes = {
  account: PropTypes.shape({
    name: PropTypes.string.isRequired,
    slug: PropTypes.string.isRequired,
    images: PropTypes.arrayOf(PropTypes.string).isRequired,
    price: PropTypes.number.isRequired
  })
};
export default Account;
