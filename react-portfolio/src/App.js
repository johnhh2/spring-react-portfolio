import logo from './logo.svg';
import './App.css';
import React from 'react';

import config from './config.json';

const serverAddress = config.SERVER_ADDR;

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      key: 0,
      foo: "foo",
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
           foo: data.foo,
         });
         this.render();
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
            Here is your data: {this.state.key} {this.state.foo}
          </p>
        </header>
      </div>
    );
  }
}

export default App;
