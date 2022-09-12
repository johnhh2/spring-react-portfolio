import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import './index.css';

import Portfolio from './Portfolio';
import Navbar from './Navbar';
import Info from './Info';
import UserCreate from './UserCreate';
import UserView from './UserView';
import UserSingleView from './UserSingleView';


import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Navbar />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Portfolio/>}/>
          <Route path="/info" element={<Info/>}/>
          <Route path="/users/create" element={<UserCreate/>}/>
          <Route path="/users/view" element={<UserView/>}/>
          <Route path="/users/view/:id" element={<UserSingleView/>}/>
        </Routes>
      </BrowserRouter>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
