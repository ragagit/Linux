#!/usr/bin/python3
import sys
sys.path.insert(0, '/usr/lib/python2.7/site-packages')

import pymysql

# yum install mariadb mariadb-server
# systemctl start mariadb.service
# /usr/bin/mysql_secure_installation
# mysql -u root -p
# create database testdb;
# CREATE USER 'testuser'@'localhost' IDENTIFIED BY 'test123';
# GRANT ALL PRIVILEGES ON * . * TO 'testuser'@'localhost';
# mysql -u testuser -p

# Open database connection
db = pymysql.connect("localhost", "testuser", "test123", "testdb");

# Prepare a cusror object using cursor() method
cursor = db.cursor()

# Execute SQL query
cursor.execute("SELECT VERSION()")

# Fetch a single row
data = cursor.fetchone()

print("Database version is: %s" % data)

cursor.execute("DROP TABLE IF EXISTS EMPLOYEE")

#create a table
sql1 = """CREATE TABLE EMPLOYEE(
	FIRST_NAME CHAR(20) NOT NULL,
	LAST_NAME CHAR(20),
	AGE INT,
	SEX CHAR(1),
	INCOME FLOAT ) """

cursor.execute(sql1)

#insert record
sql2 = """INSERT INTO EMPLOYEE(FIRST_NAME, LAST_NAME, AGE, SEX, INCOME)
	VALUES('Mac', 'Mohan', 20, 'M', 2000)"""

try:
	cursor.execute(sql2)
	db.commit()

except:
	db.rollback()
	print("DB has been rolled back")

sql3 = "SELECT * FROM EMPLOYEE WHERE INCOME > '%d'" % (1000)



try:
	cursor.execute(sql3)
	results = cursor.fetchall()
	for row in results:
		fname = row[0]
		lname = row[1]
		age = row[2]
		sex = row[3]
		income = row[4]

		print("fname=%s, lname=%s, age=%d, sex=%s, income=%d" % (fname, lname, age, sex, income ))
except:
	print("Error: unable to fetch data")

sql4 = "UPDATE EMPLOYEE SET AGE = AGE + 1 WHERE SEX = '%c'" % ('M')

sql5 = "DELETE FROM EMPLOYEE WHERE AGE > '%d'" % (20)

# disconnect from server
db.close()



 
