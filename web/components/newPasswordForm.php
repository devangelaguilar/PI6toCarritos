<div class="form-section">
    <div class="form--header">
        <h2>Ingresa tu nueva contraseña</h2>
    </div>
    <div class="form-body">
        <form action="" method="POST">
            <div class="group">
                <label for="nuevaContraseña" class="form--label">Ingresa tu nueva contraseña</label>
                <input type="password" name="nuevaContraseña" id="nuevaContraseña" class="control" placeholder="Nueva contraseña">
                <div class="error">
                    <?php if($validations->errors['nuevaContraseña']): ?>
                    <?php echo $validations->errors['nuevaContraseña']; ?>
                    <?php endif; ?>
                </div>
            </div>
            <div class="group">
                <label for="confirmaContraseña" class="form--label">Confirma tu contraseña</label>
                <input type="password" name="confirmaContraseña" id="confirmaContraseña" class="control" placeholder="Confirma contraseña">
                <div class="error">
                    <?php if($validations->errors['confirmaContraseña']): ?>
                    <?php echo $validations->errors['confirmaContraseña']; ?>
                    <?php endif; ?>
                </div>
            </div>
            <div class="group">
                <input type="submit" name="newPasswordBtn" class="btn btn-sweet" value="Cambiar contraseña">
            </div>
        </form>
    </div>
</div>