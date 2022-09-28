import React from 'react';
import './UserManagement.css'
import config from './config.json';

const serverAddress = config.SERVER_ADDR;

export default class UserCreate extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
        username: "",
        email: "",
        password: "",
    };
  }

  handleChange(event) {
    switch (event.target.id) {
      case "username":
        this.setState({username: event.target.value});
        break;
      case "email":
        this.setState({email: event.target.value});
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
        email: this.state.email,
        password: this.state.password,
      })
    };
    fetch(`${serverAddress}/api/auth/signup`, requestOptions)
      .then(async response => {
        const data = await response.json();
        if (response.ok) {
          this.setState({
            username: "",
            email: "",
            password: "",
          });
        }
        let form_response = document.getElementById('form-response');
        form_response.innerHTML = `${data.message}<br>`;
      })
      .catch(error => {
        console.error('Error', error);
      });
    this.render();
    event.preventDefault();
  }

  render() {
    return (
      <div className="UserManagement">
        <header className="UserManagement-header">
          <form onSubmit={this.handleSubmit.bind(this)}>
          <label htmlFor="username">Username: </label>
          <input type="text" id="username" name="username" value={this.state.username} onChange={this.handleChange.bind(this)} /><br/>
          <label htmlFor="email">Email: </label>
          <input type="email" id="email" name="email" value={this.state.email} onChange={this.handleChange.bind(this)} /><br/>
          <label htmlFor="password">Password: </label>
          <input type="password" id="password" name="password" value={this.state.password} onChange={this.handleChange.bind(this)} /><br/>
          <span id='form-response'></span>
          <input type="submit" id="submit" name="submit" value="Create User" />
          </form>
          <a href="view">View All Users</a>
        </header>
      </div>
    );
  }
}
