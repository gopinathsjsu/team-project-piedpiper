import React , { useEffect, useState} from 'react';
import './Header.css'
import SearchIcon from "@material-ui/icons/Search";
import LanguageIcon from "@material-ui/icons/Language";
import ExpandMoreIcon from "@material-ui/icons/ExpandMore";
import { Avatar } from '@material-ui/core';
import { Link } from "react-router-dom";
import { Button } from '@material-ui/core';
import logo from "../../Asset/logo_1.png";
import { AuthService } from '..';
import ArrowDropDownIcon from '@mui/icons-material/ArrowDropDown';
import { useHistory } from 'react-router-dom'; 
import './Account.css'

function Account() {
    const [isActive, setIsActive ] = useState(false);
    const history = useHistory();
    const logout_page = () => {
      AuthService.logout().then(
          () => { 
            console.log("heelo")
            history.push('/') 
            window.location.reload(false);
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

  const bal = () => {
    history.push('/balance') 
  }

  const booking = () => {
    history.push('/booked') 
  }
//   const register = () => {
//     setLoading(true)
//     AuthService.login(userName, userPassword).then(
//         () => { history.push('/') 
//         },
//         error => {
//             const resMessage =
//             (error.response &&
//             error.response.data &&
//             error.response.data.message) ||
//             error.message ||
//             error.toString();
//             setLoading(false)
//         }
//     )
// }
  return (
    <div className = "dropdown_i" >
      <div className = "dropdown_btn" onClick = {(e) => setIsActive(!isActive)}>
        Account
        <ArrowDropDownIcon/>
      </div>
      {isActive && (
        <div className = "dropdown_content">
          {localStorage.getItem("employee") == null ?
          <> 
          <div className = "dropdown_item" onClick = {() => booking()}>
              Booking
          </div>
          <div className = "dropdown_item" onClick = {() => bal()}>
              Rewards
          </div>
          </>:
          ''
          }
          <div className = "dropdown_item" onClick = {() => logout_page()}>
              Logout
          </div>
        </div>
      )}
    </div>
  )
}


export default Account
