import React, { useEffect, useState } from 'react';
import './bookingdetails.css';
import Bookcard from './Bookcard';
import { Header } from '..';
import AuthService from '../User_auth';


function Bookingdetails() {

    const [userData, setData] = useState([])
    
    let cars = [
        {
          "src": "https://a0.muscache.com/im/pictures/fdb46962-10c1-45fc-a228-d0b055411448.jpg?im_w=720",
          "location": "Entire homes",
          "desc": "Comfortable private places, with room for friends or family.",
        },
        {
            "src": "https://a0.muscache.com/im/pictures/fdb46962-10c1-45fc-a228-d0b055411448.jpg?im_w=720",
            "location": "Entire homes",
            "desc": "Comfortable private places, with room for friends or family.",
        },
        {
            "src": "https://a0.muscache.com/im/pictures/fdb46962-10c1-45fc-a228-d0b055411448.jpg?im_w=720",
            "location": "Entire homes",
            "desc": "Comfortable private places, with room for friends or family.",
          },
          {
              "src": "https://a0.muscache.com/im/pictures/fdb46962-10c1-45fc-a228-d0b055411448.jpg?im_w=720",
              "location": "Entire homes",
              "desc": "Comfortable private places, with room for friends or family.",
          },
          {
            "src": "https://a0.muscache.com/im/pictures/fdb46962-10c1-45fc-a228-d0b055411448.jpg?im_w=720",
            "location": "Entire homes",
            "desc": "Comfortable private places, with room for friends or family.",
          },
          {
              "src": "https://a0.muscache.com/im/pictures/fdb46962-10c1-45fc-a228-d0b055411448.jpg?im_w=720",
              "location": "Entire homes",
              "desc": "Comfortable private places, with room for friends or family.",
          },
          {
            "src": "https://a0.muscache.com/im/pictures/fdb46962-10c1-45fc-a228-d0b055411448.jpg?im_w=720",
            "location": "Entire homes",
            "desc": "Comfortable private places, with room for friends or family.",
        },
        {
          "src": "https://a0.muscache.com/im/pictures/fdb46962-10c1-45fc-a228-d0b055411448.jpg?im_w=720",
          "location": "Entire homes",
          "desc": "Comfortable private places, with room for friends or family.",
        },
        {
            "src": "https://a0.muscache.com/im/pictures/fdb46962-10c1-45fc-a228-d0b055411448.jpg?im_w=720",
            "location": "Entire homes",
            "desc": "Comfortable private places, with room for friends or family.",
        }
     ]

     let room = "Single Room"
     useEffect(() => {
         //setData(cars)
         console.log("Updated")
         AuthService.getBookingDetails().then(
             (x) => {
                  console.log(x.data)
                  if(x.data.roomType === "Single_Room"){
                    room = "Single Room"
                  }
                  else if(x.data.roomType === "Family_Lounge"){
                    room = "Family Lounge"
                  }
                  else if(x.data.roomType === "double_room"){
                    room = "Double Room"
                  }
                  else{
                    room = "Suite"
                  }
                setData(x.data)
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
      }, [])

    const users=userData.map((dataU)=>{
        return (
          <Bookcard
                 src={dataU.roomType}
                 from_date ={dataU.fromDate}
                 to_date = {dataU.toDate}
                 description={dataU.reservationID}
                //  breakfast = {dataU.amenities.daily_continental_breakfast}
                //  fitness = {dataU.amenities.access_to_fitness_room} 
                //  pool = {dataU.amenities.access_to_swimming_Pool_Jacuzzi} 
                //  parking = {dataU.amenities.daily_parking} 
                //  meals = {dataU.amenities.all_meals_included}
                //  nor = {dataU.numberOfRooms}
                //  noa = {dataU.number_of_adults}
                //  noc = {dataU.number_of_children}
                cdata = {dataU}
            />
        )
        
    })

    // const balance = () =>{   
    //     console.log("Creating array") 
    //     itemList.map((cars,id)=>{
    //         console.log(cars.color)
    //         return <Bookcard
    //             src="https://a0.muscache.com/im/pictures/fdb46962-10c1-45fc-a228-d0b055411448.jpg?im_w=720"
    //             title="Entire homes"
    //             description="Comfortable private places, with room for friends or family."
    //         /> 
    //       })
    // }

  return (
    <div>
      <Header />
    <div className='book__section'>
         {users}
    </div>
    </div>
  )
}

export default Bookingdetails
