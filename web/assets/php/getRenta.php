<?php
require 'conexion.php';
$sql = "SELECT * FROM renta_auto";

$result = mysqli_query($conn, $sql);
if($result->num_rows > 0){
	$return_arr['rentas'] = array();
	while($row = $result->fetch_array()){
		array_push($return_arr['rentas'], array(
		    'id_renta'=>$row['id_renta'],
			'id_vehiculo'=>$row['id_vehiculo'],
			'id_usuario'=>$row['id_usuario'],
			'ubicacion'=>$row['ubicacion'],
			'fecha_inicio'=>$row['fecha_inicio'],
			'fecha_fin'=>$row['fecha_fin'],
			'status'=>$row['status']
		));
	}	
	echo json_encode($return_arr);
}