## Employee Management
#### Description: 
A basic mini-project that uses JDBC calls to do CRUD Operations on DB.

#### Learnings
* Primary Learnings: JDBC, TDD
* Secondary Learnings: OOPs, Java Streams

#### Notes:
* The classes are grouped by the function they perform. For example: repository, model etc
* DriverManager Class of JDBC is responsible for managing the database connection. It is a part of java.sql package.
* Even if an exception is thrown by Test methods or setUp() method, still the tearDown() method would run.
* By conventions, save method should return the object that we just saved.
* executeUpdate() would automatically commit the data into the database. To disable autocommit use `connection.setAutoCommit(false)`
* Always use prepared statement, to avoid SQL Injection. Also, prepared statements are pre-compiled so they are fast than createStatement.
* ResultSet is a 2D array that is returned when the query has executed. It basically is the smaller version of the table.
* An entity class is a domain class that is designed to be stored in a database.
* To test equals() method we don't usually use ids.
* Regression is when you implement a new feature, and it breaks the old features.