<nav id="nav">
    <ul class="left-ul">
        <li><a href="index.php"><img src="assets/img/logo_min.png" alt="Logo not found" class="logo"></a></li>
    </ul>
    <div class="empty">
    </div>
    <ul class="right-ul">
        <?php if(isset($_SESSION['userId'])): ?>
            <li><a href="logout.php" class="btn btn-sweet">Salir</a></li>
        <?php else: ?>
            <li><a href="login.php" class="btn btn-sweet">Entrar</a></li>
            <li><a href="signup.php" class="btn btn-outline">Registrar</a></li>
        <?php endif; ?>
    </ul>
</nav>