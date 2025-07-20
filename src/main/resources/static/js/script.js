document.addEventListener("DOMContentLoaded", function () {
    console.log("Script personalizado cargado correctamente");

    // Modal de bienvenida
    const bienvenidaModalEl = document.getElementById('bienvenidaModal');
    if (bienvenidaModalEl && !sessionStorage.getItem('welcomeModalShown')) {
        const bienvenidaModal = new bootstrap.Modal(bienvenidaModalEl);
        bienvenidaModal.show();
        sessionStorage.setItem('welcomeModalShown', 'true');
    }

    // Modal de detalles de producto
    const productDetailsModalEl = document.getElementById('productDetailsModal');
    const modalForm = document.getElementById('modal-add-to-cart-form');
    const modalProductIdInput = document.getElementById('modal-product-id');

    if (productDetailsModalEl && modalForm && modalProductIdInput) {
        const productDetailsModal = new bootstrap.Modal(productDetailsModalEl);

        document.querySelectorAll('.view-product-details').forEach(button => {
            button.addEventListener('click', function () {
                const productId = this.getAttribute('data-product-id');
                const productName = this.getAttribute('data-product-name');
                const productPrice = this.getAttribute('data-product-price');
                const productImage = this.getAttribute('data-product-image');
                const productDescription = this.getAttribute('data-product-description');

                document.getElementById('modal-product-title').textContent = productName;
                document.getElementById('modal-product-price').textContent = `S/. ${parseFloat(productPrice).toFixed(2)}`;
                document.getElementById('modal-product-image').src = productImage;
                document.getElementById('modal-product-description').textContent = productDescription;
                modalProductIdInput.value = productId;

                productDetailsModal.show();
            });
        });
    }

    // Botones "Añadir al carrito" desde las tarjetas (fuera del modal)
    document.querySelectorAll('.add-to-cart-btn').forEach(btn => {
        btn.addEventListener('click', () => {
            const productoId = btn.getAttribute('data-product-id');
            const csrfToken = document.querySelector('input[name="_csrf"]')?.value;

            fetch('/carrito/agregar', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    ...(csrfToken && { 'X-CSRF-TOKEN': csrfToken })
                },
                body: `productoId=${productoId}&cantidad=1`
            }).then(response => {
                if (response.ok) {
                    alert("Producto añadido al carrito.");
                } else {
                    alert("Hubo un error al añadir el producto.");
                }
            }).catch(() => {
                alert("Error de red al añadir al carrito.");
            });
        });
    });

    // Validación del login
    const loginForm = document.getElementById('loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', function (event) {
            if (!loginForm.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            loginForm.classList.add('was-validated');
        });
    }

    // Validación del registro
    const registerForm = document.getElementById('registerForm');
    const confirmPasswordInput = document.getElementById('confirmPassword');
    const registerPasswordInput = document.getElementById('registerPassword');
    const passwordMismatchFeedback = document.getElementById('passwordMismatchFeedback');

    if (registerForm && confirmPasswordInput && registerPasswordInput && passwordMismatchFeedback) {
        registerForm.addEventListener('submit', function (event) {
            if (!registerForm.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            } else if (registerPasswordInput.value !== confirmPasswordInput.value) {
                event.preventDefault();
                event.stopPropagation();
                confirmPasswordInput.classList.add('is-invalid');
                passwordMismatchFeedback.style.display = 'block';
            } else {
                confirmPasswordInput.classList.remove('is-invalid');
                passwordMismatchFeedback.style.display = 'none';
            }
            registerForm.classList.add('was-validated');
        });

        const validatePasswordMatch = () => {
            if (registerPasswordInput.value !== confirmPasswordInput.value) {
                confirmPasswordInput.classList.add('is-invalid');
                passwordMismatchFeedback.style.display = 'block';
            } else {
                confirmPasswordInput.classList.remove('is-invalid');
                passwordMismatchFeedback.style.display = 'none';
            }
        };

        confirmPasswordInput.addEventListener('input', validatePasswordMatch);
        registerPasswordInput.addEventListener('input', validatePasswordMatch);
    }
});
