import React, { useState }from 'react'
import './Banner.css'
import { Button } from '@material-ui/core'
import Search from  '../Search/Search.js'
import { useHistory } from 'react-router-dom'; 
import { Header } from '../Header/Header';

function Banner() {
    const history = useHistory();
    const [showSearch, setShowSearch] = useState(false);

  return (
    <div className = "banner">
        <div className='banner__search'>
            {showSearch && <Search />}
                <Button onClick={() => setShowSearch(!showSearch)} className='banner__searchButton' variant='outlined'>
                {showSearch ? "Back" : "Destination"}
                </Button>
            </div>
        <div className='banner__info'>
                <h1>Welcome to Mariott</h1>
                <h5>
                    Honeymoon destination
                </h5>
                <Button onClick={() => history.push('/search')} variant='outlined'>Book Now</Button>
            </div>
    </div>
  )
}

export default Banner
