import React, { useState } from 'react';
import './SearchResult.css';
import './Details.css';
import FavoriteBorderIcon from "@material-ui/icons/FavoriteBorder";
import StarIcon from "@material-ui/icons/Star";
import { Button } from '@material-ui/core';
import { DateRangePicker } from 'react-date-range';
import RoomServiceIcon from '@mui/icons-material/RoomService';
import BedroomParentIcon from '@mui/icons-material/BedroomParent';
import AuthService from '../User_auth';
import { useHistory } from 'react-router-dom';
import { Modal } from '..';
function Details() {
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());
    const [breakfast, setCheckedBF] = React.useState(false);
    const [fit, setCheckedFit] = React.useState(false);
    const [pool, setCheckedPool] = React.useState(false);
    const [park, setCheckedParking] = React.useState(false);
    const [meals, setCheckedMeals] = React.useState(false);
    const [room, setRoomType] = useState("single_room");
    const [rooms, setRooms] = useState(1);
    const [child, setChild] = useState(1);
    const [adult,setAdult] = useState(1);
    const [price, setPrice] = useState("");
    const [res, setReservation] = useState("");
    const history = useHistory();

    

    const setCheckedOne = () => {
      setCheckedBF(!breakfast);
    };

    const setCheckedTwo = () => {
      setCheckedFit(!fit);
    };

    const setCheckedThree = () => {
      setCheckedPool(!pool);
    };

    const setCheckedFour = () => {
      setCheckedParking(!park);
    };

    const setCheckedFive = () => {
      setCheckedMeals(!meals);
    };
  
    const roomType = (event) => {
      console.log(event.target.value)
      setRoomType(event.target.value)
    }

    const roomInc = () => {
      if(rooms >= 4){
        setRooms(4)
      }
      else{
        let v1 = rooms
        v1=v1+1
        setRooms(v1)
        console.log(rooms)
      }
    }
    
    const roomDec = () => {
      if(rooms <= 1){
        setRooms(1)
      }
      else{
        let v2 = rooms
        console.log(rooms)
        setRooms(--v2)
      }
    }


    const ChildInc = () => {
      if(child >= 4){
        setChild(4)
      }
      else{
        let v1 = child
        v1=v1+1
        setChild(v1)
      }
    }
    
    const ChildDec = () => {
      if(child <= 0){
        setChild(0)
      }
      else{
        let v2 = child
        setChild(--v2)
      }
    }

    const AdultInc = () => {
      if(adult >= 4){
        setAdult(4)
      }
      else{
        let v1 = adult
        v1=v1+1
        setAdult(v1)
      }
    }
    
    const AdultDec = () => {
      if(adult <= 1){
        setAdult(1)
      }
      else{
        let v2 = adult
        setAdult(--v2)
      }
    }
    const selectionRange = {
        startDate: startDate,
        endDate: endDate,
        key: "selection",
      };
      function handleSelect(ranges) {
        setStartDate(ranges.selection.startDate);
        setEndDate(ranges.selection.endDate);
    }

    const [modal, setModal] = useState(false)
    const toggleModal = () => {
      setModal(!modal)
    };

    const book = () => {
      console.log(Date())
      var g1 = new Date();
      if(startDate.getTime() < g1.getTime()){
          window.alert("Kindly select the later dates")
          return
      }
      else if (startDate.getDate() + 7 < endDate.getDate()){
        window.alert("Kindly book only for 7 days")
        return
      }
      else if( room== "single_room" && rooms ==1 && (child > 2 || adult > 2)){
        window.alert("Max of 2 adults or children allowed")
        return
      }
      AuthService.getBookingConfirmation(room, startDate, endDate, breakfast, fit, pool, park, meals, rooms, child, adult).then(
          (x) => {  
            console.log("REVERVATIONID")
            console.log(x.reservationID)
            setPrice(x.price);
            setReservation(x.reservationID);
            toggleModal()
            console.log("What a wow!!!!")
          //window.location.reload(false);
          },
          error => {
              const resMessage =
              (error.response &&
              error.response.data &&
              error.response.data.message) ||
              error.message ||
              error.toString();
          }
      )
  }

  return (
    <div>
        <DateRangePicker ranges={[selectionRange]} onChange={handleSelect} />
        <h3>Amenities </h3>
        <input type="checkbox" checked={breakfast} onChange={setCheckedOne}/> Daily Continental Breakfast
        <br></br>
        <input type="checkbox" checked={fit} onChange={setCheckedTwo}/> Access to fitness room
        <br></br>
        <input type="checkbox" checked={pool} onChange={setCheckedThree}/> Access to Swimming Pool/Jacuzzi
        <br></br>
        <input type="checkbox" checked={park} onChange={setCheckedFour}/> Daily Parking
        <br></br>
        <input type="checkbox" checked={meals} onChange={setCheckedFive}/> All meals included (Breakfast, Lunch, Dinner)
        <br></br>
        <br></br>
        <br></br>
        <h3>Room Type </h3>
        <div onChange = {roomType}>
          <input type="radio" value="single_room" name="room" defaultChecked/> Single Room
          <br></br>
          <input type="radio" value="double_room" name="room"/> Double Room
          <br></br>
          <input type="radio" value="suite" name="room"/> Suite
          <br></br>
          <input type="radio" value="Family_lounge" name="room"/> Family Lounge
        </div>
        <br></br>
        <br></br>
        <br></br>
        <h3>Number of Rooms</h3>
        <div>
        <Button onClick  = {roomInc}>+</Button>
        {rooms}
        <Button onClick  = {roomDec}>-</Button>
        </div>
        <br></br>
        <br></br>
        <br></br>
        <h3>Number of Children</h3>
        <div>
        <Button onClick  = {ChildInc}>+</Button>
        {child}
        <Button onClick  = {ChildDec}>-</Button>
        </div>
        <br></br>
        <br></br>
        <br></br>
        <h3>Number of Adults</h3>
        <div>
        <Button onClick  = {AdultInc}>+</Button>
        {adult}
        <Button onClick  = {AdultDec}>-</Button>
        </div>
        <br></br>
        <br></br>
        <br></br>

      <Button variant='outlined' onClick={book} >Confirm</Button>
      {modal && <Modal price = { price } res = { res }/>}
    </div>
  )
}

const Dropdown = ({ label, value, options, onChange }) => {
  return (
    <label>
      <select value={value} onChange={onChange}>
        {options.map((option) => (
          <option value={option.value}>{option.label}</option>
        ))}
      </select>
    </label>
  );
};

export default Details
