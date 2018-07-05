const express = require('express');
const router = express.Router();
//importando controlador de orders
const UsersController = require('../controllers/usersController');


router.get('/', UsersController.users_get_all);

router.post('/signup', UsersController.users_signup);

router.post('/login', UsersController.users_login);

//DELETE para users
router.delete('/:userId', UsersController.users_delete);

module.exports = router;
