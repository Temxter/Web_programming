let mainForm = document.querySelector("#test-form");

let formName = [{ label: 'test-name', elemtype: 'h3', value: 'The test name'}];
let formQuestion = [{ label: 'question', elemtype: 'p', value: 'question' }];
let formAnswer =
    [
        {elemtype: 'div'},
        { label: 'answer-radio', elemtype: 'Input', attr: 'type', attrValue: 'radio'},
        { label: 'answer-text', elemtype: 'label', value: 'answer'},
    ];
let formButton = [{
    label: 'send-button', elemtype: 'button',
    value:'Submit'}];


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


addElementTo(formName, mainForm);
addElementTo(formQuestion, mainForm);

answersDiv = document.createElement('div');
mainForm.appendChild(answersDiv);

addElementTo(formAnswer, answersDiv);
addElementTo(formAnswer, answersDiv);
addElementTo(formAnswer, answersDiv);

addElementTo(formButton, mainForm);