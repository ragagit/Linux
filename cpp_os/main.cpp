/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.cpp
 * Author: Administrator
 *
 * Created on January 10, 2018, 9:03 AM
 */

#include <cstdlib>
#include <iostream>
#include <exception>
#include <fstream>
#include <vector>
#include <list>
#include <map>
#include <set>
#include <stack>
#include <queue>
#include <algorithm>
#include <typeinfo>
#include <initializer_list>
#include <functional>
#include <memory>


using namespace std;
using namespace placeholders;


/*
 * 
 */

class MyException : public exception {
private:
    char* myMsg;

public:

    MyException(char* msg) {
        this->myMsg = msg;
    }

    virtual const char* what() {
        return myMsg;
    }
};

class Test {
public:

    void goesWrong() {
        throw MyException((char*)"Error message");
    }

};

void doSomething() {
    bool err0 = false;
    bool err1 = true;
    bool err2 = false;

    cout << "In doSomething()" << endl;
    if (err0) {
       throw 20;
    }
    if (err1) {
        throw "Something went wrong";
    }
    if (err2) {
        throw string("Something else went wrong");
    }
}

void doExceptions() {

    Test test;

    try {
        doSomething();
        //test.goesWrong();
    //} catch (MyException e) {
    }catch( int e){
        //cout << e.what() << endl;
            cout << e << endl;
    }catch( char e){
        cout << "char exception " << e << endl;
    }catch( string e ){
        cout << "string exception caught " << e << endl;
    }catch(...){
        cout << "Default catch" << endl;
    }
    

    cout << "Continuing" << endl;
}

void doOutStream() {
    /* output streams */
    //ofstream outFile;
    fstream outFile;
    string fileName = "file.txt";
    outFile.open(fileName.c_str(), ios::out);

    if (outFile.is_open()) {
        outFile << "First Line" << endl;
        outFile << "Second Line" << endl;
        outFile << "Third Line" << endl;
    } else {
        cout << "File couldn't be opened";
    }

    if (outFile.is_open()) {
        outFile.close();
    }
}

void doInputStream() {

    string fileName = "file.txt";

    /* input Stream*/
    ifstream inFile;
    string line;

    inFile.open(fileName.c_str());

    if (inFile.is_open()) {
        while (inFile) {
            //inFile >> line;
            getline(inFile, line);
            cout << line << endl;
            
        }
        inFile.close();
    } else {
        cout << "Cannot open inFile";
    }
}

int doReadSpecialFormat() {

    /* Reading special formats*/

    string fileN = "stats.txt";
    ifstream inF;
    string line1;
    int pop;


    inF.open(fileN.c_str());

    if (!inF.is_open()) {
        cout << "Unable to open file";
        return 1;
    } else {
        while (inF) {
            getline(inF, line1, ':');
            inF >> pop;
            //inF.get();
            inF >> ws;

            if (!inF)
                break;

            cout << line1 << "---" << pop << endl;
        }
        inF.close();
    }
    
    return 0;

}

#pragma pack(push, 1)

typedef struct {
    char name[50];
    int age;
    double weight;
} Person;
#pragma push(pop)

void doStructs() {

    cout << sizeof (Person);
}

void doBinaryFile() {

    string fileName = "test.bin";
    ofstream outFile;
    ifstream inFile;
    Person person = {"Frodo", 220, 0.8};
    Person person1 = {};

    outFile.open(fileName.c_str(), ios::binary);

    if (outFile.is_open()) {
        outFile.write(reinterpret_cast<char *> (&person), sizeof (Person));
        outFile.close();
    } else {
        cout << "Unable to open file " + fileName + " for writing";
    }

    inFile.open(fileName.c_str(), ios::binary);

    if (inFile.is_open()) {
        inFile.read(reinterpret_cast<char *> (&person1), sizeof (Person));
        cout << person1.name << ',' << person1.age << ',' << person1.weight;
        inFile.close();
    } else {
        cout << "Unable to open file " + fileName + " for reading";
    }

}

void doVector() {

    vector<string> strings;

    strings.push_back("one");
    strings.push_back("two");
    strings.push_back("three");

    vector<string>::iterator it;

    for (it = strings.begin(); it != strings.end(); it++)
        cout << *it << endl;

}

