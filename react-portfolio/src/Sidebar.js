import './Sidebar.css';
import React from 'react';

import config from './config.json';

const serverAddress = config.SERVER_ADDR;

class Sidebar extends React.Component {
  constructor(props) {
    super(props);
    this.state = {hideNav: false};
    this.buttons = [
      {
        name: "Home",
        href: "/",
        googleIcon: "",
      }
    ];
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
           {
             this.buttons.map((button, index) =>
               React.createElement("a", { className: "Sidebar-tab", href: button.href }, button.name))
           }
         </div>
       );
    } else {
      return null;
    }
  }
}
export default Sidebar;
