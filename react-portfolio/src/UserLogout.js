import React from 'react';
import './UserManagement.css'
import config from './config.json';

const serverAddress = config.SERVER_ADDR;

export default class UserLogout extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      jsx: null,
      username: "",
    };
  }

  componentDidMount() {
    if (localStorage.getItem("AuthToken") !== null) {
      const requestOptions = {
        method: "GET",
        headers: {
          'Content-Type': 'application/json',
          'Authorization': localStorage.getItem("AuthToken"),
        },
      }
      const id = localStorage.getItem("AuthID");
      fetch(`${serverAddress}/api/user/get?id=${id}`, requestOptions)
        .then(async response => {
          const data = await response.json();
          this.setState({username: data.username});
        })
        .then(() => {
          this.setState({jsx: (
            <div className="UserManagement">
              <header className="UserManagement-header">
                <h4>You are currently logged in as {this.state.username}</h4>
                <h5>Are you sure you would like to log out?</h5>
                <form onSubmit={this.handleSubmit.bind(this)}>
                  <input type="submit" id="submit" name="submit" value="Logout"/>
                </form>
              </header>
            </div>
          )});
        })
        .catch(error => {
          console.error("Error: ", error);
        });
    } else {
      this.setState({jsx: (
        <div className="UserManagement">
          <header className="UserManagement-header">
            <h4>You are not currently logged in.</h4>
            <a href="login">Login?</a>
          </header>
        </div>
      )});
    }
  }

  handleSubmit(event) {
    localStorage.removeItem("AuthToken");
    window.location = "login";
    event.preventDefault();
  }

  render() {
    return this.state.jsx;
  }

}
