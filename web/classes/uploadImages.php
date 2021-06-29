<?php

class uploadImages extends dbQueries {
    public $imageErrors = [];
    public function uploadImg($fieldName, $extensions, $id){
        $imageName = $_FILES[$fieldName]['name'];
        $tmpName = $_FILES[$fieldName]['tmp_name'];
        $storage = "assets/img/autos/";
        $imgExtension = pathinfo($imageName, PATHINFO_EXTENSION);
        if(empty($imageName)){
            return $this->imageErrors[$fieldName] = "Debes subir la imagen.";
        }
        if(!in_array($imgExtension, $extensions)){
            return $this->imageErrors[$fieldName] = $imgExtension . " no es un archivo valido.";
        }
        $imgName = pathinfo($imageName, PATHINFO_FILENAME);
        $imgName = preg_replace("/\s+/", "-", $imgName);
        $imgName = $id;
        $imgName = $imgName.".".$imgExtension;
        move_uploaded_file($tmpName, $storage.$imgName);

        if($this->Query("UPDATE vehiculo SET foto = ? WHERE id_vehiculo = ?", [$imgName, $id])){
            $_SESSION['imageUploaded'] = "Tu imagen ha sido guardada";
            header("location: dashboard.php");
        }
    }
}