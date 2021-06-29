<?php
    require("conexion.php");
  
	$id_renta = $_POST["id_renta"];
	$id_vehiculo = $_POST["id_vehiculo"];
	$status = $_POST["status"];

    // VERIFICAMOS QUE NO ESTEN VACIAS LAS VARIABLES
    if(empty($id_vehiculo) || empty($id_renta) || empty($status)) {

        // SI ALGUNA VARIABLE ESTA VACIA MUESTRA ERROR
        //echo "Se deben llenar los dos campos";
        echo $id_vehiculo . $id_renta . $status;

    } else {

        // CREAMOS LA CONSULTA
        if ($status == 3)
            $sql = "UPDATE renta_auto SET status=1 WHERE id_renta='$id_renta'";
        else if ($status == 2)
            $sql = "UPDATE renta_auto SET status=0 WHERE id_renta='$id_renta'";
        if($conn->query($sql) === TRUE) {
            echo "MENSAJE";
        } else {
            echo "Error INSERT: " . $sql . "<br>" . $conn->error;
        }
    }
?>