void doCapacity() {

    vector<double> numbers;

    int capacity = numbers.capacity();

    cout << "Capacity:" << capacity << endl;

    numbers.push_back(34.5);

    cout << "Capacity:" << numbers.capacity() << endl;

    for (int i = 0; i < 500; i++) {

        if (numbers.capacity() != capacity) {
            cout << i << " Capacity: " << numbers.capacity() << endl;
            capacity = numbers.capacity();
        }
        numbers.push_back(34.5);
    }

    //You can increase the capacity of the array with reserve, it allocate memory 
    numbers.reserve(10000);
    cout << "After reserve: " << numbers.capacity() << endl;

    // with resize you initialize element with default values
    numbers.resize(700);
    cout << "After resize: " << numbers.size() << endl;

}

void doVector2D() {

    vector< vector<int> > grid(3, vector<int>(4, 7));

    for (int row = 0; row < grid.size(); row++) {
        for (int col = 0; col < grid[row].size(); col++) {
            cout << grid[row][col] << flush;
        }
        cout << endl;
    }
    
    vector< vector<string> > strs(3, vector<string>(2, ""));
    vector<string> row0, row1, row2;
    row0.push_back("ZeroZero");
    row0.push_back("ZeroOne");
    strs.push_back(row0);
    row1.push_back("OneZero");
    row1.push_back("OneOne");
    strs.push_back(row1);
    row2.push_back("TwoZero");
    row2.push_back("TwoOne");
    strs.push_back(row2);
    
    for( int i=0; i < strs.size(); i++){
        for(int j=0; j < strs[0].size(); j++){
            cout << strs[i][j] << " ";
        }
        cout << endl;
    }
    
}

void doList() {

    list<int> numbers;

    numbers.push_back(1);
    numbers.push_back(2);
    numbers.push_back(3);
    numbers.push_front(0);

    list<int>::iterator it = numbers.begin();
    it++;
    numbers.insert(it, 100);
    cout << "Element:" << *it << endl;

    list<int>::iterator erase = numbers.begin();
    erase++;
    erase = numbers.erase(erase);
    for (list<int>::iterator it = numbers.begin(); it != numbers.end(); it++) {
        cout << *it << endl;
    }

}

void doMap() {

    map<string, int> ages;

    ages["Mike"] = 23;
    ages["John"] = 34;
    ages["Mary"] = 45;

    cout << ages["Mary"] << endl;

    if (ages.find("Mary") != ages.end()) {
        cout << "Key Found" << endl;
    } else {
        cout << "Key not found" << endl;
    }

    ages.insert(pair<string, int>("Tom", 5));
    ages.insert(make_pair("Peter", 57));

    for (map<string, int>::iterator it = ages.begin(); it != ages.end(); it++) {
        cout << "Key:" << it->first << "Value:" << it->second << endl;
    }

}

void askQuestion() {

    string msg, readMsg;

    cout << "Type Something: ";
    cin >> msg;
    cout << "You typed:" << msg;

    ofstream outFile;
    ifstream inFile;

    outFile.open("Hello.txt");

    if (outFile.is_open()) {
        outFile << msg.c_str() << endl;
        outFile.close();
        cout << "Success writing to the file" << endl;
    } else {
        cout << "Unable to open the file" << endl;
    }

    inFile.open("Hello.txt");

    if (inFile.is_open()) {
        getline(inFile, readMsg);
        cout << readMsg;
    } else {
        cout << "Unable to read file" << endl;
    }


}

class Person1 {
private:
    string name;
    int age;

public:

    Person1() : name(""), age(0) {

    }

    Person1(string name, int age) : name(name), age(age) {

    }

    Person1(const Person1 &other) {
        //cout << "Calling copy constructor" << endl;
        name = other.name;
        age = other.age;
    }

    void print() const {
        cout << name << " " << age << flush;
    }

    bool operator<(const Person1 &other) const {
        if (name == other.name) {
            return age < other.age;
        }
        return name < other.name;
    }
};

