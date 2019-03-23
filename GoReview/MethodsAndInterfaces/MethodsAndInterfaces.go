package main

import (
	"fmt"
	"math"
	"time"
	"strings"
	"io"
)

//Methods
//Go doesn't have classes but you can define methods
//A method is a function with a special receiver argument.
//The receiver appears in its own argument list between the func keyword and the method name.
//You can only declare a method with a receiver whose type is defined in the same package as the method
type Vertex struct {
	X, Y float64
}

func (v Vertex) Abs() float64 {
	return math.Sqrt(v.X*v.X + v.Y*v.Y)
}

func doMethods(){
	v := Vertex{2.3, 4.5}
	v.Abs()
	fmt.Println(v.Abs())

	v.Scale(10)
	fmt.Println(v)

}

//Pointer receivers
func (v *Vertex) Scale(f float64) {
	v.X = v.X * f
	v.Y = v.Y * f
}

func Scale(v *Vertex, f float64) {
	v.X = v.X * f
	v.Y = v.Y * f
}

func ScaleFunc(v *Vertex, f float64) {
	v.X = v.X * f
	v.Y = v.Y * f
}

//In general, all methods on a given type should have either value or pointer receivers,
//but not a mixture of both.
func doPointerReceivers(){
	v := Vertex{2.3, 4.5}
	v.Scale(10)
	fmt.Println(v)

	Scale(&v, 10)
	fmt.Println(v)

	p := &Vertex{4, 3}
	p.Scale(3)
	ScaleFunc(p, 8)
	fmt.Println(p.X, p.Y)
}

//Interfaces
type Abser interface {
	Abs1() float64
}

type MyFloat float64

func (f MyFloat) Abs1() float64 {
	if f < 0 {
		return float64(-f)
	}
	return float64(f)
}


func (v *Vertex) Abs1() float64 {
	return math.Sqrt(v.X*v.X + v.Y*v.Y)
}
func doInterfaces(){
	var a Abser
	f := MyFloat(-math.Sqrt2)
	v := Vertex{3, 4}


	a = f  // a MyFloat implements Abser
	a = &v // a *Vertex implements Abser

	// In the following line, v is a Vertex (not *Vertex)
	// and does NOT implement Abser.
	//a = v

	fmt.Println(a.Abs1())
}

//Interfaces are implemented implicitly
type I interface {
	M()
}

type T struct {
	S string
}

type TT struct {
	S string
}
// This method means type T implements the interface I,
// but we don't need to explicitly declare that it does so.
func (t T) M() {

	fmt.Println(t.S)
}

func (t *TT) M() {
	if t == nil {
		fmt.Println("<nil>")
		return
	}
	fmt.Println(t.S)
}

func describe(i I) {
	fmt.Printf("(%v, %T)\n", i, i)
}

func describe1(i interface{}) {
	fmt.Printf("(%v, %T)\n", i, i)
}

func doInterfaceImpl(){
	var t T = T{"Hello"}
	var t1 T
	t1.S = "World"
	t.M()
	t1.M()
	var ifVar I
	ifVar = t1
	ifVar.M()

	//Implicit
	var i I = T{"Message"}
	i.M()

	fmt.Printf("(%v, %T)\n", i, i)

	//Interface values with nil underlying values
	var ii I
	var tt *TT
	ii = tt
	describe(ii)
	ii.M()

	//Interface values run time error
	var myInt I
	describe(myInt)
	//myInt.M()

}

func doEmptyInterface(){
	var i interface{}

	i = 42
	describe1(i)
	i = "Hello"
	describe1(i)
}

//A type assertion provides access to an interface value's underlying concrete value.
func doTypeAssertion(){

	var i interface{} = "hello"

	s := i.(string)
	fmt.Println(s)

	s, ok := i.(string)
	fmt.Println(s, ok)

	f, ok := i.(float64)
	fmt.Println(f, ok)

	//f = i.(float64) // panic
	//fmt.Println(f)
}

func doSwitchType(i interface{}) {
	switch v := i.(type) {
	case int:
		fmt.Printf("Twice %v is %v\n", v, v*2)
	case string:
		fmt.Printf("%q is %v bytes long\n", v, len(v))
	default:
		fmt.Printf("I don't know about type %T!\n", v)
	}
}

type Person struct {
	Name string
	Age  int
}

func (p Person) String() string {
	return fmt.Sprintf("%v (%v years)", p.Name, p.Age)
}

func doStringers(){
	a := Person{"Arthur Dent", 42}
	z := Person{"Zaphod Beeblebrox", 9001}
	fmt.Println(a, z)
}

//Errors
//	type error interface {
//    Error() string
// 	}
type MyError struct {
	When time.Time
	What string
}

func (e *MyError) Error() string {
	return fmt.Sprintf("at %v, %s",
		e.When, e.What)
}

func run() error {
	return &MyError{
		time.Now(),
		"it didn't work",
	}
}

func doErrors(){

	if err := run(); err != nil {
		fmt.Println(err)
	}

	err1 := &MyError{time.Now(), "Some error"}
	fmt.Println(err1.Error())

}

//Readers

func doReader(){

	r := strings.NewReader("Hello, Reader!")

	b := make([]byte, 8)
	for {
		n, err := r.Read(b)
		fmt.Printf("n = %v err = %v b = %v\n", n, err, b)
		fmt.Printf("b[:n] = %q\n", b[:n])
		if err == io.EOF {
			break
		}
	}

}

func main() {

	doMethods()
	doPointerReceivers()
	doInterfaces()
	doInterfaceImpl()
	doEmptyInterface()
	doTypeAssertion()
	doSwitchType(23)
	doSwitchType("Message")
	doSwitchType(true)
	doStringers()
	doErrors()
	doReader()


}

