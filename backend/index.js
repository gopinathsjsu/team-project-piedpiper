const express = require("express");
const mysql = require("mysql");


const app = express();
const PORT = 5000;
app.listen(PORT, console.log(`Server started on port ${PORT}`));
module.exports = app;
