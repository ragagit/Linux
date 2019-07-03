/*
- JavaScript components
ECMAScript
DOM
BOM

- Variables
var message;
var sum = 1 + 1;
console log(message)

- Data Types
bboolean, null, undefined, number, string, symbol
typeof
Number.MAX_VALUE
Number.MIN_VALUE
NaN
isNaN
parseInt
parseFloat
var str = "Ryan\nHall"

- Objects
Creating obbject
{} //Object literal
new Object()
Accessing properties
var person = new Object()
person.name = "Ryan"
person.age = "30"

var personn = {
name: 'Ryan',
age: 30

console.log(personn['name'], personn.age)
}

*/

//Variables Data Types
var message = "Hello console";
console.log(message);
//alert(typeof message);
//alert(Number.MAX_VALUE);
var num = '100'
//alert(Number(num))
var num1 = '100hgb57'
//alert(parseInt(num1))
num1.length
var num3 = 100;
var str = num3.toString

//++++ Objects
//Create object
var person = new Object();
person.name = "Ryan";
person.age = "30";
person.sayName = function(){
    return this.name;
}
var personn = {
name: 'Ryan',
age: 30,
sayName: function(){
    return this.name;
    },
    likes: {
        movie: 'Gladiator',
        book: 'Learrning JS'
    }

};

//Accessing properties
console.log(person.name, person.age, person.sayName())
console.log(personn['name'], personn.age, personn.sayName())

//Adding properties
personn.address = 'Sydney Australia';
//Delete properies
delete personn.address;

//Object has properties
console.log('Has Property name?' + person.hasOwnProperty('name'));

//Embedded Objects
console.log(personn.likes.movie);
//-------

//+++++ Operators
// ++, --, 
// + - conversion
var str1 = '01'; 
var str2 = +str1; // 1
var str3 = -str1; // -1
console.log(str1);

//&& || !
// * %
//type conversion
var res = 5 * '10'; //50
// + if one is string, the result is a string
// - converted to Number

//Relationl Operators
// < > <= >=

//Equality Operators
//== != === !==
console.log('1' == 1); //true
console.log('1' === 1); //false
console.log('1' !== 1); //true

//Trenary Operators
//variable = expression ? true : false

//Compund assignment operators
// += -= *=
//-----

//++++++++ Loops
//if else
var num = 101;
if( num > 100 ){
    console.log('yes');
}else{
    console.log('no');
}
//do while
var it = 0;
do{
    it++;
    console.log(it);
}while(num < 5)
//for
for( i=0; i < 5; i++){
    console.log(i);
}
//for in
for(item in person){
    console.log(item)
}
//labeled

//break continue
outerLabel: for( var i=0; i<5;i++){
for(j=0;j<3;j++){
    if(j === 2)
    break outerLabel;
}
}
//switch
var k=10;
switch(k){
    case 5:
        console.log('5')
    break;
    case 7:
        console.log('7')
        break;
default:
    console.log('10')

}

//+++++ Functions

function total(one, tywo){
    var totalThis = one * two;
    return totalThis;
}

var res = total(3,7);

// You can use arguments array to acces the arguments 
function sayHi(){
    var n = arguments.length;
    return arguments[0];
}
console.log(sayHi('Ryan'))

//primitives and references
//Execution context. What othert data it has access to.
//Global Execution is the outer most one. In web browser it is the window object.
//Scope Chain. A function returns the val of a var 
//No Block Level Scope like Java or C { }

if(true){
    var color = 'blue'; //added to the global scope
}

// In Java we have class in JS Object

//-----
//++++++ Arrays 
//Arrays can hold any type of data
var col1 = new Array("red", "blue");
var col2 = ['red', 'blue'];
console.log(col1[1], col2.length, Array.isArray(col2));
col1[1] = "brown";
console.log(col1 instanceof Array);
//objects have toString(), toLocalString() and valueOf()
console.log(col1.toString());
console.log(col2.join(' '));
//stack methods push (adds at then end), pop (removes last item)
col1.push('pink');
var rmItem = col1.pop();
console.log(rmItem);

//Queue methods. Push to the beginning unshift, shift() removes the first
col1.unshift('purple', 'yellow');
var first = col1.shift();

