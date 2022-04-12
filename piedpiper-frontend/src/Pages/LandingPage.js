import React from "react";
import { Link } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react";
import { usedHistory } from "react-router-dom";

import "../../App.css";

export default function LandingPage() {
  const { loginWithRedirect } = useAuth0();

  return (
    <header style={HeaderStyle}>
      <button className="primary-button" onClick={() => loginWithRedirect()}>
        Admin Dashboard
     
        </Link>
        <Link to="/register">
          <button className="primary-button" id="reg_btn">
            <span>register </span>
          </button>
        </Link>
      </div>
    </header>
  );
}

const HeaderStyle = {
  width: "100%",
  height: "100vh",
  background: `url(https://thenextavenue.com/wp-content/uploads/2020/10/01-mercedes-benz-innovation-f015-luxury-in-motion-autonomous-driving-3400x1440-1.jpg)`,
  backgroundPosition: "center",
  backgroundRepeat: "no-repeat",
  backgroundSize: "cover",
};