void storeObjects() {

    map<int, Person1> persons;

    //persons.insert(make_pair(1, Person1("Pete", 5)));
    persons[0] = Person1("Holly", 12);
    persons[1] = Person1("Tim", 24);
    persons[2] = Person1("Dave", 34);

    for (map<int, Person1>::iterator it = persons.begin(); it != persons.end(); it++) {
        it->second.print();
    }

    persons.insert(make_pair(1, Person1("Fox", 23)));


}

void storeObjects1() {

    map<Person1, int> persons;

    //persons.insert(make_pair(Person1("Pete", 5), 1));
    persons[Person1("Holly", 12)] = 1;
    persons[Person1("Tim", 24)] = 2;
    persons[Person1("Dave", 34)] = 3;
    persons[Person1("Dave", 34)] = 4;
    persons[Person1("Dave", 35)] = 7;


    for (map<Person1, int>::iterator it = persons.begin(); it != persons.end(); it++) {
        cout << it->second << " ";
        it->first.print();
        cout << endl;
    }


}

void doMultiMaps() {
    multimap<int, string> lookup;

    lookup.insert(make_pair(5, "One"));
    lookup.insert(make_pair(6, "Two"));
    lookup.insert(make_pair(7, "Three"));
    lookup.insert(make_pair(8, "Four"));

    for (multimap<int, string>::iterator it = lookup.begin(); it != lookup.end(); it++) {
        cout << it->first << " " << it->second << endl;
    }

    cout << " Separation " << endl;

    for (multimap<int, string>::iterator it = lookup.find(7); it != lookup.end(); it++) {
        cout << it->first << " " << it->second << endl;
    }


}

void doSets() {

    set<int> numbers;

    numbers.insert(1);
    numbers.insert(2);
    numbers.insert(3);
    numbers.insert(4);

    for (set<int>::iterator it = numbers.begin(); it != numbers.end(); it++) {
        cout << *it << endl;
    }

}

class Testt {
private:
    string name;
    int age;

public:

    Testt() : name(""), age(0) {

    }

    Testt(string name, int age) : name(name), age(age) {

    }

    //    Test(const Test &other) {
    //        //cout << "Calling copy constructor" << endl;
    //        name = other.name;
    //        age = other.age;
    //    }

    void printt() const {
        cout << name << " " << age << endl;
    }

    bool operator<(const Testt &other) const {
        if (name == other.name) {
            return age < other.age;
        }
        return name < other.name;
    }
};

void doSetss() {

    set<Testt> numbers;

    numbers.insert(Testt("Laura", 1));
    numbers.insert(Testt("Paul", 1));
    numbers.insert(Testt("Dave", 2));
    numbers.insert(Testt("Mario", 3));
    numbers.insert(Testt("Jane", 4));

    for (set<Testt>::iterator it = numbers.begin(); it != numbers.end(); it++) {
        it->printt();
    }

}

class Testtt {
private:
    string name;

public:

    Testtt() : name("") {
    }

    Testtt(string name) : name(name) {
    }

    ~Testtt() {
        //cout << "Calling the destructor" << endl;
    }

    void print() {
        cout << name << endl;
    }

};

void doStacks() {

    stack<Testtt> testStack;

    testStack.push(Testtt("John"));
    testStack.push(Testtt("Mark"));
    testStack.push(Testtt("George"));

    while (testStack.size() > 0) {
        Testtt &test = testStack.top();
        test.print();
        testStack.pop();
    }

    cout << "-- Queue --" << endl;

    queue<Testtt> testQueue;
    testQueue.push(Testtt("John"));
    testQueue.push(Testtt("Mark"));
    testQueue.push(Testtt("George"));

    while (testQueue.size() > 0) {
        Testtt &test = testQueue.front();
        test.print();
        testQueue.pop();
    }

}

