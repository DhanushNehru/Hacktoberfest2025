const imageInput = document.getElementById('imageInput');
const previewContainer = document.getElementById('preview-container');
const imagePreview = document.getElementById('imagePreview');

imageInput.addEventListener('change', function () {
    const file = this.files[0];
    if (file) {
        const reader = new FileReader();

        previewContainer.style.display = "block";

        reader.addEventListener("load", function () {
            imagePreview.setAttribute("src", this.result);
        });

        reader.readAsDataURL(file);
    } else {
        previewContainer.style.display = "none";
    }
});
