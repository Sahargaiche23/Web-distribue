# Annonce Management API

This is a Spring Boot application for managing announcements (annonces). It consists of three projects: API Gateway, Eureka, and Annonce. The application provides RESTful endpoints for adding, retrieving, updating, and deleting announcements. Features include adding a new announcement with an optional image file, retrieving an announcement by its ID, retrieving all announcements, updating an existing announcement, deleting an announcement by its ID, and searching for announcements by title. The application is built using Spring Boot, Spring MVC, Spring Data JPA, and Maven.

## Setup

### Prerequisites
Docker
Docker Compose


### Installation
1. Clone the repository: git clone https://github.com/Sahargaiche23/Web-distribue.git
2. Navigate to the project directory: cd Web-distribue/oumaimaannonce
Build and run the application using Docker Compose:

   ```bash
   docker-compose build
   docker-compose up -d API Endpoints
Add Announcement: POST /annonce/addannonce (Parameters: Annonce JSON, optional file)
Get Announcement by ID: GET /annonce/{id}
Get All Announcements: GET /annonce/annonces
Update Announcement: PATCH /annonce (Parameters: Annonce JSON, optional file)
Delete Announcement: DELETE /annonce/{id}
Search Announcement by Title: GET /annonce/searchAnnonce/{titreAnnonce}
