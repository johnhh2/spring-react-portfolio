import logo from './images/logo.svg';
import springLogo from './images/spring-logo.svg';
import './Portfolio.css';
import React from 'react';
/*import {BrowserRouter, Route, Routes} from "react-router-dom";*/

import config from './config.json';

const serverAddress = config.SERVER_ADDR;

export default class Portfolio extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      realname: null,
      projects: null,
    };
  }

  componentDidMount() {
    const requestOptions = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    };
    fetch(`${serverAddress}/api/portfolio/get`, requestOptions)
      .then(async response => {
         const data = await response.json();
         this.setState({
           realname: data.realname,
           categories: data.categories,
           projects: data.projects,
         });
         this.render();
      })
      .catch(error => {
        this.setState({ errorMessage: error.toString() });
        console.error('Error', error);
      });
  }

  render() {
    if (this.state.realname != null) {
      return (
        <div className="Portfolio">
          <header className="Portfolio-header">
            <h1>{ this.state.realname }</h1>
            <div className="Portfolio-project">
              Created with
              <img src={logo} className="React-logo" alt="logo" />
              React.js and
              <img src={springLogo} className="Spring-logo" alt="logo" />
              Spring
            </div>
          </header>
        </div>
      );
    } else {
      return (
        <div className="Portfolio">
          <header className="Portfolio-header">
            <p className="Loading"></p>
          </header>
        </div>
      );
    }
  }
}
