<div class="form-section">
    <div class="form--header">
        <h2>Olvidaste tu contraseña?</h2>
        <p>Ingresa tu direccion de correo electronico</p>
    </div>
    <div class="form--body">
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
                <input type="submit" name="requestPassword" class="btn btn-sweet" value="Enviar link">
            </div>
        </form>
    </div>
</div>