#!/usr/bin/python3
import sys
import time
import calendar
import re

print ("Hello Pyhton!")
###############
x = input("Type something:")

print( x, "Thanks" )

print( "Result of:3/2 is:", 3/2 )

###############
if True:
   print("\ntrue\n")
else:
   print("\nfalse\n")

###############
str = "Hello Olympics Rio 2016"

print(str)
print(str[0]+"\n")
print(str[2:5]+"\n")
print(str[2:]+"\n")
print(str*2+"\n")
print(str + "TEST"+"\n")
###############

list = ["One", 111, "Two", 2 ]
tinylist = [123, "john" ]

print(list)
print(list[0])
print(list[1:2])
print(list + tinylist)

##############
dict = {}

dict["One"] = "First Element"
dict["Two"] = "Second Value"


print(dict["Two"])

##############

x = "7"

print( int(x) )

y = "9+7"

print(eval(y))

##############
count = 0

while (count < 9 ):
	print("The count is:", count)
	count+=1;

print("That's all folks")

##############
seq = ["one", "two", 5, 8 ]

for var in seq:
	print(var)

############## iterator next
list = [1,2,3,4,5]

it = iter(list)

print( next(it) )
print( next(it) )

############## Generator Yield

def myfunc(n):
	count= n
	while count < n:
		print(count)
		count +=1
		yield count

#f = myfunc(7)

#while True:
#	try:
#		print( next(f), end=" ")
#	except StopIteration:
#		print("Exception caught!")
		#sys.exit()

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

print( int(d) + int(e) )

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

############### List

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

############## Dictionary

dict = { 'Name' : 'Zara', 'Age' : 7 }

print(dict['Name'])

# cmp(), len(), str(), type(), dict.clear(), dict.copy, dict.get

############## Time

print(time.localtime())

print(time.localtime(time.time()) )

print( time.asctime(time.localtime(time.time() ) ) )

#time.altzone(), time.clock(), time.sleep(secs)

############## Calendar

cal = calendar.month(2016, 8)

print(cal)

#calendar.firstweekday(), calendar.isleap()

############# Functions

def MyFunc( str ):
	print(str)
	return 0

MyFunc("Hello World")

#All values are passed by reference

def Myref( alist ):
	alist[0] = "diez"
	for i in alist:
		print(i)

alist = ["uno", "dos", "tres" ]

#Argument required
Myref(alist)

#Arguments keyword

def NameAge( name, age ):
	print("Name:", name)
	print("Age", age)

NameAge( age=15, name="Ana" )

#Argument default

def DefName( name, age = 15 ):
	print("Name:", name)
	print("Age", age)

#DefName(name, age = 15)

#Variable length

def printinfo( arg1, *var ):
	for i in var:
		print(i)
	return

printinfo(10)
printinfo(10,20,13)

#scope of a variable if in the function is local outside is global

############ Modules
# it is related code grouped together
# use #import
# for specific attributes from modname import name1, name2
# within a module, the module's name is available as the value of the global variable __name__
# Modules location is in : local dir, PYTHONPATH and /usr/local/lib/python3
# A name space is a dictionary of variable names and their corresponding objects. Use "global" for global namespaces
# dir() list names in the namespace

content = dir(sys)

print(content)

#globals() and locals() are used to return the names of globals and local spaces

print(globals())

print(locals())

#reload() reloads a module

############# Files I/O
# file object = open( file_name [, access_mode][, buffering]) - r, rb, r+, rb+, w, wb, w+, wb+, a, ab, a+, ab+, file.closed, file.mode, file.name

fd = open("foo.txt", "w+")
fd.write("Python is a great language.\nYeah it is great\n")
fd.close()


fd = open("printing.log", "r")
fo = open("exception.log", "w")

for line in fd:
	if "exception" in line:
		print(line)
		fo.write(line)

fd.close()
fo.close()

print("end of file")

#tell(), seek(), file.close(),file.flush(),file.readline(),file.read([size])
#os.rename(), os.remove(), os.mkdir(), od.chdir(), os.rmdir(), os.getcwd()

############# Exceptions
#Exception, SystemExit, ArithmeticError, OverflowError, ZeroDivisonError, EOFError, IOError, etc

#Assertion
def KtoF(temp):
	assert(temp >=0),"Colder than Zero"
	return((temp-273)*1.8)+32

print(KtoF(35))
#print(KtoF(-5))

try:
	fh = open("somefile.txt", "r" )
	fh.readline()
except IOError:
	print("Unable to open file")
else:
	print("File successfully opened")

#finally

def temp(var):
	try:
		return int(var)
	except ValueError as Argument:
		print("The argument is not number\n", Argument)

temp("xyz")


def func(level):
	if( level < 1):
		raise Exception(level)
	return level

try:
	l=func(-1)
	print("level", l)
except Exception as e:
	print("error in level", e)


################ Classes
# class ClassName:
# 'Optional class documentation'
# class_suite
# Documentation can be accessed ClassName.__doc__

class Employee:
	'Common base class for all employees'
	empCount=0

	def __init__(self, name, salary):
		self.name = name
		self.salary = salary
		Employee.empCount +=1

	def __del__(self):
		class_name = self.__class__.__name__
		print(class_name, "destroyed")

	def displayCount(self):
		print("Total Employees %d" % Employee.empCount)

	def displayEmployee(self):
		print("name:", self.name, ", Salary: ", self.salary)


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

	def __init__(self):
		print("Calling child")


sube = SubEmployee()

sube.displayCount()

# issubclass(sub, sup), isinstance(obj, Class)
#You can override methods and overload operators
# Data hiding can be done by adding __ at the beginnin of the variable

class JustCounter:
	__secretCount = 0

	def count(self):
		self.__secretCount += 1
		print(self.__secretCount)

counter = JustCounter()
counter.count()

#print(counter.__secretCount)
#to access 
print(counter._JustCounter__secretCount)


############# Regular Expresions
# import re
# re.match(pattern, string, flags=0)
# group(num=0) entire match
# groups() all matching subgroups
# . single character \w any word \W non-word \s space \S non-space, \n \r \t, \d decmal, \D no decimal, ^ start of the string, \$ end of the string
# match checks for a match at the beginning of the string search anywhere in the string.
#

line= "Cats are smarter than dogs"

matchObj = re.match(r'dogs', line, re.M|re.I)

if matchObj:
	print("Found match:", matchObj.group())
else:
	print("No match")

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















