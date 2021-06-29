<!DOCTYPE html>
<html lang="en">
<head>
    <?php include "components/header.php"; ?>
    <title>Upload photo</title>
</head>
<body>
    <?php include "components/nav.php"; ?>
    <div class="container">
        <div class="dashboard">
            <?php include "components/card.php"; ?>
            <div class="content">
                <?php include "components/uploadImageForm.php"; ?>
            </div>
        </div>
    </div>
    <script>
        function imageName(){
            let image = document.getElementById("mLabel").value;
            let imageName = image.split("\\");
            let index = imageName.length - 1;
            let label = document.getElementById("label");
            label.innerText = imageName[index];
        }
    </script>
</body>
</html>