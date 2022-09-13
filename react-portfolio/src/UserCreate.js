import React from 'react';
import './UserManagement.css'
import config from './config.json';

const serverAddress = config.SERVER_ADDR;

export default class UserCreate extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
        name: "",
        username: "",
        email: "",
        age: 0
    };
  }

  handleChange(event) {
    switch (event.target.id) {
      case "name":
        this.setState({name: event.target.value});
        break;
      case "username":
        this.setState({username: event.target.value});
        break;
      case "email":
        this.setState({email: event.target.value});
        break;
      case "age":
        this.setState({age: event.target.value});
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
        name: this.state.name,
        username: this.state.username,
        email: this.state.email,
        age: this.state.age})
    };
    fetch(`${serverAddress}/api/user/create`, requestOptions)
      .then(async response => {
        const data = await response.json();
        if (data.success) {
          this.setState({
            name: "",
            username: "",
            email: "",
            age: 0});

          const user = data.user;
          let form_response = document.getElementById('form-response');
          form_response.innerHTML = `User created successfully.<br>
            <a href="view/${user.id}">View ${user.username}</a><br>`;
          this.render();
        } else {
          // TODO: Display reason for error
          let form_response = document.getElementById('form-response');
          form_response.innerHTML = "An error occurred while creating the user.<br>";
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
          <label htmlFor="name">Name: </label>
          <input type="text" id="name" name="name" value={this.state.name} onChange={this.handleChange.bind(this)} /><br/>
          <label htmlFor="username">Username: </label>
          <input type="text" id="username" name="username" value={this.state.username} onChange={this.handleChange.bind(this)} /><br/>
          <label htmlFor="email">Email: </label>
          <input type="email" id="email" name="email" value={this.state.email} onChange={this.handleChange.bind(this)} /><br />
          <label htmlFor="age">Age: </label>
          <input type="number" id="age" name="age" value={this.state.age} onChange={this.handleChange.bind(this)} /><br />
          <span id='form-response'></span>
          <input type="submit" id="submit" name="Create User" />
          </form>
          <a href="view">View All Users</a>
        </header>
      </div>
    );
  }
}
