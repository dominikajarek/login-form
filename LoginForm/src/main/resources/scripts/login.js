const form = document.querySelector("#login-form");

form.addEventListener('submit', function (e) {
    e.preventDefault();
    const data = `email=${this.email.value}&password=${this.password.value}`;
    console.log(data);
    login(data)
        .then(function (message) {
            // user is authenticated, and cookie send by server is set in browser
            console.log(message);
            window.location.href = "hello.html";
            document.cookie = `user=${JSON.stringify(data)}`;

        }).catch(function (error) {
            // user NOT authenticated, server return different status than 200-299
            console.log(error);
        });
});


async function login(data) {
    const response = await fetch("http://localhost:8080/login",
        {
            credentials: 'same-origin',
            method: "POST",
            body: data,
            mode: "no-cors"
        })
    //if !response
    const message = (await response).text();
}
