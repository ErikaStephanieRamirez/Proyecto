const express = require('express');
const app = express();
//paquete de body bodyParser
const bodyParser = require('body-parser');
//paquete monoose
const mongoose = require('mongoose');
//variables
require('dotenv').config();

//lidiando con errores, si no logro acceder a ninguna de las rutas de arriba
app.use((req, res, next) => {
  const error = new Error('Not found');
  error.status = 404;
  next(error);//se pasa a la funcion de abajo
});

//esta funcion maneja todo tipo de errores
app.use((error, req, res, next) => {
  res.status(error.status || 500);
  res.json({
    error: {
      message: error.message
    }
  });
});

module.exports = app;
