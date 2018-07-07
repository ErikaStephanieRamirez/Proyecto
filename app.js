const express = require('express');
const app = express();
//paquete de body bodyParser
const bodyParser = require('body-parser');
//paquete monoose
const mongoose = require('mongoose');
//variables
require('dotenv').config();

//rutas
const usersRoutes = require('./api/routes/usersRoute');
const bebidasRoutes = require('./api/routes/bebidasRoute');
const pupusasRoutes = require('./api/routes/pupusasRoute');
const pollosRoutes = require('./api/routes/pollosRoute');

//conexion a la base de datos, desde el link que nos proporciono la base de datos en ATLAS.
mongoose.connect('mongodb+srv://maxisun:'+process.env.MONGO_ATLAS_PASSWORD+'@rep-sazon-im8dq.mongodb.net/test?retryWrites=true');
mongoose.Promise = global.Promise;

app.use('/uploads', express.static('uploads'));//primer parametro permite que se incluya el nombre del folder para que se busque en un browser
//configurando el uso de bodyParser
app.use(bodyParser.urlencoded({extended: false}));//parser para bodies de urlencoded al cual no queremos que sean extendidos, por eso el "false"
app.use(bodyParser.json());//configuracion para bodies que contengan JSON

//manejo de error CORS por medio de Headers
app.use((req, res, next) => {
  res.header('Access-Control-Allow-Origin','*');//permite cualquier origen para acceder al API
  //res.header('Access-Control-Allow-Origin','http://peneduro.com');//solo esa direccion  de origen puede acceder
  res.header("Access-Control-Allow-Headers",
  "Origin, X-Requested-With, Content-Type, Accept, Authorization");//son los Headers que permitimos para acceder al API
  //El browser siempre envia esto, asi que le indicamos que metodos estan disponibles para el uso de la API
  if (req.method === 'OPTIONS'){
    res.header('Access-Control-Allow-Methods', 'PUT, POST, GET, DELETE, PATCH');//protocolos Http que soporta esta API
    return res.status(200).json({});//se responde que metodos tiene permitido usar al browser o cliente
  }
  next();//procedemos a resto de las rutas
});

app.use('/users', usersRoutes);
app.use('/bebidas', bebidasRoutes);
app.use('/pupusas', pupusasRoutes);
app.use('/pollos', pollosRoutes);

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
