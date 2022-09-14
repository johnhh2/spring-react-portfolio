import React from 'react';
import './UserManagement.css'
import config from './config.json';

const serverAddress = config.SERVER_ADDR;

export default class UserView extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      jsx: null
    }
  }

  componentDidMount() {
    let rows = [];
    const requestOptions = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      }
    };
    fetch(`${serverAddress}/api/user/get/all`, requestOptions)
      .then(async response => {
        const data = await response.json();

        for (let key in data) {
            const user = data[key];
            const href = `view/${user.id}`;
            const content = <tr key={key}>
                      <td><a href={href}>{user.id}</a></td>
                      <td>{user.account.realname}</td><td>{user.username}</td>
                      <td>{user.email}</td><td>{user.age}</td>
                      <td>{String(user.account.darkMode)}</td></tr>;
            rows.push(content);
        }
      })
      .then(() => {
        this.setState({jsx: (
          <div className="UserManagement">
            <header className="UserManagement-header">
              <h1>User Data</h1>
              <table border="1">
                <thead>
                  <tr>
                    <th>ID</th><th>Name</th><th>Username</th>
                    <th>Email</th><th>Age</th><th>Dark Mode</th>
                  </tr>
                </thead>
                <tbody>
                  {rows}
                </tbody>
              </table>
            </header>
          </div>
        )});
        this.render();
      });
  }

  render() {
    return this.state.jsx;
  }
}
