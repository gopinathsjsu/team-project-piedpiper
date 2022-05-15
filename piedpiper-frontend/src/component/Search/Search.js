import React, { useState } from 'react'
import './Search.css'
import { DateRangePicker } from 'react-date-range'
import "react-date-range/dist/styles.css";
import "react-date-range/dist/theme/default.css";
import { Button, TextField } from '@material-ui/core'
import PeopleIcon from "@material-ui/icons/People";
import { useHistory } from 'react-router-dom'; 
import Axios from 'axios'
import AuthService from '../User_auth';

function Search() {
    const history = useHistory();
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());
    const [location, setLocation ] = useState("");
    const handleLocChange = (event) => {
      console.log(event.target.value)
      setLocation(event.target.value);
    };
    const selectionRange = {
        startDate: startDate,
        endDate: endDate,
        key: "selection",
      };
      function handleSelect(ranges) {
        setStartDate(ranges.selection.startDate);
        setEndDate(ranges.selection.endDate);
    }
    const details = () => {

        console.log(location)
        // let hotel_details = window.localStorage.getItem("employeeAdd")
        // hotel_details = JSON.parse(hotel_details)
        // if(location===hotel_details.hotel_location)
        // {
        //   alert("Location present")
        //   history.push('/search')
        // }
        // else{
        //   alert("No Hotels in this Location")
        // }
        AuthService.getHotelLocation(location).then(
          (res) => {
            console.log("****",res)
            window.localStorage.setItem("employeeAdd", JSON.stringify(res.data[0]))
          let hotel_details = window.localStorage.getItem("employeeAdd")
          hotel_details = JSON.parse(hotel_details)
          console.log("from ls", hotel_details);



            if(!AuthService.validUser()){
                window.alert("Please Login")
              }
              else{
            history.push('/search')
              }
           }).catch((error) => {
           if (error.response) {
               window.alert("No Hotels in this location")
           } else if (error.request) {
               window.alert("No Hotels in this location")
               console.log(error.request);
           } else {
               window.alert("No Hotels in this location")
               console.log('Error', error.message);
           }
           console.log(error.config);
       });   
    }
  return (
    <div className='search'>
      <TextField
        className = "dropdown"
        id="first-name"
        label=""
        placeholder = "Enter Location"
        value= {location}
        onChange= {handleLocChange}
        />
      <Button onClick={details}>Search Mariotts</Button>
    </div>
  )
}

const Dropdown = ({ label, value, options, onChange }) => {
  return (
    <label className = "label">
      {label}
      <select value={value} className = "dropdown" onChange={onChange}>
        {options.map((option) => (
          <option value={option.value}>{option.label}</option>
        ))}
      </select>
    </label>
  );
};

export default Search
