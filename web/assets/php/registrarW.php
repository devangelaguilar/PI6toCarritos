<?php

require_once 'conexion.php';

$clase_usuario  = 1;
$correo  = $_POST["correo"];
$password  = $_POST["password"];
$nombres  = $_POST["nombres"];
$apellido_paterno  = $_POST["apellido_paterno"];
$apellido_materno  = $_POST["apellido_materno"];
$telefono  = $_POST["telefono"];
$direccion  = 'Proceso';
$fecha_de_nacimiento  = $_POST["fecha_de_nacimiento"];
$licencia  = $_POST["licencia"];
$status = 1;

$password = password_hash($password, PASSWORD_BCRYPT);

$sql = "INSERT INTO usuario(`clase_usuario`, `correo`, `password`, `nombres`, `apellido_paterno`, `apellido_materno`, `telefono`, `direccion`, `fecha_de_nacimiento`, `licencia`,`status`) VALUES ('$clase_usuario','$correo','$password','$nombres','$apellido_paterno','$apellido_materno','$telefono','$direccion','$fecha_de_nacimiento','$licencia', '$status')";


if($conn->query($sql) === TRUE) {
    echo "MENSAJE";
    header("Location: https://cinderellaride.000webhostapp.com/");
    
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
?>