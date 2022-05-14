import React, { useState } from "react";
import { Link, Redirect } from "react-router-dom";
import Axios from "axios";

import "../../App.css";

export default function RegisterPage() {
  const [first_name, setFname] = useState("");
  const [last_name, setLname] = useState("");
  const [user_name, setUname] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [dob, setDob] = useState("");
  const [phone, setPhone] = useState("");
  const [location, setLocation] = useState("AV User");

  const [user_type, setUtype] = useState("AV User");

  function onSubmit(e) {
    e.preventDefault();
    let data = {
      first_name,
      last_name,
      user_name,
      email,
      password,
      user_type,
      dob,
      phone,
      location,
    };

    Axios.post("http://localhost:8001/register", data)
      .then((response) => {
        console.log(response.data.message);
        // if (response.data.message == "ok") {
        //   window.alert("Successfully registered");
        //   window.location.href = "/login";
        // }
      })
      .catch((err) => {
        console.log(err);
      });
  }

  return (
    <div className="text-center m-5-auto" style={HeaderStyle}>
      <h2 className="main-para">Join us</h2>
      <h5>Create your account</h5>
      <form onSubmit={onSubmit}>
        <p>
          <label>First Name</label>
          <br />
          {/* <input
            type="text"
            name="first_name"
            onChange={(e) => setFname(e.target.value)}
            required
          />
        </p> */}

        <p>
          <label>Last Name</label>
          <br />
          <input
            type="text"
            name="last_name"
            onChange={(e) => setLname(e.target.value)}
            required
          />
        </p>
        <p>
          <label>Username</label>
          <br />
          <input
            type="text"
            name="username"
            onChange={(e) => setUname(e.target.value)}
            required
          />
        </p>
        <p>
          <label>Email address</label>
          <br />
          <input
            type="email"
            name="email"
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </p>
        <p>
          <label>DOB</label>
          <br />
          <input
            type="date"
            name="dob"
            onChange={(e) => setDob(e.target.value)}
            required
          />
        </p>
        <p>
          <label>Phone</label>
          <br />
          <input
            type="number"
            name="phone"
            onChange={(e) => setPhone(e.target.value)}
            required
          />
        </p>
        <p>
          <label>Address</label>
          <br />
          <input
            type="text"
            name="address"
            onChange={(e) => setLocation(e.target.value)}
            required
          />
        </p>
        <p>
          <label>User Type</label>
          <br />

          <select
            name="option"
            id="selectList"
            onChange={(e) => setUtype(e.target.value)}
          >
              <option value="AV user">AV User</option>
            <option value="AV owner">AV Owner</option>
          </select>
        </p>

        <p>
          <label>Password</label>
          <br />
          <input
            type="password"
            name="password"
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </p>
        <p>
          <input type="checkbox" name="checkbox" id="checkbox" required />{" "}
          <span>
            I agree all statements in{" "}
            <a
              href="https://google.com"
              target="_blank"
              rel="noopener noreferrer"
            >
              terms of service
            </a>
          </span>
          .
        </p>
        <p>
          <button id="sub_btn" type="submit">
            Register
          </button>
        </p>
      </form>
      <footer>
        <p>
          <Link to="/">Back to Homepage</Link>
        </p>
      </footer>
    </div>
  );
}

const HeaderStyle = {
  width: "100%",
  height: "100%",
  background: `url(https://img.freepik.com/free-vector/taxi-out-from-phone-booking-online-taxi-service-graphic-design-vector-illustration_620585-19.jpg?w=2000)`,
  backgroundPosition: "center",
  backgroundRepeat: "no-repeat",
  backgroundSize: "cover",
};
