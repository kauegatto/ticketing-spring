db.getSiblingDB('ticketing-service').ticket.insertMany([
  {
    "requesterEmail":"example@mail1.com",
    "assigneeEmail":"example@agent.com",
    "title":"Pls help me",
    "description":"How do i open a support request",
    "createDate": {
           "$date": "2023-10-08T22:19:31.605Z"
   },
    "createDate": {
           "$date": "2023-10-08T22:19:31.605Z"
    },
  }
]);