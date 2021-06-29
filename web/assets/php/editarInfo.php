<?php

require_once 'conexion.php';

$id_usuario = $_POST["id_usuario"];
$correo  = $_POST["correo"];
$nombres  = $_POST["nombres"];
$apellido_paterno  = $_POST["apellido_paterno"];
$apellido_materno  = $_POST["apellido_materno"];
$telefono  = $_POST["telefono"];
$fecha_de_nacimiento  = $_POST["fecha_de_nacimiento"];


$sql = "UPDATE usuario SET correo='$correo',nombres='$nombres',apellido_paterno='$apellido_paterno',apellido_paterno='$apellido_paterno',telefono='$telefono',fecha_de_nacimiento='$fecha_de_nacimiento' WHERE id_usuario='$id_usuario'";

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