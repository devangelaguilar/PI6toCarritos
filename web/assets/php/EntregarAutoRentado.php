<?php
    require("conexion.php");
  
	$id_renta = $_POST["id_renta"];
	$id_vehiculo = $_POST["id_vehiculo"];

    // VERIFICAMOS QUE NO ESTEN VACIAS LAS VARIABLES
    if(empty($id_vehiculo) || empty($id_renta)) {

        // SI ALGUNA VARIABLE ESTA VACIA MUESTRA ERROR
        //echo "Se deben llenar los dos campos";
        echo $id_vehiculo . $id_renta;

    } else {

        // CREAMOS LA CONSULTA
        $sql = "UPDATE renta_auto SET status=2 WHERE id_renta='$id_renta'";
        if($conn->query($sql) === TRUE) {
            echo "MENSAJE";
        } else {
            echo "Error INSERT: " . $sql . "<br>" . $conn->error;
        }
    }
            //$update = "UPDATE vehiculo SET status=1 WHERE id_vehiculo='$id_vehiculo'";
            //if($conn->query($update) === TRUE) {
            //    echo "MENSAJE";
            //} else {
            //    echo "Error UPDATE: " . $sql . "<br>" . $conn->error;
            //}
?>