void doArrays() {

    char myArray[20][10];
    string myStr[] = {"One", "Two", "Three"};
    int myInt[][3] = {
        {1, 2, 3},
        {4, 5, 7}
    };

    int arr[2][3];
    
    arr[0][0]=0;
    arr[0][1]=1;
    arr[0][2]=2;
    arr[1][0]=3;
    arr[1][1]=4;
    arr[1][2]=6;
    
    cout << "Array size:" << sizeof (myArray) << endl;
    cout << "Array size:" << sizeof (myStr) << endl;
    cout << "Array size:" << sizeof (myInt) << endl;

    for (int i = 0; i < sizeof (myInt) / sizeof (int); i++)
        cout << myInt[i] << endl;
    for (int i = 0; i < sizeof (myStr) / sizeof (myStr[0]); i++)
        cout << myStr[i] << endl;
}

void doPointers() {

    //    int i = 7;
    //    
    //    cout << "i: " << i << "address:" << &i << endl;
    //    
    //    int &j = i; // int *j = &i;
    //
    //    j = 8; // *j = 8;
    //    
    //    cout << "i: " << i << " j address: " << &j << endl;
    //    
    //    string texts[] = { "One", "Two", "Three" };
    //    
    //    int size = sizeof(texts)/sizeof(string);
    //    string *pStrBegin = &texts[0];
    //    string *pStrEnd = &texts[2];
    //    
    //    for( int i = 0; i < size; i++){
    //        cout << texts[i] << endl;
    //    }
    //    
    //    for( int i = 0; i < size; i++, pStrBegin++){
    //        cout << *pStrBegin << endl;
    //    }
    //    
    //    cout << pStrBegin << endl;
    //    cout << pStrEnd << endl;

    char cStr[] = "Hello";


    int size = sizeof (cStr) - 1;

    char *pStart = cStr;
    char *pEnd = cStr + size - 1;
    char Holder;

    cout << "pStart: " << pStart << " pEnd: " << pEnd << "size: " << sizeof (cStr) << endl;

    while (pStart < pEnd) {

        Holder = *pStart;
        *pStart = *pEnd;
        *pEnd = Holder;

        pStart++;
        pEnd--;
    }

    cout << "Reverse str: " << cStr << flush;


}

class Animal {
private:
    string name;

public:

    Animal() {
        cout << "Animal constructor" << endl;
    }

    Animal(const Animal &other) : name(other.name) {
        cout << "Animal created by copy constructor" << endl;
        name = other.name;
    }

    void setName(string name) {
        this->name = name;
    }

    void speak() const {
        cout << "Animal: " << name << endl;
    }

    const Animal &operator=(const Animal &other) {
        name = other.name;
        cout << "Created by operator overloaded" << endl;

        return *this;
    }

};

void doCopyConstructor() {

    Animal animal1;
    animal1.setName("Jiraf");

    Animal animal2 = animal1;

    Animal animal3;
    animal3 = animal2;

}

Animal createObject() {

    Animal a;
    a.setName("Horse");

    return a;
}

bool comp(int i, int j) {
    return i < j;
}

void doSort() {

    vector<int> vect;

    vect.push_back(3);
    vect.push_back(1);
    vect.push_back(17);
    vect.push_back(10);

    cout << "Before" << endl;

    for (vector<int>::iterator it = vect.begin(); it != vect.end(); it++) {
        cout << *it << endl;
    }

    sort(vect.begin(), vect.end(), comp);

    cout << "After" << endl;

    for (vector<int>::iterator it = vect.begin(); it != vect.end(); it++) {
        cout << *it << endl;
    }

}

// Inheritance

class Animall {
private:
    int id;
public:

    Animall() {
    }

    Animall(int id) : id(id) {
    }

    void speak() {
        cout << "Grrr" << endl;
    }

    void showInfo() {
        cout << id << endl;
    }
};

class Cat : public Animall {
public:

    Cat() {
    }

    Cat(int id) : Animall(id) {
    }

    void jump() {
        cout << "Jump high" << endl;
    }

};

class Tiger : public Cat {
public:

    Tiger() {
    }

    Tiger(int id) : Cat(id) {
    }

    void attack() {
        cout << "Go killer" << endl;
    }
};

void doInheritance() {

    Tiger tiger(777);
    tiger.speak();
    tiger.jump();
    tiger.attack();
    tiger.showInfo();

}

// Templates

template<class T>
class Temp {
private:
    T msg;

public:

    Temp(T msg) : msg(msg) {
    }

    void print() {
        cout << msg << endl;
    }

};

