<?php

    // VARIABLES QUE ALMACENAN LA CONEXION A LA BASE DE DATOS
    $mysqli = new mysqli(
        "localhost",
        "id16405021_cride_user",
        "z1ITmzGEsJ)qGiK=",
        "id16405021_cride"
    );
	$conn = mysqli_connect("localhost", "id16405021_cride_user", "z1ITmzGEsJ)qGiK=", "id16405021_cride");

    // COMPROBAMOS LA CONEXION
    if($mysqli->connect_errno) {
        die("Fallo la conexion");
    } else {
        // echo "Conexion exitosa";
    }

?>