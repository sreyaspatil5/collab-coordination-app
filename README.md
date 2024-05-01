# Web Application Backend for Job Portal

This repository contains the backend code for a job portal web application. It provides RESTful APIs for various functionalities related to job management.

## Controllers

### AdminController

- **Create Admin:** POST `/api/admins`
  - Endpoint to create a new admin.
- **Get Admin by ID:** GET `/api/admins/{id}`
  - Endpoint to retrieve admin details by ID.
- **Update Admin:** PUT `/api/admins/{id}`
  - Endpoint to update admin details.
- **Delete Admin:** DELETE `/api/admins/{id}`
  - Endpoint to delete an admin by ID.

### PostController

- **Create Post:** POST `/api/post`
  - Endpoint to create a new job post.
- **Get All Posts:** GET `/api/post`
  - Endpoint to retrieve all job posts.

### UserController

- **Create User:** POST `/api/users`
  - Endpoint to create a new user.
- **Update User Image:** PUT `/api/users/{id}/image`
  - Endpoint to update user profile image.
- **Get All Users:** GET `/api/users`
  - Endpoint to retrieve all users.
- **Get User by ID:** GET `/api/users/{id}`
  - Endpoint to retrieve user details by ID.
- **Get User by Username:** GET `/api/users/username/{username}`
  - Endpoint to retrieve user details by username.
- **Update User:** PUT `/api/users/{id}`
  - Endpoint to update user details.
- **Search Users by First Letter:** GET `/api/users/search/{letter}`
  - Endpoint to search users by the first letter of their username.
- **Delete User:** DELETE `/api/users/{id}`
  - Endpoint to delete a user by ID.
- **Delete Self:** DELETE `/api/users/self`
  - Endpoint to delete the currently logged-in user.

## Services

- **AdminService:** Service layer for admin-related operations.
- **PostService:** Service layer for job-related operations.
- **UserService:** Service layer for user-related operations.

## Exception Handling

- **GlobalExceptionHandler:** Handles exceptions globally across the application.

## Repositories

- **AdminRepository:** Repository for admin entities.
- **PostRepository:** Repository for job post entities.
- **UserRepository:** Repository for user entities.

## Testing

- **CoordinationAppApplicationTests:** JUnit tests for the application.

## Suggestions

- Please feel free to review any suggestions for this project.
