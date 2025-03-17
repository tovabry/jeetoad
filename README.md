# Toad API - Jakarta EE

This project provides a RESTful API to manage a database of toads using Jakarta EE. It supports Create, Read, Update, and Delete (CRUD) operations on toad records in a PostgreSQL database.

## Feature
- Create a new toad record in the database
- Read toad records from the database
- Update toad records
- Delete toad records from the database
- Filter toad by id, name or gender

## Constraints
- No identical toads when it comes to name + warts or name + birthday
- Name must start with capital letter
- Warts can't be negative or over 100
- Weight can't be negative or over 100
- Birthday must be past or present
- Gender must be m/M or f/F, uppercase converts to lowercase

## Getting started

Make sure you got maven and docker installed on your computer. 

### Clone the repository
````
git clone https://github.com/tovabry/jeetoad
cd jeetoad
````

### Start the database and server
````
docker-compose --profile production up
````

## Use the application

### Port and API Endpoints

#### GET
- Port: `http://localhost:8080/`
- Get all toads: `/api/toads`
- Get toad by id: `/api/toads/{id}`
- Filter toads by name: `/api/toads/name/{name}`
- Filter toads by gender `/api/toads/gender/{m/f}`
#### POST
- Create new toad: `/api/toads/` and then body. See example body further down.
#### PATCH
- Update a toad: `/api/toads/{id}` and with your wanted updates in the body.
#### DELETE
- Delete a toad: `/api/toads/delete/{id}`

### Recuest body
- Id is generated automaticaly.
- Uppercase gender is converted to lowercase.
- NotNull constraints for every column in POST
````
{
"name": "Toady",
"warts": 5,
"gender": "f",
"weight": 20,
"birthday": "2020-03-02",
"description": "Information is private"
}
````


