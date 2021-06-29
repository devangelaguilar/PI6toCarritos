<?php
include "init.php";
$dbQueries = new dbQueries;
if(isset($_GET['forgot'])){
    $forgotCode = $_GET['forgot'];
    if($dbQueries->Query("SELECT * FROM forgotpassword WHERE code = ?", [$forgotCode])){
        if($dbQueries->rowCount() > 0){
            $row = $dbQueries->fetch();
            $userId = $row->userId;
            $_SESSION['requestUserId'] = $userId;
            if($dbQueries->Query("DELETE FROM forgotpassword WHERE code = ?", [$forgotCode])){
                header("location: newPassword.php");
            }
        }
        else{
            $_SESSION['forgotWrongURL'] = "Error en tu solicitud.";
            header("location: message.php");
        }
    }
}
?>