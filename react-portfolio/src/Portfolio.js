import logo from './images/logo.svg';
import springLogo from './images/spring-logo.svg';
import './Portfolio.css';
import React from 'react';

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

  createDummyPortfolio() {
    const requestOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        'realname': 'John Doe'})
    };
    fetch(`${serverAddress}/api/portfolio/create`, requestOptions)
      .then(async response => {
        const data = await response.json();
        if (data.success) {
          window.location.reload();
        } else {
          console.error("An unknown error occurred");
        }
      })
      .catch(error => {
        console.error("Error: ", error);
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
            <button onClick={this.createDummyPortfolio.bind(this)}>Create Portfolio</button>
          </header>
        </div>
      );
    } else {
      return (
        <div className="Portfolio">
          <header className="Portfolio-header">
            <p>Loading</p>
            <button onClick={this.createDummyPortfolio.bind(this)}>Create Portfolio</button>
          </header>
        </div>
      );
    }
  }
}
