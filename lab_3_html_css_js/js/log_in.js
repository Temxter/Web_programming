let form = document.querySelector("#login-form");
let formElements =
    [
        { label: 'Login-text', elemtype: 'p', value: 'Login'},
        { label: 'login-field', elemtype: 'Input', attr:'type', typeValue: 'text'},
        { label: 'password-text', elemtype: 'p', value: 'Password'},
        { label: 'password-field', elemtype: "Input", attr:'type', typeValue: 'password'},
        { label: 'login-button', elemtype: 'button', value: 'Log in'}
    ];

for (var i = 0; i < formElements.length; i++) {
    let element = document.createElement(formElements[i].elemtype);
    if (formElements[i].attr)
        element.setAttribute(formElements[i].attr, formElements[i].typeValue);
    if (formElements[i].value)
        element.innerHTML = formElements[i].value;
    element.setAttribute('id', formElements[i].label);
    console.log(element);
    form.appendChild(element);
}

let loginField = document.querySelector("#login-field");

//regex for check login
let format = /[\w@_]+$/;
form.onclick = (event) => {
    event.preventDefault();
    console.log(format.test(loginField.value));
    //check login value
    if (!format.test(loginField.value)) {
        //red border around login field
        loginField.classList.add('bordFail');
        return;
    }
    else
        loginField.classList.remove('bordFail');
    window.open("../pages/student_menu.html");
}