let mainForm = document.querySelector("#marks-form");

let formMark =
    [
        { label: 'test-name', elemtype: 'p', value: 'Test'},
        { label: 'mark', elemtype: 'p', value: 'mark - 8' }
    ];

function addElementTo(formElements, mainForm) {
    for (var i = 0; i < formElements.length; i++) {
        let element = document.createElement(formElements[i].elemtype);
        if (formElements[i].attr)
            element.setAttribute(formElements[i].attr, formElements[i].attrValue);
        if (formElements[i].value)
            element.innerHTML = formElements[i].value;
        element.setAttribute('id', formElements[i].label);
        mainForm.appendChild(element);
    }
}

addElementTo(formMark, mainForm);
addElementTo(formMark, mainForm);
