<?php
    $idUsuario = $_POST['idUsuario'];
    

    //require_once '../conexion/conexion.php';
    $mysqli = new mysqli(
        "localhost",
        "chilaqu3_cap_root",
        "5ZdPKw!Zszc^",
        "chilaqu3_vulnerables"
    );
	$conn = mysqli_connect("localhost", "chilaqu3_cap_root", "5ZdPKw!Zszc^", "chilaqu3_vulnerables");
/////////////////////Corregir conexi[on a base de datos]
    $sql = "SELECT * FROM usuario WHERE id_Usuario='$idUsuario' ";

    $response = mysqli_query($conn, $sql);

    $result = array();
    $result['login'] = array();
    
    if ( mysqli_num_rows($response) > 0 ) {
        
        $row = mysqli_fetch_assoc($response);

       
            
	        $index['nombres'] = utf8_encode($row['nombres']);
            $index['apellido_paterno'] = utf8_encode($row['apellido_paterno']);
	        $index['telefono'] = utf8_encode($row['telefono']);
	        $index['correo'] = utf8_encode($row['correo']);
            $index['direccion'] = utf8_encode($row['direccion']);
	        $index['fecha_de_nacimiento'] = utf8_encode($row['fecha_de_nacimiento']);
	        $index['licencia'] = utf8_encode($row['licencia']);	
	


            array_push($result['login'], $index);

            $result['success'] = "1";
            $result['message'] = "success";
            echo json_encode($result);

            mysqli_close($conn);

         else {

            $result['success'] = "0";
            $result['message'] = "error";
            echo json_encode($result);

            mysqli_close($conn);

        }

    }



?>