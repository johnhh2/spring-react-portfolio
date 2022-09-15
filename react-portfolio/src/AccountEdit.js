import React from 'react';
import './UserManagement.css'
import config from './config.json';

const serverAddress = config.SERVER_ADDR;

export default class AccountEdit extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
        email: "",
        name: "",
        hostname: "",
        hostnameEnabled: false,
        darkMode: false,
        categories: null,
        projects: null,
    };
  }

  componentDidMount() {
    const requestOptions = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    }
    const userId = localStorage.getItem("AuthID")
    this.setState({userId: userId});
    fetch(`${serverAddress}/api/user/get?id=${userId}`, requestOptions)
      .then(async response => {
        const data = await response.json();
        if (response.ok) {
          this.setState({
            email: data.email,
            name: data.account.realname,
            hostname: data.account.hostname.name,
            hostnameEnabled: data.account.hostname.enabled,
            darkMode: data.account.darkMode,
            categories: data.account.categories,
            projects: data.account.projects,
          }, function() {
            console.log(this.state.name);
          });
        }
      })
      .catch(error => {
        console.error('Error', error);
      });
    if (this.state.name !== "") {
      this.setState({title: "Settings"});
    } else {
      this.setState({title: "Set up your Account!"});
    }
    this.render();
  }

  handleChange(event) {
    switch (event.target.id) {
      case "name":
        this.setState({name: event.target.value});
        break;
      case "hostname":
        this.setState({hostname: event.target.value});
        break;
      case "hostname_enabled":
        this.setState({hostnameEnabled: !this.state.hostnameEnabled});
        break;
      case "dark_mode":
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

  handleSubmit(event) {
    const requestOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        name: this.state.name,
        hostname: this.state.hostname,
        hostnameEnabled: this.state.hostnameEnabled,
        darkMode: this.state.darkMode,
        categories: this.state.categories,
        projects: this.state.projects,
      })
    };
    fetch(`${serverAddress}/api/account/edit?userId=${this.state.userId}`, requestOptions)
      .then(async response => {
        const data = await response.json();
        if (data.success) {
          this.setState({
            message: data.message,
          });
        } else {
          console.error('Error', data.message);
          this.setState({errorMessage: data.message});
        }
      })
      .catch(error => {
        console.error('Error', error);
        this.setState({errorMessage: error});
      });
    this.render();
    event.preventDefault();
  }

  render() {

    return (
      <div className="UserManagement">
        <header className="UserManagement-header">
          <h2>{this.state.title}</h2>
          <p>{this.state.email}</p>
          <br/>
          <span id='form-response'>{this.state.message}</span>
          <span id='form-error'>{this.state.errorMessage}</span>
          <form onSubmit={this.handleSubmit.bind(this)}>
          <label htmlFor="name">Name: </label>
          <input type="text" id="name" name="name" value={this.state.name} onChange={this.handleChange.bind(this)} /><br/>
          <label htmlFor="hostname">Hostname: </label>
          <input type="text" id="hostname" name="hostname" value={this.state.hostname} onChange={this.handleChange.bind(this)} /><br/>
          <label htmlFor="hostname_enabled">Hostname Enabled: </label>
          <input type="checkbox" id="hostname_enabled" name="hostname_enabled" checked={this.state.hostnameEnabled} onChange={this.handleChange.bind(this)} /><br/>
          <label htmlFor="dark_mode">Dark Mode: </label>
          <input type="checkbox" id="dark_mode" name="dark_mode" checked={this.state.darkMode} onChange={this.handleChange.bind(this)} /><br/>
          <input type="submit" id="submit" name="submit" value="Save" />
          </form>
        </header>
      </div>
    );
  }
}