//Sorting
//sort(), reverse()
var values = [ 1, 2, 5, 4, 7, 8]
console.log(values.sort());//sort is for strings
function compare(val1, val2){
    return val2 - val1;
}
function comp1(val1,val2){
    if(val1 > val2){
        return -1;
    }else if(val1 < val2){
        return 1;
    }else{
        return 0;
    }
}

//concat(), slice(), splice() - inse\ert/replace/delete
var totCols = col1.concat(['purple','green'])
var pCol = totCols.slice(1,3);
var deleteItems = totCols.splice(2,1);//starting pos 2 delete 1 item
var inItems = totCols.splice(1,0,'not a color','many');//insert a pos 1
var inItems1 = totCols.splice(1,2,'replacement');
console.log(inItems);

//indexOf(), lastIndexOf()
var idx = totCols.indexOf('purple', 1);//start looking from pos 1

//Iterative every(), some(), filter(), forEach(), map()
var tot = [1,2,4,5,3,4,7];
var n = tot.every(function(item,index,array){
    return item > 2;
});//this retirn false becasue not all of the items are > 2

var n1 = tot.some(function(item,index,array){
    return item > 2;
});//this retirn true becasue some of the items are > 2

var n2 = tot.filter(function(item,index,array){
    return item > 2;
});//this return items are > 2

var n3 = tot.forEach(function(item,index,array){
    console.log(item);
});//

var n4 = tot.map(function(item,index,array){
    return 'Num' + item;
});//this retirn true becasue some of the items are > 2

//reduce(), reduceRight
var sum = tot.reduce(function(preVal, curVal, index, array){
return preVal + curVal;
});

//------

//++++ Date type
var date = new Date();
var fy = date.getFullYear();
var m = date.getDay();

//------

//++++ Reg expressions
//you can use new or  /  /
var re = new RegExp();
var re1 = /hello/;
var sentence  = 'hello world';
console.log(re.test(sentence));

var re2 = /^\d{3}$/;
re2.test('hello');
re2.test('123hello')

//----

//++++ Functions
var f = new Function('num1', 'num2', 'return num1 + num2');

//Function declaration
function total(num1, num2){
    return num1 + num2;
}

//Function expression
var ff = function total(num1, num2){
    return num1 + num2;
}
console.log(total);//this will print the function itself
console.log(f(1,2));

function callingAFunction(func, args){
    return func(args);
}
function func(num){
    return num + 100;
}

console.log(callingAFunction(func, 17));

//callee 
function factorial(num){
    if(num <1){
        return 1;
    }else{
        //return num* factorial(num - 1);
        return num * arguments.callee(num -1);//callee
    }
}

//caller
function outside(){
    inside();
}

function inside(){
    console.log(inside.caller);
}
//this is a reference to the execution context
window.color = 'red';
var obj = { color: 'blue'};

function sayColor(){
    console.log(this.color);
}
sayColor(); //red
obj.sayColor = sayColor;
obj.sayColor();//blue

//when a fuunction is called in a global scope of a web page this point to the window object

//Function methods apply(), call(), bind()
//aply accepts two arguments
function summ(num1,num2){
    return num1 + num2;
}
function callingSum(num1,num2){
    //return sum.apply(this, arguments);//or (this, [num1,num2])
    return summ.call(this, num1, num2)
}
console.log(callingSum(11,22));

//bind
var objSayColor = sayColor.bind(obj);//bind the function to the obj
objSayColor();
//------

//++++++ Primitive Wrapper Type
// String, Number, Boolean
var myString = new String("Hello World");
var myString1 = "Hello World";
var mySubString = myString.substring(2);
console.log(mySubString);

var num = new Number(10);
var num1 = 10;

//String manipulation
//concat
var res1 = myString1.concat(" and JS")
//slice
myString.slice(3);//lo world
myString.slice(3,7);//lo w
//substring
myString.substring(3);//lo world
myString.substring(3,7);//lo world
//substr
myString.substr(3); //lo world
myString.substr(3,7);//lo worl

//indexOf, lastIndexOf, trim, toUpperCase
myString.indexOf('o', 4);//start looking from 4
myString.lastIndexOf('o',5);//from the pos 5 to the beginnin
myString.toUpperCase();

//Pattern matching match, search, replace, split
var text = 'cat, bat, sat, fat';
var pattern = /.at/;

