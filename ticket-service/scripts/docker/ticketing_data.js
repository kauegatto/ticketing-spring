db.getSiblingDB('ticketing-service').ticket.insertMany([
  {
    "requesterEmail":"example@mail1.com",
    "assigneeEmail":"example@agent.com",
    "title":"Pls help me",
    "description":"How do i open a support request",
    "createDate": "2023-10-09T22:56:52.175+00:00"
  }
]);