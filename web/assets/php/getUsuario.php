<?php
require 'conexion.php';
$sql = "SELECT * FROM usuario";
if(!$conn->query($sql)){
	echo "Error";
}
else{
	$result = $conn->query($sql);
	if($result->num_rows > 0){
		$return_arr['usuarios'] = array();
		while($row = $result->fetch_array()){
			array_push($return_arr['usuarios'], array(
				'id_Usuario'=>$row['id_Usuario'],
    			'clase_usuario'=>$row['clase_usuario'],
    			'correo'=>$row['correo'],
    			'nombre'=>$row['nombres'],
    			'apellido_paterno'=>$row['apellido_paterno'],
    			'apellido_materno'=>$row['apellido_materno'],
    			'telefono'=>$row['telefono'],
    			'direccion'=>$row['direccion'],
    			'fecha_de_nacimiento'=>$row['fecha_de_nacimiento']
			));
		}	
		echo json_encode($return_arr);
	}
}