import './App.css';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { Header, Footer, Login, SearchPage, Home, Signup, AuthService, Balance, Employee, Bookingdetails, Update , EmployeeLogin, EmployeeSignup, EmployeeAdd} from './component';
import SignInOutContainer from './containers/SignInOutContainer';
import React, { useEffect, useState } from 'react'

function App() {
  return (
    <div className="app">
        <Router>
        <Switch> 
            <Route path="/search">
              <SearchPage />
            </Route>
          <Route path="/login">
            
            <SignInOutContainer />
          </Route>
          <Route path="/signin">
            <Login />
          </Route>
          <Route path="/signup">
            <Signup />
          </Route>
          <Route path="/balance">
              <Balance />
          </Route>
          <Route path="/employee">
              <Employee />
          </Route>
          <Route path="/update">
              <Update />
          </Route>
          <Route path="/booked">
              <Bookingdetails />
          </Route>
          <Route path="/employeelogin">
              <EmployeeLogin />
          </Route>
          <Route path="/employeesignup">
              <EmployeeSignup />
          </Route>
          <Route path="/employeeadd">
              <EmployeeAdd />
          </Route>
          <Route path="/">
            <Home />
          </Route>
        </Switch>
        <Footer />
      </ Router>
    </div>
  );
}

export default App;
