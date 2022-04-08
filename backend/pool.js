const mysql = require("mysql");

var con = mysql.createConnection({
    host:"project-piedpiper-database.cs9yuznqv1i8.us-west-1.rds.amazonaws.com",
    user: 'admin',
	password: 'password',
	database: 'hotelmanagement',
});

con.connect(function (err) {
  if (err) throw err;
  console.log("Connected!");
});

module.exports = con;