import React, {useState, useEffect} from "react";
import {Route, BrowserRouter as Router, Routes, Navigate} from 'react-router-dom';
import {AppShell, Loader, Header} from "@mantine/core";
import logo from './logo.svg';
import './App.css';
import {RegisterForm} from "./components/RegisterForm";
import {Login} from "./pages/Login";
import {Home} from "./pages/Home";
import {LoginForm} from "./components/LoginForm"

function App() {

  return (

      <div className={"App"}>

          <Router>
              <Routes>
                  <Route path={'/'} element={<Home/>}/>
                  <Route path={'*'} element={<Navigate to={'/'}/>}/> //404 page goes here
                  <Route path={'/login'} element={<Login/>}/>
              </Routes>
          </Router>

      </div>
  );
}

export default App;
