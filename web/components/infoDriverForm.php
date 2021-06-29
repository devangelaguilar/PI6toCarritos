<?php 
include_once "conexion.php";
$conexion = mysqli_connect($host, $user, $password, $db);
if ($conexion != true) {
    die("Error de conexion " . mysqli_connect_error());
}
$sql = "SELECT * FROM usuario WHERE id_usuario = ".$_GET['id'];
$resultSet = mysqli_query($conexion, $sql);
while ($row = mysqli_fetch_row($resultSet)) {
?>
<div class="content--section">
    <h1 class="dashboard-heading">Datos auto</h1>
    <form action="" method="POST">
        <div class="group">
            <label for="correo" class="form--label">Correo</label>
            <input type="text" name="correo" id="correo" class="control" placeholder="Correo" value="<?php echo $row[2]; ?>" disabled>
        </div>
        <div class="group">
            <label for="nombre" class="form--label">Nombre</label>
            <input type="text" name="nombre" id="nombre" class="control" placeholder="Nombre" value="<?php echo $row[4]; ?>" disabled>
        </div>
        <div class="group">
            <label for="apPaterno" class="form--label">Apellido Paterno</label>
            <input type="text" name="apPaterno" id="apPaterno" class="control" placeholder="Apellido Paterno" value="<?php echo $row[5]; ?>" disabled>
        </div>
        <div class="group">
            <label for="apMaterno" class="form--label">Apellido Materno</label>
            <input type="text" name="apMaterno" id="apMaterno" class="control" placeholder="Apellido Materno" value="<?php echo $row[6]; ?>" disabled>
        </div>
        <div class="group">
            <label for="telefono" class="form--label">Telefono</label>
            <input type="number" name="telefono" id="telefono" class="control" placeholder="Telefono" value="<?php echo $row[7]; ?>" disabled>
        </div>
        <div class="group">
            <label for="licencia" class="form--label">Licencia</label>
            <input type="text" name="licencia" id="licencia" class="control" placeholder="Licencia" value="<?php echo $row[10]; ?>" disabled>
        </div>
        <div class="group">
            <input type="hidden" name="updateDriver" id="updateDriver" class="btn btn-sweet" value="Actualizar driver">
            <input type="hidden" name="desactivarDriver" id="desactivarDriver" class="btn btn-sweet" value="Desactivar driver">
            <input type="button" name="activateDriver" id="activateDriver" class="btn btn-sweet" value="Editar driver" onclick="editar()">
        </div>
    </form>
</div>
<?php } ?>