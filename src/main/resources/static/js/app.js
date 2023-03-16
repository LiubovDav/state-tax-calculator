function signUp() {

    // let name = document.getElementById('signUp__name');
    // let email = document.getElementById('signUp__email');
    // let phone = document.getElementById('signUp__phone');
    // let password = document.getElementById('signUp__password');
    // let password2 = document.getElementById('signUp__password2');

    // here I used jQuery
    let name = $('#signUp__name');
    let email = $('#signUp__email');
//    let phone = $('#signUp__phone');
    let password = $('#signUp__password');
    let password2 = $('#signUp__password2');

    const patternEmail = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i;
    const patternPhoneNumber = /^(\+\d{1,2}\s)?\(?\d{3}\)?[\s.-]\d{3}[\s.-]\d{4}$/i;

    if (name.val().length == 0) {
        alert('Fill in the name');
        name.focus();
        name.select();
        return false;
    }

    if (email.val().length == 0) {
        alert('Fill in the e-mail');
        email.focus();
        email.select();
        return false;
    }

    // here I used a regular expression
    if (!patternEmail.test(email.val())) {
        alert('Not a valid e-mail address');
        email.focus();
        email.select();
        return false;
    }

//    if (phone.val().length == 0) {
//        alert('Fill in the phone number');
//        phone.focus();
//        phone.select();
//        return false;
//    }
//
//    // here I used a regular expression
//    if (!patternPhoneNumber.test(phone.val())) {
//        alert('Not a valid phone number');
//        phone.focus();
//        phone.select();
//        return false;
//    }

    if (password.val().length == 0) {
        alert('Fill in the password');
        password.focus();
        password.select();
        return false;
    }

    if (password.val().length < 8) {
        alert('The password must be at least 8 characters long');
        password.focus();
        password.select();
        return false;
    }

    if (password2.val().length == 0) {
        alert('Fill in the password');
        password2.focus();
        password2.select();
        return false;
    }

    if (password.val() != password2.val()) {
        alert('Password doesn\'t match');
        password2.val('');
        password2.focus();
        password2.select();
        return false;
    }
}

function contactUs() {
    let firstName = $('#contactUs__firstname');
    let lastName = $('#contactUs__lastname');
    let email = $('#contactUs__email');
    let phone = $('#contactUs__phone');
    let message = $('#contactUs__message');

    const patternEmail = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i;
    const patternPhoneNumber = /^(\+\d{1,2}\s)?\(?\d{3}\)?[\s.-]\d{3}[\s.-]\d{4}$/i;


    if (firstName.val().length == 0) {
        alert('Fill in the first name');
        firstName.focus();
        firstName.select();
        return;
    }

    if (lastName.val().length == 0) {
        alert('Fill in the last name');
        lastName.focus();
        lastName.select();
        return;
    }

    let inputs = document.getElementsByTagName('input');
    if (!isRadioSelected(inputs, 'contact_by')) {
        alert('Please clarify how do you wish to be contacted?');
        return;
    }

    if (email.css('display') != 'none') {
        if (email.val().length == 0) {
            alert('Fill in the e-mail');
            email.focus();
            email.select();
            return;
        }

        // here I used a regular expression
        if (!patternEmail.test(email.val())) {
            alert('Not a valid e-mail address');
            email.focus();
            email.select();
            return;
        }
    }

    if (phone.css('display') != 'none') {
        if (phone.val().length == 0) {
            alert('Fill in the phone number');
            phone.focus();
            phone.select();
            return;
        }

        // here I used a regular expression
        if (!patternPhoneNumber.test(phone.val())) {
            alert('Not a valid phone number');
            phone.focus();
            phone.select();
            return;
        }
    }

    if (message.val().length == 0) {
        alert('Fill in the message');
        message.focus();
        message.select();
        return;
    }

}

function isRadioSelected(inputs, inputName) {
    for (let i = 0; i < inputs.length; i++) {
        if (inputs[i].type == 'radio' && inputs[i].name == inputName && inputs[i].checked) {
            return true;
        }
    }
    return false;
}

function contactBySelected() {
    if (document.getElementById('contactUs__by_email').checked) {
        document.getElementById('contactUs__email').style.display = 'block';
        document.getElementById('contactUs__phone').style.display = 'none';
    } else {
        document.getElementById('contactUs__email').style.display = 'none';
        document.getElementById('contactUs__phone').style.display = 'block';
    }
}

$.fn.digits = function(){
    return this.each(function(){
        $(this).text( $(this).text().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,") );
    })
}
