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

$password = password_hash($password, PASSWORD_BCRYPT);

$sql = "INSERT INTO usuario(`clase_usuario`, `correo`, `password`, `nombres`, `apellido_paterno`, `apellido_materno`, `telefono`, `direccion`, `fecha_de_nacimiento`, `licencia`) VALUES ('$clase_usuario','$correo','$password','$nombres','$apellido_paterno','$apellido_materno','$telefono','$direccion','$fecha_de_nacimiento','$licencia')";


if($conn->query($sql) === TRUE) {
    $query = "SELECT id_usuario FROM usuario ORDER BY id_usuario DESC LIMIT 1";
    $last_id = $conn->query($query);
    if ($last_id->num_rows > 0) {
        // output data of each row
        while($row = $last_id->fetch_assoc()) {
            echo $row["id_usuario"];
        }
    }
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
?>