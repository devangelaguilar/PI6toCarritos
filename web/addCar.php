<?php include "init.php";
    $dash = new dash;
    $uploadImages = new uploadImages;
    $dbQueries = new dbQueries;
    $validations = new validations;
    if(isset($_POST['uploadCar'])){
        $validations->validate("modelo", "Modelo", "required");
        $validations->validate("precio", "Precio", "required");
        $validations->validate("transmision", "Transmision", "required");
        $validations->validate("placas", "placas", "required");
        if($validations->run()){
            $modelo = $validations->input("modelo");
            $precio = $validations->input("precio");
            $tranmision = $validations->input("transmision");
            $placas = $validations->input("placas");
            $id = rand(8,100);
            if($dbQueries->Query("INSERT INTO vehiculo(id_vehiculo, tipo_vehiculo, placas, modelo, color, status, precio, transmision) VALUES (?,?,?,?,?,?,?,?)",
                                [$id, 1, $placas, $modelo, 1, 1, $precio, $tranmision])){
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
    <title>Upload photo</title>
</head>
<body>
    <?php include "components/nav.php"; ?>
    <div class="container">
        <div class="dashboard">
            <?php include "components/card.php"; ?>
            <div class="content">
                <?php include "components/addCarForm.php"; ?>
            </div>
        </div>
    </div>
    <script>
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