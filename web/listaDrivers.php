<?php include "init.php"; $dash = new dash;?>
<!DOCTYPE html>
<html lang="en">
<head>
    <?php include "components/header.php"; ?>
    <title>Dashboard</title>
</head>
<body>
    <?php include "components/nav.php"; ?>
    <div class="container">
        <div class="dashboard">
            <?php include "components/card.php"; ?>
            <div class="content">
                <?php include "components/contentDrivers.php"; ?>
            </div>
        </div>
    </div>
</body>
</html>