import { Link } from "react-router-dom";
import React from "react";
import { useAuth0, withAuthenticationRequired } from "@auth0/auth0-react";

const AdminDashboard = () => {
  const { user } = useAuth0();

  return (
    <div className="text-center">
      <h1 className="main-para">Admin Dashboard</h1>

      <Link to="/UserData">
        <button className="primary-button">See User Data</button>
      </Link>

      <Link to="/AVData">
        <button className="primary-button">See AV Data</button>
      </Link>
    </div>
  );
};
export default AdminDashboard;
