# 🚌 Reservation Microservice - Spring Boot

This is a simple Spring Boot microservice that manages transport reservations. It provides full CRUD operations and allows searching reservations by departure or arrival location.

## 📦 Project Structure

- `entities/Reservation.java`: Entity representing a reservation.
- `services/ReservationService.java`: Business logic.
- `controllers/ReservationControllers.java`: REST controller exposing APIs.
- `repositories/ReservationRepository.java`: JPA repository interface.

## ⚙️ Features

- ➕ Add a reservation
- 🔍 Retrieve a reservation by ID
- 📋 List all reservations
- ✏️ Update a reservation
- ❌ Delete a reservation
- 🔎 Search by **departure location**
- 🔎 Search by **arrival location**

## 🔗 API Endpoints

| Method | Endpoint                                     | Description                       |
|--------|----------------------------------------------|-----------------------------------|
| POST   | `/reservation/add`                           | Add a new reservation             |
| GET    | `/reservation/{id}`                          | Get a reservation by ID           |
| GET    | `/reservation/all`                           | List all reservations             |
| PUT    | `/reservation/update`                        | Update a reservation              |
| DELETE | `/reservation/delete/{id}`                   | Delete a reservation by ID        |
| GET    | `/reservation/search/departure/{lieuDepart}` | Search by departure location      |
| GET    | `/reservation/search/arrival/{lieuArrivee}`  | Search by arrival location        |

## 📥 Sample JSON for POST

```json
{
  "lieuDepart": "Tunis",
  "lieuArrivee": "Sfax",
  "heureDepart": "2025-04-25T08:30:00",
  "heureArrivee": "2025-04-25T11:00:00",
  "nbrplaces": 45,
  "prixticket": 30.0
}
