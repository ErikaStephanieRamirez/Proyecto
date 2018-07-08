const express = require('express');
const router = express.Router();

//paquete Multer
const multer = require('multer');
//storage strategy
const storage = multer.diskStorage({
  destination: function(req, file, cb) { //cb es callback
    cb(null, './uploads/'); // cath de null por posible error, relative path to upload folder
  },
  filename: function(req, file, cb) {
    cb(null, new Date().toISOString().replace(/:/g,'-') + file.originalname.split(" ").join(""));
    //cb(null, file.filename); // ejemplo de uso de uso del callback
  }
});
//filtros de multer personalizados para la carga de archivos
const fileFilter = (req, file, cb) => {
  if (file.mimetype === 'image/jpeg' || file.mimetype === 'image/png') {
    cb(null, true);
  } else {
    cb(null, false);
  }
};
//inicializando Multer, pasandole la estrategia de guardado de files
const upload = multer({
  storage: storage,
  limits: {
    fileSize: 1024 * 1024 * 5 // 5MB
  },
  fileFilter: fileFilter
});

//importanto Authentication de Token
const checkAuth = require('../middleware/check-auth');
//importando controlador de orders
const DesayunosController = require('../controllers/desayunosController');


router.get('/', DesayunosController.desayunos_get_all);

router.post('/', checkAuth, upload.single('productImage'), DesayunosController.desayunos_insert_one);

router.delete('/:productId', checkAuth, DesayunosController.desayunos_delete_one);

router.patch('/:productId', checkAuth, DesayunosController.desayunos_patch_one);

module.exports = router;
