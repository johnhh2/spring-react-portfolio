import logo from './logo.svg';
import './App.css';
import React from 'react';

import config from './config.json';

const serverAddress = config.SERVER_ADDR;

class App extends React.Component {
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

  multiplyNumber() {
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
      })
      .catch(error => {
        this.setState({ errorMessage: error.toString() });
        console.error('Error', error);
      });
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Number of times clicked * 3 = {this.state.key}
          </p>
          <button onClick={this.multiplyNumber}>Update</button>
        </header>
      </div>
    );
  }
}

export default App;
