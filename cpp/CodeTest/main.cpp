/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.cpp
 * Author: raga
 *
 * Created on January 21, 2018, 9:17 PM
 */

#include <cstdlib>
#include <stdio.h>
#include <iostream>
#include <vector>
#include <sstream>
#include <math.h>
#include <map>
#include <set>
#include <deque>
#include "AsyncFilter.h"

using namespace std;

/*
 * 
 */

void doScan() {

    int a = 0, b = 0, c = 0;

    cout << "Trying to debug ..." << endl;


    fscanf(stdin, "%d,%d,%d", &a, &b, &c);

    int d = a + b + c;

    printf("d:%d", d);


}

void doFormats() {

    int i;
    unsigned long l;
    char c;
    float f;
    double d;

    scanf("%d %ld %c %f %lf", &i, &l, &c, &f, &d);

    printf("%d\n%ld\n%c\n%6.3f\n%12.9lf\n", i, l, c, f, d);

}

void doReverseNumbers() {

    int nums, num;
    vector<int> vect;

    fscanf(stdin, "%d", &nums);

    int nArray[nums];

    if (nums > 0) {
        for (int i = 0; i < nums; i++) {
            fscanf(stdin, "%d", &num);
            vect.push_back(num);
        }
    }

    for (vector<int>::iterator it = vect.end() - 1; it >= vect.begin(); it--) {
        cout << *it;
        cout << " ";
    }
}

void doStringStream() {

    stringstream ss;
    char c;


    //This is like sprintf()
    ss << 100 << ',' << 50;

    int first, second;

    //This is like sscanf
    ss >> first >> c >> second;

    cout << first << endl;
    cout << second << endl;


}

class Box {
private:
    int length, breadth, height;

public:

    Box() : length(0), breadth(0), height(0) {

    }

    Box(int l, int b, int h) : length(l), breadth(b), height(h) {

    }

    Box(const Box& other) {
        length = other.length;
        breadth = other.breadth;
        height = other.height;
    }

    int getLenght() {
        return length;
    }

    int getBreadth() {
        return breadth;
    }

    int getHeight() {
        return height;
    }

    long CalculateVolume() {
        return length * breadth * height;
    }

    bool operator<(const Box &b) {

        if (length < b.length)
            return true;
        else if ((breadth < b.breadth) && (length == b.length))
            return true;
        else if ((height < b.height) && (breadth == b.breadth) && (length == b.length))
            return true;
        else
            return false;
    }

    friend ostream& operator<<(ostream& out, const Box& B) {
        out << B.length << " " << B.breadth << " " << B.height;
        return out;
    }
};

void check2() {
    int n;
    cin>>n;
    Box temp;
    for (int i = 0; i < n; i++) {
        int type;
        cin>>type;
        if (type == 1) {
            cout << temp << endl;
        }
        if (type == 2) {
            int l, b, h;
            cin >> l >> b>>h;
            Box NewBox(l, b, h);
            temp = NewBox;
            cout << temp << endl;
        }
        if (type == 3) {
            int l, b, h;
            cin >> l >> b>>h;
            Box NewBox(l, b, h);
            if (NewBox < temp) {
                cout << "Lesser\n";
            } else {
                cout << "Greater\n";
            }
        }
        if (type == 4) {
            cout << temp.CalculateVolume() << endl;
        }
        if (type == 5) {
            Box NewBox(temp);
            cout << NewBox << endl;
        }

    }
}

class BadLengthException : public exception {
private:
    int n;
    char msg[20];

public:

    BadLengthException(int n) : n(n) {

    }
    //We have to override what() to change the message

    virtual char* what() {
        //stringstream ss;

        //ss << "Too short: " << n;
        //char msg[20];
        sprintf(msg, "%d", n);

        return (char*) msg;
    }


};

void doException() {

    if (true)
        throw BadLengthException(3);

}

class Server {
private:
   static int load;
   
public:

    static int compute(long long A, long long B) {
        //load += 1;
        if (A < 0) {
            throw std::invalid_argument("A is negative");
        }
        vector<int> v(A, 0);
        int real = -1, cmplx = sqrt(-1);
        if (B == 0) throw 0;
        real = (A / B) * real;
        int ans = v.at(B);
        return real + A - B*ans;
    }

    static int getLoad() {
        return load;
    }
};



