# 🔐 Spring Security JWT Authentication (Java 19)

This project demonstrates a **Spring Boot** application secured with **Spring Security**, using:

- ✅ In-Memory Authentication
- ✅ DAO-based Authentication (MySQL DB)
- ✅ JWT Token Generation and Validation
- ✅ Custom Security Filter to validate JWT tokens
- ✅ REST APIs with Role-based access

---

## ⚙️ Tech Stack

- Java 19
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- JWT (JSON Web Token)
- Maven

---

## 📌 Key Features

### 🔐 In-Memory Authentication
- Provides a simple hardcoded user for quick testing
- No database needed
- Useful for quick admin-level access during development

```java
UserDetails admin = User.withUsername("admin")
    .password("{noop}admin123")
    .roles("ADMIN")
    .build();
🗃️ DAO Authentication (DaoAuthenticationProvider)
Fetches user credentials and roles from MySQL database

Implements UserDetailsService and uses DaoAuthenticationProvider

Encapsulates full Spring Security authentication flow

Validates user credentials using database-stored passwords

@Autowired
private UserDetailsService userDetailsService;

@Bean
public DaoAuthenticationProvider authProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    return provider;
}
🔑 JWT (JSON Web Token) Authentication
Stateless authentication mechanism

Token is created after successful login

Token contains encrypted user info (username, roles, etc.)

Must be included in the Authorization: Bearer <token> header

String token = Jwts.builder()
    .setSubject(user.getUsername())
    .claim("role", user.getRole())
    .setIssuedAt(new Date())
    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
    .compact();
🔄 JWT Security Filter
Custom filter that intercepts every request

Extracts and validates JWT token

Sets authentication in the Spring Security context

Allows authorized access without saving session on server

UsernamePasswordAuthenticationToken authToken = 
    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
SecurityContextHolder.getContext().setAuthentication(authToken);
📮 API Endpoints
Endpoint	Method	Description	Secured?	Role
/users/save	POST	Create new user	❌ No	-
/users/login	POST	Login and generate JWT token	❌ No	-
/users/get_all	GET	Get all users	✅ Yes	ADMIN
/users/admin	GET	Admin-only endpoint	✅ Yes	ADMIN
/users/user	GET	User-only endpoint	✅ Yes	USER
/users/	GET	Open public endpoint	❌ No	-

🛠️ How to Run
Clone the repo

git clone git@github.com:temptation4/spring-security-jwt-auth.git
Update application.yml
Set your MySQL DB connection details.

Run the App

./mvnw spring-boot:run
Test in Postman

POST /users/save → Register user

POST /users/login → Get JWT token

GET /users/user or /users/admin → Access with token

📦 Example Login Request
POST /users/login
Content-Type: application/json

{
  "username": "john",
  "password": "pass123"
}
🔁 Returns JWT Token

{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
✅ Summary
Component	Purpose
InMemoryAuthentication	Quick test login
DAO Authentication	Real DB user login using DaoAuthenticationProvider
JWT Token	Stateless secure request handling
JWT Filter	Validates JWT and sets authentication context

📧 Author
Neelu Sahai
GitHub: @temptation4
Email: neelhuma@gmail.com

Built with ❤️ using Spring Boot Security
