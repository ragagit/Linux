# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

import sys
import time
import calendar
import re
# import mysql.connector
# from mysql.connector import errorcode
#
# import numpy as np
# import matplotlib.pyplot as plt



if __name__ == "__main__":

    #Printing something
    def Print():
        print ("Hello Python")


    #Getting User Input
    def getUserInput():
        name = input("What is your name:")
        print ("Hello", name)
        age = input("How old are you:")
        print ("You are ", age, " years old")

    #if conditional
    def ifCondition():
        if True:
            print("\ntrue\n")
        else:
            print("\nfalse\n")

    #String elements
    def strElements():
        str = "Hello Olympics Rio 2016"

        print(str)
        print(str[0]+"\n")
        print(str[2:5]+"\n")
        print(str[2:]+"\n")
        print(str*2+"\n")
        print(str + "TEST"+"\n")

    #lists
    def listExample():
        list = ["One", 111, "Two", 2 ]
        tinylist = [123, "john" ]

        print(list)
        print(list[0])
        print(list[1:2])
        print(list + tinylist)

    #Dictionary
    def dictionary():
        dict = {}

        dict["One"] = "First Element"
        dict["Two"] = "Second Value"
        print(dict["Two"])

    #Evaluate
    def evaluate():
        x = "7"
        print( int(x) )
        y = "9+7"
        print(eval(y))

    #While loop
    def whileLoop():
        count = 0

        while (count < 9 ):
            print("The count is:", count)
            count+=1;

        print("That's all folks")

    #for loop
    def forLoop():
        seq = ["one", "two", 5, 8 ]

        for var in seq:
            print(var)


    #iterator
    def iterator():
        list = [1,2,3,4,5]

        it = iter(list)

        print( next(it) )
        print( next(it) )

    #Function param
    def myFunc(n):
        count= 0
        while count < n:
            #print(count)
            count +=1
            yield count

#Exeption
#Exception, SystemExit, ArithmeticError, OverflowError, ZeroDivisonError, EOFError, IOError, etc
def exception():
    f = myFunc(3)
    for j in f:
        print (j)

    while True:
        try:
            print( next(f) )
        except StopIteration:
            print("Exception caught!")
            sys.exit()

#Number Convertion
def numConvert():
    ############### int, float, complex
    #conversion int(x) float(x) complex(x)
    #math functions: abs(x) ceil(x) exp(x) .....
    a=4
    b=1.3
    c=2 + 5j
    print(a)
    print(b)
    print(c)
    d="4"
    e="3"
    print(float(4))
    print( int(d) + int(e) )

#String
def doString():
    ################ Strings
    #escape \a \r \t
    # + ,* ,[] ,in ,not in
    #string formatting %
    #use """ for long paragraphs
    #capitalize,center, count. decode, encode, find, isalnum

    str = "Hello World!"
    print(str[5:7])

    str1 = "Wo"

    if str1 in str:
        print( "Found!" )
    else:
        print("Not Found!" )

    print("My name is: %s and age is:%d" % ("John", 21))

    para_str = """This is a long string
                and tere is no need to use 
                continuation cgaracters"""

    print(para_str)
    #raw strings
    print(r"C:\\nowhere")

#List
def doList():
    list = ["uno", "dos", "tres" ]
    print( "list[0] is:", list[0] )
    list[0] = "cinco"
    print("list[0]", list[0] )
    del list[2]
    print("After deleting", list)
    #Operations len, +, *
    list1=[1,2,3,4]
    list2=[7,8,9,0]
    print(list1+list2)
    print(list1*2)

#Dictionary
def doDict():
    dict = { 'Name' : 'Zara', 'Age' : 7 }
    print(dict['Name'])
    # cmp(), len(), str(), type(), dict.clear(), dict.copy, dict.get

#Time
def doTime():
    print(time.localtime())
    print(time.localtime(time.time()) )
    print( time.asctime(time.localtime(time.time() ) ) )
    #time.altzone(), time.clock(), time.sleep(secs)

#Calendar
def doCalendar():
    cal = calendar.month(2016, 8)
    print(cal)
    #calendar.firstweekday(), calendar.isleap()

#Functions
#All parameters are passed by reference
def Myref( alist ):
    alist[0] = "diez"
    for i in alist:
        print(i)

#Arguments keywords
def NameAge( name, age ):
    print("Name:", name)
    print("Age", age)

#Default argument value
def DefName( name, age = 15 ):
    print("Name:", name)
    print("Age", age)

#Variable args
def printinfo( arg1, *var ):
    for i in var:
        print(i)
    return