void doMoreException() {

    int A, B;
    
    
    
    cin >> A >> B;
    
    try {

        int res = Server::compute(A, B);
        
    } catch (std::invalid_argument e) {
        cout << "Exception: " << e.what() << endl;
    } catch (std::bad_alloc e) {
        cout << "Not enough memory" << endl;
    } catch (std::exception e) {
        cout << "Exception: " << e.what() << endl;
    } catch (...) {
        cout << "Other Exception";
    }

}


class Person{
    
    protected:
    string name;
    int age;
    
    public:
    Person():name(""), age(0){
        
    }
    virtual void getdata(){
        
    }
    virtual void putdata(){
        
    }
};

class Professor: public Person{
    
   private:
    int publications;
    static int cur_id;
      
   public:
  
    void getdata(){
        cin >> name >> age >> publications;
        cur_id+=1;
    }
    
    void putdata(){
        cout << name << " " << age << " " << publications << " " << cur_id << endl;
    }
    
};

class Student: public Person{
    
   private:
    int marks[6];
    static int cur_id;
          
   public:
           
    void getdata(){
        cin >> name >> age;
        for(int i=0; i < 6; i++){
            cin >> marks[i];
        }
        cur_id+=1;
    }
    
    void putdata(){
        int sum = 0;
        for(int i=0; i< 6; i++){
            sum+= marks[i];
        }
        cout << name << " " << age << " " << sum << " " << cur_id << endl;
    }

};

int Professor::cur_id = 0;
int Student::cur_id = 0;

void doStatic(){
    int n, val;
    cin>>n; //The number of objects that is going to be created.
    Person *per[n];

    for(int i = 0;i < n;i++){

        cin>>val;
        if(val == 1){
            // If val is 1 current object is of type Professor
            per[i] = new Professor;
        }
        else per[i] = new Student; // Else the current object is of type Student

        per[i]->getdata(); // Get the data from the user.

    }

    for(int i=0;i<n;i++)
        per[i]->putdata(); // Print the required output for each object.

}

class stat{
    
private:
    static int id;
    
public:
    void showId(){
        cout << "The id is: " << id << endl;
    }
    
};

int stat::id = 1;

struct Node{
   Node* next;
   Node* prev;
   int value;
   int key;
   Node(Node* p, Node* n, int k, int val):prev(p),next(n),key(k),value(val){};
   Node(int k, int val):prev(NULL),next(NULL),key(k),value(val){};
};

class Cache{
   
   protected: 
   map<int,Node*> mp; //map the key to the node in the linked list
   int cp;  //capacity
   Node* tail; // double linked list tail pointer
   Node* head; // double linked list head pointer
   virtual void set(int, int) = 0; //set function
   virtual int get(int) = 0; //get function

};

class LRUCache: public Cache{
    private:
    int capacity;
    int used;
    map<int, Node*>::iterator it;
    Node *node;
    
    public:
    
    LRUCache( int capacity):capacity(capacity),  used(0){
      
    }
    
    void set( int key, int value ){
        node = new Node(key, value);
        
        if( used == 0){         
            mp.insert(make_pair(key, node));
            tail = node;
            head = node;
            used+=1;
        }
        else if(used < capacity){           
            it = mp.end();
            it->second->next = node;
            node->prev = it->second;
            mp.insert(make_pair(key, node));
            head=node;
            used+=1;
        }
        else{            
            it = mp.begin();
            node->next = it->second->next;
            node->prev = NULL;
            mp.erase(it);
            mp.insert(make_pair(key, node));
            tail = node;
        }
    }
    
    int get( int key ){
        it = mp.find(key);
        if( it != mp.end() )
            return it->second->value;
        else
            return -1;
    }
    
};

// doSort
bool comp(int i, int j ){
    return i < j;
}

void doSort(){
       int num;
    
    vector<int> numbers;
    
    cin >> num;
    
    if( num > 1 ){
                
        for(int i; i < num; i++){
            cin >> num;
            numbers.push_back(num);
        }
    }
    
    sort( numbers.begin(), numbers.end(), comp );
    
    for( vector<int>::iterator it = numbers.begin(); it != numbers.end(); it++)
        cout << *it << " "; 
}

void doErase(){
        int num_elements;
    int num;
    int posToDelete;
    int begin, end;
    vector<int> numbers;
    
    
    cin >> num_elements;
    
    for ( int i =0; i < num_elements; i++ ){
        cin >> num;
        numbers.push_back(num);
        
    }
    cin >> posToDelete;
    
    vector<int>::iterator it = numbers.begin();
    
    //The trick here is to substract 1 because start with position 0
    numbers.erase(it+posToDelete-1);
    
    for( it = numbers.begin(); it != numbers.end(); it++ )
        cout << *it << " ";
    
    cout << endl;
    
    cin >> begin >> end;
    
    it = numbers.begin();
    
    numbers.erase(it+begin-1, it+end-1);
    
    cout << numbers.size() << endl;
    
    for( it = numbers.begin(); it != numbers.end(); it++ )
        cout << *it << " ";
    
}

