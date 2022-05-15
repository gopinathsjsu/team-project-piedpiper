import React , { useState } from 'react';
import './SearchPage.css';
import { Button } from "@material-ui/core";
import SearchResult from "../SearchResult/SearchResults";
import "react-date-range/dist/styles.css";
import "react-date-range/dist/theme/default.css";
import { Header } from '..';
function SearchPage() {

    let hotel_details = window.localStorage.getItem("employeeAdd")
          hotel_details = JSON.parse(hotel_details)
  return (
      <div>
          <Header/>
    <div className='searchPage'>
            <h2>Select the available hotels</h2>
            <div className='searchPage__info'>
            </div>
            <SearchResult
                img="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ_wbPYTxQPMcBh7SPzLFActXnP3uhifeVT_g&usqp=CAU"
                title = {hotel_details.hotelName}
                // description="1 guest · 1 bedroom · 1 bed · 1.5 shared bthrooms · Wifi · Kitchen · Free parking · Washing Machine"
                star={5}
                // price="£30 / night"
                // total="£117 total"
            />


        </div>
        </div>
  )
}

export default SearchPage
