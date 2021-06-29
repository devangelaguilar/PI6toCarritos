<?php
include "init.php";
$dbQueries = new dbQueries;

if(isset($_GET['confirmation'])){
    $confirmationCode = $_GET['confirmation'];
    if($dbQueries->Query("SELECT * FROM email_confirmation WHERE confirmationCode = ?", [$confirmationCode])){
        if($dbQueries->rowCount() > 0 ){
            $row = $dbQueries->fetch();
            $userEmail = $row->correo;
            if($dbQueries->Query("SELECT * FROM usuario WHERE correo = ?", [$userEmail])){
                $userRow = $dbQueries->fetch();
                $userId = $userRow->id_Usuario;
                $status = 1;
                if($dbQueries->Query("UPDATE usuario SET status = ? WHERE id_Usuario = ?", [$status, $userId])){
                    if($dbQueries->Query("DELETE FROM email_confirmation WHERE confirmationCode = ?", [$confirmationCode])){
                        $_SESSION['userId'] = $userId;
                        $_SESSION['verify'] = "Tu cuenta ha sido verificada.";
                        header("location: message.php");
                    }
                }
            }
        }
        else{
            $_SESSION['invalidURL'] = "Invalid URL, please check your confirmation URL";
            header("location: message.php");
        }
    }
}


?>