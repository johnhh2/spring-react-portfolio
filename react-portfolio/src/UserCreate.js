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
        age: 0,
        adminRole: false,
        modRole: false,
        userRole: false,
        darkMode: false,
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
      case "age":
        this.setState({age: event.target.value});
        break;
      case "password":
        this.setState({password: event.target.value});
        break;
      case "adminRole":
        this.setState({adminRole: !this.state.adminRole});
        break;
      case "modRole":
        this.setState({modRole: !this.state.modRole});
        break;
      case "userRole":
        this.setState({userRole: !this.state.userRole});
        break;
      case "darkMode":
        this.setState({darkMode: !this.state.darkMode});
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

  generateRoles() {
    let roles = [];
    if (this.state.adminRole)
      roles.push("admin");
    if (this.state.modRole)
      roles.push("mod");
    if (this.state.userRole)
      roles.push("user");
    return roles;
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
        age: this.state.age,
        password: this.state.password,
        role: this.generateRoles(),
        darkMode: this.state.darkMode,
      })
    };
    fetch(`${serverAddress}/api/auth/signup`, requestOptions)
      .then(async response => {
        const data = await response.json();
        if (response.ok) {
          this.setState({
            username: "",
            email: "",
            age: 0,
            password: "",
            adminRole: false,
            modRole: false,
            userRole: false,
            darkMode: false,
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
          <label htmlFor="age">Age: </label>
          <input type="number" id="age" name="age" value={this.state.age} onChange={this.handleChange.bind(this)} /><br/>
          <label htmlFor="adminRole">Admin Role: </label>
          <input type="checkbox" id="adminRole" name="adminRole" checked={this.state.adminRole} onChange={this.handleChange.bind(this)} /><br/>
          <label htmlFor="modRole">Mod Role: </label>
          <input type="checkbox" id="modRole" name="modRole" checked={this.state.modRole} onChange={this.handleChange.bind(this)} /><br/>
          <label htmlFor="userRole">User Role: </label>
          <input type="checkbox" id="userRole" name="userRole" checked={this.state.userRole} onChange={this.handleChange.bind(this)} /><br/>
          <label htmlFor="darkMode">Dark Mode: </label>
          <input type="checkbox" id="darkMode" name="darkMode" checked={this.state.darkMode} onChange={this.handleChange.bind(this)} /><br/>
          <span id='form-response'></span>
          <input type="submit" id="submit" name="submit" value="Create User" />
          </form>
          <a href="view">View All Users</a>
        </header>
      </div>
    );
  }
}