// lower_bound
void doLowerbound(){
    int numbs, num, q, search;
    vector<int> numbers;
    
    cin >> numbs;
    for(int i=0; i<numbs; i++){
        cin >> num;
        numbers.push_back(num);
    }
    
    cin >> q;
    
    vector<int>::iterator it, low;
    
    for( int j=0; j < q; j++ ){
        cin >> search;
        
        //vector doesn't have a find member function, you have to use std::find()
        it = find( numbers.begin(), numbers.end(), search);
        if( it != numbers.end()){
            cout << "Yes" << " " << it - numbers.begin() + 1 << endl;
        }else{
            low = lower_bound( numbers.begin(), numbers.end(), search );
            cout << "No" << " " << low - numbers.begin() + 1 << endl;
        }
    }
}


// do sets

void doSets(){
    
    set<int> numbers;
    int num_q;
    int q_type;
    int num;
    set<int>::iterator it;
    
    cin >> num_q;
    
    for(int i=0; i<num_q; i++){
        cin >> q_type;
        cin >> num;
        
        if( q_type == 1){
            numbers.insert(num);
        }else if( q_type == 2){
            it = numbers.find(num);
            if( it != numbers.end())
                numbers.erase(it);
        }else if( q_type == 3){
            it = numbers.find(num);
            if( it != numbers.end())
                cout << "Yes" << endl;
            else
                cout << "No" << endl;
        }
    }
}

void doMaps(){
    
    int num_q;
    string name;
    int marks;
    int q_type;
    map<string,int> students;
    map<string,int>::iterator it;
    
    cin >> num_q;
    
    for(int i=0; i<num_q; i++){
        
        cin >> q_type;
        
        if( q_type == 1){
            cin >> name >> marks;
            it = students.find(name);            
            if( it != students.end() ){
                it->second = it->second + marks;
                //students.erase(it);
            }else{         
                students.insert(make_pair(name, marks));
            }
        }else if( q_type == 2 ){             
            cin >> name;
            it = students.find(name);            
            if( it != students.end() ){
                it -> second = 0;
            }            
        }else if( q_type == 3 ){
            cin >> name;
            it = students.find(name);
            if( it != students.end() )
                cout << it->second << endl;
        }
        
    }
}

//Formats
//I didn't know how to pad with '_' 
void doFormatss(){
    
    int T; cin >> T;
	//cout << setiosflags(ios::uppercase);
	//cout << setw(0xf) << internal;
	while(T--) {
		double A; cin >> A;
		double B; cin >> B;
		double C; cin >> C;
              
            //%[flags][width][.precision][length]specifier 
            int x = (int)( 100 * A )  /  100.0;
            printf("0x%x\n", x);
            printf("%+15.2f\n", B);
            printf("%.9E\n", C);
        }
}

//Deque

void printKMax(int arr[], int n, int k){
   //Write your code here.
    deque<int> numbers;
    deque<int>::iterator it;
    int max = 0;
    
    for(int i = 0; i < n; i++ ){
        numbers.push_back(arr[i]);
    }
    
    cout << "Starting" << endl;
    
    for( deque<int>::iterator it=numbers.begin(); it != numbers.end(); it++){
        cout << "Number:" << *it << " ";
    }
    
    cout << endl;
    
    it = numbers.begin();
        for( int j = 0; j < (n - k + 1); j++ ){
            for( int l = 0; l < k; l++ ){
                cout << *it << endl;
                if( *it > max)
                    max = *it;
                it++;
            }
            cout << "Max:" << max << " ";
            it = numbers.begin() + j + 1;
            max = 0;
        }
    cout << endl;
   
    
}

void doDeque(){

    int array[] = { 3, 4, 6, 3, 4};
    printKMax(array, 5, 2);
    
}


class Rectangle{
    protected:
    int width, height;
    
    public:
    virtual void display(){
        cout << width << " " << height << endl;
    }
    
};

class RectangleArea : public Rectangle{
  
    public:
    
    void read_input(){
        cin >> width >> height;
    }
    
    void display(){
        cout << width * height << endl;
    }
    
};


