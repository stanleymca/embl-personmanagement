--Person Management Application
--Application Setup minimum requirement
	1) Java 1.8.0_202
	2) gradle-6.6.1
	3) STS
	4) DB :- I have used H2 in-memory database.
	
--Features used in this application are given below.
	1) Java 8
	2) Spring Boot 2
			-Sring REST - For rest controller
			-Spring Data - For data to persist
			-Swagger2 - List of APIs to execute and test
			-Logger -logging
			-Security - To mentain security
			-Exception handling
			-H2 in-memory database

--Deployment - Please execute the below docker command to run the in the docker environment
    	docker run --name embl-test -d -p 8085:8085  stanleymca/embl:v2
	
	
--Database Setup.
	 
	Person table will be created automatically once the server is started.
		
--Application Detail
	This application is for contact detail where we can perform CURD operation.
	
--Access API
	I have implemented swagger to execute all the CURD operation for Person management.
	
	Please find the Swagger url below. Kindly open it in Browser.
	
	http://localhost:8085/swagger-ui.html
	
	Username : embl
	Password : embl

	Under Person Controller, you will see all the APIs.	
		1) If we want add more data we can use POST API:- (use one at a time)
			{
				"id": 0,
				"firstName": "John",
				"lastName": "Keynes",
				"age": 29,
				"favouriteColour": "Red"
			}

			{
				"id": 0,
				"firstName": "Sarah",
				"lastName": "Robinson",
				"age": 54,
				"favouriteColour": "Blue"
			}
		2) As we have already created person data through POST API in the above step, we can now get all persons using GET API.
		3) If we want to get perticular person data, we can get by using id. So for that we have GET API by ID.
		4) If we want to update perticular person data we can do it by using PUT API by valid data, below is the sample data.
			id = 2
			personDto {
			  "id": 2,
			  "firstName": "Sarah",
			  "lastName": "Robinson",
			  "age": 54,
			  "favouriteColour": "Pink"
			}
		
		5) If we want to delete perticular person data, we can do it by using DELETE API by valid ID