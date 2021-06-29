<div class="form-section">
    <div class="form--header">
        <h2>Sign In</h2>
        <p>Log into your account</p>
    </div>
    <div class="form--body">
        <form method="POST">
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
                <label for="contraseña" class="form--label">Contraseña
                    <!--<span class="forgotPassword">
                        <a href="">Olvidaste tu contraseña?</a>
                    </span>-->
                </label>
                <input type="password" name="contraseña" id="contraseña" class="<?php if($validations->errors['contraseña']): echo 'borderRed'; endif; ?> control" placeholder="Contraseña" value="<?php if($validations->setValue('contraseña')): echo $validations->setValue('contraseña'); endif; ?>">
                <div class="error">
                    <?php if($validations->errors['contraseña']): ?>
                    <?php echo $validations->errors['contraseña']; ?>
                    <?php endif; ?>
                </div>
            </div>
            <div class="group">
                <input type="submit" name="loginBtn" class="btn btn-sweet" value="Entrar">
            </div>
        </form>
    </div>
</div>