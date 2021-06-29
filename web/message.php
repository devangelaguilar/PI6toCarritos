<?php include "init.php"; ?>
<?php 
    $messages = new messages;
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <?php include "components/header.php"; ?>
    <title>Registro</title>
</head>
<body class="messageBg">
    <?php include "components/nav.php"; ?>
    <div class="messageBox">
        <div class="messageBoxContainer">
            <?php $messages->showMessage("accountCreated", "success"); ?>
            <?php $messages->showMessage("InvalidURL", "error"); ?>
            <?php $messages->showMessage("verify", "verify"); ?>
            <?php $messages->showMessage("notVerified", "error"); ?>
            <?php $messages->showMessage("forgotRequest", "success"); ?>
            <?php $messages->showMessage("forgotWrongURL", "error"); ?>
            <?php $messages->showMessage("accessForbidden", "error"); ?>
            <?php unset($_SESSION['requestUserId']); ?>
            <?php $messages->showMessage("passwordUpdate", "success"); ?>
        </div>
    </div>
    <script>
        let verify = document.querySelector(".verify-box");
        setTimeout(() => {
            verify.classList.add("showVerify");
        }, 3000);
    </script>
</body>
</html>