<?php
//$conn = mysqli_connect("localhost", "id16405021_crideuser", "HB\l*#e3dHhYn8?G", "id16405021_cride");

require 'conexion.php';


$id_usuario = $_POST['id_usuario'];
//$id_usuario = "74";
$sql = "SELECT numeracion_tarjeta, fecha_vencimiento FROM forma_pago WHERE id_usuario = $id_usuario";

	$result = $conn->query($sql);
	if($result->num_rows > 0){
		$return_arr['numeracion_tarjeta'] = array();
		while($row = $result->fetch_array()){
			array_push($return_arr['numeracion_tarjeta'], array(
				'numeracion_tarjeta'=>$row['numeracion_tarjeta'],
				'fecha_vencimiento'=>$row['fecha_vencimiento']
			));
		}	
		echo json_encode($return_arr);
        mysqli_close($conn);
	}
    else {

        $result['success'] = "0";
        $result['message'] = "error";
        echo json_encode($result);

        mysqli_close($conn);

    }
		