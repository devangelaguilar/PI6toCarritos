<?php
    require("conexion.php");
    
    // RECIBE LOS DATOS DE LA APP
    
	$id_usuario = $_POST["id_usuario"];
    $numeracion_tarjeta = $_POST["numeracion_tarjeta"];
    $fecha_vencimiento = $_POST["fecha_vencimiento"];

    // VERIFICAMOS QUE NO ESTEN VACIAS LAS VARIABLES
    if(empty($id_usuario) || empty($numeracion_tarjeta)||  empty($fecha_vencimiento)) {

        // SI ALGUNA VARIABLE ESTA VACIA MUESTRA ERROR
        //echo "Se deben llenar los dos campos";
        echo "ERROR 1";

    } else {

        // CREAMOS LA CONSULTA
        $sql = "INSERT INTO forma_pago(id_usuario,numeracion_tarjeta,fecha_vencimiento) 
        VALUES ('$id_usuario','$numeracion_tarjeta','$fecha_vencimiento')";

        //$query = $mysqli->query($sql);

        if($conn->query($sql) === TRUE) {
            echo "MENSAJE";
        } else {
            echo "Error: " . $sql . "<br>" . $conn->error;
        }
        
    }

?>