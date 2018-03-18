/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function sayHello() {
    $("#hello").html("Hello World!");
}

//Objects
//Arrays (pop and push
//Self invoking function

//Number methods
//toFixed(), toString()

//String methods
//toLowerCase()
//toUpperCase()
//charAt()
//concat()
//indexOf()
//length()
//slice()
//split()
//substr

//Array methods
//forEach() calls a function for each element of the array
//join() join all elemnts to form a string
//filter()
//reverse()
//sort()

//DOM is stored in a tree form

//Selectors. $('selector').action(); 
//There are four forms:
/*
 * 1.- Element selector selecting HTML elements
 *      $("p");
 *      
 * 2.- Class selctor
 *      $(".classname");
 *      
 * 3.- ID selector
 *      
 *      $("#idName");
 *      
 * 4.- Attribute selector
 * 
 *      $("[Attribute"]);
 */

function doSelectors() {

    $(".myclass").text("class text change from jquery");
    $("#myid").text("id text change from jquery");
    //$("p").css("color", "red");

}

//Advance selectors
/*
 *  $(*) Slects all elements
 *  $("div:first") Selects the first <div> element
 *  $("ul li:first") Selects the first <li> element of the list <ul>
 *  $("ul li:first-child") Selects the first <li> element of the every <ul>
 *  $("ul li:eq(2)") Selects second <li>
 *  $("ul li:nth-child(2)") Selects nth-child <li> of the every parent <ul>
 *  $("ul" li:even") Selects all <li> with even indexes.
 *  $("ul li:odd") Selects all <li> with odd indexes.            
 *               
 */
function doAdvancedSelectors() {

    $("ul li:first").css("color", "blue");

    $("ul li:first-child").css("color", "green");

    $("ul li:eq(2)").css("color", "orange");

    $("ul li:nth-child(2)").css("color", "brown");

    $("ul li:even").css("font-size", "28px");

    //$("*").css("color", "red");

}

//Attributes
/*
 * Get Attributes values
 * $("selector").attr("attribute_name")
 * 
 * Set Attributes values
 * $("selector").attr("attribute_name", "value")
 * 
 * Set Multiple attributes
 * $("selector").attr({"attribute_name" : "value", "attribute_name" : value"})
 */
function doAttributes() {

    var val = $("a").attr("name");
    console.log("attribute value of name is:" + val);

    $("a").attr("href", "http://www.tutorialspoint.com");
    $("a").attr({"href": "http://www.tutorialspoint.com", "target": "_blank"});


}

//Attribute Functions
/*
 * removeAttr("attribute_name") Removes attribute from selected element
 * hasClass("class_name") Check if the class is present in selected element
 * addClass("class_name") Add a class to selected elemnt
 * removeClass("class_name") Remove class from selected element
 * toggleClass("class_name") Adds the class if not present removes it if present
 */
function doAttributeFunctions() {

    $("a").removeAttr("target");
    console.log($("a").hasClass("someclass"));
//    $("p").click(function(){
//        $("p").toggleClass("pclass");
//    });
    console.log($("p").hasClass("pclass"));
}

// DOM Traversing
/*
 * Moving Up
 * parent() Returns the direct parent of the element
 * parents() Returns all parents of the element.
 * parentsUntil Returns parents until the element specified as argument
 * 
 * Moving Down
 * children() Return all children of the element down a single level
 * Find() Returns all the descendent elements which match one specified elemnt
 * 
 * Moving on the same level
 * siblings() Returns all elements at the same level.
 * next()/prev() Returns the next/previous adjacent sibling of the elemnt
 * nextAll()/prevAll() Returns all the next/previous sibling elemments of the elemnt
 * nextUntil/prevUntil() Returns all the next/previous adjacent sibling until the specified argument.
 * 
 * Filtering
 * filter() Returns elements which satisfies the criteria specified in function.
 * not() Returns elements which do not satisfy the criteria specified in function
 * eq() Returns element which matches the specified index number.
 * first()/last() Returns first/last element of selected element
 */
