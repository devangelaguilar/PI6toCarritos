<?php include "init.php";
$validations = new validations;
$dbQueries = new dbQueries;
$email = new email;
if(isset($_POST['requestPassword'])){
    $validations->validate("correo", "Correo", "required");
    if($validations->run()){
        $correo = $validations->input("correo");
        if($dbQueries->Query("SELECT * FROM usuario WHERE correo = ?", [$correo])){
            if($dbQueries->rowCount() > 0){
                $row = $dbQueries->fetch();
                $userId = $row->id_Usuario;
                $name = $row->nombres;
                $code = password_hash(rand(), PASSWORD_DEFAULT);
                $url = "http://". $_SERVER['SERVER_NAME'] ."/profile/forgotPassword.php?forgot=".$code; 
                if($dbQueries->Query("INSERT INTO forgotPassword(userId, code) VALUES (?,?)", [$userId, $code])){
                    if($email->sendEmail($name, $correo, $url, "FORGOT", "Forgot Password")){
                        $_SESSION['forgotRequest'] = "Por favor, revisa tu email hemos enviado el link para cambiar tu contraseña";
                        header("location: message.php");
                    }
                }
            }
            else{
                $validations->errors['correo'] = "Tu email no esta registrado.";
            }
        }
    }
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <?php include "components/header.php"; ?>
    <title>Forgot password</title>
</head>
<body>
    <?php include "components/nav.php"; ?>
    <section id="form">
        <?php include "components/requestPasswordForm.php"; ?>
    </section>
</body>
</html>