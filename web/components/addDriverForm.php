<div class="content--section">
    <h1 class="dashboard-heading">Añadir Driver</h1>
    <form action="" method="POST">
        <div class="group">
            <label for="correo" class="form--label">Correo</label>
            <input type="email" name="correo" id="correo" class="<?php if($validations->errors['correo']): echo 'borderRed'; endif; ?> control" placeholder="Correo" value="<?php if($validations->setValue('correo')): echo $validations->setValue('correo'); endif; ?>">
            <div class="error">
                <?php if($validations->errors['correo']): ?>
                <?php echo $validations->errors['correo']; ?>
                <?php endif; ?>
            </div>
        </div>
        <div class="group">
            <label for="contraseña" class="form--label">Contraseña</label>
            <input type="password" name="contraseña" id="contraseña" class="<?php if($validations->errors['contraseña']): echo 'borderRed'; endif; ?> control" placeholder="Contraseña" value="<?php if($validations->setValue('contraseña')): echo $validations->setValue('contraseña'); endif; ?>">
            <div class="error">
                <?php if($validations->errors['contraseña']): ?>
                <?php echo $validations->errors['contraseña']; ?>
                <?php endif; ?>
            </div>
        </div>
        <div class="group">
            <label for="nombre" class="form--label">Nombre</label>
            <input type="text" name="nombre" id="nombre" class="<?php if($validations->errors['nombre']): echo 'borderRed'; endif; ?> control" placeholder="Nombres" value="<?php if($validations->setValue('nombre')): echo $validations->setValue('nombre'); endif; ?>">
            <div class="error">
                <?php if($validations->errors['nombre']): ?>
                <?php echo $validations->errors['nombre']; ?>
                <?php endif; ?>
            </div>
        </div>
        <div class="group">
            <label for="apPaterno" class="form--label">Apellido Paterno</label>
            <input type="text" name="apPaterno" id="apPaterno" class="<?php if($validations->errors['apPaterno']): echo 'borderRed'; endif; ?> control" placeholder="Apellido Paterno" value="<?php if($validations->setValue('apPaterno')): echo $validations->setValue('apPaterno'); endif; ?>">
            <div class="error">
                <?php if($validations->errors['apPaterno']): ?>
                <?php echo $validations->errors['apPaterno']; ?>
                <?php endif; ?>
            </div>
        </div>
        <div class="group">
            <label for="apMaterno" class="form--label">Apellido Materno</label>
            <input type="text" name="apMaterno" id="apMaterno" class="<?php if($validations->errors['apMaterno']): echo 'borderRed'; endif; ?> control" placeholder="Apellido Materno" value="<?php if($validations->setValue('apMaterno')): echo $validations->setValue('apMaterno'); endif; ?>">
            <div class="error">
                <?php if($validations->errors['apMaterno']): ?>
                <?php echo $validations->errors['apMaterno']; ?>
                <?php endif; ?>
            </div>
        </div>
        <div class="group">
            <label for="fecNac" class="form--label">Fecha de nacimiento</label>
            <input type="date" name="fecNac" id="fecNac" class="<?php if($validations->errors['fecNac']): echo 'borderRed'; endif; ?> control" placeholder="Fecha de Nacimiento" value="<?php if($validations->setValue('fecNac')): echo $validations->setValue('fecNac'); endif; ?>">
            <div class="error">
                <?php if($validations->errors['fecNac']): ?>
                <?php echo $validations->errors['fecNac']; ?>
                <?php endif; ?>
            </div>
        </div>
        <div class="group">
            <label for="telefono" class="form--label">Telefono</label>
            <input type="number" name="telefono" id="telefono" max="9999999999" class="<?php if($validations->errors['telefono']): echo 'borderRed'; endif; ?> control" placeholder="Telefono" value="<?php if($validations->setValue('telefono')): echo $validations->setValue('telefono'); endif; ?>">
            <div class="error">
                <?php if($validations->errors['telefono']): ?>
                <?php echo $validations->errors['telefono']; ?>
                <?php endif; ?>
            </div>
        </div>
        <div class="group">
            <label for="licencia" class="form--label">Licencia</label>
            <input type="text" name="licencia" id="licencia" maxlength="10" class="<?php if($validations->errors['licencia']): echo 'borderRed'; endif; ?> control" placeholder="Licencia" value="<?php if($validations->setValue('licencia')): echo $validations->setValue('licencia'); endif; ?>">
            <div class="error">
                <?php if($validations->errors['licencia']): ?>
                <?php echo $validations->errors['licencia']; ?>
                <?php endif; ?>
            </div>
        </div>
        <div class="group">
            <input type="submit" name="añadirDriver" id="añadirDriver" class="btn btn-sweet" value="Añadir driver">
        </div>
    </form>
</div>