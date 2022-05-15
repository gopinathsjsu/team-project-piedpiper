import { Button } from '@material-ui/core';
import React, { useState } from 'react';
import './bookcard.css';
import { DateRangePicker } from 'react-date-range';
import Details from '../SearchResult/Details';
import { useHistory } from 'react-router-dom'; 
import AuthService from '../User_auth';

function Bookcard({ src, title, to_date, from_date, description, cdata}) {
  const [showSearch, setShowSearch] = useState(false);
  const history = useHistory();
  let cdate = new Date()
  let tod = new Date(to_date)
  const update = () => {
    console.log(cdata)
    history.push( {pathname: '/update',
    //search: `?query=${description}`, 
    state : {detail: cdata}
    }) 
  }

  const cancel = () => {
    if (window.confirm('Do you really want to cancel this booking?'))
    {
      // They clicked Yes
      AuthService.getCancelBooking(description).then(
        () => { 
          window.location.reload(false);
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
    else
    {
        // They clicked no
        history.push('/booked')
    }
    
  }

  return (
    <div className='card'>
    <img src="https://a0.muscache.com/im/pictures/fdb46962-10c1-45fc-a228-d0b055411448.jpg?im_w=720" alt="" />
    <div className="card__info">
        <h2>Room Type : {src}</h2>
        <h4>From : {from_date}</h4>
        <h4>To : {to_date}</h4>
        <h5>Id : {description}</h5>
    </div>
    {
    
    (tod.getTime() > cdate.getTime())?
    <>
    <span>
    <Button onClick ={update} >Update</Button>
    </span>
    <Button onClick ={cancel}>Cancel</Button>
    </>
    :
    ''
    
    }
    </div>
  )
}

export default Bookcard
