<?php

class dash extends dbQueries {

   
    public function settings(){
        
        $userId = $_SESSION['userId'];
        if($this->Query("SELECT * FROM usuario WHERE id_Usuario = ?", [$userId])){

            $row = $this->fetch();
            //$image = $row->image;
            $telefono  = $row->telefono;
            $licencia = $row->licencia;
            $direccion = $row->direccion;

            /*if(empty($image)){
                echo '<span class="btn-section"><a href="uploadImage.php" class="btn-white">Add Picture <span class="icon">&#43;</span></a></span>';
            } else {
                echo '<span class="btn-section"><a href="uploadImage.php" class="btn-white">Update Picture <span class="icon">&rarr;</span></a></span>';
            }

            if(empty($telefono)){
                echo '<span class="btn-section"><a href="addTelefono.php" class="btn-white">Añadir telefono <span class="icon">&#43;</span></a></span>';
            } else {
                echo '<span class="btn-section"><a href="addTelefono.php" class="btn-white">Modificar telefono <span class="icon">&rarr;</span></a></span>';
            }

            if(empty($licencia)){
                echo '<span class="btn-section"><a href="addLicencia.php" class="btn-white">Añadir licencia <span class="icon">&#43;</span></a></span>';
            } else {
                echo '<span class="btn-section"><a href="addLicencia.php" class="btn-white">Modificar licencia <span class="icon">&rarr;</span></a></span>';
            }

            if(empty($direccion)){
                echo '<span class="btn-section"><a href="addDireccion.php" class="btn-white">Añadir direccion <span class="icon">&#43;</span></a></span>';
            } else {
                echo '<span class="btn-section"><a href="addDireccion.php" class="btn-white">Modificar direccion <span class="icon">&rarr;</span></a></span>';
            }

            echo '<span class="btn-section"><a href="updateName.php" class="btn-white">Modificar nombre <span class="icon">&rarr;</span></a></span>';

            echo '<span class="btn-section"><a href="updatePassword.php" class="btn-white">Modificar contraseña <span class="icon">&rarr;</span></a></span>'; */

            echo '<span class="btn-section"><a href="addDriver.php" class="btn-white">Añadir driver <span class="icon">&rarr;</span></a></span>';

            echo '<span class="btn-section"><a href="addCar.php" class="btn-white">Añadir auto <span class="icon">&rarr;</span></a></span>';

            echo '<span class="btn-section"><a href="listaDrivers.php" class="btn-white">Ver drivers<span class="icon">&rarr;</span></a></span>';
        }

    }
}