import React from 'react';
import Banner from '../Banner/Banner';
import './Home.css';
import Card from '../Card/Card'
import { Header } from '..';
import { Rotate90DegreesCcw } from '@material-ui/icons';
function Home() {
  return (
    <div className = 'home'>
        <Header/>
        <Banner />
        <div className='home__section'>
            <Card
                src ="road.jpeg"
                title="Near Nature"
                description="Get one with the Nature"
            />
            <Card
                src="lakeview.jpeg"
                title="Views"
                description="Enjoy amazing views"
            />
            <Card
                src="pool.jpeg"
                title="Amenities"
                description="Best amenities for you"
            />
            </div>
    </div>
  )
}

export default Home
