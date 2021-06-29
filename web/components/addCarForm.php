<div class="content--section">
    <h1 class="dashboard-heading">Agregar auto</h1>
    <form action="" method="POST" enctype="multipart/form-data">
        <div class="group">
            <label for="modelo" class="form--label">Modelo</label>
            <input type="text" name="modelo" id="modelo" class="control" placeholder="Modelo del auto">
        </div>
        <div class="group">
            <label for="precio" class="form--label">Precio</label>
            <input type="number" name="precio" id="precio" class="control" placeholder="Precio del auto">
        </div>
        <div class="group">
            <label for="transmision" class="form--label">Transmision</label>
            <input type="text" name="transmision" id="transmision" class="control" placeholder="Tipo de transmision">
        </div>
        <div class="group">
            <label for="placas" class="form--label">Placas</label>
            <input type="text" name="placas" id="placas" class="control" placeholder="Placas del auto">
        </div>
        <div class="group">
            <label for="mLabel" class="form--label" id="label"></label>
            <input type="file" name="image" id="mLabel" class="myImage" onchange="imageName();">
            <div class="error">
                <?php if($uploadImages->imageErrors['image']): ?>
                <?php echo $uploadImages->imageErrors['image']; ?>
                <?php endif; ?>
            </div>
        </div>
        <div class="group">
            <input type="submit" name="uploadCar" class="btn btn-sweet" value="Añadir auto">
        </div>
    </form>
</div>