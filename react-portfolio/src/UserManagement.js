import React from 'react';
import './UserManagement.css'
import config from './config.json';

const serverAddress = config.SERVER_ADDR;

class UserManagement extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
        key: 0,
        username: "",
        email: "",
        age: 0
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
      default:
        console.error("Unexpected event target: ", event.target);
        break;
    }
  }

  handleSubmit(event) {
    const requestOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        key: this.state.key,
        username: this.state.username,
        email: this.state.email,
        age: this.state.age})
    };
    fetch(`${serverAddress}/api/create_user`, requestOptions)
      .then(async response => {
        const data = await response.json();
        console.log(data)
        if (data.success) {
          console.log("user created: " + data.user.toString());
          this.setState({key: this.state.key + 1});
          this.render();
        }
        else {
          console.error("An error occurred while creating the test user.");
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
          <label htmlFor="email">Email: </label>
          <input type="email" id="email" name="email" value={this.state.email} onChange={this.handleChange.bind(this)} /><br />
          <label htmlFor="age">Age: </label>
          <input type="number" id="age" name="age" value={this.state.age} onChange={this.handleChange.bind(this)} /><br />
          <input type="submit" id="submit" name="Create User" />
          </form>
        </header>
      </div>
    );
  }
}

export default UserManagement;