# Modules
# it is related code grouped together
# use #import
# for specific attributes from modname import name1, name2
# within a module, the module's name is available as the value of the global variable __name__
# Modules location is in : local dir, PYTHONPATH and /usr/local/lib/python3
# A name space is a dictionary of variable names and their corresponding objects. Use "global" for global namespaces

# dir() list names in the namespace
def doDir():
    #Names in the namespace
    content = dir(sys)
    print(content)
    print(globals())
    print(locals())

# Files I/O
# file object = open( file_name [, access_mode][, buffering]) - 
# r, rb, r+, rb+, w, wb, w+, wb+, a, ab, a+, ab+, file.closed, file.mode, file.name
# tell(), seek(), file.close(),file.flush(),file.readline(),file.read([size])
# os.rename(), os.remove(), os.mkdir(), od.chdir(), os.rmdir(), os.getcwd()
def doFile():
    fd = open("foo.txt", "w+")
    fd.write("Python is a great language.\nYeah it is great\nException error\n")
    fd.close()

    fd = open("foo.txt", "r")

    for line in fd:
        if "Exception" in line:
            print(line)

    fd.close()
    print("end of file")

    try:
        fh = open("somefile.txt", "r" )
        fh.readline()
    except IOError:
        print("Unable to open file")
    else:
        print("File successfully opened")

#Assertion
def Temperature(temp):
    #assert(temp >=0),"Colder than Zero"
    res = ((temp-273)*1.8)+32
    print (res)
    return res

def tempExc(var):
    try:
        return int(var)
    except ValueError as Argument:
        print("The argument is not number\n", Argument)




def func(level):
    if( level < 1):
        raise Exception(level)
        return level

    try:
        l=func(-1)
        print("level", l)
    except Exception as e:
        print("error in level", e)

# Classes
# class ClassName:
# 'Optional class documentation'
# class_suite
# Documentation can be accessed ClassName.__doc__
# issubclass(sub, sup), isinstance(obj, Class)
# You can override methods and overload operators
# Data hiding can be done by adding __ at the beginnin of the variable
class Employee:
    'Common base class for all employees'
    empCount=0

def __init__(self, name, salary):
    self.name = name
    self.salary = salary
    Employee.empCount +=1
print("Employee %d created" % Employee.empCount)

def __del__(self):
    class_name = self.__class__.__name__
    print(class_name, "destroyed")

def displayCount(self):
    print("Total Employees %d" % Employee.empCount)

def displayEmployee(self):
    print("name:", self.name, ", Salary: ", self.salary)

def doClass():
    emp1 = Employee("John", 50000)
    emp2 = Employee("Mary", 70000)
    emp1.displayCount()
    emp1.displayEmployee()
    print("Employee.__doc__:", Employee.__doc__)
    print("Employee.__name__:", Employee.__name__)
    print("Employee.__module__:", Employee.__module__)
    print("Employee.__bases__:", Employee.__bases__)
    print("Employee.__dict__:", Employee.__dict__)
    # use del to destroy objects but garbage collector does it automatically

#Inheritance
class SubEmployee(Employee):

    def __init__(self, name, salary, position):
        self.__position = position
        Employee.__init__(self, name, salary )
        print("Calling child")

    def showPosition(self):
        print(self.__position)

def doInhe():
    subemp = SubEmployee("John", 37, "Accountant")
    subemp.displayCount()
    subemp.showPosition()


# Data hiding can be done by adding __ at the beginnin of the variable
# This makes the attribute private. However you can access it with (see below)
class JustCounter:
    __secretCount = 7

    def count(self):
        self.__secretCount += 1
        print(self.__secretCount)


def doDataHide():
    x = JustCounter()
    x.count()
    print(x)
    print(x._JustCounter__secretCount)


# Regular Expresions
# import re
# re.match(pattern, string, flags=0)
# group(num=0) entire match
# groups() all matching subgroups
# . single character \w any word \W non-word \s space \S non-space, \n \r \t, \d decmal, \D no decimal, 
# ^ start of the string, \$ end of the string
# match checks for a match at the beginning of the string search anywhere in the string.
#

def doRegExp():

    line= "Cats are smarter than dogs"

    #match search at the beginning
    matchObj = re.match(r'dogs', line, re.M|re.I)
    if matchObj:
        print("Found match:", matchObj.group())
    else:
        print("No match")

    #search look for anywhere
    searchObj = re.search(r'dogs', line, re.M|re.I)
    if searchObj:
        print("Search found:", searchObj.group())
    else:
        print("No search found:")

    #re.sub(pattern, repl,string,max=0)
    phone="2004-955-567 # This is my number"
    num = re.sub(r'#.*$', "", phone)
    print("Phone Num:", num)
    num = re.sub(r'\D', "", phone )
    print("Phone:", num)


