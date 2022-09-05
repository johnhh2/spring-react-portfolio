import logo from './images/logo.svg';
import springLogo from './images/spring-logo.svg';
import './Home.css';
import React from 'react';

import config from './config.json';

const serverAddress = config.SERVER_ADDR;

class Home extends React.Component {

  render() {
    let authors = [...config.AUTHORS];
    if (Math.random() < 0.5) {
      authors = authors.reverse();
    }
    let lastAuthor = authors.pop();
    let authorText = authors.join(', ') + ' and ' + lastAuthor;
    
    return (
      <div className="Home">
        <header className="Home-header">
	  <h1>Portfolio Project</h1>
          <div className="Home-authors">
            by {authorText}
          </div>
          <div className="Home-framework">
	    Created with
            <img src={logo} className="React-logo" alt="logo" />
	    React and
            <img src={springLogo} className="Spring-logo" alt="logo" />
            Spring.js
          </div>
            
        </header>
      </div>
    );
  }
}

export default Home;
