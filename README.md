# RestApiExample
This is a simple CRUD Rest API built using Spring Boot.

## Getting Started

To build and run this application, you will need:
* Java 11 or later
* Maven 3.6.0 or later

The API has the following endpoints:

* GET /api/student/ - Get all students
* GET /api/student/id={id} - Get student by ID
* GET /api/student/firstname={firstName} - Get students by FirstName
* POST /api/student/save - Create a new student
* PUT /api/student/update - Update an existing student
* DELETE /api/student//delete - Delete a student by ID

## Integration Tests

No configuration is needed to run integration tests with H2 Database, just run: <i><b>mvn test</b></i>.