template<typename T>
void print(T n) {

    cout << "Using template: " << n << endl;

}

void print(int m) {

    cout << "Using regular function: " << m << endl;

}

template<class T>
void show() {
    cout << T() << endl;
}

void doTemplate() {

    Temp<string> temp("Hello");
    temp.print();
    print(78);
    print<>(78);
    print("Hi without specifying type");
    show<double>();

}

//Function pointers

int FunctPtr(int n) {

    cout << "FunctPtr: " << n << endl;

    return n;

}

void doFunctPtr() {

    cout << "Calling function" << endl;
    int i = FunctPtr(7);
    int (*pFunct)(int);
    pFunct = FunctPtr;
    cout << "Calling function pointer" << endl;
    int j = (*pFunct)(7);
    int k = pFunct(8);

}

int compara(vector<string> &names, bool (*pFunct)(string)) {

    int count = 0;

    for (vector<string>::iterator it = names.begin(); it != names.end(); it++) {
        if (pFunct(*it))
            count++;
        //cout << *it << endl;
    }
    cout << "Count using pointer: " << count;
    return 0;
}

bool match(string str) {
    return str.size() == 3;
}

void doCount() {

    vector<string> names;
    bool (*pFunc)(string) = match;

    names.push_back("Norma");
    names.push_back("Ali");
    names.push_back("Jon");
    names.push_back("David");

    int ctn = count_if(names.begin(), names.end(), match);

    cout << "The count is: " << ctn << endl;

    compara(names, match);

}

// Polymorphism

class Parent {
private:
    int one;
public:

    Parent() : one(0) {

    }

    Parent(const Parent &other) : one(0) {

    }

    virtual ~Parent() {

    }

    virtual void print() {
        cout << "I am the parent" << endl;
    }

};

class Child : public Parent {
private:
    int two;
public:

    Child() : two(0) {

    }

    void print() {
        cout << "I am the child" << endl;
    }

    void printVar() {
        cout << two << endl;
    }
};

class A {
private:
    int one;
public:

    virtual void printA() {
        cout << "PrintA" << endl;
    }
    //virtual void printB();   
};

class Ani {
public:
    virtual void speak() = 0;
    virtual void run() = 0;

};

class Dog : public Ani {
public:

    virtual void speak() {
        cout << "Bark" << endl;
    }
};

class Lab : public Dog {
public:

    virtual void run() {
        cout << "Run" << endl;
    }
};

void doPoly() {

    Parent p1;
    p1.print();

    Child ch1;
    ch1.print();
    ch1.printVar();
    ch1.printVar();
    Child *ptr = new Child();
    Parent *p2 = ptr;
    ptr->print();
    ptr->printVar();

    A var;
    var.printA();

    Lab lab;
    lab.run();
    lab.speak();

    //Dog dog; //Error as Dog doesn't implement run()

    //Even you can't instantiate Ani you can use it like this
    Ani * pAni[2];
    pAni[0] = &lab;
    pAni[0]->speak();

    delete ptr;

}

//struct Tes{
//    virtual bool operator()(string &text)=0;
//};
//
//struct MatchTest: public Tes{
//    bool operator()(string &text){
//        return text == "lion";
//    }
//};

class Tes {
public:
    virtual bool operator()(string &text) = 0;
};

class MatchTest : public Tes {
public:

    bool operator()(string &text) {
        return text == "lion";
    }
};

void check(string text, Tes &match) {
    if (match(text)) {
        cout << "Matched" << endl;
    } else
        cout << "Not matched" << endl;

}

void doFructors() {
    MatchTest mt;

    string msg = "lion";

    check(msg, mt);
}

// C++ 11

void doTypeInfo() {
    int valInt;
    string valStr;

    decltype(valStr) newVar;

    cout << typeid (valInt).name() << endl;
    cout << typeid (valStr).name() << endl;
    cout << typeid (newVar).name() << endl;
}

template<class T, class K>
auto myAuto(T val1, K val2) -> decltype(val1) {
    return val1 + val2;
}

void doAuto() {

    auto i = 7; //compiler infers the type

    cout << myAuto(7, 8) << endl;

}

