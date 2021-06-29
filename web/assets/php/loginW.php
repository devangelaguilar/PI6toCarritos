<?php
    $correo = $_POST['correo'];
    $password = $_POST['password'];
	//$correo = "admin0";
    	//$password = "1234";
    require_once 'conexion.php';
    $sql = "SELECT * FROM usuario WHERE correo='$correo' ";
    $response = mysqli_query($conn, $sql);
    $result = array();
    $result['login'] = array();
    
    if ( mysqli_num_rows($response) > 0 ) {
        $row = mysqli_fetch_assoc($response);
        if (password_verify($password, $row['password']) ) {
        //if ($password == $row['password']){
	        $index['id_Usuario'] = utf8_encode($row['id_Usuario']);
	        $index['clase_usuario'] = utf8_encode($row['clase_usuario']);
            $index['nombres'] = utf8_encode($row['nombres']);
	        $index['apellido_paterno'] = utf8_encode($row['apellido_paterno']);
	        $index['apellido_materno'] = utf8_encode($row['apellido_materno']);
            $index['correo'] = utf8_encode($row['correo']);
	        $index['telefono'] = utf8_encode($row['telefono']);
	        $index['fecha_de_nacimiento'] = utf8_encode($row['fecha_de_nacimiento']);	
            array_push($result['login'], $index);
            $result['success'] = "1";
            $result['message'] = "success";
            mysqli_close($conn);
            header("Location: https://cinderellaride.000webhostapp.com/home.html");
            die();
        } 
        else {
            $result['success'] = "0";
            $result['message'] = "error";
            echo json_encode($result);
            mysqli_close($conn);
        }
    }
?>