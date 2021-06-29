<div class="card">
    <div class="card--image">
        <img src="assets/img/user.png" alt="">
    </div>
    <div class="card--name">
        <p><?php echo $_SESSION['name']; ?></p>
    </div>
    <div class="card--job">
        <p>Administrador</p>
    </div>
    <div class="card--setting">
        <a href="dashboard.php" class="btn btn-card">Menu <span class="icon">&rarr;</span></a>
    </div>
</div>