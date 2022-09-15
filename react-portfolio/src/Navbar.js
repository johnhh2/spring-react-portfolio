import './Navbar.css';
import React from 'react';

import config from './config.json';

const serverAddress = config.SERVER_ADDR;


export default class Navbar extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      hideSidebar: false,
      authenticated: false,
      pages: [],
    };
  }

  componentDidMount() {
    window.addEventListener("resize", this.resize.bind(this));
    this.resize();

    const requestOptions = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': localStorage.getItem("AuthToken"),
      },
    };
    const id = localStorage.getItem("AuthID");
    fetch(`${serverAddress}/api/user/get?id=${id}`, requestOptions)
      .then(async response => {
        const data = await response.json();
        console.log(data);
        if (data.length === 0) {
          localStorage.removeItem("AuthToken");
        } else {
          this.setState({authenticated: true});
        }
      });

    this.setState({pages: this.getPages()})

    fetch(`${serverAddress}/api/hostname/get`, requestOptions)
      .then(async response => {
        const data = await response.json();
        this.setState({
          realname: data.realname,
          categories: data.categories,
        }, () => {
          if (this.state.categories != null) {
            let newPages = this.state.categories.map(category =>
              ({
                name: category.name,
                href: "/" + category.slug,
                googleIcon: category.googleIcon,
              }));

            this.setState({
              pages: this.getPages(newPages),
            }, () => {
              this.render();
            });
          }
        });
      })
      .catch(error => {
        this.setState({ errorMessage: error.toString() });
        console.error('Error', error);
      });
  }

  getAuthPages() {
    if (this.state.authenticated) {
      return [
        {
          name: "Settings",
          href: "/account/edit",
          googleIcon: "settings",
        },
        {
          name: "Logout",
          href: "/users/logout",
          googleIcon: "logout",
        },
      ];
    } else {
      return [
        {
          name: "Login",
          href: "/users/login",
          googleIcon: "login",
        },
      ];
    }
  }

  getPages(insertPages) {
    if (insertPages == null) {
      insertPages = [];
    }
    return [
      {
        name: "Overview",
        href: "/",
        googleIcon: "home",
      },
    ].concat(insertPages).concat([
      {
        name: "About",
        href: "/info",
        googleIcon: "info",
      },
      {
        name: "User Management",
        href: "/users/create",
        googleIcon: "person",
      },
    ]).concat(this.getAuthPages());
  }

  resize() {
    let currentHideNav = (window.innerWidth < 760);
    if (currentHideNav !== this.state.hideNav) {
      this.setState({hideSidebar: currentHideNav});
    }
  }

  componentWillUnmount() {
    window.removeEventListener("resize", this.resize.bind(this));
  }

  render() {
    let icons = <link key='link' href="https://fonts.googleapis.com/icon?family=Material+Icons+Outlined" rel="stylesheet"></link>;

    if (this.state.hideSidebar !== true) {
      return [icons, (
        <div key='div' className="Sidebar">
          {
            this.state.pages.map((button, index) => React.createElement(
              "a", {key: `a${index}`,
                    className: "Sidebar-tab",
                    href: button.href},
              React.createElement(
                "span", {key: `span${index}`,
                         className: "material-icons-outlined"},
                button.googleIcon),
              button.name)
            )
          }
        </div>
      )
    ];
    } else {
      return null;
    }
  }
}
