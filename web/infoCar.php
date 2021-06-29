<?php include "init.php"; 
    $uploadImages = new uploadImages;
    $dbQueries = new dbQueries;
    $validations = new validations;
    if(isset($_POST['updateCar'])){
        $validations->validate("modelo", "Modelo", "required");
        $validations->validate("precio", "Precio", "required");
        $validations->validate("transmision", "Transmision", "required");
        $validations->validate("placas", "placas", "required");
        if($validations->run()){
            $modelo = $validations->input("modelo");
            $precio = $validations->input("precio");
            $tranmision = $validations->input("transmision");
            $placas = $validations->input("placas");
            $id = $_GET['id'];
            if($dbQueries->Query("UPDATE vehiculo SET tipo_vehiculo = ?, placas = ?, modelo = ?, color = ?, status = ?, precio = ?, transmision = ? WHERE id_vehiculo = ?",
                                [1, $placas, $modelo, 1, 1, $precio, $tranmision, $id])){
                                    header("location: dashboard.php");
            }
        }   
        $extensions = ['jpg', 'jpeg', 'png'];
        $uploadImages->uploadImg('image', $extensions, $id);
    }
    if(isset($_POST['desactivarCar'])){
        $validations->validate("modelo", "Modelo", "required");
        $validations->validate("precio", "Precio", "required");
        $validations->validate("transmision", "Transmision", "required");
        $validations->validate("placas", "placas", "required");
        if($validations->run()){
            $modelo = $validations->input("modelo");
            $precio = $validations->input("precio");
            $tranmision = $validations->input("transmision");
            $placas = $validations->input("placas");
            $id = $_GET['id'];
            if($dbQueries->Query("UPDATE vehiculo SET tipo_vehiculo = ?, placas = ?, modelo = ?, color = ?, status = ?, precio = ?, transmision = ? WHERE id_vehiculo = ?",
                                [1, $placas, $modelo, 1, 0, $precio, $tranmision, $id])){
                                    header("location: dashboard.php");
            }
        }   
        $extensions = ['jpg', 'jpeg', 'png'];
        $uploadImages->uploadImg('image', $extensions, $id);
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
                <?php include "components/infoCarForm.php"; ?>
            </div>
        </div>
    </div>
    <script>
        var btnEnviar = document.getElementById("updateCar");
        var btnDesactivar = document.getElementById("desactivarCar");
        var active = document.getElementById("activateCar");
        var caja1 = document.getElementById("modelo");
        var caja2 = document.getElementById("precio");
        var caja3 = document.getElementById("transmision");
        var caja4 = document.getElementById("placas");
        var caja5 = document.getElementById("mLabel");

        function editar() {
            caja1.disabled = false;
            caja2.disabled = false;
            caja3.disabled = false;
            caja4.disabled = false;
            caja5.disabled = false;
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