void doLoop() {

    auto texts = {"one", "two", "three"};

    for (auto text : texts)
        cout << text << endl;

    vector<int> myints;
    myints.push_back(5);
    myints.push_back(7);

    for (auto in : myints)
        cout << in << endl;

    string myvar = "Hello";

    for (auto c : myvar)
        cout << c << endl;

}

class Ring {
public:

    class Iterator {
    public:

        void print() {
            cout << "Iterator" << endl;
        }
    };
};

template<class T>
class Ring_ {
public:
    class Iterator_;

};

template<class T>
class Ring_<T>::Iterator_ {
public:

    void print() {
        cout << "Iterator" << endl;
    }
};

void doNested() {
    Ring_<string>::Iterator_ it;
    it.print();
}

class Ini {
public:
    string str;
    int id;
};

struct {
    string name;
    int age;
} r1 = {"Sue", 5}, r2 = {"Laura", 8};

class MyInit {
public:

    MyInit(initializer_list<string> texts) {
        for (auto t : texts)
            cout << t << endl;
    }

    void print(initializer_list<int> nums) {
        for (auto i : nums)
            cout << i << endl;
    }
};

void doIni() {

    Ini myIni = {"Name", 7};

    cout << r1.name << endl;
    cout << r2.name << endl;

    int i{5};
    string var{"Hello"};
    int nums[]{1, 2, 3, 4};

    int *pInt{&i};
    cout << *pInt << endl;

    int *pPtr(nullptr); //*pPtr = nullptr;

    vector<string> texts{"one", "two", "three"};

    for (auto v : texts)
        cout << v << endl;

    MyInit myini{"Uno", "Dos", "Tres"};

    myini.print({3, 5, 7});

}

class ObjInit {
private:
    int id{0};
    string name{"Juan"};

public:
    ObjInit() = default;

    ObjInit(int id) : id(id) {

    }
    //ObjInit(  const ObjInit &other ) = delete;
    ObjInit(const ObjInit &other) = default;
    ObjInit &operator=(const ObjInit &other) = default;

    void print() {
        cout << id << endl;
        cout << name << endl;
    }
};

void doObjInit() {

    ObjInit Test;
    Test.print();
    ObjInit Test1(7);
    Test1.print();

    ObjInit t1 = Test;

}

void runLambda(void (*pFunc)()) {
    pFunc();
}

void runLambda1(void (*pFunc)(string)) {
    pFunc("Maria");
}

void runLambda3(double (*pFunc)(double, double)) {
    auto res = pFunc(9, 3);
    cout << res << endl;
}

void doLambdaExp() {

    //[](){}; empty lambda

    auto func = []() {
        cout << "Hello Lambda" << endl;
    };

    func();

    runLambda(func);

    runLambda([]() {
        cout << "Hello Lambda direct" << endl; });

    auto func1 = [](string name) {
        cout << "Hello " << name << endl;
    };

    runLambda1(func1);

    auto func3 = [](double i, double j) -> double {
        if (j == 0)
            return 0;
        return i / j;
    };

    runLambda3(func3);
}

class Capt {
private:
    int one{1};
    int two{2};

public:

    void run() {
        int three{3};
        int four{4};

        auto pLambda = [ = ](){cout << "In lambda" << one << two << three << four << endl;};
        pLambda();
    }

};

void doCaptLambda() {

    int one{1};
    int two{2};
    int three{3};

    //
    [one, two]() {
        cout << one << two << endl;
    }();

    //All
    [ = ](){cout << one << two << endl;}
    ();

    //Three by reference
    [ =, &three](){three = 7;
        cout << one << two << three << endl;}
    ();

    Capt cap;
    cap.run();
}

bool check2(string &text) {
    return text.size() == 3;
}

class Check {
public:

    bool operator()(string &name) {
        return name.size() == 3;
    }
};

void run(function<bool(string&) > check) {
    string test = "dog";
    cout << check(test) << endl;
}

