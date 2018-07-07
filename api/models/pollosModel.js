//paquete de mongoose
const mongoose = require('mongoose');

//creacion del Schema de productos
const polloSchema = mongoose.Schema({
  _id: mongoose.Schema.Types.ObjectId,
  name: {type: String, required: true},
  price: {type: Number, required: true},
  priceCombo: {type: Number, required: true},
  productImage: {type: String, required: true}
});

//esto es para exportar el Schema como un modelo. Como un modelo de objeto para ser construido
module.exports = mongoose.model('Pollo', polloSchema);
