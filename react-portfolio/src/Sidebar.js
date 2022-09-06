import './Sidebar.css';
import React from 'react';

const PAGES = [
      {
        name: "Home",
        href: "/",
        googleIcon: "home",
      },
      {
        name: "Test",
        href: "/test",
        googleIcon: "info",
      },
      {
        name: "User Management",
        href: "/users",
        googleIcon: "person",
      },
    ];

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
    if (this.state.hideNav !== true) {
      return (
        <div className="Sidebar">
          <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet"></link>
          {
            PAGES.map((button, index) => React.createElement(
              "a", { className: "Sidebar-tab", href: button.href },
              React.createElement(
                "span", { className: "material-icons-outlined" },
                button.googleIcon),
              button.name)
            )
           }
         </div>
       );
    } else {
      return null;
    }
  }
}
export default Sidebar;
