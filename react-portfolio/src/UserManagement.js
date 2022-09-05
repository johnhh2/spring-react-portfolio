import React from 'react';

import config from './config.json';

const serverAddress = config.SERVER_ADDR;

class Portfolio extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }

  createTestUser() {
    const requestOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ key: 0, username: "testuser", email: "test@user.com", dark_mode: true, age: 25})
    };
    fetch(`${serverAddress}/api/create_user`, requestOptions)
      .then(async response => {
        const data = await response.json();
        console.log(data)
        if (data.success) {
          console.log("user created: " + data.user.toString());
          this.render();
        }
        else {
          console.error("An error occurred while creating the test user.");
        }
      })
      .catch(error => {
        console.error('Error', error);
      });
  }

  render() {
    return (
      <div className="Test">
        <header className="Test-header">
          <button onClick={this.createTestUser.bind(this)}>Create Test User</button>
        </header>
      </div>
    );
  }
}

export default Portfolio;
