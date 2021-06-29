<?php
require 'conexion.php';
$sql = "SELECT * FROM renta_auto";

$result = mysqli_query($conn, $sql);
if($result->num_rows > 0){
    $return_arr['rentas'] = array();
    while($row = $result->fetch_array()){
        array_push($return_arr['rentas'], array(

            'id_renta'=>utf8_encode($row['id_renta']),
            'id_vehiculo'=>utf8_encode($row['id_vehiculo']),
            'id_usuario'=>utf8_encode($row['id_usuario']),
            'ubicacion'=>utf8_encode($row['ubicacion']),
            'status'=>utf8_encode($row['status'])
            
        ));
    }
    echo json_encode($return_arr);
}