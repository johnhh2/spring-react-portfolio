import logo from './logo.svg';
import springLogo from './spring-logo.svg';
import './Home.css';
import React from 'react';

import config from './config.json';

const serverAddress = config.SERVER_ADDR;

class Home extends React.Component {

  render() {
    return (
      <div className="Home">
        <header className="Home-header">
	  <h1>Portfolio Project</h1>
          <div className="Home-framework">
	    Created with
            <img src={logo} className="React-logo" alt="logo" />
	    React and
            <img src={springLogo} className="Spring-logo" alt="logo" />
            Spring
          </div>
        </header>
      </div>
    );
  }
}

export default Home;
