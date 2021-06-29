<?php
    require("conexion.php");
  
	$id_vehiculo = $_POST["id_vehiculo"];
	$id_usuario  = $_POST["id_usuario"];
	$ubicacion  = $_POST["ubicacion"];	
	$fecha_inicio = $_POST["fecha_inicio"];
	$fecha_fin = $_POST["fecha_fin"];
	$idv = (int)$id_vehiculo;
	$idu = (int)$id_usuario;
    // VERIFICAMOS QUE NO ESTEN VACIAS LAS VARIABLES
    if(empty($id_vehiculo) || empty($id_usuario)||  empty($ubicacion) || empty($fecha_inicio) || empty($fecha_fin)) {

        // SI ALGUNA VARIABLE ESTA VACIA MUESTRA ERROR
        //echo "Se deben llenar los dos campos";
        echo $id_vehiculo . $id_usuario . $ubicacion . $fecha_inicio . $fecha_fin;

    } else {

        // CREAMOS LA CONSULT
        $sql = "INSERT INTO renta_auto(`id_vehiculo`, `id_usuario`, `ubicacion`, `fecha_inicio`, `fecha_fin`, `status`) VALUES ('".$idv."','".$idu."','".$ubicacion."','".$fecha_inicio."', '".$fecha_fin."', '3')";

        if($conn->query($sql) === TRUE) {
            
            echo "MENSAJE";
            
        } else {
            echo "Error INSERT: " . $sql . "<br>" . $conn->error;
        }
    }
?>