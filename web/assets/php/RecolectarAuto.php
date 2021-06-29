<?php
    require("conexion.php");
  
	$id_renta = $_POST["id_renta"];
	$id_vehiculo = $_POST["id_vehiculo"];
	$id_usuario = $_POST["id_usuario"];
	$km = $_POST["km"];
	$notas = $_POST["notas"];

    // VERIFICAMOS QUE NO ESTEN VACIAS LAS VARIABLES
    if(empty($id_vehiculo) || empty($id_renta) || empty($id_usuario) || empty($km) || empty($notas)) {

        // SI ALGUNA VARIABLE ESTA VACIA MUESTRA ERROR
        //echo "Se deben llenar los dos campos";
        echo $id_vehiculo . $id_renta . $id_usuario;

    } else {

        // INGRESAR LAS NOTAS DE LA ENTREGA
        $ev = "INSERT INTO estado_vehiculo(`id_vehiculo`,`kilometraje`,`notas`,`ultima_entrega`,`ultimo_usuario`) VALUES ('$id_vehiculo', '$km', '$notas', '$id_renta', '$id_usuario')";
        if ($conn->query($ev) === TRUE){
            // OBETENER EL ID DEL DATO QUE SE ACABA DE INGRESAR
            $query = "SELECT id_estado_vehiculo FROM estado_vehiculo ORDER BY id_estado_vehiculo DESC LIMIT 1";
            $last_id = $conn->query($query);
            if ($last_id->num_rows > 0) {
                while($row = $last_id->fetch_assoc()) {
                    $id_ev = $row["id_estado_vehiculo"];
                }
            }
            // INSERTAR A BITACORA
            $bit = "INSERT INTO bitacora(`id_vehiculo`, `id_usuario`, `id_entrega`, `id_estado_vehiculo`) values ('$id_vehiculo', '$id_usuario', '$id_renta', '$id_ev')";
            if ($conn->query($bit) === TRUE) {
                // CAMBIAR EL ESTADO DE RENTA A 0 PARA QUE ESTE COMPLETADA
                $sql = "UPDATE renta_auto SET status=0 WHERE id_renta='$id_renta'";
                if($conn->query($sql) === TRUE) {
                    // PONER EL AUTO DISPONIBLE DE NUEVO
                    $update = "UPDATE vehiculo SET status=1 WHERE id_vehiculo='$id_vehiculo'";
                    if($conn->query($update) === TRUE) {
                        echo "MENSAJE";
                    } else {
                        echo "Error UPDATE VEHICULO: " . $sql . "<br>" . $conn->error;
                    }
                } else {
                    echo "Error UPDATE RENTA: " . $sql . "<br>" . $conn->error;
                }
            } else {
                    echo "Error INSERT BIT: " . $sql . "<br>" . $conn->error;
                }
            
        }else {
                echo "Error INSERT EV: " . $sql . "<br>" . $conn->error;
        }
        
    }
            
?>