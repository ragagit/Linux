//Packages
package main

//Imports
import (
	"fmt"
	"math/rand"
	"math"
	"math/cmplx"
	"runtime"
	"time"

	"strings"
)

func add1(x, y int) int {
	return x + y
}

func add(x int, y int) int {
	return x + y
}

//Multiple results
func swap(x, y string) (string, string) {
	return y, x
}

//Named return values
func split(sum int) (x, y int) {
	x = sum * 4 / 9
	y = sum - x
	return
}

func needInt(x int) int {
	return x*10 + 1
}

func needFloat(x float64) float64 {
	return x * 0.1
}

func sqrt(x float64) string {
	if x < 0 {
		return sqrt(-x) + "i"
	}
	return fmt.Sprint(math.Sqrt(x))
}

func pow(x, n, lim float64) float64 {
	if v := math.Pow(x, n); v < lim {
		return v
	}
	return lim
}

func pow1(x, n, lim float64) float64 {
	if v := math.Pow(x, n); v < lim {
		return v
	} else {
		fmt.Printf("%g >= %g\n", v, lim)
	}
	// can't use v here, though
	return lim
}

func printSlice(s []int) {
	fmt.Printf("len=%d cap=%d %v\n", len(s), cap(s), s)
}

func compute(fn func(float64, float64) float64) float64 {
	return fn(3, 4)
}

func adder() func(int) int {
	sum := 0
	return func(x int) int {
		sum += x
		return sum
	}
}

