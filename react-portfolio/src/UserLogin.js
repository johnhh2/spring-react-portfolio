import React from 'react';
import './UserManagement.css'
import config from './config.json';

const serverAddress = config.SERVER_ADDR;

export default class UserLogin extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
        username: "",
        password: "",
    };
  }

  handleChange(event) {
    switch (event.target.id) {
      case "username":
        this.setState({username: event.target.value});
        break;
      case "password":
        this.setState({password: event.target.value});
        break;
      default:
        console.error("Unexpected event target: ", event.target);
        break;
    }
    let form_response = document.getElementById('form-response');
    if (form_response.innerHTML) {
      form_response.innerHTML = "";
    }
  }

  handleSubmit(event) {
    const requestOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        username: this.state.username,
        password: this.state.password,
      })
    };
    fetch(`${serverAddress}/api/auth/signin`, requestOptions)
      .then(async response => {
        const data = await response.json();
        if (response.ok) {
          this.setState({
            username: "",
            password: "",
          });
          // TODO: redirect and add auth token
          localStorage.setItem("AuthToken", data.tokenType + " " + data.accessToken);
          window.location = "/";
        }
      })
      .catch(error => {
        console.error('Error', error);
      });
    event.preventDefault();
  }

  render() {
    return (
      <div className="UserManagement">
        <header className="UserManagement-header">
          <form onSubmit={this.handleSubmit.bind(this)}>
          <label htmlFor="username">Username: </label>
          <input type="text" id="username" name="username" value={this.state.username} onChange={this.handleChange.bind(this)} /><br/>
          <label htmlFor="password">Password: </label>
          <input type="password" id="password" name="password" value={this.state.password} onChange={this.handleChange.bind(this)} /><br/>
          <span id='form-response'></span>
          <input type="submit" id="submit" name="Create User" />
          </form>
        </header>
      </div>
    );
  }
}
