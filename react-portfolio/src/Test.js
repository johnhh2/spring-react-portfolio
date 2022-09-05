import logo from './logo.svg';
import './Test.css';
import React from 'react';

import config from './config.json';

const serverAddress = config.SERVER_ADDR;

class Test extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      key: "Loading...",
    };
  }

  componentDidMount() {
    const requestOptions = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    };
    fetch(`${serverAddress}/api/`, requestOptions)
      .then(async response => {
         const data = await response.json();
         this.setState({
           key: data.key,
         });
         this.render();
      })
      .catch(error => {
        this.setState({ errorMessage: error.toString() });
        console.error('Error', error);
      });
  }

  incrementNumber() {
    const requestOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ increment: 3 })
    };
    fetch(`${serverAddress}/api/edit`, requestOptions)
      .then(async response => {
        const data = await response.json();
        this.setState({
          key: data.key,
        });
        this.render();
      })
      .catch(error => {
        console.error('Error', error);
      });
  }

  render() {
    return (
      <div className="Test">
        <header className="Test-header">
          <img src={logo} className="Test-logo" alt="logo" />
          <p>
            Number of times clicked * 3 = {this.state.key}
          </p>
          <button onClick={this.incrementNumber.bind(this)}>Update</button>
        </header>
      </div>
    );
  }
}

export default Test;
