import React, { useState } from 'react'
import './Modal.css';
import { useHistory } from 'react-router-dom';
import { Button } from '@material-ui/core';
import AuthService from '../User_auth';

function Modal(price, res) {
  const history = useHistory();
  const Confirm = () =>{
      history.push('/')
  }

  const Cancel = () => {
    console.log("Cancelling")
    console.log(res)
    AuthService.getCancelBooking(res.res).then(
      () => { 
       history.push('/') 
          
       //window.location.href = "/";
       // return <Redirect to ="/"/>
      }).catch((error) => {
      // Error
      if (error.response) {
          window.alert(error.response)
      } else if (error.request) {
          window.alert(error.request)
          console.log(error.request);
      } else {
          // Something happened in setting up the request that triggered an Error
          window.alert(error.message)
          console.log('Error', error.message);
      }
      console.log(error.config);
  });
  }

  let button = <h2></h2>
  if (price.price < 0) {
    button = <h2>Your will be refunded { Math.abs(price.price) } </h2>;
  } else {
    button = <h2>Amount Debited ${ Math.abs(price.price) } </h2>;
  }

  return (
    <div className="modal">
          <div className="overlay"></div>
          <div className="modal-content">
            <h2>Booking Successful</h2>
            {button}
            <Button className="btn_modal" onClick = {Confirm}>
              Book New
            </Button>
            <span>
            <Button className="right_btn" onClick = {Cancel}>
              Cancel
            </Button>
            </span>
          </div>
        </div>
  )
}

export default Modal
