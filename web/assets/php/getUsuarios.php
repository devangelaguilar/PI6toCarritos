<?php
require 'conexion.php';
$sql = "SELECT * FROM usuario";
if(!$conexion->query($sql)){
	echo "Error";
}
else{
	$result = $conexion->query($sql);
	if($result->num_rows > 0){
		$return_arr['usuarios'] = array();
		while($row = $result->fetch_array()){
			array_push($return_arr['usuarios'], array(
				'id_Usuario'=>$row['id_Usuario'],
				'correo'=>$row['correo']
			));
		}	
		echo json_encode($return_arr);
	}
}
		