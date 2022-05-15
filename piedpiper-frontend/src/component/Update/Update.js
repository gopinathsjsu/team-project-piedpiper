import React, {useEffect, useState} from 'react';
import AuthService from '../User_auth';
import { useHistory } from 'react-router-dom'; 
import './Update.css';
import { Button } from '@material-ui/core';
import { DateRangePicker } from 'react-date-range';
import RoomServiceIcon from '@mui/icons-material/RoomService';
import BedroomParentIcon from '@mui/icons-material/BedroomParent';
import { useLocation } from 'react-router-dom';
import queryString from 'query-string';
import { Header } from '..';
import { Modal } from '..';
import { red } from '@material-ui/core/colors';

function Update() {


    // daily_continental_breakfast,
    //     access_to_fitness_room,
    //     access_to_swimming_Pool_Jacuzzi,
    //     daily_parking,
    //     all_meals_included

    const[curr, setCurr] = useState([]);
    const[breakfast_book, setBreak] = useState("No");
    const[fit_book, setFit] = useState("No");
    const[swim_book, setSwim] = useState("No");
    const[park_book, setPark] = useState("No");
    const[meal_book, setMeal] = useState("No");
    const [price, setPrice] = useState("");
    const [res, setReservation] = useState("");
    const [query,setQuery] = useState("");
    const [modal, setModal] = useState(false)

    const [state, setState] = useState([
      {
        startDate: new Date(),
        endDate: null,
        key: "selection"
      }
    ]);
    
    //const { search } = useLocation()
    //const {query} = queryString.parse(search)
    const location = useLocation() 

    // let x = {
    //     "fromDate" : "2022-04-30",
    //     "toDate" : "2022-05-07",
    //     "amenities": {
    //         "daily_continental_breakfast":true,
    //         "access_to_fitness_room":false,
    //         "access_to_swimming_Pool_Jacuzzi":true,
    //         "daily_parking":true,
    //         "all_meals_included":false
    //       }
    // }

    useEffect(() => {
        console.log(location.state.detail)
        setQuery(location.state.detail.reservationID)
        let x = location.state.detail
        setCurr(x)
        if(x.amenities.daily_continental_breakfast){
            setBreak("Yes")
        }
        if(x.amenities.access_to_fitness_room){
            setFit("Yes")
        }
        if(x.amenities.access_to_swimming_Pool_Jacuzzi){
            setSwim("Yes")
        }
        if(x.amenities.daily_parking){
          setPark("Yes")
        }
        if(x.amenities.all_meals_included){
          setMeal("Yes")
        }
        setRooms(x.numberOfRooms)
        setChild(x.number_of_children)
        setAdult(x.number_of_adults)
        // AuthService.getBookingDetails().then(
        //         (x) => {
        //             setCurr(x)
        //              
        //         },
        //         error => {
        //             const resMessage =
        //             (error.response &&
        //             error.response.data &&
        //             error.response.data.message) ||
        //             error.message ||
        //             error.toString();
        //         }
        //     )
     }, [])


//location.state.detail.fromDate
    const [startDate, setStartDate] = useState(new Date(location.state.detail.fromDate));
    const [endDate, setEndDate] = useState(new Date(location.state.detail.toDate));
    const [breakfast, setCheckedBF] = React.useState(location.state.detail.amenities.daily_continental_breakfast);
    const [fit, setCheckedFit] = React.useState(location.state.detail.amenities.access_to_fitness_room);
    const [pool, setCheckedPool] = React.useState(location.state.detail.amenities.access_to_swimming_Pool_Jacuzzi);
    const [park, setCheckedParking] = React.useState(location.state.detail.amenities.daily_parking);
    const [meals, setCheckedMeals] = React.useState(location.state.detail.amenities.all_meals_included);
    const [room, setRoomType] = React.useState(location.state.detail.roomType);
    const [rooms, setRooms] = useState(location.state.detail.numberOfRooms);
    const [child, setChild] = useState(location.state.detail.number_of_children);
    const [adult,setAdult] = useState(location.state.detail.number_of_adults);
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

    const toggleModal = () => {
      setModal(!modal)
    };

    const UpdateBook = () => {
        var g1 = new Date();
        if(startDate.getTime() < g1.getTime()){
            window.alert("Kindly select the later dates")
            return
        }
        else if (startDate.getDate() + 7 < endDate.getDate()){
          window.alert("Kindly book only for 7 days")
          return
        }
        else if( room== 'single room' && rooms ==1 && (child > 2 || adult > 2)){
          window.alert("Max of 2 adults or children allowed")
          return
        }
        AuthService.getUserUpdate(room, startDate, endDate, breakfast, fit, pool, park, meals, rooms, child, adult, query).then(
            (x) => { 
              console.log("Wasssssssssup")
              console.log(x.price)
              setPrice(x.price);
              // x.reservationID
              setReservation(x.reservationID)
              toggleModal()
              
              //history.push('/booked') 
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
      <Header/>
        <div className = "card_update">
        <h2>Current Booking</h2>
        <br></br>
        <div className = "cardupdate__info">
        <div>
        <h4>From</h4>
        {curr.fromDate}
        </div>
        <div>
        <h4>To</h4>
        {curr.toDate}
        </div>
        <div>
        <h4>Breakfast</h4>
        {breakfast_book}
        </div>
        <div>
        <h4>Fitness</h4>
        {fit_book}
        </div>
        <div>
        <h4>Pool</h4>
        {swim_book}
        </div>
        <div>
        <h4>Parking</h4>
        {park_book}
        </div>
        <div>
        <h4>All Meals</h4>
        {meal_book}
        </div>
        </div>
        </div>
        <div className = "card_update">
        <h2>Please Update your booking</h2>
        <br></br>
        <br></br>
        <DateRangePicker ranges={[selectionRange]} onChange={handleSelect} showpreview={curr.fromDate, curr.toDate, red}/>
        <h3>Amenities <RoomServiceIcon /></h3>
        <input type="checkbox" defaultChecked={location.state.detail.amenities.daily_continental_breakfast} onChange={setCheckedOne}/> Daily Continental Breakfast
        <br></br>
        <input type="checkbox" defaultChecked={location.state.detail.amenities.access_to_fitness_room} onChange={setCheckedTwo}/> Access to fitness room
        <br></br>
        <input type="checkbox" defaultChecked={location.state.detail.amenities.access_to_swimming_Pool_Jacuzzi} onChange={setCheckedThree}/> Access to Swimming Pool/Jacuzzi
        <br></br>
        <input type="checkbox" defaultChecked={location.state.detail.amenities.daily_parking} onChange={setCheckedFour}/> Daily Parking
        <br></br>
        <input type="checkbox" defaultChecked={location.state.detail.amenities.all_meals_included} onChange={setCheckedFive}/> All meals included (Breakfast, Lunch, Dinner)
        <br></br>
        <br></br>
        <br></br>
        <h3>Room Type <BedroomParentIcon /></h3>
        <div onChange = {roomType}>
          <input type="radio" value="single_room" name="room" defaultChecked = {location.state.detail.roomType == "single_room"}/> Single Room
          <br></br>
          <input type="radio" value="double_room" name="room" defaultChecked = {location.state.detail.roomType == "double_room"}/> Double Room
          <br></br>
          <input type="radio" value="suite" name="room" defaultChecked = {location.state.detail.roomType == "suite"}/> Suite
          <br></br> 
          <input type="radio" value="Family_lounge" name="room" defaultChecked = {location.state.detail.roomType == "Family_lounge"}/> Family Lounge
        </div>
        <br></br>
        <br></br>
        <br></br>
        <h3>Number of Room</h3>
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
        <span>
        <Button onClick = {UpdateBook}>Confirm</Button>
        {modal && <Modal price = { price } res = { res }/>}
        </span>
        </div>
    </div>
  )
}

export default Update
