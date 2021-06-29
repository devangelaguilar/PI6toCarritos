<?php
require 'conexion.php';
$sql = "SELECT * FROM vehiculo";

$result = mysqli_query($conn, $sql);
if($result->num_rows > 0){
	$return_arr['vehiculos'] = array();
	while($row = $result->fetch_array()){
		array_push($return_arr['vehiculos'], array(
			'id_vehiculo'=>$row['id_vehiculo'],
			'tipo_vehiculo'=>$row['tipo_vehiculo'],
			'placas'=>$row['placas'],
			'modelo'=>$row['modelo'],
			'color'=>$row['color'],
			'foto'=>$row['foto'],
			'status'=>$row['status'],
			'precio'=>$row['precio'],
			'transmision'=>$row['transmision']
		));
	}	
	echo json_encode($return_arr);
}
		