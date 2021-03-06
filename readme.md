## Context
Our application is a payment gateway which allows merchants to charge their users with a single integration.

Right now we are able to charge users, and we want to add the refund feature.

Following User Story describes the expected behavior and what needs to be tested.
___

## User Story
As a Merchant

In order to revert a previous transaction

I need to be able to refund a charge operation by its id.

### Acceptance criteria
- API should expose the following endpoint POST /operations/{id}/refund
- The "id" should be a valid uuid v4 (ex. d1e90d8f-11f7-41e0-92ff-235e2a85ab3b) to get 201 OK
- With an invalid uuid you should get a 400
- Only one concurrent refund operation (on the same transaction id) can be performed, so the resource should be blocked if another refund is being processed. Failing concurrencies should get a 423

___
## Your work
- The test runner should generate a report file
- You should provide a GIT repository accessible with the solution.
- The repo should contain a readme.md with clear instructions of how to execute it.

## Requirements
The application is prepared to be run using docker-compose. So having docker and docker-compose configured is mandatory.

https://docs.docker.com/compose/install/

## Application start
After having docker configured, there is only a command needed to start the application.
```shell
docker-compose up
```
Then the application should be available at http://localhost:8088 (a Spring whitelabel error is expected)
