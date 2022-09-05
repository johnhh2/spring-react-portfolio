import './Sidebar.css';
import React from 'react';

import config from './config.json';

const serverAddress = config.SERVER_ADDR;

class Sidebar extends React.Component {

  render() {
    return (
      <div className="Sidebar">
        <a className="Sidebar-tab">Home</a>
      </div>
    );
  }
}
export default Sidebar;
