# ResturantAPIs

Restaurant web applicaion.
## Built With
- [Spring Boot](https://spring.io/projects/spring-boot) - REST APIs backend
- [AngularJS](https://angular.io) - Frontend 
- [Spring Data](https://spring.io/projects/spring-data) - Data access
- [MySQL](https://www.mysql.com) - DataBase
  

### Spring boot
REST APIs backend using [JWT](https://jwt.io) for Authentication. App run on port :8080.
User should use Email to login to the system.
  - version 2.3.1
  - [Maven](https://maven.apache.org/) - Dependency Management.
    ##### - Available Routes:
    - User routes (for sign up and login) :
    ```
    Post /auth/sign-up  
    ```
    ```
    Post /auth  
    ```
    - Tables routes:
    ```
    Get /tables/   # to get all tables from db
    ```

    ```
    Post /tables/ # to add table to db 
    ```

    ```
    Get /tables/available/{date}  # to get available tables for certain date
    ```
            
    - Reservations routes:
    ```
    Get /books/all  # to get all reservations from db 
    ```

    ```
    Post /books/   # to add reservation to db 
    ```

    ```
    Get /book/me  # to get user reservations for certain date
    ```


##### When the app run for the first time:
- the initailizer class do the following:
  - create admin user 'email = admin@admin.com , password = "admin"'
  - create user 'email = user@user.com , password = "user"'
  - create number of tables:
    - Four tables that can accommodate a max of 2 persons.
    - Seven tables that can accommodate a max of 5 persons.
    - Two tables that can accommodate a max of 10 persons.


### AngularJS
The web application frontend run on port:4200.
  - version 8.3.28
  - 
    ##### App Compoments:
    
    - Home Component - web home page.
    - Sign-up Component - sign up form.
    - Sign-in Component - login form.
    - User-Home Component - reservation form to book table, user own reservations.
    - Available-tables Component - user can search for availabe tables for specific date.
    - Admin-Home Component - add table form, view all restaurant tables.
    - Reservation Component - admin see and search for specific books by date.
    
    ##### App Services:
    
    - Server service : deal with server side attach token to the header each request if user is logged in.
    - Auth service : responsible for user login, save token in local storage and decode token to check user role.
    - GuardAuth service : guard routes according user role.
    - Tables sevice : responsible for tables and resevation "add and fetch".
    
    ##### App routes:
    --
    | Route | Compnent |
    | ------ | ------ |
    | '' or /home | HomeCompnent|
    | /sign-in   | SignInComponent |
    | /sign-up  | SignUpComponent |
    | /user  | UserHomeComponent |
    |/available| AvailableTablesComponent |
    | /admin  | AdminHomeComponent |
    | /admin-books | ReservationComponent |


### MySQL

Web application DataBase
- database name: orange_restaurant
-  ##### db tables:
    - ###### user:
     
    - ###### tables:
     
    - ###### reservations
--    
