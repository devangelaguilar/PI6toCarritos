<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<?php 
error_reporting(E_ALL);
?>
<div class="content--section">
    <h1 class="dashboard-heading">Dashboard</h1>
    <div>
        
        <?php
            include_once "conexion.php";
            $conexion = mysqli_connect($host, $user, $password, $db);
            if ($conexion != true) {
                die("Error de conexion " . mysqli_connect_error());
            }
            $sql = "SELECT * FROM vehiculo";
            $resultSet = mysqli_query($conexion, $sql);
            while ($row = mysqli_fetch_row($resultSet)) {
                ?>
                <div class="carCard">
                    <div class="car--image">
                        <img src="assets/images/logo_aa.png" alt="">
                    </div>
                    <div class="car--modelo">
                        <p><?php echo $row[0]; ?></p>
                    </div>
                    <div class="car--precio">
                        <p><?php echo $row[2]; ?></p>
                    </div>
                    <div class="car--transmision">
                        <p><?php echo $row[3]; ?></p>
                    </div>
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
                        <a href="" class="btn btn-card">Settings <span class="icon">&rarr;</span></a>
                    </div>
                </div>
            <?php
            }
            ?>
    </div>
</div>
</body>
</html>