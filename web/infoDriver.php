<?php include "init.php"; 
    $dbQueries = new dbQueries;
    $validations = new validations;
    if(isset($_POST['updateDriver'])){
        $validations->validate("correo", "Correo", "required");
        $validations->validate("nombre", "Nombre", "required");
        $validations->validate("apPaterno", "Apellido Paterno", "required");
        $validations->validate("apMaterno", "Apellido Materno", "required");
        $validations->validate("telefono", "Telefono", "required");
        $validations->validate("licencia", "Licencia", "required");
        if($validations->run()){
            $correo = $validations->input("correo");
            $nombre = $validations->input("nombre");
            $apPaterno = $validations->input("apPaterno");
            $apMaterno = $validations->input("apMaterno");
            $telefono = $validations->input("telefono");
            $licencia = $validations->input("licencia");
            $id = $_GET['id'];
            if($dbQueries->Query("UPDATE usuario SET correo = ?, nombres = ?, apellido_paterno = ?, apellido_materno = ?, telefono = ?, licencia = ?, status = ? WHERE id_Usuario = ?",
                                [$correo, $nombre, $apPaterno, $apMaterno, $telefono, $licencia, 1, $id])){
                                    header("location: listaDrivers.php");
            }
        }   

    }
    if(isset($_POST['desactivarDriver'])){
        $validations->validate("correo", "Correo", "required");
        $validations->validate("nombre", "Nombre", "required");
        $validations->validate("apPaterno", "Apellido Paterno", "required");
        $validations->validate("apMaterno", "Apellido Materno", "required");
        $validations->validate("telefono", "Telefono", "required");
        $validations->validate("licencia", "Licencia", "required");
        if($validations->run()){
            $correo = $validations->input("correo");
            $nombre = $validations->input("nombre");
            $apPaterno = $validations->input("apPaterno");
            $apMaterno = $validations->input("apMaterno");
            $telefono = $validations->input("telefono");
            $licencia = $validations->input("licencia");
            $id = $_GET['id'];
            if($dbQueries->Query("UPDATE usuario SET correo = ?, nombres = ?, apellido_paterno = ?, apellido_materno = ?, telefono = ?, licencia = ?, status = ? WHERE id_usuario = ?",
                                [$correo, $nombre, $apPaterno, $apMaterno, $telefono, $licencia, 0, $id])){
                                    header("location: listaDrivers.php");
            }
        }   

    }
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <?php include "components/header.php"; ?>
    <title>Licencia</title>
</head>
<body>
    <?php include "components/nav.php"; ?>
    <div class="container">
        <div class="dashboard">
            <?php include "components/card.php"; ?>
            <div class="content">
                <?php include "components/infoDriverForm.php"; ?>
            </div>
        </div>
    </div>
    <script>
        var btnEnviar = document.getElementById("updateDriver");
        var btnDesactivar = document.getElementById("desactivarDriver");
        var active = document.getElementById("activateDriver");
        var caja1 = document.getElementById("correo");
        var caja2 = document.getElementById("nombre");
        var caja3 = document.getElementById("apPaterno");
        var caja4 = document.getElementById("apMaterno");
        var caja5 = document.getElementById("telefono");
        var caja6 = document.getElementById("licencia");

        function editar() {
            caja1.disabled = false;
            caja2.disabled = false;
            caja3.disabled = false;
            caja4.disabled = false;
            caja5.disabled = false;
            caja6.disabled = false;
            btnEnviar.type = "submit";
            btnDesactivar.type = "submit";
            active.type = "hidden";
        }

        function imageName(){
            let image = document.getElementById("mLabel").value;
            let imageName = image.split("\\");
            let index = imageName.length - 1;
            let label = document.getElementById("label");
            label.innerText = imageName[index];
        }
    </script>
</body>
</html>