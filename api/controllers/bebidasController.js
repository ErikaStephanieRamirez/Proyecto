//paquete mongoose
const mongoose = require('mongoose');
//importando el modelo de bebidas
const Bebida = require('../models/bebidasModel');

//GET all drinks
exports.bebidas_get_all = (req, res, next) => {
  Bebida.find()
  .select('_id name price productImage')
  .exec()
  .then(docs => {
    const response = {
      bebidas: docs.map(doc =>{
        return {
          _id: doc._id,
          name: doc.name,
          price: doc.price,
          productImage: doc.productImage
        }
      }) //ponemos un campo de Arreglo de Productos
    }
    if (docs.length > 0){
      res.status(200).json(response);//retornamos el response de arriba
    } else { //por si no hay productos
      res.status(404).json({
        message: "No drinks found"
      });
    }
  })
  .catch(err => {
    console.log(err);
    res.status(500).json({
      error: err
    });
  });
}

//POST a drink
exports.bebidas_insert_one = (req, res, next) => {
  //creando una instanca del modelo de producto. Como un constructor de Java. Se crea a partir de los datos que se envian en la req de POST
  const bebida = new Bebida({
    _id: new mongoose.Types.ObjectId(), //esto nos creara un Id automaticamente. Por el paquete mongoose
    name: req.body.name,
    price: req.body.price,
    productImage: req.file.path
  });
  // lo guarda en la base de datos. y hace log a la consola
  bebida.save()
  .then(result => {
    console.log(result);
    //request
    res.status(201).json({
      message: 'The drink was created succesfully!',
      //mostrando el producto creado
      createdProduct: {
        _id: result._id,
        name: result.name,
        price: result.price,
        productImage: result.productImage
      }
    });
    //error
  }).catch(err => {
    res.status(500).json({
      error: err
    });
  });
}

exports.bebidas_delete_one = (req, res, next) => {
  const id = req.params.productId
  Bebida.findByIdAndRemove(id)
  .exec()
  .then(result => {
    res.status(200).json({
      message: 'The drink was successfully deleted'
    });
  })
  .catch(err => {
    console.log(err);
    res.status(500).json({
      error: err
    });
  });
}
