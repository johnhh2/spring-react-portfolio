import logo from './images/logo.svg';
import springLogo from './images/spring-logo.svg';
import './Info.css';
import React from 'react';

import config from './config.json';

export default class Info extends React.Component {

  render() {
    let authors = [...config.AUTHORS];
    if (Math.random() < 0.5) {
      authors = authors.reverse();
    }
    let lastAuthor = authors.pop();
    let authorText = authors.join(', ') + ' and ' + lastAuthor;
    
    return (
      <div className="Info">
        <header className="Info-header">
	  <h1>Portfolio Project</h1>
          <div className="Info-authors">
            by {authorText}
          </div>
          <div className="Info-framework">
	    Created with
            <img src={logo} className="React-logo" alt="logo" />
	    React.js and
            <img src={springLogo} className="Spring-logo" alt="logo" />
            Spring
          </div>
            
        </header>
      </div>
    );
  }
}
