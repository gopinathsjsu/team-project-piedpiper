import React from 'react';
import { useState } from 'react';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import { Login, Signup } from '../component';

function SignInOutContainer() {
  const [value, setValue] = useState(0)
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  function TabPanel(props) {
    const { children, value, index, ...other } = props;
  
    return (
      <div
        role="tabpanel"
        hidden={value !== index}
        id={`simple-tabpanel-${index}`}
        aria-labelledby={`simple-tab-${index}`}
        {...other}
      >
        {value === index && (
          <Box sx={{ p: 3 }}>
            <Typography>{children}</Typography>
          </Box>
        )}
      </div>
    );
  }
  return (
    <div>
  <Box sx={{ backgroundSize: 'cover',backgroundRepeat: 'no-repeat',position:'relative' ,width: '100%', backgroundImage:`url(${"https://www.marriott.com/content/dam/marriott-leisure/destinations/hero/north-america/usa/nv/las-vegas/lasVegas-hero-destination.jpeg.transform/mcom-leisure-transform-2880/image.jpg"})`  }}>
    <Tabs value={value} onChange={handleChange} aria-label="disabled tabs example" >
      {/* <Tab label="Login" />
      <Tab label="Signup" /> */}
    </Tabs>
    <TabPanel value={value} index={0}>
      <Login />
      </TabPanel>
      <TabPanel value={value} index={1}>
        <Signup />
      </TabPanel>
    </Box>
    </div>
  
  )
}

export default SignInOutContainer
