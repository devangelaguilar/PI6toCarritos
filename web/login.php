<?php include "init.php";  ?>
<?php if(isset($_SESSION['userId'])): ?>
<?php header("location: dashboard.php"); ?>
<?php endif; ?>
<?php 
$validations = new validations;
$dbQueries = new dbQueries;
if(isset($_POST['loginBtn'])){
    $validations->validate("correo", "Correo", "required");
    $validations->validate("contraseña", "Contraseña", "required");
    if($validations->run()){
        $correo = $validations->input("correo");
        $contraseña = $validations->input("contraseña");
        if($dbQueries->Query("SELECT * FROM usuario WHERE correo = ?", [$correo])){
            if($dbQueries->rowCount() > 0){
                $row = $dbQueries->fetch();
                $name = $row->nombres;
                $id = $row->id_Usuario;
                $dbPassword = $row->password;
                $tipoUsuario = $row->clase_usuario;
                $status = $row->status;
                if($status == 0){
                    $_SESSION["notVerified"] = "Por favor, verifica tu cuenta.";
                    header("location: message.php");
                }
                else{
                    if(password_verify($contraseña, $dbPassword)){
                        if($tipoUsuario == 0){
                            $_SESSION['userId'] = $id;
                            $_SESSION['name'] = $name;
                            header("location: dashboard.php");
                        }
                        else{
                            $validations->errors['contraseña'] = "Esta cuenta no es de administrador, favor de entrar desde la app movil";    
                        }
                    }
                    else{
                        $validations->errors['contraseña'] = "Por favor, verifica los datos ingresados";
                    }
                }
            }
            else{
                $validations->errors['correo'] = "No existe una cuenta con tal correo.";
            }
        }
    }
}

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <?php include "components/header.php"; ?>
    <title>Login</title>
</head>

<body>
    <?php include "components/nav.php"; ?>
    <section id="form">
        <?php include "components/loginForm.php"; ?>
    </section>
</body>

</html>