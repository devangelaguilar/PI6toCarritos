<?php 
include "init.php";
$validations = new validations;
$dbQueries = new dbQueries;
$emailObj = new email;
if(isset($_POST['signup'])){
    $validations->validate("fullName", "Nombres", "required|alphabetic");
    $validations->validate("apPaterno", "Apellido paterno", "required|alphabetic");
    $validations->validate("apMaterno", "Apellido materno", "required|alphabetic");
    $validations->validate("telefono", "Telefono", "required|min_len|10");
    $validations->validate("fecNac", "Fecha de nacimiento", "required");
    $validations->validate("licencia", "Licencia", "required|min_len|10");
    $validations->validate("correo", "Correo", "required|uniqueEmail|usuario");
    $validations->validate("contraseña", "Contraseña", "required|min_len|8");
    if($validations->run()){
        $nombres = $validations->input("fullName");
        $apPaterno = $validations->input("apPaterno");
        $apMaterno = $validations->input("apMaterno");
        $telefono = $validations->input("telefono");
        $fecNac = $validations->input("fecNac");
        $licencia = $validations->input("licencia");
        $correo = $validations->input("correo");
        $contraseña = $validations->input("contraseña");
        $contraseña = password_hash($contraseña, PASSWORD_BCRYPT);
        $claseUsuario = 0;
        $direccion = 'Proceso aún';
        $status = 1;
        $code = password_hash(time(), PASSWORD_DEFAULT);
        $url = "http://" . $_SERVER['SERVER_NAME'] ."/profile/verify.php?confirmation=". $code;
        if($dbQueries->Query("INSERT INTO usuario(clase_usuario, correo, password, nombres, apellido_paterno, apellido_materno, telefono, direccion, fecha_de_nacimiento, licencia, status) VALUES (?,?,?,?,?,?,?,?,?,?,?)", [$claseUsuario, $correo, $contraseña, $nombres, $apPaterno, $apMaterno, $telefono, $direccion, $fecNac, $licencia, $status])){
            /*if($dbQueries->Query("INSERT INTO email_confirmation(correo, confirmationCode) VALUES (?,?)", [$correo, $code])){
                if($emailObj->sendEmail($nombres, $correo, $url, "CONFIRM", "Confirma tu cuenta por favor.")){
                    $_SESSION['accountCreated'] = "Tu cuenta ha sido creada satisfactoriamente, revisa tu correo por favor.";
                    header("location: message.php");
                }
            }*/
            header("location: login.php");
       }
       else{
           echo "Error";
       }
    }
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <?php include "components/header.php"; ?>
    <title>Registro</title>
</head>
<body>
    <?php include "components/nav.php"; ?>
    <section id="form">
        <?php include "components/signupForm.php"; ?>
    </section>
</body>
</html>