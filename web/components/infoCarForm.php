<?php 
include_once "conexion.php";
$conexion = mysqli_connect($host, $user, $password, $db);
if ($conexion != true) {
    die("Error de conexion " . mysqli_connect_error());
}
$sql = "SELECT * FROM vehiculo WHERE id_vehiculo = ".$_GET['id'];
$resultSet = mysqli_query($conexion, $sql);
while ($row = mysqli_fetch_row($resultSet)) {
?>
<div class="content--section">
    <h1 class="dashboard-heading">Datos auto</h1>
    <form action="" method="POST" enctype="multipart/form-data">
        <img src="assets/img/autos/<?php echo $row[5]; ?>" alt="" style="width:50%; margin: 0 auto; text-align: center;">
        <div class="group">
            <label for="modelo" class="form--label">Modelo</label>
            <input type="text" name="modelo" id="modelo" class="control" placeholder="Modelo del auto" value="<?php echo $row[3]; ?>" disabled>
        </div>
        <div class="group">
            <label for="precio" class="form--label">Precio</label>
            <input type="number" name="precio" id="precio" class="control" placeholder="Precio del auto" value="<?php echo $row[7]; ?>" disabled>
        </div>
        <div class="group">
            <label for="transmision" class="form--label">Transmision</label>
            <input type="text" name="transmision" id="transmision" class="control" placeholder="Tipo de transmision" value="<?php echo $row[8]; ?>" disabled>
        </div>
        <div class="group">
            <label for="placas" class="form--label">Placas</label>
            <input type="text" name="placas" id="placas" class="control" placeholder="Placas del auto" value="<?php echo $row[2]; ?>" disabled>
        </div>
        <div class="group">
            <label for="mLabel" class="form--label" id="label"></label>
            <input type="file" name="image" id="mLabel" class="myImage" onchange="imageName();" disabled>
            <div class="error">
                <?php if($uploadImages->imageErrors['image']): ?>
                <?php echo $uploadImages->imageErrors['image']; ?>
                <?php endif; ?>
            </div>
        </div>
        <div class="group">
            <input type="hidden" name="updateCar" id="updateCar" class="btn btn-sweet" value="Actualizar auto">
            <input type="hidden" name="desactivarCar" id="desactivarCar" class="btn btn-sweet" value="Desactivar auto">
            <input type="button" name="activateCar" id="activateCar" class="btn btn-sweet" value="Editar auto" onclick="editar()">
        </div>
    </form>
</div>
<?php } ?>