var matches = text.match(pattern);
console.log(matches);
var result = text.replace('at', 'ond');
var r1 = text.replace(/(.at)/g, 'word($1)');
var r2 = text.split(',');

//------

//++++++ Object Oriented JavaScript
//Factory pattern
function createPerson(name, age, job){
    var obj = new Object();
    obj.name = name;
    obj.age = age;
    obj.job = job;
    obj.sayName = function(){
        console.log(this.name)
    };
    return obj;
}
var person1 = createPerson('Ryan', 30, 'Developer');

//Constructor pattern
function Person(name, age, job){

    this.name = name;
    this.age = age;
    this.job = job;
    this.sayName = function(){
        console.log(this.name)
    };
    return obj;
}
var person2 = new Person('Ryan', 30, 'Developer');

//Prototype pattern
//Whenever a function is created, its prototype property is also created and is an object
console.log(Person.prototype.constructor);
function PersonP(){

}
PersonP.prototype.name = 'Ryan';
PersonP.prototype.age = 30;
PersonP.prototype.sayName = function(){
    console.log(this.name);
};
var person3 = new PersonP();
person3.sayName();
console.log(PersonP.prototype.isPrototypeOf(person3));

var keys = Object.keys(PersonP.prototype);
console.log(keys);

function Personn(){

}
Personn.prototype = {
    name: 'Ryan',
    age: 30,
    sayName: function(){
        console.log(this.name);
    }
};

var person4 = new Personn();
person4.sayName();

function Personnn(){

}
Personnn.prototype = {
    constructor: Personnn,
    name: 'Ryan',
    age: 30,
    sayName: function(){
        console.log(this.name);
    }
};

var person5 = new Personnn();
person5.sayName();

// Combination of Constructor and Prototype
//Each instance ends up with its own copy of the instance properties
//but they all share references to methods
function PPerson(name,age, job){
    this.name = name;
    this.age = age;
    this.job = job;
    this.friends = [ 'Zen', 'Ray'];
}

PPerson.prototype = {
    constructor: PPerson,
    sayName: function(){
        console.log(this.name);
    }
};

var per1 = new PPerson('Ryan', 30, 'Developer');
per1.friends.push('Mary');
var per2 = new PPerson('Ryn', 20, 'Developer');

//Function declaration
//Function declaration hoisting it becomes available in the global conetxxt
sayHi();
function sayHi(){
    console.log('Hi');
}
//Function expression
var sayHi = function(){
    console.log('hi');
}

//Recursion
function factorial1(num){
    if(num <=1){
        return 1;
    }else{
        return num * factorial(num - 1);
    }
}
console.log(factorial1(4));

//Function closures
//closures are functions that have access to variables from another fucntion's scope

function eat(){
    var fruit = 'Apple';

    function watch(){
        console.log(fruit)
    }
    return watch;
}
var favFruit = eat();
favFruit();

//Objects closures
//'this' object is based on the context in which a function is executed
//anonymous functions are not bound to an object in 'this' context

var name = 'The window';

var object = {
    name: 'The object',
    getName: function(){
        var that = this;
        return function(){
            //return this.name;
            return that.name;
        }
    }
}
//alert(object.getName()()); //returns 'The window'

//Creating block scope using IIFE Immediatle Invoke Function Expression
//This function is in the window scope, to avoid that wrap it in ()
function sayColor(){
    var color = 'red';
    console.log(color)
}
//------
//++++++ BOM
//Windows object properties
window.document
window.location
window.localStorage.setItem("name", "Ryan")
window.localStorage.getItem("name")
window.navigator.geolocation
window.history.back

//Window object methods
//alert() prompt() open/close setInterval() setTimeout()
//var result = prompt("what is your name")
if(result !== null){
    //alert('welcome' + result)
}

//++++ 
//setInterval setTimeout
//JS execution in browser is single-threaded
//you can use setInterval or setTimeout to run the code in specific time.
// setTimeout(function(){
//     alert('Hello JS')
// }, 3000);

// setInterval(function(){
//     alert('Hello JS');
// }, 3000)

//-----
//+++++ DOM write, get attribute, set attribute, create element
//document.write('Hello World and JS');
var div = document.getElementById('root');
// div.getAttribute('id');
// div.setAttribute('id', 'hahaha');
// alert(div.getAttribute('id'));

