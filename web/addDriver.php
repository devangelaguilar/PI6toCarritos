<?php include "init.php";
    $dash = new dash;
    error_reporting(E_ALL);
    $dbQueries = new dbQueries;
    $validations = new validations;
    if(isset($_POST['añadirDriver'])){
        $validations->validate("correo", "Correo", "required");
        $validations->validate("contraseña", "Contraseña", "required|min_len|8");
        $validations->validate("nombre", "Nombre", "required");
        $validations->validate("fecNac", "Fecha de nacimiento", "required");
        $validations->validate("apPaterno", "Apellido Paterno", "required");
        $validations->validate("apMaterno", "Apellido Materno", "required");
        $validations->validate("telefono", "Telefono", "required|min_len|10");
        $validations->validate("licencia", "Licencia", "required");
        if($validations->run()){
            $correo = $validations->input("correo");
            $contraseña = $validations->input("contraseña");
            $contraseña = password_hash($contraseña, PASSWORD_BCRYPT);
            $claseUsuario = 1;
            $direccion = 'Proceso aún';
            $status = 1;
            $nombre = $validations->input("nombre");
            $fecNac = $validations->input("fecNac");
            $apPaterno = $validations->input("apPaterno");
            $apMaterno = $validations->input("apMaterno");
            $telefono = $validations->input("telefono");
            $licencia = $validations->input("licencia");
            if($dbQueries->Query("INSERT INTO usuario(clase_usuario, correo, password, nombres, apellido_paterno, apellido_materno, telefono, direccion, fecha_de_nacimiento, licencia, status) VALUES (?,?,?,?,?,?,?,?,?,?,?)", [$claseUsuario, $correo, $contraseña, $nombre, $apPaterno, $apMaterno, $telefono, $direccion, $fecNac, $licencia, $status])){
                header("location: listaDrivers.php");
            }
        } 
    }  
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <?php include "components/header.php"; ?>
    <title>Upload photo</title>
</head>
<body>
    <?php include "components/nav.php"; ?>
    <div class="container">
        <div class="dashboard">
            <?php include "components/card.php"; ?>
            <div class="content">
                <?php include "components/addDriverForm.php"; ?>
            </div>
        </div>
    </div>
</body>
</html>