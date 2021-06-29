<?php
session_start();
error_reporting(0);
include "config/config.php";
spl_autoload_register(function($className){
    include "classes/$className.php";
});

?>