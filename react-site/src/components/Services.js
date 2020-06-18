import React, {Component} from 'react';
import {FaGlobe, FaUserShield, FaHandHoldingUsd, FaHeadset} from "react-icons/fa";
import Title from './Title';


export default class Services extends Component {
  state = {
    services:[
      {
        icon:<FaGlobe/>,
        title:"Virtual Banking",
        info: 'Your success is in your hands & we are here to help you manage!'
      },
      {
        icon:<FaUserShield/>,
        title:"Secured Accounts",
        info: 'Create secured Personal & Business Accounts at your fingertips!'
      },
      {
        icon:<FaHandHoldingUsd/>,
        title:"Investing",
        info: 'Numerous opportunities to secure your future!'
      },
      {
          icon:<FaHeadset/>,
          title:"24/7 Chat & Support",
          info: 'We are here to help whenever you need it!'
      }
    ]
  };
  render() {
    return (
      <section className="services">
        <Title title="services"/>
        <div className="services-center">{this.state.services.map((item, index) =>{
          return <article key={index} className="sercice">
          <span>{item.icon}</span>
          <h6>{item.title}</h6>
          <p>{item.info}</p>
          </article>
        })}
        </div>
      </section>
    );
  }
}
