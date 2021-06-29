<?php 
include "init.php";
$dbQueries = new dbQueries;
error_reporting(E_ALL);
?>
<div class="content--section">
    <h1 class="dashboard-heading">Dashboard</h1>
    <div>
        <?php $dash->settings(); ?>
        <?php
            include_once "conexion.php";
            $conexion = mysqli_connect($host, $user, $password, $db);
            if ($conexion != true) {
                die("Error de conexion " . mysqli_connect_error());
            }
            $sql = "SELECT * FROM usuario WHERE clase_usuario = 1";
            $resultSet = mysqli_query($conexion, $sql);
            while ($row = mysqli_fetch_row($resultSet)) {?>
                <div class="userCard">
                    <div class="user--image">
                        <img src="assets/img/user.png" alt="">
                    </div>
                    <div class="cars--modelo">
                        <p>Correo: <?php echo $row[2]; ?></p>
                    </div>
                    <div class="cars--precio">
                        <p>Nombre: <?php echo $row[4]." ".$row[5]." ".$row[6]; ?> </p>
                    </div>
                    <div class="cars--transmision">
                        <p>Telefono: <?php echo $row[7]; ?></p>
                    </div>
                    <div class="user--licencia">
                        <p>Licencia: <?php echo $row[10]; ?></p>
                    </div>
                    <?php if($row[11] == "1"): ?>
                        <div class="cars-estado">
                            <p style="color: green;">Status: Activo</p>
                        </div> 
                    <?php else: ?>
                        <div class="cars-estado">
                            <p style="color: red;">Status: Fuera de servicio</p>
                        </div>
                    <?php endif; ?>
                    <!--<div class="card--skills">
                        <span class="skill">PHP</span>
                        <span class="skill">JS</span>
                        <span class="skill">NodeJS</span>
                        <span class="skill">Flutter</span>
                        <span class="skill">Android</span>
                    </div>
                    <div class="card--intro">
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
                    </div>-->
                    <div class="card--setting">
                        <a href="infoDriver.php?id=<?php echo $row[0]; ?>" class="btn btn-card">Info <span class="icon">&rarr;</span></a>
                    </div>
                </div>
            <?php } ?>
    </div>
</div>