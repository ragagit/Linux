package main

import (
	"fmt"
	"time"
	"sync"
)

// go functions (Threads)
func sayIt( msg string){
	for i:=0;i<5;i++ {
		fmt.Println(msg)
		time.Sleep(100 * time.Millisecond)
	}
}



func doGoFunc(){

	//like threads
	go sayIt("World")
	sayIt("Hello")

}

//Channels
func sum(s []int, c chan int) {
	sum := 0
	for _, v := range s {
		sum += v
	}
	c <- sum // send sum to c
}


func doChannels(){

	s := []int{7, 2, 8, -9, 4, 0}

	c := make(chan int)
	go sum(s[:len(s)/2], c)
	go sum(s[len(s)/2:], c)
	x, y := <-c, <-c // receive from c

	fmt.Println(x, y, x+y)

}

//Buffered channels
func doBufferedChannels()  {
	ch := make(chan int, 2)
	ch <- 1
	ch <- 2
	fmt.Println(<-ch)
	fmt.Println(<-ch)
}

func fibonacci(n int, c chan int) {
	x, y := 0, 1
	fmt.Println("Fibonacci")
	for i := 0; i < n; i++ {
		c <- x
		x, y = y, x+y
	}
	close(c)
}



func doCloseChannel(){

	c := make(chan int, 10)
	go fibonacci(cap(c), c)

	//The loop for i := range c receives values from the channel repeatedly until it is closed.
	for i := range c {
		fmt.Println(i)
	}

	//Receivers can test whether a channel has been closed by assigning a second parameter to the receive expression: after
	//
	v, ok := <-c
	fmt.Println(v,ok)


}


//select

func fibonaccii(c, quit chan int) {
	x, y := 0, 1
	for {
		select {
		case c <- x:
			x, y = y, x+y
		case <-quit:
			fmt.Println("quit")
			return
		}
	}
}

func doSelect(){
	c := make(chan int)
	quit := make(chan int)

	go func() {
		for i := 0; i < 10; i++ {
			res:=<-c
			fmt.Println(res)
		}
		quit <- 0
	}()
	fibonaccii(c, quit)
	close(c)
	close(quit)
}

//Default selection
func doDefualtSelect(){
	tick := time.Tick(100 * time.Millisecond)
	boom := time.After(500 * time.Millisecond)
	for {
		select {
		case <-tick:
			fmt.Println("tick.")
		case <-boom:
			fmt.Println("BOOM!")
			return
		default:
			fmt.Println("    .")
			time.Sleep(50 * time.Millisecond)
		}
	}
}

//Mutex
// SafeCounter is safe to use concurrently.
type SafeCounter struct {
	v   map[string]int
	mux sync.Mutex
}

// Inc increments the counter for the given key.
func (c *SafeCounter) Inc(key string) {
	c.mux.Lock()
	// Lock so only one goroutine at a time can access the map c.v.
	c.v[key]++
	c.mux.Unlock()
}

// Value returns the current value of the counter for the given key.
func (c *SafeCounter) Value(key string) int {
	c.mux.Lock()
	// Lock so only one goroutine at a time can access the map c.v.
	defer c.mux.Unlock()
	return c.v[key]
}

func doMutex(){
	c := SafeCounter{v: make(map[string]int)}
	for i := 0; i < 1000; i++ {
		go c.Inc("somekey")
	}

	time.Sleep(time.Second)
	fmt.Println(c.Value("somekey"))
}

func main() {

	////go functions are like threads
	//doGoFunc()
	//
	//// Channels are a typed conduit through which you can send and
	//// receive values with the channel operator, <-.
	//doChannels()
	//
	////Buffered channels
	//doBufferedChannels()
	//
	////Close channels
	//doCloseChannel()
	//
	//select
	doSelect()

	//The default case in a select is run if no other case is ready.
	//Use a default case to try a send or receive without blocking
	doDefualtSelect()

	//Mutex
	doMutex()

}
