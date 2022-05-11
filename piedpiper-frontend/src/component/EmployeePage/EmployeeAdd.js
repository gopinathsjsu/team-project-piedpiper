import React, {useEffect, useState} from 'react';
import AuthService from '../User_auth';
import { useHistory } from 'react-router-dom'; 
import { DateRangePicker } from 'react-date-range';
import RoomServiceIcon from '@mui/icons-material/RoomService';
import BedroomParentIcon from '@mui/icons-material/BedroomParent';
import { useLocation } from 'react-router-dom';
import queryString from 'query-string';
import { Header } from '..';
import { Grid,Paper, Avatar, TextField, Button, Typography,Link} from '@material-ui/core';
import './EmployeeAdd.css';

function EmployeeAdd() {
    const [location, setLocation] = useState("")
    const [name, setHotelName] = useState("")
    const [id, setIHoteld] = useState("")
    const history = useHistory()
    const setDetails = (loc) => {
        setLocation(loc)
    }
    const setName = (loc) => {
        setHotelName(loc)
    }
    const setId = (loc) => {
        setIHoteld(loc)
    }
    const addUpdate = () => {
        AuthService.postemployeeHotel(location, name, id).then(
            () => { 
             history.push('/employee') 
                
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
        console.log(location,name,id);
//         let data = {
//             hotel_location : location,
//             hotel_name : name,
//             hotel_id : id
//         }
        
// window.localStorage.setItem("employeeAdd", JSON.stringify(data))

// let hotel_details = window.localStorage.getItem("employeeAdd")
// hotel_details = JSON.parse(hotel_details)
// console.log(hotel_details);
// console.log(hotel_details.hotel_location);
// console.log(hotel_details.hotel_name)
// console.log(hotel_details.hotel_id)
// window.alert("New Hotel is added");
// window.location.href = "/";

    }
  return (
    <div>
      <Header/>
        <div className="details">
        <h2>Please Fill the new Details</h2>
        <br></br>
        <br></br>
        <TextField
        id="first-name"
        placeholder = "New Location"
        margin="normal"
        onChange = {(e)=> {setDetails(e.target.value)}} required
        />
        <br></br>
        <br></br>
        <TextField
        id="first-name"
        placeholder = "New hotel name"
        margin="normal"
        onChange = {(e)=> {setName(e.target.value)}} required
        />
        <br></br>
        <br></br>
        <TextField
        id="first-name"
        placeholder = "New id"
        margin="normal"
        onChange = {(e)=> {setId(e.target.value)}} required
        />
        <Button type='submit' color='primary' variant="contained" onClick ={addUpdate} >Confirm</Button>
        </div>
    </div>
  )
}

export default EmployeeAdd
