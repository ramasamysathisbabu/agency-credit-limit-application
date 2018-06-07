# agency-credit-limit-application


Instructions:
1) Download and import the project 'bank-credit-limit-application' in to STS IDE
2) Open the java file BankServiceApplication.java
3) Right click on the file and select 'Run As' -> 'Java Application' (This app willl be running on tomcat localhost and port 6060)
4) Download and import the project 'agency-credit-limit-application' in to STS IDE
5) Open the java file AgentServiceApplication.java 
6) Right click on the file and select 'Run As' -> 'Java Application' (This app willl be running on tomcat localhost and port 8080)
7) Import the postman collection from 'Credit-Limit-Increase.postman_collection.json' under the root folder of the project 'bank-credit-limit-application'

8) Payload for Success/Approval scenario - Rest Request & Response<br />
Rest end point: http://localhost:6060/creditcard/limits<br />
Rest Method   : PUT<br />
Rest Request  : {
"name":"Dave",
	"ssn":"100000000",
	"creditCardNumber":"1111333355557777",
	"zipCode":"12345"
}<br />
Rest Response : {
"approvalStatus": "Approved",
    "newCreditLimitAmount": "Your new credit limit is 12000",
    "error": null
}<br />


9) Payload for Failure/Rejected scenario - Rest Request & Response<br />
Rest end point: http://localhost:6060/creditcard/limits<br />
Rest Method   : PUT<br />
Rest Request  : {
"name":"Dave",
	"ssn":"100000001",
	"creditCardNumber":"1111333355557777",
	"zipCode":"12345"
}<br />
Rest Response : {
"approvalStatus": "Rejected",
    "newCreditLimitAmount": "0",
    "error": null
}<br />


10) Payload for Error scenario - Rest Request & Response<br />
Rest end point: http://localhost:6060/creditcard/limits<br />
Rest Method   : PUT<br />
Rest Request  : {
"name":"Dave",
	"ssn":"100000005",
	"creditCardNumber":"1111333355557777",
	"zipCode":"12345"
}<br />
Rest Response : {
"approvalStatus": "Rejected",
    "newCreditLimitAmount": "0",
    "error": "Could not identify the customer"
}<br />