function myClick(){
    var div = document.createElement('h1');
    var text = 'hello world';
    var newH1 = document.body.appendChild(div);
    newH1.innerText = text;
}

// DOM using selector
// document.getElementById('root'); //get by id
// document.getElementsByClassName('intro'); // class names
// document.getElementsByTagName('p'); //tags such as p or h1
// document.querySelectorAll('p.intro'); // using css selectors
// document.getElementById('root').innerHTML = 'some text'

//+++++
//JQuery and Bootstrap 
//https://getbootstrap.com it comes with JQuery
//For JQuery you always use $();
//https://cdnjs.com/libraries/animate.css/ for animated bounce
// Animate css https://daneden.github.io/animate.css/
// Targeting the class use ".", targeting the id use #
$( document ).ready(function() {
    $('button').addClass('animated bounce');
    $('.btn-success').addClass('shake');
    //$('#target6').addClass('fadeOut');
    $('button').removeClass('btn-success');
    //css
    $('#target1').css('color', 'red');
    $('#target2').css({color:'green', background : 'blue'})
    //property
    $('#target2').prop('disabled', true);
    $('h1').html('Learning <strong>JQuery</strong> is so much fun');
    //Removing
    //$('#target5').remove();
    //Apend
    $('#target4').appendTo('.left');
    //Clone
    //$('.left').clone().appendTo('.right');
    //target parent
    $('.heading2').parent().css('backgroundColor', 'red');
    //target children
    $('.heading2right').children().css('color', 'red');
    //target child
    $('.target:nth-child(2)').addClass('animated swing');
    //even odd numbers
    $('.target:odd').addClass('animated swing');
});

//Vanilla Java Script search for youmightnotneedjquery.com

//++++++++ Even handling

$(document).ready(function () {
    $('.question').on('click', function () {
        $('.answer').html('JQuery is the best!')
    });
    $('.question').on('mouseover', function () {
        $('.answer').html('JQuery is the best!')
    });
});

//For weather API https://www.apixu.com/my/#
//For nice backgrounds https://pixabay.com/
//Ajax request to API with JQuery
$(document).ready(function(){
    var url = 'https://hacker-news.firebaseio.com/v0/item/8863.json?print=pretty'
    $.getJSON(url, function(data){
        console.log(data);
    });
});

//Ajax response Object or Array
$(document).ready(function(){
    var url = 'https://hn.algolia.com/api/v1/search?query=javascript'
    $.getJSON(url, function(data){
        //console.log(data instanceof Array );
        console.log(data instanceof Object );
        if(Array.isArray(data.hits)){
            console.log('It is an array');
        }else{
            console.log('it is not an array')
        }
        var allNews = data.hits;
        var eachNews = '';
        allNews.map(function(item, index, array){
            $('.question1').on('click', function(){
                eachNews += '<div>';
                eachNews += item.title;
                eachNews += '</div>';
                $('.answer1').html(eachNews);
            });
        });
    });
});

//Geolocation data
if(navigator.geolocation){
    // navigator.geolocation.getCurrentPosition(function(position){
    //     $('.answer2').html('latitude: ' + position.coords.latitude + ' longitude: ' + position.coords.longitude);
    // });
}

// ++++ ES6 +++++
//JavaScript is based on EcmaScript (European Computer Manufacturer Associations)

//const con't be change once assigned
const myname = 'Alex'

//let unlike var it is not part of the window object
if(true){
    let name = 'Ryan'; //it is defined in the if scope
}
// Template Strings, better option to string concatenation
let fname = 'Ryan';
let lname = 'Dew';
let fullName = fname + ' ' + lname + ' '
let fullName1 = `${fname} ${lname}`

//Default parameters
function welcome(name='Sir', message='Good day'){
    console.log(`Hello ${name} ${message}`)
}

//Arrow functions Anonymous Functions
function greeting(message){
    return console.log(`${message} everyone!`)
}

let greet = (message) => console.log(`${message} everyone`);
greet("Hello");

let greet1 = () => console.log('No arguments');
greet1();

//Arrow functions and this keyword
let nepal = {
    mountains: ['Everest', 'Fish'],
    printWith: function(){
        setTimeout(()=>console.log(this.mountains.join('-')), 3000);
    }
};

