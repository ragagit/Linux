/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function DataTypes() {
    var num1 = 35;
    var num2 = 10;
    //alert(num1 + num2 );

    var num3 = '15';
    var num4 = "10";

    //alert( num3 + num4 );

    //Name convention
    //Letters, numbers, underscore, $
    //begin with letter, _ or $   
    //They are case sensitive

    //camel case
    var myFavoriteNumber;

    //partial case
    var MyFavoriteNumber;

    //underscore
    var my_favorite_number;
}

function MyArray() {
    var colors = ['red', 'blue', 'green'];

    //alert(colors[0]);
    colors.push('white');
    //alert(colors);

    var colors1 = new Array("yellow", "orange", "brown");

    //To add
    colors1[3] = "black";
    colors1.push('purple');

    //alert(colors1);

    var numbs = [8, 7, 14, 10];
    //alert( numbers[1] + numbers[4] + ' ' + numbers.length);
    var idx = numbs.indexOf(14);

    //alert(idx + ' ' + numbs.sort());

}

function callback(num) {
    console.log('callback:' + num);
}
function Loops() {

    for (var i = 0; i < 5; i++) {
        console.log('for loop:' + i);
    }

    i = 0;
    while (i < 5) {
        console.log('while loop:' + i++);
    }

    var numbers = [4, 2, 8, 7];
    numbers.forEach(function (num) {
        console.log('forEach:' + num);
    });

    numbers.forEach(callback);

}

function Conditionals() {

    var var1 = 0;
    var var2 = 0;
    if (var1 == 0 && var2 == var1)
        console.log('equal');
    else
        console.log('not eqaul');

    var fruit = 'apple';

    switch (fruit) {
        case 'banana':
            console.log('I hate banana');
            break;

        case 'apple':
            console.log('I love apples');
            break;
        default:
            console.log('I love all fruit');
            break;
    }

}

function forinExample() {
    var aProperty;
    document.write("Navigator Object Properties<br /> ");

    for (aProperty in navigator) {
        document.write(aProperty);
        document.write("<br />");
    }
    document.write("Exiting from the loop! <br/>");
}

// break continue label :

function labelExample() {
    document.write("Entering the loop!<br /> ");
    outerloop: // This is the label name

            for (var i = 0; i < 5; i++)
    {
        document.write("Outerloop: " + i + "<br />");
        innerloop:
                for (var j = 0; j < 5; j++)
        {
            if (j > 3)
                break; // Quit the innermost loop
            if (i == 2)
                break innerloop; // Do the same thing
            if (i == 4)
                break outerloop; // Quit the outer loop
            document.write("Innerloop: " + j + " <br />");
        }
    }

    document.write("Exiting the loop!<br /> ");
}

// Cookies            
/*
 Cookies are a plain text data record of 5 variable-length fields −
 Expires − The date the cookie will expire. If this is blank, the cookie will expire when the visitor quits the browser.
 Domain − The domain name of your site.
 Path − The path to the directory or web page that set the cookie. This may be blank if you want to retrieve the cookie from any directory or page.
 Secure − If this field contains the word "secure", then the cookie may only be retrieved with a secure server. If this field is blank, no such restriction exists.
 Name=Value − Cookies are set and retrieved in the form of key-value pairs
 document.cookie = "key1=value1;key2=value2;expires=date";                      
 
 */

function writeCookie(){
    
    var firstName = document.getElementById("firstName").value;
    console.log("writeCookie name:" + firstName );
    setCookie("user", firstName, 7);
    
}

function readCookie(){
    
    var user = getCookie("user");
    console.log( "readCookie name:" + user );
    
}

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    console.log(cname + "=" + cvalue + ";" + expires + ";path=/");
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function checkCookie() {
    var user = getCookie("user");
    if (user != "") {
        alert("Welcome again " + user);
    } else {
        user = prompt("Please enter your name:", "");
        if (user != "" && user != null) {
            setCookie("user", user, 365);
        }
    }
}

function deleteCookie(){
    
    document.cookie = "user=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    
}

//To delete a cookie you just need to set the expiry date to a time in the past.
//   var now = new Date();
//   now.setMonth( now.getMonth() + 1 );


// Page redirection
function Redirect() {

    var browsername = navigator.appName;

    if (browsername == "Netscape")
    {
        window.location = "http://www.location.com/ns.htm";
    }

    window.location = "http://www.tutorialspoint.com";

}

