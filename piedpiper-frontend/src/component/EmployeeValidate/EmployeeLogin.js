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

const EmployeeLogin=()=>{
    const [userName, setuserNameReg] = useState("");
    const [userPassword, setuserPassword] = useState("");
    const [user, setUser] = useState(null)
    const [loading, setLoading]  = useState(false)
    const history = useHistory();
    const paperStyle={padding :20,height:'70vh',width:280, margin:"20px auto"}
    const avatarStyle={backgroundColor:'#ff7779'}
    const btnstyle={margin:'8px 0'}

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
        AuthService.employeelogin(userName, userPassword).then(
            () => { 
             history.push('/employeeAdd') 
                
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

    return(
        <div>
            <Header />
        <Grid>
            <Paper elevation={10} style={paperStyle}>
                <Grid align='center'>
                     <Avatar style={avatarStyle}><LockOutlinedIcon/></Avatar>
                    <h2>Employee Sign In</h2>
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
                <Typography > Do you have an account ?
                     <Link href="./employeesignup" >
                         Employee Sign Up
                     </Link>
                </Typography>
            </Paper>
        </Grid>
        </div>
    )
}

export default EmployeeLogin