
Election crud and nomination

Method	URL	Description
POST	/election	Create election
GET	/election	Get all elections
GET	/election/{id}	Get election by ID
PUT	/election/{id}	Update election
DELETE	/election/{id}	Delete election
POST	/election/{id}/nominate/{pid}	Nominate a party to election

// crud of party

This controller now supports:

POST /party → Create a party

GET /party → Get all parties

GET /party/{id} → Get party by ID

PUT /party/{id} → Update a party

DELETE /party/{id} → Delete a party

//candidate crud

Method	URL	Description
POST	/candidate	Create a new candidate
GET	/candidate	Get all candidates
GET	/candidate/{id}	Get candidate details by ID
PUT	/candidate/{id}	Update candidate by ID
DELETE	/candidate/{id}	Delete candidate by ID

//Voter crud

Method	URL	Description
POST	/voter	Create a new voter
GET	/voter	Get all voters
GET	/voter/{id}	Get a voter by ID
PUT	/voter/{id}	Update a voter by ID
DELETE	/voter/{id}	Delete a voter by ID

//ballot

Method	URL	Description
POST	/ballot	Cast a new vote
GET	/ballot	Get all ballots
GET	/ballot/{id}	Get ballot by ID
PUT	/ballot/{id}	Update ballot (rare use)
DELETE	/ballot/{id}	Delete a ballot