void doFuncType() {

    int num = 5;

    vector<string> vec{"one", "two", "three"};

    int res = count_if(vec.begin(), vec.end(), [num](string name) {
        return name.size() == num;
    });

    auto lambda = [num](string name) {
        return name.size() == num;
    };

    cout << res << endl;

    res = count_if(vec.begin(), vec.end(), check2);

    cout << res << endl;

    Check check1;

    res = count_if(vec.begin(), vec.end(), check1);

    cout << res << endl;

    run(lambda);

    function<int(int, int) > add = [](int i, int j) {
        return i + j;
    };

    cout << add(5, 7) << endl;

}

void doMutable() {

    int num = 5;

    auto show = [num]() mutable {

        num = 7; //won't work unless you use mutable but it will change locally only
        cout << num << endl;
    };

    show();

    cout << num << endl;


}

//Delegating constructor

class Padre {
    int num;
    string text;

public:

    Padre(string text) : text(text) {
        cout << "Padre constructor " << endl;
    }

    void print() {
        cout << text << endl;
    }
};

class Hijo : public Padre {
public:

    Hijo() : Padre("Hello") {//in C+= 11 we can specify which const to use


    }
};

void doDelgConstructor() {

    Hijo son; //Here there is an error as we don't have default const in Padre
    son.print();

}

class Te {
    int num{7};

public:

    Te() {
        cout << "Te def constructor" << endl;
    }

    Te(int num) : num(num) {
        cout << "Te int constructor" << endl;
    }

    void print() {
        cout << num << endl;
    }
    
    void greet(){
        cout << "Hi there" << endl;
    }

    ~Te() {
        cout << "Te destructor" << endl;
    }
};

Te getTe() {
    return Te();
}

void check(const Te &te) {
    cout << "Lvalue function" << endl;
}

void check(Te &&te) {
    cout << "Rvalue function" << endl;
}

void doRLValues() {
    //L value is something that you can take the address off
    // for example int i = 7; int *pPtr = &i; you can't do it with 7
    int val = 7;
    int *pPtr = &val; //ok
    pPtr = &++val; //ok
    //pPtr = &val++; // not ok because C++ has to create a temp var then increase it
    //pPtr = &( val + 7); // not ok

    Te test = getTe();
    test.print(); //This works but not right thing to do

    //if we do this , it won't work
    //Te &rte = getTe();

    // This works
    const Te &rte = getTe();

    check(test);

    // Right values is to handle temporary values
    Te &&te1 = getTe();
    check(getTe());
    check(te1);
    check(Te());
}

class Mem {
private:
    int *pBuffer{nullptr};
    const int SIZE = 200;
public:

    Mem() {
        pBuffer = new int[SIZE] {
        };
        //to initialize we can use memset or {} ()      
    }

    Mem(const Mem &other) {
        //memcpy(pBuffer, other.pBuffer, sizeof(int)*SIZE);
    }

    //Move constructor

    Mem(Mem &&other) {
        cout << "Move constructor" << endl;
        pBuffer = other.pBuffer;
        other.pBuffer = nullptr;
    }

    Mem &operator=(const Mem &other) {
        //memcpy(pBuffer, other.pBuffer, sizeof(int)*SIZE);
        return *this;
    }

    //Move assignment operator

    Mem &operator=(Mem &&other) {
        cout << "Move assignment operator" << endl;
        delete [] pBuffer;
        pBuffer = other.pBuffer;
        other.pBuffer = nullptr;
        return *this;
    }

    ~Mem() {
        delete [] pBuffer;
    }
};

Mem getMem() {
    return Mem();
}

void doMoveConstructor() {

    vector<Mem> vec;
    //Invokes the Move constructor
    vec.push_back(Mem());

    //Move assignment operator
    Mem mem;
    mem = getMem();

}

class Parentt {
public:

    virtual void speak() {
        cout << "Hi" << endl;
    }
};

class Brother : public Parentt {
};

class Sister : public Parentt {
};

void doStaticCast() {
    Parentt parent;
    Brother brother;

    //Review of casting
    float num = 3.21;
    cout << static_cast<int> (num) << endl;

    //
    Parentt *pP = &brother; // OK
    //Brother *pB = &parent; //not OK
    Brother *pB = static_cast<Brother *> (&parent); //Very risky, not recommended. It is just at compile time

    Parentt &&p = static_cast<Parentt &&> (parent);
    p.speak();
}