nepal.printWith();

//Destructing objects
let { mountains } = nepal;
console.log('Destructor' + mountains);

//using destructing
let uniStudent1 = student => {
    console.log(`${student.name} from ${student.univ}`)
}
let uniStudent = ({name,univ}) => {
    console.log(`${name} fron ${univ}`)
};

uniStudent({
    name:'Ryan',
    univ: 'Univ student'
});

//Destructing array
let [firstM] = ['Mount1', 'Mount2'];
console.log(firstM);

//Restructuring
var name2 = 'Ryan';
var output2 = function(){
    console.log(`${name2}`)
}
var myObj = {name2,output2};
myObj.output2();

myObj1 = {
    name2: 'Ryan',
    output2: function(){
        console.log(`${name2}`)
    },
    //ES6
    out3(){ console.log('ES6')}  
}

myObj1.out3()

//Spread and Rest Operator.
//Combbine two arrays or obejcts without modifying the original
var mountains1 = ['Everets', 'Fish'];
var mountains2 = ['Fuji'];
var allm = [...mountains1, ...mountains2];
console.log(allm);

var night = {
    breakfast: 'toast',
   lunch: 'rice' 
};

var day = {
    dinner: 'meat',
    app: 'bread'
};

var food = {...day, ...night};
console.log(food);

var {onefood, ...rest} = food;
console.log(rest);

//Constructors
//functions are objects
function Holiday(destination, days){
    this.destination = destination
    this.days = days
}
Holiday.prototype.info = function(){
    console.log(this.destination +  ' | ' + this.days);
}

var nep = new Holiday('Nepal', 30);
console.log(nep.info());

class Holi{
    constructor(destination, days){
        this.destination = destination;
        this.days = days;
    }
    info(){
        console.log(`${this.destination} will take ${this.days} days`)
    }
}
const trip = new Holi('Katmadu', 30)
console.log(trip.info());
class Expedition extends Holi{
    constructor(destination, days, gear){
        super(destination, days);
        this.gear = gear;
    }
    //override
    info(){
        super.info();
        console.log(`Bring your ${this.gear.join(' and your')}`);
    }
}

const tripWithGear = new Expedition('Everest', 30, ['Sunglasses', 'Flags', 'Camera']);
tripWithGear.info();

//Installing React
//1.- Install nodejs.org You can type node -v To seemif npm is installed npm -v
// brew install node  This will install node and npm
// brea install yarn
// To create a new application create a directory and run
// npx create-react-app reactapp or create-react-app reactapp
// cd reactapp npm start or yarn start

//File structure. See reapp

//https://github.com/axios/axios for Http client request
// Install it with yarn add axios
// https://randomuser.me  for random data api calls
//https://api.randomuser.me/?nat=US&results=5
//you can add react chrome extension for better development
//In react everytime you want to use javascript use {}

// React Redux Firebase
//https://www.youtube.com/watch?v=8lwHTR1ER-Q
//https://www.youtube.com/watch?v=8lwHTR1ER-Q&list=PL7gBJKednu-rZiR5xqJial4JdwOE2CwPB

// React CDN 
// https://reactjs.org
// if you have an exsting a[plication you can start adding react adding script as shown in the HTML found
// in the above link Quick Start 

// let can be change
// const can't be changed unless is an array or object
//https://jsbin.com to run java script html css
// react bootstrap
// (npm install react-bootstrap bootstrap Beta)
// (npm install --save react react-dom)
// npm install --save react-bootstrap

// Life Cycle Methos in React
// - Mounting -
// constructor()
// componentWillMount()
// render()
// componentDidMount
// - Updating -
// componentWillReceiveProps()
// shouldComponentUpdate()
// componentWillUpdate()
// render()
// componentDidUpdate()
// - Unmounting -
// componentWillUnmount()

// prop type checks the props passed from parent to children
// npm install --save prop-types
// PropTypes.array , bool, func, numbder, object, string

// High Order Components
// They are the same as high order functions but take and return components

// Sorting
// We can use a utility lodash
// npm install --save lodash
// https://lodash.com

// Lifting State
// The process of factoring substate from a component to another component is called lifting state.

//react router dom
//npm install --save react-router-dom
//https://reacttraining.com/react-router/web/guides/quick-start

