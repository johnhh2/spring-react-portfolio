import './Sidebar.css';
import React from 'react';

import config from './config.json';

const serverAddress = config.SERVER_ADDR;

class Sidebar extends React.Component {
  constructor(props) {
    super(props);
    this.state = {hideNav: false};
  }

  componentDidMount() {
    window.addEventListener("resize", this.resize.bind(this));
    this.resize();
  }

  resize() {
    let currentHideNav = (window.innerWidth < 760);
    if (currentHideNav !== this.state.hideNav) {
      this.setState({hideNav: currentHideNav});
    }
  }

  componentWillUnmount() {
    window.removeEventListener("resize", this.resize.bind(this));
  }

  render() {
    if (this.state.hideNav != true) {
      return (
        <div className="Sidebar">
          <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet"></link>
          <a className="Sidebar-tab"><span class="material-icons-outlined">home</span>Home</a>
        </div>
      );
    } else {
      return null;
    }
  }
}
export default Sidebar;
