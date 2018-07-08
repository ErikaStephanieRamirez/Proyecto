//paquete mongoose
const mongoose = require('mongoose');
//importando el modelo de bebidas
const Res = require('../models/resModel');

//GET all pollos
exports.reces_get_all = (req, res, next) => {
  Res.find()
  .select('_id name price priceCombo productImage')
  .exec()
  .then(docs => {
    const response = {
      reces: docs.map(doc =>{
        return {
          _id: doc._id,
          name: doc.name,
          price: doc.price,
          priceCombo: doc.priceCombo,
          productImage: doc.productImage
        }
      }) //ponemos un campo de Arreglo de Productos
    }
    if (docs.length > 0){
      res.status(200).json(response);//retornamos el response de arriba
    } else { //por si no hay productos
      res.status(404).json({
        message: "No reces found"
      });
    }
  })
  .catch(err => {
    res.status(500).json({
      error: err
    });
  });
}

//POST a drink
exports.reces_insert_one = (req, res, next) => {
  //creando una instanca del modelo de producto. Como un constructor de Java. Se crea a partir de los datos que se envian en la req de POST
  const reces = new Res({
    _id: new mongoose.Types.ObjectId(), //esto nos creara un Id automaticamente. Por el paquete mongoose
    name: req.body.name,
    price: req.body.price,
    priceCombo: req.body.priceCombo,
    productImage: req.file.path
  });
  // lo guarda en la base de datos. y hace log a la consola
  reces.save()
  .then(result => {
    console.log(result);
    //request
    res.status(201).json({
      message: 'The res was stored succesfully!',
      //mostrando el producto creado
      createdProduct: {
        _id: result._id,
        name: result.name,
        price: result.price,
        priceCombo: result.priceCombo,
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

//DELETE a drink
exports.reces_delete_one = (req, res, next) => {
  const id = req.params.productId
  Res.findByIdAndRemove(id)
  .exec()
  .then(result => {
    res.status(200).json({
      message: 'The res was successfully deleted'
    });
  })
  .catch(err => {
    res.status(500).json({
      error: err
    });
  });
}

//PATCH a drink
exports.reces_patch_one = (req, res, next) => {
  const id = req.params.productId;
  const updateOperations = {};
  for (const ops of req.body){
    updateOperations[ops.propName] = ops.value;
  }
  Res.update({_id: id}, {$set: updateOperations})
  .exec()
  .then(result =>{
    res.status(200).json({
      message: 'The res was updated successfully'
    });
  })
  .catch(err => {
    res.status(500).json({
      error: err
    });
  });
}
