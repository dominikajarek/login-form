const logoutForm = document.querySelector("#logout-form");

logoutForm.addEventListener('submit', function (e) {
    e.preventDefault();
    window.location.href = "login-form.html";
});