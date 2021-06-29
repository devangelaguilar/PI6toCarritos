<?php
require_once 'conexion.php';


$id_usuario = $_POST['id_usuario'];

$sql = "DELETE FROM forma_pago WHERE id_usuario = $id_usuario";
$result = $conn->query($sql);

mysqli_close($conn);

    
	