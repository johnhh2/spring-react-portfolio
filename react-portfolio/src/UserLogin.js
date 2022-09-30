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

  redirectToAccountEditOrHome() {
    const requestOptions = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': localStorage.getItem("AuthToken"),
      },
    };
    const id = localStorage.getItem("AuthID");
    fetch(`${serverAddress}/api/user/get?id=${id}`, requestOptions)
      .then(async response => {
        const data = await response.json();
        let redirectLocation = "/account/edit";
        if (data.account.realname !== "") {
          redirectLocation = "/";
        }
        this.setState({redirect: redirectLocation},
          () => { window.location = this.state.redirect }
        );
      })
      .catch(error => {
        console.error("Error: ", error);
      });
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
        if (response.ok) {
          const data = await response.json();
          this.setState({
            username: "",
            password: "",
          });
          localStorage.setItem("AuthToken", data.tokenType + " " + data.accessToken);
          localStorage.setItem("AuthID", data.id);
          this.redirectToAccountEditOrHome();
        } else {
          let form_response = document.getElementById('form-response');
          if (response.status === 401) {
            form_response.innerHTML = "Invalid login credentials.<br>";
          } else {
            form_response.innerHTML = "An error occurred while logging in.<br>";
          }
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
          <input type="submit" id="submit" name="submit" value="Login" />
          </form>
          <a href="/users/create">Create User</a>
        </header>
      </div>
    );
  }
}
