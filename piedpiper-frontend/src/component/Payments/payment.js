import React from 'react';
import './payment.css';
function Payment() 
{
    return (
      <div>
         <div className="py-3 bg-warning">
             <div className="container">
                 {/* <h6>Home/Checkout</h6> */}
             </div>
         </div>
         <div>
             <div className='your-details'>
                 <h2 style={{marginLeft:"43px", color: "coral"}}> Your Details</h2> <br></br>
                 <div className='name-data'>
                     <div>
                         <label> First Name </label> <br></br>
                         <input type="text" name="firstname" className="form-control"/>
                     </div>
                     <div>
                         <label style={{padding:"60px" }}> Last Name </label> <br></br>
                         <input style={{marginLeft:"60px"}} type="text" name="firstname" className="form-control"/>
                     </div>
                 </div> <br></br>
                 <div className='contact-details'>
                    <label> Mobile Number </label> <br></br>
                    <input type="text" name="phone" className="form-control"/> <br></br> <br></br>
                    <label> Email ID </label> <br></br>
                    <input type="text" name="email" className="form-control"/>
                 </div>
             </div> <br></br>
             <div className='payment-details'>
                <h2 style={{marginLeft:"43px", color: "coral"}}> Payment Details</h2> <br></br> 
                <div className='card-details'>
                    <h5> Your Card Details </h5> <br></br>
                    <input size={40} type="text" name="card number" placeholder="Card Number"/> <br></br>
                    <input size={40} type="text" name="card holder's name" placeholder="Card Holder's Name"/><br></br>
                    <input size={18} type="text" name="expiry date" placeholder="MM/YY"/>
                    <input size={18} type="text" name="CVV" placeholder="CVV"/> 
                </div>
             </div>
             <div>
                <button className="confirm-btn"> Confirm Booking </button>
             </div>
         </div>
     </div>
     )
  }
  
  export default Payment;