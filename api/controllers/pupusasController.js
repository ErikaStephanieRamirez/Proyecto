//paquete mongoose
const mongoose = require('mongoose');
//importando el modelo de bebidas
const Pupusa = require('../models/pupusasModel');

//GET all drinks
exports.pupusas_get_all = (req, res, next) => {
  Pupusa.find()
  .select('_id name price productImage')
  .exec()
  .then(docs => {
    const response = {
      pupusas: docs.map(doc =>{
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
        message: "No pupusas found"
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
exports.pupusas_insert_one = (req, res, next) => {
  //creando una instanca del modelo de producto. Como un constructor de Java. Se crea a partir de los datos que se envian en la req de POST
  const pupusa = new Pupusa({
    _id: new mongoose.Types.ObjectId(), //esto nos creara un Id automaticamente. Por el paquete mongoose
    name: req.body.name,
    price: req.body.price,
    productImage: req.file.path
  });
  // lo guarda en la base de datos. y hace log a la consola
  pupusa.save()
  .then(result => {
    console.log(result);
    //request
    res.status(201).json({
      message: 'The pupusa was stored succesfully!',
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

//DELETE a drink
exports.pupusas_delete_one = (req, res, next) => {
  const id = req.params.productId
  Pupusa.findByIdAndRemove(id)
  .exec()
  .then(result => {
    res.status(200).json({
      message: 'The pupusa was successfully deleted'
    });
  })
  .catch(err => {
    res.status(500).json({
      error: err
    });
  });
}

//PATCH a drink
exports.pupusas_patch_one = (req, res, next) => {
  const id = req.params.productId;
  const updateOperations = {};
  for (const ops of req.body){
    updateOperations[ops.propName] = ops.value;
  }
  Pupusa.update({_id: id}, {$set: updateOperations})
  .exec()
  .then(result =>{
    res.status(200).json({
      message: 'The pupusa was updated successfully'
    });
  })
  .catch(err => {
    res.status(500).json({
      error: err
    });
  });
}
