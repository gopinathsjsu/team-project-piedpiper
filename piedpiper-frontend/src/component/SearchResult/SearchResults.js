import React, { useState } from 'react';
import './SearchResult.css';
import FavoriteBorderIcon from "@material-ui/icons/FavoriteBorder";
import StarIcon from "@material-ui/icons/Star";
import { Button } from '@material-ui/core';
import { DateRangePicker } from 'react-date-range';
import Details from './Details';

function SearchResult({
    img,
    location,
    title,
    description,
    star,
    price,
    total,
}

) {
    const [showSearch, setShowSearch] = useState(false);
    let hotel_details = window.localStorage.getItem("employeeAdd");
    hotel_details = JSON.parse(hotel_details)
    let hotelLocation = hotel_details.location;
    let hotelName = hotel_details.hotelName;
    description = hotelName + ' Hotel offers ultimate comfort and luxury. This 4-storied hotel is a beautiful combination of traditional grandeur and modern facilities. ';
    return (
        <div className='searchResult'>
            <img src={img} alt="" />
            <FavoriteBorderIcon className="searchResult__heart" />
            
            <div className='searchResult__info'>
                <div className="searchResult__infoTop">
                    <p>{hotelLocation}</p>
                    <h3>{hotelName}</h3>
                    <p>69 Reviews</p>
                    <p>{description}</p>
                </div>

                <div className="searchResult__infoBottom">
                    <div className="searchResult__stars">
                        <StarIcon className="searchResult__star" />
                        <p>
                            <strong>{star}</strong>
                        </p>
                    </div>
                    <div className='searchResults__price'>
                        <h2></h2>
                        <p></p>
                        {!showSearch? <Button onClick={() => setShowSearch(!showSearch)} variant='outlined'>Book</Button>:null}
                         {/* <Button onClick={() => setShowSearch(!showSearch)} variant='outlined'>Book</Button> */}
                    </div>
                    {showSearch && <Details/>}
                </div>
            </div>
        </div>
    )
}

export default SearchResult