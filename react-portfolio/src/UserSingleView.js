import { Component } from 'react';
import { useParams } from 'react-router-dom';
import './UserManagement.css'
import config from './config.json';

const serverAddress = config.SERVER_ADDR;

function withParams(Component) {
  return props => <Component {...props} params={useParams()} />;
}

class UserSingleView extends Component {
  constructor(props) {
    super(props);
    this.state = {
      id: -1,
      account: null,
      username: "",
      email: "",
      age: 0,
    }
  }

  componentDidMount() {
    const requestOptions = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': localStorage.getItem("AuthToken"),
      },
    };
    const params_id = this.props.params.id
    fetch(`${serverAddress}/api/user/get?id=${params_id}`, requestOptions)
      .then(async response => {
        const data = await response.json();

        if (data) {
          this.setState({
            id: data.id,
            account: data.account,
            username: data.username,
            email: data.email,
            age: data.age
          });
        }
        this.render();
      });
  }

  render() {
    let content;
    if (this.state.id !== -1) {
      content = (<tr>
        <td>{this.state.id}</td><td>{this.state.account.realname}</td>
        <td>{this.state.username}</td><td>{this.state.email}</td>
        <td>{this.state.age}</td><td>{String(this.state.account.darkMode)}</td>
      </tr>);
    } else {
      content = (<tr>
        <td>Loading...</td><td>Loading...</td><td>Loading...</td>
        <td>Loading...</td><td>Loading...</td><td>Loading...</td>
      </tr>);
    }
    return (
      <div className="UserManagement">
        <header className="UserManagement-header">
          <h3>User information from database</h3>
            <table border="1">
              <thead>
                <tr>
                  <th>ID</th><th>Name</th><th>Username</th>
                  <th>Email</th><th>Age</th><th>Dark Mode</th>
                </tr>
              </thead>
              <tbody>
                {content}
              </tbody>
            </table>
        </header>
      </div>
    );
  }
}

export default withParams(UserSingleView);
