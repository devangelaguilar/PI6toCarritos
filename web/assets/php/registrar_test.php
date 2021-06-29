<?php

require_once 'conexion.php';

// $clase_usuario  = '1';
// $correo  = $_POST["correo "];
// $password  = $_POST["password "];
// $nombres  = $_POST["nombres "];
// $apellido_paterno  = $_POST["apellido_paterno "];
// $apellido_materno  = $_POST["apellido_materno "];
// $telefono  = $_POST["telefono "];
// $direccion  = 'Proceso';
// $fecha_de_nacimiento  = $_POST["fecha_de_nacimiento "];
// $licencia  = $_POST["licencia "];

$clase_usuario  = 1;
$correo  ="a";
$password  = "a";
$nombres  = "a";
$apellido_paterno  = "a";
$apellido_materno  = "a";
$telefono  = "a";
$direccion  = "a";
$fecha_de_nacimiento  = "a";
$licencia  = "a";



$password = password_hash($password, PASSWORD_BCRYPT);

$sql = "INSERT INTO usuario(`clase_usuario`, `correo`, `password`, `nombres`, `apellido_paterno`, `apellido_materno`, `telefono`, `direccion`, `fecha_de_nacimiento`, `licencia`) VALUES ('$clase_usuario','$correo','$password','$nombres','$apellido_paterno','$apellido_materno','$telefono','$direccion','$fecha_de_nacimiento','$licencia')";



if($conn->query($sql) === TRUE) {
    echo "MENSAJE";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}