# Readme - Ticket Service

### A walk through of the implementation and the thought process behind it

#### First of all a list of classes wrote by myself for the implementation
* InvalidPurchaseException
* PersistData
* TicketServiceImpl
* TicketServiceImplTest
* User

### InvalidPurchaseException
This class extends a RuntimeException and as a result of this I decided to just allow for a constructor that takes a custom message. That way I there was no need for extra bloat and create different errors for every circumstance.

### PersistData
I decided to create this class mainly to complete the follow of the application, so I could test it accurately. It is used to get a list of users to act as a stand in db and has a function that allows you to get a user by id. This is used in the getUser() method found in the TicketServiceImpl class.

### TicketServiceImpl
Starting with the solution I first created the list of users for testing purposes. Then for the purchaseTickets() method acting as the primary function for this call. Everything should be called out of this class to break up the functionality of the code.

ticketPurchaseChecker(user, costOfTickets) - a function that completes final checks before the purchase is made. I decided in this case to do the checks in a method as it made the code more readable not cramming it into one class. Also it allows for easier addition of validating checks in the future as it is grouped purposely. 

It will return true if all checks are passed or an error is thrown.


getUser(accountId) - returns the user from the list. This is so we can check the id to make sure it is greater than 0. It is also so we can get the balance of the user to make sure there is sufficient funds.

getCostOfTickets(TicketTypeRequest ... ticketTypeRequests) - will return an int depending on the outcome. If all checks are passed then it will return the cost of tickets. If they fail checks on either too many tickets or no adult ticket then it will return a negative number which will be passed into the checker method where the correct error is thrown. Essentially the return variable is also used as a state variable for tracking any errors. 

how this function works is it takes the ticketTypeRequests and turns it into an array for easier manipulation. Then it is looped through and for each ticket type the amount of tickets with the costs are calculated to be returned. 

getNumOfSeats(TicketTypeRequest... ticketTypeRequests) - returns the number of seats to be used in the purchaseTickets() so that can be passed into the reserveSeat method.

### TicketServiceImplTest 
Along with some manual testing I created some unit tests to test the error handling of the exceptions. The tests look to see if bad data causes the exceptions to be thrown.

### User
The user class is a class that acts as a model and is what is added to the list in persistData. It has an id and balance. It was added so I could test the codes functionality but is not a part of the assessment requirements. 
