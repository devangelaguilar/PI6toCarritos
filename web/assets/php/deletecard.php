<?php
require_once 'conexion.php';


$numeracion_tarjeta = $_POST['numeracion_tarjeta'];

$sql = "DELETE FROM forma_pago WHERE numeracion_tarjeta = $numeracion_tarjeta";
$result = $conn->query($sql);
mysqli_close($conn);

    
	