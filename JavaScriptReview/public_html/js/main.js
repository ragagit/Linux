/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function DataTypes(){
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

function MyArray(){
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

function callback(num){
    console.log('callback:'+num);
}
function Loops(){
    
    for(var i=0; i < 5; i++){
        console.log('for loop:' + i);
    }
    
    i = 0;
    while( i < 5 ){
        console.log('while loop:' + i++);
    }
    
    var numbers = [4,2,8,7];
    numbers.forEach(function(num){
        console.log('forEach:'+num);
    });
    
    numbers.forEach(callback);
    
}

function Conditionals(){
    
    var var1 = 0;
    var var2 = 0;
    if( var1 == 0 && var2 == var1)
        console.log('equal');
    else
        console.log('not eqaul');
    
    var fruit = 'apple';
    
    switch(fruit){
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

function Objects(){
    
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
        fullName: function(){
            return this.firstName + ' ' + this.lastName;
        }
        
    };
    
    console.log(person.firstName);
    console.log(person.fullName());
    
    //Object constructor
    var apple = new Object();
    apple.color = 'red';
    apple.shape = 'round';
    apple.describe = function(){
        return 'Apples are good';
    };
    console.log(apple.describe());
    
    //Constructor Pattern
    function Fruit(name, color, shape){
        this.name = name;
        this.color = color;
        this.shape = shape;
        this.describe=function () {
            return 'A ' +  this.name + ' has a color ' + this.color; 
        };
    }
    
    var apple1 = new Fruit('apple', 'red', 'round');
    console.log(apple1);
    console.log(apple1.describe());
    
    //Array of objects
    var users = [
        {
            name:'John',
            age:30
        },
        {
            name:'Vic',
            age:45
        },
        {
            name:'Sonia',
            age:55
        }
             
    ];
    
    console.log(users[1]);
    
    var Fruits = [new Fruit('banana', 'yellow', 'long'), new Fruit('pear','green','round')];
    console.log(Fruits[0].describe());
    
}
