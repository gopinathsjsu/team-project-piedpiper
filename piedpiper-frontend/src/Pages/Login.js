import React, { useState, useEffect } from "react";
import { Link, Redirect } from "react-router-dom";
import Axios from "axios";

export default function LoginPage() {
  const [user_name, setUname] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  function onSubmit(e) {
    e.preventDefault();
    let data = {
      user_name,
      password,
    };
    Axios.post("http://localhost:8001/login", data)
      .then((response) => {
        console.log(response);
        console.log(response.data.message);
        if (response.statusText == "OK") {
          setMessage(response.data.message);
        } else setMessage(response.data.message);
      })
      .catch((err) => {
        console.log(err);
      });
  }

  let redirectVar = null;
  if (message === "AV User ok") {
    console.log("AV User Ok from frontend");
    localStorage.setItem("username", user_name);
    alert(`Logged in successfully, welcome ${user_name}!`);
    redirectVar = <Redirect to="/homeUser" />;
  } else if (message === "AV Owner ok") {
    localStorage.setItem("username", user_name);
    alert(`Logged in successfully, welcome ${user_name}!`);
    redirectVar = <Redirect to="/homeOwner" />;
  } else if (message === "notok") {
    alert("Log in failed");
    redirectVar = <Redirect to="/" />;
  }

  return (
    <div id="Login" style={HeaderStyle}>
      <div className="text-center m-5-auto">
        {redirectVar}
        <h2 className="main-para">Sign in</h2>
        <form onSubmit={onSubmit}>
          <p>
            <label>Username or email address</label>
            <br />
            <input
              type="text"
              name="username"
              onChange={(e) => setUname(e.target.value)}
              required
            />
          </p>
          <p>
            <label>Password</label>
            <Link to="/forget-password">
              <label className="right-label">Forget password?</label>
            </Link>
            <br />
            <input
              type="password"
              name="password"
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </p>
          <p>
            <button id="sub_btn" type="submit">
              Login
            </button>
          </p>
        </form>
        <footer>
          <p>
            First time? <Link to="/register">Create an account</Link>.
          </p>
          <p>
            <Link to="/">Back to Homepage</Link>.
          </p>
        </footer>
      </div>
    </div>
  );
}

const HeaderStyle = {
  width: "100%",
  height: "120vh",
  background: `url(https://mir-s3-cdn-cf.behance.net/project_modules/fs/836e7134316935.570280fbcf7a4.gif)`,
  //backgroundPosition: "bottom",
  backgroundRepeat: "no-repeat",
  backgroundSize: "cover",
};
