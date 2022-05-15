import React , { useEffect, useState } from 'react';
import './Header.css'
import { Link } from "react-router-dom";
import { Button } from '@material-ui/core';
import logo from "../../Asset/logo_1.png";
import { AuthService } from '..';
import Account from './Account';

function Header() {
  const [valid, setvalidState] = useState("false");
  const [rerender, setRerender] = useState(false);
  let button;
  useEffect(() => {
     let x = AuthService.validUser()
     console.log("here ",x)
      if(!x){
         setvalidState(false)
      }
      else{
        setvalidState(true)
      } 
  }, [valid])

  // const register = () => {
  //   setLoading(true)
  //   AuthService.login(userName, userPassword).then(
  //       () => { history.push('/') 
  //       },
  //       error => {
  //           const resMessage =
  //           (error.response &&
  //           error.response.data &&
  //           error.response.data.message) ||
  //           error.message ||
  //           error.toString();
  //           setLoading(false)
  //       }
  //   )
  // }

  if (valid) {
    button = 
      <Account />
  } else {
    button = 
    <Link to='./Login' className="header__right" style={{ backgroundColor: 'black' }}>
    <Button variant='outlined'>Login</Button>
    </Link> 
  }
  return (
    <div class='header'>
        <Link to='/'>
        <img
            className='header__icon'
            src={logo}
            alt=""
        />
        </Link>
        { button }     
    </div>
  )
}

export default Header