void doDynamicCast() {
    Parentt parent;
    Brother brother;
    Sister sister;

    //
    Parentt *pPP = &parent;
    Parentt *pPB = &brother;
    Parentt *pP = &brother; // OK
    //Brother *pB = &parent; //not OK
    //Dynamic cast does a run time checking
    Brother *pB = dynamic_cast<Brother *> (pPP);

    if (pB == nullptr)
        cout << "Invalid pointer" << endl;
    else
        cout << pB << endl;

    Brother *pBB = dynamic_cast<Brother *> (pPB);

    if (pBB == nullptr)
        cout << "Invalid pointer" << endl;
    else
        cout << pBB << endl;

    //Reinterpret cast
    Parentt *pa = &brother;
    Sister *ps = reinterpret_cast<Sister *> (pa);

    if (ps == nullptr)
        cout << "Invalid pointer" << endl;
    else
        cout << ps << endl;
}

template<typename T>
void call( T &&arg){
    //check(static_cast<T>(arg)); // same as line below
    check(forward<T>(arg));
}
void check1( Te &&val){
    cout << "Function R value called" << endl;
}

void check1( Te &val){
    cout << "Function L value called" << endl;
 }

void doPerfectForwarding(){
    
    Te te; // This is an L value
    
    Te &&t = Te(); //OK because Te() is a right value
    //Reference collapsing rule
    auto &&t1 = te;
    
    t.greet();
    
    call(Te());
    call(te);
}

int add(int a, int b, int c ){
   cout << a << " "  << b << " " << c << endl;
   return a + b + c;
}

int run_(function<int(int, int, int)> func){
    return func(5, 7, 8);
}
void doBind(){
    
    //Bind are like alias to functions
    cout << add(1,2,3) << endl;
    
    //auto calculate = bind( add, _1, _2, _3); //Place holders
    //auto calculate = bind( add, _2, _1, _3); // 20, 10, 30
    auto calculate = bind( add, _1, 100, _3); // passing 100
    cout << calculate(10,20,30) << endl;
    run_(calculate);
}

class Temp_{
    unique_ptr<Te> pPtr;
    
public:
    Temp_():pPtr(new Te){
        
    }
    
};
void doSmartPointers(){
   
    unique_ptr<int> pPtr( new int );
    
    *pPtr = 7;
    
    cout << *pPtr << endl;
    
    unique_ptr<Te> pPtr_( new Te );
    
    pPtr_->greet();
    
    Temp_ temp;
   
}

void doSharedPointers(){
    
    shared_ptr<Te> pPtr(new Te);
    
    pPtr->greet();
    
    shared_ptr<Te> pPtr1 = make_shared<Te>();
    
    pPtr1->greet();
    
    unique_ptr<int> pInt;
    
    pInt = unique_ptr<int>(new int);
    
    *pInt = 7;
    
    cout << "Result: " << *pInt << endl;
    
    cout << "Finished" << endl;
    
}

int main(int argc, char** argv) {

    //doExceptions();
    //doReadSpecialFormat();
    //doStructs();
    //doBinaryFile();
    //doVector();
    //doCapacity();
    //doVector2D();
    //doList();
    //doMap();
    //askQuestion();
    //storeObjects();
    //storeObjects1();
    //doMultiMaps();
    //doSetss();
    doStacks();
    //doArrays();
    //doPointers();
    //doCopyConstructor();
    //Animal an = createObject();
    //an.speak();
    //doSort();
    //doInheritance();
    //doTemplate();
    //doFunctPtr();
    //doCount();
    //doPoly();
    //doFructors();
    //doTypeInfo();
    //doAuto();
    //doLoop();
    //doNested();
    //doIni();
    //doObjInit();
    //doLambdaExp();
    //doCaptLambda();
    //doFuncType();
    //doMutable();
    //doDelgConstructor();
    //doRLValues();
    //doMoveConstructor();
    //doStaticCast();
    //doDynamicCast();
    //doPerfectForwarding();
    //doBind();
    //doSmartPointers();
    //doSharedPointers();


    // * copy elision is a compiler optimization to get rid of unnecessay copy objects

    return 0;
}