//Inheritance

void doInheritance(){
    
RectangleArea r_area;
    
    /*
     * Read the width and height
     */
    r_area.read_input();
    
    /*
     * Print the width and height
     */
    r_area.Rectangle::display();
    
    /*
     * Print the area
     */
    r_area.display();
        
    
}

// Multilevel Inheritance

class Triangle{
   public:
      void triangle(){
         cout<<"I am a triangle\n";
      }
};

class Isosceles : public Triangle{
     public:
        void isosceles(){
          cout<<"I am an isosceles triangle\n";
        }
};

class Equilateral : public Isosceles{
    public:
    void equilateral(){
        cout << "I am an equilateral triangle" << endl;
    }
    
};

void doMultiInheritance(){
    
    Equilateral eqr;
    eqr.equilateral();
    eqr.isosceles();
    eqr.triangle();
    
}

// dynamic_cast
class Spell { 
    private:
        string scrollName;
    public:
        Spell(): scrollName("") { }
        Spell(string name): scrollName(name) { }
        virtual ~Spell() { }
        string revealScrollName() {
            return scrollName;
        }
};

class Fireball : public Spell { 
    private: int power;
    public:
        Fireball(int power): power(power) { }
        void revealFirepower(){
            cout << "Fireball: " << power << endl;
        }
};

class Frostbite : public Spell {
    private: int power;
    public:
        Frostbite(int power): power(power) { }
        void revealFrostpower(){
            cout << "Frostbite: " << power << endl;
        }
};

class Thunderstorm : public Spell { 
    private: int power;
    public:
        Thunderstorm(int power): power(power) { }
        void revealThunderpower(){
            cout << "Thunderstorm: " << power << endl;
        }
};

class Waterbolt : public Spell { 
    private: int power;
    public:
        Waterbolt(int power): power(power) { }
        void revealWaterpower(){
            cout << "Waterbolt: " << power << endl;
        }
};

class SpellJournal {
    public:
        static string journal;
        static string read() {
            return journal;
        }
}; 
string SpellJournal::journal = "";

void counterspell(Spell *spell) {
    
/* Enter your code here */
Spell *fb1 = dynamic_cast<Spell *>(spell);
    fb1->revealScrollName();

    Fireball *fb2 = dynamic_cast<Fireball *> (spell);
    if( fb2 )
        fb2 -> revealFirepower();  

    Frostbite *fb3 = dynamic_cast<Frostbite *> (spell);
    if( fb3 )
        fb3 -> revealFrostpower();  

    Thunderstorm *fb4 = dynamic_cast<Thunderstorm *> (spell);
    if( fb4 )
        fb4 -> revealThunderpower();  

    Waterbolt *fb5 = dynamic_cast<Waterbolt *> (spell);
    if( fb5 )
        fb5 -> revealWaterpower();  



}

class Wizard {
    public:
        Spell *cast() {
            Spell *spell;
            string s; cin >> s;
            int power; cin >> power;
            if(s == "fire") {
                spell = new Fireball(power);
            }
            else if(s == "frost") {
                spell = new Frostbite(power);
            }
            else if(s == "water") {
                spell = new Waterbolt(power);
            }
            else if(s == "thunder") {
                spell = new Thunderstorm(power);
            } 
            else {
                spell = new Spell(s);
                cin >> SpellJournal::journal;
            }
            return spell;
        }
};

void doDynamicCast(){
    int T;
    //cin >> T;
    Wizard Arawn;
    //while(T--) {
        Spell *spell = Arawn.cast();
        counterspell(spell);
    //}
}


// Templates
template <class T>
    class AddElements{
      private:
        T element1;
        
        public:
        AddElements(T element):element1(element){
            
        }
        
        T add( T element2 ){
            return element1 + element2;
        }
        
    };

    /* Specialized template */
template <>
class AddElements <string>{
    private:
    string element1;
    
    public:
    AddElements(string element ):element1(element){
        
    }
    string concatenate(string element2){
        return element1 + element2;
    }
};

void doTemplates(){
    
    int n,i;
  cin >> n;
  for(i=0;i<n;i++) {
    string type;
    cin >> type;
    if(type=="float") {
        double element1,element2;
        cin >> element1 >> element2;
        AddElements<double> myfloat (element1);
        cout << myfloat.add(element2) << endl;
    }
    else if(type == "int") {
        int element1, element2;
        cin >> element1 >> element2;
        AddElements<int> myint (element1);
        cout << myint.add(element2) << endl;
    }
    else if(type == "string") {
        string element1, element2;
        cin >> element1 >> element2;
        AddElements<string> mystring (element1);
        cout << mystring.concatenate(element2) << endl;
    }
  }
  
}

