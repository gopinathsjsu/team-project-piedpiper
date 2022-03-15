const mysql = require("mysql");

var con = mysql.createConnection({
  host: "http://project-piedpiper-database.cs9yuznqv1i8.us-west-1.rds.amazonaws.com",
  user: "admin",
  password: "password",
  port: "3306",
  database: "hotelmanagement",
});

con.connect(function (err) {
  if (err) throw err;
  console.log(" Databse Connected!");
});

module.exports = con;