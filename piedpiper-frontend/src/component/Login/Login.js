import React, { useState } from 'react'
import { Grid,Paper, Avatar, TextField, Button, Typography,Link } from '@material-ui/core'
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Axios from 'axios';
import AuthService from '../User_auth';
import { useHistory } from 'react-router-dom'; 
import validator from 'validator';
import { Header } from '..';
import './Login.css'
const Login=()=>{
    const [userName, setuserNameReg] = useState("");
    const [userPassword, setuserPassword] = useState("");
    const [user, setUser] = useState(null)
    const [loading, setLoading]  = useState(false)
    const history = useHistory();
    const paperStyle={padding :20,height:'70vh',width:280, margin:"20px auto"}
    const avatarStyle={backgroundColor:'#ff7779'}
    const btnstyle={margin:'8px 0'}


    var regularExpression = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;

    const register = () => {
        setLoading(true)
        if( userName == "" || userPassword == ""){
            window.alert("Username or Password Missing")
            return
        }
        if(!validator.isEmail(userName)){
            window.alert("Please enter valid Email")
            return
        }
        AuthService.login(userName, userPassword).then(
            () => { 
             history.push('/') 
                
             //window.location.href = "/";
             // return <Redirect to ="/"/>
            }).catch((error) => {
            // Error
            if (error.response) {
                window.alert("Enter correct email/password")
            } else if (error.request) {
                window.alert("Enter correct email/password")
                console.log(error.request);
            } else {
                // Something happened in setting up the request that triggered an Error
                window.alert("Enter correct email/password")
                console.log('Error', error.message);
            }
            console.log(error.response);
        });
    }

    return(
        <div id='login-page'>
        <Grid>
            <Paper elevation={10} style={paperStyle}>
                <Grid align='center'>
                     <Avatar style={avatarStyle}><LockOutlinedIcon/></Avatar>
                    <h2>Login</h2>
                </Grid>
                <TextField label='Username' placeholder='Enter username' onChange = {(e)=> {setuserNameReg(e.target.value)}} fullWidth required/>
                <TextField label='Password' placeholder='Enter password' onChange = {(e)=> {setuserPassword(e.target.value)}} type='password' fullWidth required/>
                <FormControlLabel
                    control={
                    <Checkbox
                        name="checkedB"
                        color="primary"
                    />
                    }
                    label="Remember me"
                 />
                <Button type='submit' color='primary' variant="contained" onClick ={register} style={btnstyle} fullWidth>Sign in</Button>
                <Typography > Already member? 
                     <Link href="./Signup" >
                         Sign Up
                     </Link>
                </Typography>
                <Typography > Are you an employee?
                     <Link href="./employeelogin" >
                         Click here
                     </Link>
                </Typography>
            </Paper>
        </Grid>
        </div>
    )
}

export default Login