// Processors
#define minimum( a, b ) a<b?a:a=b
#define maximum( a, b ) a>b?a:a=b
#define foreach( v, i) for(int i=0; i < v.size(); i++)
#define io {int j;cin >> j; v[i] = j;}
#define toStr(x) #x
#define INF 1
#define FUNCTION( a, b ) 

#if !defined toStr || !defined io || !defined FUNCTION || !defined INF
#error Missing preprocessor definitions
#endif 

FUNCTION(minimum, <)
FUNCTION(maximum, >)

void doProcessors(){
    
    int n; cin >> n;
	vector<int> v(n);
	foreach(v, i) {
		io(v)[i];
	}
	int mn = INF;
	int mx = -INF;
	foreach(v, i) {
		minimum(mn, v[i]);
		maximum(mx, v[i]);
	}
	int ans = mx - mn;
	cout << toStr(Result =) <<' '<< ans;
    
}

// Matrix 

class Matrix{
    public:
    vector< vector<int> > a;
    
        Matrix &operator+(const Matrix &other){
        for( int i = 0; i < a.size(); i++){
            for( int j = 0; j < a[i].size(); j++ ){
                a[i][j] = a[i][j] + other.a[i][j];
            }
        }
        return *this;
    }
};

void doMatrix(){
   
    int cases,k;
   cin >> cases;
   for(k=0;k<cases;k++) {
      Matrix x;
      Matrix y;
      Matrix result;
      int n,m,i,j;
      cin >> n >> m;
      for(i=0;i<n;i++) {
         vector<int> b;
         int num;
         for(j=0;j<m;j++) {
            cin >> num;
            b.push_back(num);
         }
         x.a.push_back(b);
      }
      for(i=0;i<n;i++) {
         vector<int> b;
         int num;
         for(j=0;j<m;j++) {
            cin >> num;
            b.push_back(num);
         }
         y.a.push_back(b);
      }
      result = x+y;
      for(i=0;i<n;i++) {
         for(j=0;j<m;j++) {
            cout << result.a[i][j] << " ";
         }
         cout << endl;
      }
   }
    
}


// doComplex


class Complex
{
public:
    int a,b;
    void input(string s)
    {
        int v1=0;
        int i=0;
        while(s[i]!='+')
        {
            v1=v1*10+s[i]-'0';
            i++;
        }
        while(s[i]==' ' || s[i]=='+'||s[i]=='i')
        {
            i++;
        }
        int v2=0;
        while(i<s.length())
        {
            v2=v2*10+s[i]-'0';
            i++;
        }
        a=v1;
        b=v2;
    }
    
    Complex &operator+( const Complex &other ){
        a = a + other.a;
        b = b + other.b;
        return *this;
    }

     friend ostream& operator<<( ostream& out, const Complex& other){
    
        out << other.a << " " << "+" << " " << "i" << other.b;
    
        return out;
    
    }
    

};


void doComplex(){
 
    Complex x,y;
    string s1,s2;
    cin>>s1;
    cin>>s2;
    x.input(s1);
    y.input(s2);
    Complex z=x+y;
    cout<<z<<endl;
    
}


struct Workshops{
    int start_time;
    int duration;
    int end_time;  
};

struct Available_Workshops{
    int n;   
    Workshops *array;
};

Available_Workshops *initialize(int start_time[], int duration[], int n ){
    
    Available_Workshops * aws = new Available_Workshops;
    
    if( aws != NULL ){
        aws -> n = n;
        aws -> array = new Workshops[n];
    }
    else
        return NULL;
    
    for( int i = 0; i < n; i++ ){
            aws -> array[i].start_time = start_time[i];
            aws -> array[i].duration = duration[i];
            aws -> array[i].end_time = start_time[i] + duration[i];
    }
    return aws;
}

int CalculateMaxWorkshops(Available_Workshops* ptr){
    
    Available_Workshops *pAWS = ptr;

    int maxws = 0;
    
    if( ptr == NULL )
        return 0;
    
    for( int i=1; i < pAWS->n - 1; i++ ){
        if(  pAWS->array[i+1].start_time < pAWS->array[i].end_time )
            maxws+=1;
    }
    
    return maxws;
}


void doStructs(){
    
}



// enum class

using namespace std;
enum class Fruit { apple, orange, pear };
enum class Color { red, green, orange };

