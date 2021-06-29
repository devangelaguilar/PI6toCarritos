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
            $sql = "SELECT * FROM vehiculo";
            $resultSet = mysqli_query($conexion, $sql);
            while ($row = mysqli_fetch_row($resultSet)) {?>
                <div class="carCard">
                    <div class="cars--image">
                        <img src="assets/img/autos/<?php echo $row[5]; ?>" alt="">
                    </div>
                    <div class="cars--modelo">
                        <p>Modelo: <?php echo $row[3]; ?></p>
                    </div>
                    <div class="cars--precio">
                        <p>Precio: $<?php echo $row[7]; ?> p/d.</p>
                    </div>
                    <div class="cars--transmision">
                        <p>Transmision: <?php echo $row[8]; ?></p>
                    </div>
                    <?php if($row[6] == "1"): ?>
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
                        <a href="infoCar.php?id=<?php echo $row[0]; ?>" class="btn btn-card">Info <span class="icon">&rarr;</span></a>
                    </div>
                </div>
            <?php } ?>
    </div>
</div>