# yum install mariadb mariadb-server
# systemctl start mariadb.service
# /usr/bin/mysql_secure_installation
# mysql -u root -p
# create database testdb;
# CREATE USER 'testuser'@'localhost' IDENTIFIED BY 'test123';
# GRANT ALL PRIVILEGES ON * . * TO 'testuser'@'localhost';
# mysql -u testuser -p 
# On mac I had to go to mysql.org->downloads->community->connectors/python and install the connector
# import mysql.connector
# def doDBVer():
#     # Open database connection
#     #db = mysql.connector.connect("localhost", "root", "P4ssw0rd", "ragadb");
#
#     try:
#         db = mysql.connector.connect(user='root', password='P4ssw0rd', host='127.0.0.1', database='ragadb')
#
#         # Prepare a cusror object using cursor() method
#         cursor = db.cursor()
#
#         # Execute SQL query
#         cursor.execute("SELECT VERSION()")
#
#         # Fetch a single row
#         data = cursor.fetchone()
#         print("Database version is: %s" % data)
#
#     except mysql.connector.Error as err:
#
#         if err.errno == errorcode.ER_ACCESS_DENIED_ERROR:
#             print("Something is wrong with your user name or password")
#         elif err.errno == errorcode.ER_BAD_DB_ERROR:
#             print("Database does not exist")
#         else:
#             print(err)
#     else:
#         db.close()
#         print("Closing connection")
#     finally:
#         print("Operation on DB was successful")
#
# def doDBTables():
#
#     db = mysql.connector.connect(user='root', password='P4ssw0rd', host='127.0.0.1', database='ragadb')
#
#     # Prepare a cusror object using cursor() method
#     cursor = db.cursor()
#     cursor.execute("DROP TABLE IF EXISTS EMPLOYEE")
#
#     #create a table
#     sql1 = """CREATE TABLE EMPLOYEE(
# 	FIRST_NAME CHAR(20) NOT NULL,
# 	LAST_NAME CHAR(20),
# 	AGE INT,
# 	SEX CHAR(1),
# 	INCOME FLOAT ) """
#
#     cursor.execute(sql1)
#
#     #insert record
#     sql2 = """INSERT INTO EMPLOYEE(FIRST_NAME, LAST_NAME, AGE, SEX, INCOME)
# 	VALUES('Mac', 'Mohan', 20, 'M', 2000)"""
#
#     try:
#         cursor.execute(sql2)
#         db.commit()
#
#     except:
#         db.rollback()
#         print("DB has been rolled back")
#
#     sql3 = "SELECT * FROM EMPLOYEE WHERE INCOME > '%d'" % (1000)
#
#     try:
#         cursor.execute(sql3)
#         results = cursor.fetchall()
#         for row in results:
#             fname = row[0]
#             lname = row[1]
#             age = row[2]
#             sex = row[3]
#             income = row[4]
#             print("fname=%s, lname=%s, age=%d, sex=%s, income=%d" % (fname, lname, age, sex, income ))
#     except:
#         print("Error: unable to fetch data")
#
#     #sql4 = "UPDATE EMPLOYEE SET AGE = AGE + 1 WHERE SEX = '%c'" % ('M')
#     #sql5 = "DELETE FROM EMPLOYEE WHERE AGE > '%d'" % (20)
#
#     # disconnect from server
#     db.close()

class NeuralNetwork():
    def __init__(self):
        self.inputLayerSize = 2
        self.hiddenLayerSize = 3
        self.outputLayerSize = 2

    # def sigmoid(self, z):
    #     return 1/(1 + np.exp(-z))

# def doNN():
#     NN = NeuralNetwork()
#     testInput = np.arange(-6, 6, 0.01)
#     plt.plot(testInput, NN.sigmoid(testInput), linewidth = 2)
#     plt.grid(1)


#Print()
getUserInput()
#ifCondition()
#strElements()
#listExample()
#dictionary()
#evaluate()
#whileLoop()
#forLoop()
#iterator()
#myFunc(3)
#exception()
#numConvert()
#doString()
#doList()
#doDict()
#doTime()
#doCalendar()
#Myref([1,2,3])
#NameAge( age=15, name="Ana" )
#DefName("Mary")
#printinfo(2,3,7,8, "Mary")
#doDir()
#doFile()
#Temperature(23)
#tempExc("Hi")
#func(1)
#doClass()
#doInhe()
#doDataHide()
#doRegExp()
#doDBVer()
#doDBTables()
#doNN()






