function doDOMTraversing() {

    var parentDisp = $("#test").parent();
    var parentDisps = $("#test").parents();
    var parentsUntilDisp = $("#test").parentsUntil("div");

    console.log("parentDisp:" + parentDisp);
    console.log("parentDisps:" + parentDisps);
    console.log("parentUntilDisp:" + parentsUntilDisp);

    var findDemo = $("#remText").find("p");
    console.log("find", findDemo);

    var childDemo = $("ol").children();
    console.log("children", childDemo);

    var sibDemo = $("second-child").siblings();
    console.log("siblings", sibDemo);

    var nextDemo = $("#second-child").next();
    console.log("Next", nextDemo);

    var filterDemo = $("li").filter(".inner-child");
    console.log("Filter", filterDemo);

}

//CSS Methods
/*
 * css() Sets or return css properties od selected element
 * width()/height() Sets or return the width/height of element
 * InnerHeight()/InnerWidth() Sets or returns the height/width with padding
 * outerHeight()/outerWidth() Sets or returns the height/width with padding and borders.
 * offset() Gets or sets the coordinates of selected element.
 * 
 */
function doCSSMethods() {

    var retCss = $(".mydiv").css("background-color");
    console.log("background color" + retCss);

    //$(".mydiv").css("height", '200px');

    $(".mydiv").css({"height": '200px', "Width": "300px", "font-size": "30px", "padding": "20px"});

    var h = $(".mydiv").height();
    console.log("Height:" + h);

    var w = $(".mydiv").width();
    console.log("Width:" + w);

    var ih = $(".mydiv").innerHeight();
    console.log("InnerHeight:" + ih);

    var myoffset = $(".mydiv").offset();
    console.log("Offset:" + myoffset);

    //$(".mydiv").offset({"top":"55", "left":"25"});
}

//DOM Manipulation
/*
 * Editing content
 * text() sets or return the text content of element
 * val() sets or returns the values of the form fields
 * html() sets or return the content of element with html markup
 * 
 * Adding content
 * append() places content at the end of selected element
 * prepend() places content at the start of selected element.
 * 
 * Removing content
 * remove() Remove element including its child elemnts
 * empty() Removes all the child elemnt of the selected element.
 * 
 */
function doDOMManipulation() {

    var textDisp = $("#domid").text();
    console.log("textDisp", textDisp);

    var htmlDisp = $("#domid").html();
    console.log("htmlDisp", htmlDisp);

    var valDisp = $("#testVal").val();
    console.log("valDisp", valDisp);

    $("#mytest").text("Adding some text");
    $("#mytest1").html("Adding html <b>text</b>");
    $("#mytest1").val("Prank");

    $("#mytest").append(" Appending text");
    $("#mytest").prepend("Insering text ");
    $("#domid").remove();
    //$("#mytest").empty();

}
//Events
/*
 * Using bind()
 * $(selector).bind(eventType, eventData, handlerFunction);
 * 
 * Using event method directly
 * $(selector).click(handlerFunction);
 * $(selector).hover(handlerFunction);
 * 
 * Removing event handler
 * $(selector).unbind(eventType, handlerFunction);
 * 
 * 
 */
function doEvents() {

//    $("#lev1").bind("click", function(){
//        $(".linkMod").toggleClass("imRed");
//    });

    $("#lev1").click(function () {
        $(".linkMod").toggleClass("imRed");
    });

}

function echoText() {
    var elem1 = document.getElementById("myi");
    var elem2 = document.getElementById("myp");

    elem2.innerHTML = elem1.value;
}
function echoText1() {
    alert('You clicked me');
}
function echoText3() {
    $("#myi").keypress(function () {
        $("#myp").text($("#myi").val());
    });

}

//Event Object Attribute
/*
 * The handler function takes an event object as a parameter when it is called
 * 
 * Available Event Attributes
 * target
 * type
 * keyCode
 * screenX, screenY
 * timeStamp
 * altKey
 * ctrlKey
 * 
 */
function doEventObjAttr() {

    $("#IdObj").click(function (e) {
        console.log("Target:", e.target);
        console.log("Type:", e.type);
        console.log("TimeStamp:", e.timeStamp);
        console.log("Ctrl Key:", e.ctrlKey);
        console.log("ScreenX:", e.screenX);
    });

    $("#InIdObj").keypress(function (event) {
        var keyPressed = event.keyCode;
        console.log("key code:", keyPressed);
    });
}



















