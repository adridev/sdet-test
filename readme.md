# Introduction
About Docomo
Coding Test rules, expectations....


## Requirements
The application is prepared to be run using docker-compose. So having docker and docker-compose configured is a mandatory.

https://docs.docker.com/compose/install/

## Application start
After having docker configured there is only a command needed to start the application.
```shell
docker-compose up
```
It will be available at http://localhost:8088

## User Story
As a Merchant

In order to revert a debit operation

I need to be able of refund the debit operation by its id

## Acceptance criteria
- API should expose the following endpoint POST /operations/{id}/refund
- The "id" should be a valid uuid v4 (ex. d1e90d8f-11f7-41e0-92ff-235e2a85ab3b) otherwise the response status is 400
- Only one concurrent refund operation can be performed so the resource should be blocked if another refund is been processed 
- If the resource is blocked the response status is 423
- A successful response status is 201

## Testbook
?


### Disclaimer
Don't use any of this code in production, it was created for the purpose of a simple coding-test.
