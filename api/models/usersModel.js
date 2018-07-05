//paquete de mongoose
const mongoose = require('mongoose');

//creacion del Schema de productos
const userSchema = mongoose.Schema({
  _id: mongoose.Schema.Types.ObjectId, //es un tipo de serial que es como una String larga
  username: {type: String, required: true},
  password: {type: String, required: true}
});

//esto es para exportar el Schema como un modelo. Como un modelo de objeto para ser construido
module.exports = mongoose.model('User', userSchema);
