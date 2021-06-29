<?php include "init.php"; ?>
<?php 
$validations = new validations;
$dbQueries = new dbQueries;
if(isset($_POST['newPasswordBtn'])){
    $validations->validate("nuevaContraseña", "Nueva contraseña", "required|min_len|8");
    $validations->validate("confirmaContraseña", "Confirmar contraseña", "required");
    if($validations->run()){
        $newPassword = $validations->input("nuevaContraseña");
        $confirmPassword = $validations->input("confirmaContraseña");
        if($newPassword !== $confirmPassword){
            $validations->errors['confirmaContraseña'] = "Por favor confirma tu contraseña";
        }
        else{
            $hashPassword = password_hash($newPassword, PASSWORD_BCRYPT);
            $userId = $_SESSION['requestUserId'];
            if($dbQueries->Query("UPDATE usuario SET password = ? WHERE id_Usuario = ?", [$hashPassword,$userId])){
                $_SESSION['passwordUpdate'] = "Tu contraseña ha sido cambiada satisfactoriamente. <a href='login.php'>Iniciar sesion</a>";
                header("location: message.php");
            }
        }
    }
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <?php include "components/header.php"; ?>
    <title>New Password</title>
</head>
<body>
    <?php include "components/nav.php"; ?>
    <section id="form">
        <?php if(isset($_SESSION['requestUserId'])): ?>
        <?php include "components/newPasswordForm.php"; ?>
        <?php else: $_SESSION["accessForbidden"] = "404 amiko, 404"; header("location: message.php"); ?>
        <?php endif; ?>
    </section>
</body>
</html>