func main() {

	//Exported Names start with Capital, if not they can't be used outside the package
	fmt.Printf("My favorite number %d\n", rand.Intn(10))
	fmt.Printf("Now you have %g problem\n", math.Sqrt(7))

	//Functions
	fmt.Printf("Calling add function:%d\n", add( 7, 3))

	//Functions you can omit type if all are the same but the last
	fmt.Printf("Calling add1 function:%d\n", add1( 5, 3))

	//Multiple results
	a, b := swap("hello", "world")
	fmt.Println(a, b)

	//Named returned values
	x1, y1 := split(17)
	fmt.Printf("x:%d, y:%d\n", x1, y1 )

	//Variables
	var x int
	var c, python, java bool
	x = 7
	x = 5
	fmt.Println(x, c, python, java)

	//Short variable declaration. Implicit type. Valid only inside a function.
	v := 4.1
	fmt.Println(v)
	v = 7.1
	fmt.Println(v)

	//Basic types

	// bool
	// string
	// int  int8  int16  int32  int64
	// uint uint8 uint16 uint32 uint64 uintptr
	// byte // alias for uint8
	// rune // alias for int32 represents a Unicode code point
	// float32 float64
	// complex64 complex128

	var (
		ToBe   bool       = false
		MaxInt uint64     = 1<<64 - 1
		z      complex128 = cmplx.Sqrt(-5 + 12i)
	)
	fmt.Printf("Type: %T Value: %v\n", ToBe, ToBe)
	fmt.Printf("Type: %T Value: %v\n", MaxInt, MaxInt)
	fmt.Printf("Type: %T Value: %v\n", z, z)

	//Zero values when variables don't get assign one
	var i int
	var f float64
	var d bool
	var s string
	fmt.Printf("%v %v %v %q\n", i, f, d, s)

	//Type conversion
	var xx, yy int = 3, 4
	var ff float64 = math.Sqrt(float64(xx*xx + yy*yy))
	var zz uint = uint(ff)
	fmt.Println(xx, yy, zz)

	//Type Inference
	vv := 42 // change me!
	fmt.Printf("v is of type %T\n", vv)
	vv = 7
	fmt.Printf("v is of type %T and value:%d\n", vv, vv)

	//Constants, use const can't use :=
	const Pi = 3.1416

	//Numeric constants
	//Numeric constants are high-precision values.
	//An untyped constant takes the type needed by its context.
	const (
		Big = 1 << 100
		Small = Big >> 99
	)
	fmt.Println(needInt(Small))
	fmt.Println(needFloat(Small))
	fmt.Println(needFloat(Big))

	//for loop
	sum := 0
	for i := 0; i < 10; i++ {
		sum += i
	}
	fmt.Println(sum)

	sum = 1
	for ; sum < 1000; {
		sum += sum
	}
	fmt.Println(sum)

	//while is the for
	sum = 1
	for sum < 1000 {
		sum += sum
	}
	fmt.Println(sum)

	//Forever for{ }

	//if
	fmt.Println(sqrt(2), sqrt(-4))

	//if with a short statement. Valid only on the short statement
	fmt.Println(
		pow(3, 2, 10),
		pow(3, 3, 20),
	)

	//if else
	fmt.Println(
		pow1(3, 2, 10),
		pow1(3, 3, 20),
	)

	//switch
	fmt.Print("Go runs on ")
	switch os := runtime.GOOS; os {
	case "darwin":
		fmt.Println("OS X.")
	case "linux":
		fmt.Println("Linux.")
	default:
		// freebsd, openbsd,
		// plan9, windows...
		fmt.Printf("%s.", os)
	}

	fmt.Println("When's Saturday?")
	today := time.Now().Weekday()
	switch time.Saturday {
	case today + 0:
		fmt.Println("Today.")
	case today + 1:
		fmt.Println("Tomorrow.")
	case today + 2:
		fmt.Println("In two days.")
	default:
		fmt.Println("Too far away.")
	}

	//switch without condition a clean way to long if else
	t := time.Now()
	switch {
	case t.Hour() < 12:
		fmt.Println("Good morning!")
	case t.Hour() < 17:
		fmt.Println("Good afternoon.")
	default:
		fmt.Println("Good evening.")
	}

	//Defer
	//defer fmt.Println("world")
	//fmt.Println("hello")

	//Stacking defer
	//fmt.Println("counting")

	//for i := 0; i < 5; i++ {
	//	defer fmt.Println(i)
	//}

	fmt.Println("done")

	//Pointers
	i, j := 42, 2701
	p := &i         // point to i
	fmt.Println(*p) // read i through the pointer
	*p = 21         // set i through the pointer
	fmt.Println(i)  // see the new value of i
	p = &j         // point to j
	*p = *p / 37   // divide j through the pointer
	fmt.Println(j) // see the new value of j

	//struct
	type Vertex struct {
		X int
		Y int
	}

	fmt.Println(Vertex{1, 2})

	//struct fields are accessed using a dot
	vvv := Vertex{1, 2}
	vvv.X = 4
	fmt.Println(vvv.X)

	//pointer to struct (*p).X is the same as p.X
	pp := &vvv
	pp.X = 1e9
	fmt.Println(vvv)

	//struct literal
	var (
		v1 = Vertex{1, 2}  // has type Vertex
		v2 = Vertex{X: 1}  // Y:0 is implicit
		v3 = Vertex{}      // X:0 and Y:0
		p1 = &Vertex{1, 2} // has type *Vertex
	)
	fmt.Println(v1, p1, v2, v3)

	//Arrays
	var aa [2]string
	aa[0] = "Hello"
	aa[1] = "World"
	fmt.Println(aa[0], aa[1])
	fmt.Println(aa)

	primes := [6]int{2, 3, 5, 7, 11, 13}
	fmt.Println(primes)

	//Slice it is declared a[ low : high ]. This excludes the last element
	//It doesn't store data it is just a section of the underlying array.
	//A change in the slice is a change in the array.
	primes1 := [6]int{2, 3, 5, 7, 11, 13}
	var s1 []int = primes1[1:4]
	fmt.Println(s1)

	names := [4]string{
		"John",
		"Paul",
		"George",
		"Ringo",
	}
	fmt.Println(names)

	a5 := names[0:2]
	b5:= names[1:3]
	fmt.Println(a5, b5)

	b5[0] = "XXX"
	fmt.Println(a5, b5)
	fmt.Println(names)

	//A slice literal is like an array literal without the length.
	q := []int{2, 3, 5, 7, 11, 13}
	fmt.Println(q)

	r := []bool{true, false, true, true, false, true}
	fmt.Println(r)

	st := []struct {
		i int
		b bool
	}{
		{2, true},
		{3, false},
		{5, true},
		{7, true},
		{11, false},
		{13, true},
	}
	fmt.Println(st)

	//slice defaults
	//When slicing, you may omit the high or low bounds to use their defaults instead.
	//The default is zero for the low bound and the length of the slice for the high bound.
	s3 := []int{2, 3, 5, 7, 11, 13}
	s3 = s3[1:4]
	fmt.Println(s3)
	s3 = s3[:2]
	fmt.Println(s3)
	s3 = s3[1:]
	fmt.Println(s3)

	//The length of a slice is the number of elements it contains.
	//The capacity of a slice is the number of elements in the underlying array,
	//counting from the first element in the slice.
	s4 := []int{2, 3, 5, 7, 11, 13}
	printSlice(s4)

	// Slice the slice to give it zero length.
	s4 = s4[:0]
	printSlice(s4)

	// Extend its length.
	s4 = s4[:4]
	printSlice(s4)

	// Drop its first two values.
	s4 = s4[2:]
	printSlice(s4)

	//Nil slices
	var s5 []int
	fmt.Println(s5, len(s5), cap(s5))
	if s5 == nil {
		fmt.Println("nil!")
	}

	//Creating a slice with make
	a2 := make([]int, 5)  // len(a)=5
	b2 := make([]int, 0, 5) // len(b)=0, cap(b)=5
	fmt.Println(a2)
	b2 = b2[:cap(b2)] // len(b)=5, cap(b)=5
	b2 = b2[1:]      // len(b)=4, cap(b)=4

	//Slices of slices
	board := [][]string{
		[]string{"_", "_", "_"},
		[]string{"_", "_", "_"},
		[]string{"_", "_", "_"},
	}

	// The players take turns.
	board[0][0] = "X"
	board[2][2] = "O"
	board[1][2] = "X"
	board[1][0] = "O"
	board[0][2] = "X"

	for i := 0; i < len(board); i++ {
		fmt.Printf("%s\n", strings.Join(board[i], " "))
	}

	var s7 []int
	printSlice(s7)

	// append works on nil slices.
	s7 = append(s7, 0)
	printSlice(s7)
	// The slice grows as needed.
	s7 = append(s7, 1)
	printSlice(s7)
	// We can add more than one element at a time.
	s7 = append(s7, 2, 3, 4)
	printSlice(s7)

	//Range
	//The range form of the for loop iterates over a slice or map.
	//When ranging over a slice, two values are returned for each iteration.
	//The first is the index, and the second is a copy of the element at that index.
	var pow = []int{1, 2, 4, 8, 16, 32, 64, 128}
	for i, v := range pow {
		fmt.Printf("2**%d = %d\n", i, v)
	}

	//You can skip the index or value by assigning to _.
	//If you only want the index, drop the ", value" entirely.
	pow1 := make([]int, 10)
	for i := range pow1 {
		pow1[i] = 1 << uint(i) // == 2**i
	}
	for _, value := range pow1 {
		fmt.Printf("%d\n", value)
	}

	//Maps
	//var m map[string]Vertex
	m := make(map[string]Vertex)
	m["Bell Labs"] = Vertex{
		40, -74,
	}
	fmt.Println(m["Bell Labs"])

	//maps literals
	//Map literals are like struct literals, but the keys are required.
	var m1 = map[string]Vertex{
		"Bell Labs": Vertex{
			40, -74,
		},
		"Google": Vertex{
			37, -122,
		},
	}
	fmt.Println(m1)

	var m2 = map[string]Vertex{
		"Bell Labs": {40, -74},
		"Google":    {37, -122},
	}
	fmt.Println(m2)

	//mutating maps
	m3 := make(map[string]int)

	m3["Answer"] = 42
	fmt.Println("The value:", m["Answer"])

	m3["Question"] = 48
	fmt.Println("The value:", m["Answer"])

	delete(m3, "Answer")
	fmt.Println("The value:", m["Answer"])

	h, ok := m3["Answer"]
	fmt.Println("The value:", h, "Present?", ok)

	//Function values
	hypot := func(x, y float64) float64 {
		return math.Sqrt(x*x + y*y)
	}
	fmt.Println(hypot(5, 12))

	fmt.Println(compute(hypot))
	fmt.Println(compute(math.Pow))

	//Closures. References a variable from outside its body
	pos, neg := adder(), adder()
	for i := 0; i < 10; i++ {
		fmt.Println(
			pos(i),
			neg(-2*i),
		)
	}

}


