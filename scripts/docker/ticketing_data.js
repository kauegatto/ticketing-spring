db.getSiblingDB('ticketing-service').tickets.insertMany([
  {
    "requesterEmail":"example@mail1.com",
    "assigneeEmail":"example@agent.com",
    "title":"Pls help me",
    "description":"How do i open a support request",
    "createDate":"2016-05-18T16:00:00Z",
    "resolutionDate":"2016-05-19T16:00:00Z",
  }
]);