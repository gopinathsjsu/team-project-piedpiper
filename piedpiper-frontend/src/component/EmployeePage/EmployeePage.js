import Axios from "axios";
import React, { useEffect, useState } from "react";
import { Grid,Paper, Avatar, TextField, Button, Typography,Link } from '@material-ui/core'
import { Header } from "..";
import { useHistory } from 'react-router-dom'; 
import './EmployeePage.css';
import AuthService from '../User_auth';

function Employee() {
  const [bookingHistory, setBookingHistory] = useState([]);
  // const [purchasedProducts, setPurchasedProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [currentPage, setCurrentPage] = useState(1);
  const [itemsPerPage, setItemsPerPage] = useState(5);
  const history = useHistory();

    useEffect(() => {
      //getBookingEmployee();
      AuthService.getBookingEmployee().then(
        (x) => {
          console.log(x.data)
          setBookingHistory(x.data)
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
    }, []);

    const getBookingHistory = () => {
        const history = [
            {
                id: "1",
                customerName: "John",
                roomNumber: "104",
                checkIn: "4th May",
                expectedCheckout: "7th May"
            },  {
                id: "2",
                customerName: "Claire",
                roomNumber: "202",
                checkIn: "6th May ",
                expectedCheckout: "9th May"
            }
        ]
        setBookingHistory(history);
    }

    

    const deleteBooking = (id) => {
      console.log("Cancelling")
      console.log(id)
      AuthService.getCancelBooking(id, localStorage.getItem('user')).then(
        () => { 
          console.log("Hello")
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



    const users=bookingHistory.map((pro)=>{
      return (
        <div>
        <div style={{width:"50%", marginLeft:"20%", marginBottom:"50px"}}  className="home_cards col-md-4 mb-4">
          <div className="home_card card">
            <div
              className="purchase_item_header"
              style={{backgroundColor:"#ff7779", height:"40px", padding:"10px", marginBottom:"10px"}}
            >
              <h4 style={{ width: "100%", color: "white"}} className="purchase_item_price">
                Room Type.: {pro.roomType}
              </h4><br></br>
            </div>

            <hr style={{ marginTop: "-2px" }}></hr>

            <div className="item">

              <div style={{ marginLeft: "10px" }} className="item-details">
                
                <h5 style={{marginTop:"5px" }}> Check-in Date: </h5>
                <p className="card-title">  {pro.fromDate} </p>
                <h5 style={{marginTop:"5px" }}> Expected Checkout Date:</h5>
                <p className="card-title"> {pro.toDate} </p>
                <h5 style={{marginTop:"5px" }}> Number of Adults:</h5>
                <p className="card-title"> {pro.number_of_adults ? pro.number_of_adults: 0 } </p>
                <h5 style={{marginTop:"5px" }}> Number of Childen:</h5>
                <p className="card-title"> {pro.number_of_children ? pro.number_of_children: 0} </p>
              </div>
            </div>
          </div>
        </div>
        </div>
      )
      
    })


    const newHotel = () => {
      history.push('/employeeadd') 
    }


  let renderPurchases = null;
  if (bookingHistory.length === 0) {
    renderPurchases = () => {
      return <div>No bookings till now...</div>;
    };
  } else {
      return (
        <div>
        <Header />
        <div className = "employeepage">
          <h2>These are the current bookings</h2>
          <div>
            {users}
          </div>
          <br></br>
          <br></br>
          <br></br>
          <br></br>
          <br></br>
          <br></br>
          <br></br>
          <Button type='submit' variant='contained' onClick ={newHotel} color='#ff7779'>New Hotel</Button>
        </div>
        </div>
      );
  }
}

export default Employee;