function RedirectMsg() {

    document.write("You will be redirected to main page in 10 sec.");
    setTimeout('Redirect()', 10000);
}

// Dialog Boxes
function Warn() {

    alert("This is a warning message!");
    document.write("This is a warning message!");

}

function getConfirmation() {

    var retVal = confirm("Do you want to continue ?");

    if (retVal == true) {
        document.write("User wants to continue!");
        return true;
    } else {
        document.write("User does not want to continue!");
        return false;
    }

}

function getValue() {

    var retVal = prompt("Enter your name : ", "your name here");
    document.write("You have entered : " + retVal);

}

//Print
function printMypage() {

    window.print();

}

function Objects() {

    var person = {

        firstName: 'Brad',
        lastName: 'Traversy',
        age: 34,
        children: ['Mary', 'Nichole'],
        //Another object
        address: {
            street: '555 Fake Street',
            city: 'Boston',
            state: 'MA'
        },
        fullName: function () {
            return this.firstName + ' ' + this.lastName;
        }

    };

    console.log(person.firstName);
    console.log(person.fullName());

    //Object constructor
    var apple = new Object();
    apple.color = 'red';
    apple.shape = 'round';
    apple.describe = function () {
        return 'Apples are good';
    };
    console.log(apple.describe());

    //Constructor Pattern
    function Fruit(name, color, shape) {
        this.name = name;
        this.color = color;
        this.shape = shape;
        this.describe = function () {
            return 'A ' + this.name + ' has a color ' + this.color;
        };
    }

    var apple1 = new Fruit('apple', 'red', 'round');
    console.log(apple1);
    console.log(apple1.describe());

    //Array of objects
    var users = [
        {
            name: 'John',
            age: 30
        },
        {
            name: 'Vic',
            age: 45
        },
        {
            name: 'Sonia',
            age: 55
        }

    ];

    console.log(users[1]);

    var Fruits = [new Fruit('banana', 'yellow', 'long'), new Fruit('pear', 'green', 'round')];
    console.log(Fruits[1].describe());

}

function doClick() {
    alert("You clicked!");
}

function changeText(id) {
    id.innerHTML = "You clicked";
}

function changeHeaderText() {

    var header = document.getElementById("heading");

    if (header.textContent == "You clicked") {
        header.innerHTML = "Learning Javascript";
    } else {
        header.innerHTML = "You clicked"
    }

}

function showTime() {

    var time = document.getElementById("time");
    time.innerHTML = Date();

}

function clearTime() {

    var time = document.getElementById("time");
    time.innerHTML = '';

}

function changeBackground(id) {

    console.log(id.value);
    var body = document.getElementById("heading");
    body.style.color = id.value;

}

function validateForm() {

    var firstName = document.forms["myForm"]["firstName"].value;
    //var firstName = document.getElementById("firstName");
    console.log("In validateForm")

    if (firstName == null || firstName == "") {
        alert("First Name can't be empty");
        return false;
    }

}

function errorHandlingExample() {

    var a = 100;

    try {
        alert("Value of variable a is : " + a);
    } catch (e) {
        alert("Error: " + e.description);
    } finally {
        alert("Finally block will always execute!");
    }
}

function myFunc()
{
    var a = 100;
    var b = 0;

    try {
        if (b == 0) {
            throw("Divide by zero error.");
        } else
        {
            var c = a / b;
        }
    } catch (e) {
        alert("Error: " + e);
    }
}

function browserType() {
    var userAgent = navigator.userAgent;
    var opera = (userAgent.indexOf('Opera') != -1);
    var ie = (userAgent.indexOf('MSIE') != -1);
    var gecko = (userAgent.indexOf('Gecko') != -1);
    var netscape = (userAgent.indexOf('Mozilla') != -1);
    var version = navigator.appVersion;

    if (opera) {
        document.write("Opera based browser");
        // Keep your opera specific URL here.
    } else if (gecko) {
        document.write("Mozilla based browser");
        // Keep your gecko specific URL here.
    } else if (ie) {
        document.write("IE based browser");
        // Keep your IE specific URL here.
    } else if (netscape) {
        document.write("Netscape based browser");
        // Keep your Netscape specific URL here.
    } else {
        document.write("Unknown browser");
    }
    // You can include version to along with any above condition.
    document.write("<br /> Browser version info : " + version);
}