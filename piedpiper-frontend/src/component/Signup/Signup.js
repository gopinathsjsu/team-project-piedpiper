import React, { useState } from 'react'
import { Grid, Paper, Avatar, Typography, TextField, Button } from '@material-ui/core'
import AddCircleOutlineOutlinedIcon from '@material-ui/icons/AddCircleOutlineOutlined';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Axios from 'axios';
import AuthService from '../User_auth';
import validator from 'validator';
import { Header } from '..';
import { useHistory } from 'react-router-dom'; 

const Signup = () => {
    const [userName, setuserNameReg] = useState("");
    const [userPassword, setuserPassword] = useState("");
    const [userGender, setGender] = useState("");
    const [userPhoneno, setPhoneNumber] = useState("");
    const [confirmPassword, setconfirmPassword] = useState("");
    const [email, setEmail] = useState("");
    const [succesful, setSuccesfull] = useState(false);
    const history = useHistory();

    const paperStyle = { padding: '30px 20px', width: 300, margin: "20px auto" }
    const headerStyle = { margin: 0 }
    const avatarStyle = { backgroundColor: '#ff7779' }
    const marginTop = { marginTop: 5 }
    
    var regularExpression = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;

    const register = (event) => {
        event.preventDefault();
        if(!validator.isEmail(email)){
            window.alert("Please enter valid Email")
            return
        }
        if(!regularExpression.test(userPassword)){
            console.log(userPassword);
            window.alert("Keep special chars in the password and length greater than 6")
            return
        }
        if(confirmPassword != userPassword){
            window.alert("Password is not matching")
            return
        }
        AuthService.register(userName, email, userPassword, userPhoneno).then(
            (x) => {
            history.push('/login')
            }
        ).catch((error) => {
            // Error
            let x = error.response.data
            if (error.response) {
                window.alert(x)
            } else if (error.request) {
                window.alert(x)
                console.log(error.response);
            } else {
                window.alert(x)
                console.log('Error', error.message);
            }
            console.log(error.response.data);
        });
    }

    return (
        <div>
            <Header/>
        <Grid>
            <Paper elevation={20} style={paperStyle}>
                <Grid align='center'>
                    <Avatar style={avatarStyle}>
                        <AddCircleOutlineOutlinedIcon />
                    </Avatar>
                    <h2 style={headerStyle}>Sign Up</h2>
                    <Typography variant='caption' gutterBottom>Please fill this form to create an account !</Typography>
                </Grid>
                <form>
                    <TextField fullWidth label='Name' onChange = {(e)=> {setuserNameReg(e.target.value)}} placeholder="Enter your name"  required/>
                    <TextField fullWidth label='Email' onChange = {(e)=> {setEmail(e.target.value)}} placeholder="Enter your email" required/>
                    <FormControl component="fieldset" style={marginTop}>
                        <FormLabel component="legend">Gender</FormLabel>
                        <RadioGroup aria-label="gender" name="gender" onChange = {(e)=> {setGender(e.target.value)}} style={{ display: 'initial' }}>
                            <FormControlLabel value="female" control={<Radio />} label="Female" />
                            <FormControlLabel value="male" control={<Radio />} label="Male" />
                        </RadioGroup>
                    </FormControl>
                    <TextField fullWidth label='Phone Number' onChange = {(e)=> {setPhoneNumber(e.target.value)}} placeholder="Enter your phone number" />
                    <TextField fullWidth label='Password' onChange = {(e)=> {setuserPassword(e.target.value)}} placeholder="Enter your password"/>
                    <TextField fullWidth label='Confirm Password' onChange = {(e)=> {setconfirmPassword(e.target.value)}} placeholder="Confirm your password"/>
                    <FormControlLabel
                        control={<Checkbox name="checkedA" />}
                        label="I accept the terms and conditions."
                    />
                    <Button type='submit' variant='contained' onClick ={register} color='primary'>Sign up</Button>
                </form>
            </Paper>
        </Grid>
        </div>
    )
}

export default Signup;