template <typename T> struct Traits;

template<>
class Traits <Fruit>{

public:

static string name(int index ){

    if( index == static_cast<int>(Fruit::apple) )
        return "apple";
    else if( index == static_cast<int>(Fruit::orange) )
        return "orange";
    else if( index == static_cast<int>(Fruit::pear) )
        return "pear";
    else 
        return "Unkown";
}

};

template<>
class Traits <Color>{

public:

static string name(int index ){

if( index == static_cast<int>(Color::red) )
    return "red";
else if( index == static_cast<int>(Color::green) )
    return "green";
else if( index == static_cast<int>(Color::orange) )
    return "orange";
    else
    return "Unknown";

}

};


void doEnumClass(){
    
    int t = 0; std::cin >> t;

    for (int i=0; i!=t; ++i) {
        int index1; std::cin >> index1;
        int index2; std::cin >> index2;
        cout << Traits<Color>::name(index1) << " ";
        cout << Traits<Fruit>::name(index2) << "\n";
    }
    
}

// Variadic

//template<typename T>
//T adder(T v) {
//  return v;
//}

template<typename T, typename... Args>
T adder(T first, Args... args) {
  return first + adder(args...);
}

template<typename T>
bool pair_comparer(T a, T b) {
  // In real-world code, we wouldn't compare floating point values like
  // this. It would make sense to specialize this function for floating
  // point types to use approximate comparison.
  return a == b;
}

//typename... Args is called template parameter pack. Args is function parameter pack
template<typename T, typename... Args>
bool pair_comparer(T a, T b, Args... args) {
  return a == b && pair_comparer(args...);
}

////// ----
template <typename T>
int addtwo( T first, T second){
    return first + second;
}

template< typename T, typename... Args>
int addtwo(T a, T b, Args... args){
    int sum = addtwo( a, b ) + addtwo(args...);
    
    cout << "sum:" << sum << endl;
    
    return sum;
}

template<typename T>
int power( T num ){
    int res = num * num;
    cout << "num:" << num << " " << res << endl;
    return res;
}

template<typename T, typename... Args>
int power( T num, Args... args){
    
    int res = power(num) + power(args...);
    
    return res;
}

void doVariadic(){
    
    //int res = addtwo(1,1,2,2,3,3);
    int res = power(1,2,3);
    cout << "total: " << res << endl;
    
    
//    long sum = adder(1, 2, 3, 8, 7);
//    
//    cout << "sum:" << sum << endl;
//
//    std::string s1 = "x", s2 = "aa", s3 = "bb", s4 = "yy";
//    std::string ssum = adder(s1, s2, s3, s4);
//    
//    cout << "ssum" << ssum << endl;
    
}

void doAsync(){
    
    mutex m;
    condition_variable cv;
    vector<float> data;
    AsyncFilter filter( m, cv, data );
    filter.start();
    
    loadData(data);
    filter.setData(data);
    cv.notify_one();
    
    {
        unique_lock<mutex> lock(m);
        cv.wait(lock);
    } 
    
    
    
}

int main(int argc, char** argv) {

    //doScan();
    //doFormats();
    //doReverseNumbers();
    //doStringStream();
//    try {
//        doException();
//    } catch (BadLengthException e) {
//        cout << "Too short:" << e.what();
//    }
    
    //stat st;
    //st.showId();

    // The key here with statics is that they are declare inside the class but the allocation
    // and initialization doesn't happen till you do it outside int stat::id = 1;
    // also not static function CAN have access to static variables but static functions CAN'T have
    // access to non-static variables.
    
    //doStatic();
    //doSort();
    
    //When erasing position start 0 then you have to substarct 1 to the position 
    //doErase();
    //doLowerbound();
    
    //sets have a find method
    //doSets();
    
    //You can modify it -> first and it -> second 
    //doMaps();
    //doDeque();
    
    //The dynamic_cast will return a valid pointer if the cast succeeded it can be used
    //like instanceof in Java
    //doDynamicCast();
    
    //Here the trick is the specialize template -> template<> class MyTemp <string>{ } 
    //doTemplates();
    
    //Processor here the trick is to replace code in the macro
    //doProcessors();
    
    //Matrix, the trick here is indexing the vector inside a vector
    //doMatrix();
    
    //Complex
    //doComplex();
    
    // Here the trick is to use static_cast to compare enum class with integer.
    //doEnumClass();
    
    //Variadic
    doVariadic();
    
    return 0;
}

