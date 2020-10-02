##### Application: 
When you first start this application you will first come to a homepage where you can log in. 
The username here is: **admin** and password: **123**. 
If you want to make another user you can click on sign up. When you first log in you will se some data about some pokemons, with id, title, description and item details. Where you can click and see which users have this item. 
It is a welcome message with your name an a description of how many available loot boxes it is and your game currency. 
You can also see your purchase history and buy loot. When you click on item details as well you can purchase item and then see it in the purchase history. 
It is also possible to log out from all the pages. 
If you want to you can also filter the items on the home page by title or cost. 
You also have the opportunity to log out and from every page go back to the home page. 


##### Technology Stack:
* For Backend: (Java-11, Spring Boot, Spring Security, JPA, Hibernate, H2 Database, flywaydb)
* Java-11: Java 11 used for better performance some benefit from Java API

##### Spring Boot: 
*	Auto configuration (NO XML CONFIGURATION WHATSOEVER)
*	spring boot JPA (jpa will help you do CRUD operations in just 4 lines, how?? Follow below link)
*	standalone jars (you can create full application as one standalone fat jar and deploy it)
*	embedded servers (spring boot comes with embedded servers, do not need to add one)
*	health check (you can monitor application health from any system).

##### Spring Security:
Spring security can be used for authentication and authorization purposes of application. We can secure our app with it. It provides integration with LDAP as well. If we want to add role-based access to our project pages, aka Authorization, this is the framework to use. It is powerful in a way that it provides a lot of features out of the box, but also allows we can customize/override the features provided by it.

##### Spring Data JPA implementation Hibernate:
Basically, we choose to execute DML operation like Insert, update, delete, find. JPA is fastest interface abstract ORM its implementation is hibernate. We are just used entity manager do manage DML operation.

##### H2Database:
This is in-memory database for meet plug and play concept. In runtime we access the database and done some crud operation testing etc. it is high scalable for in memory database.

##### Flyway Database:
Tools such as Flyway can prevent database schema mismatch when working with multiple environments, such as dev, test, and prod, or when switching branches. They allow developers to recreate a database from scratch, which is valuable when creating a new environment.

##### Heroku:
Heroku is a container-based cloud Platform as a Service (PaaS). Developers use Heroku to deploy, manage, and scale modern apps. Our platform is elegant, flexible, and easy to use, offering developers the simplest path to getting their apps to market.


##### For Front End (Spring Boot, JSF,HeroKu)
##### JSF: 
The benefits of using JSF are not only in generating xhtml + css + js. Sometimes JSF imposes a restriction on the markup you can generate, like any component-based framework. But JSF is not just for that, its lifecycle helps greatly. After validating the input, it can update the model and sync your server side beans without any effort. you just say, "whatever the user types here, check if it's a number, if yes then store it in the property YY in object XX" and JSF will do all that.

##### Heroku:
Heroku is a container-based cloud Platform as a Service (PaaS). Developers use Heroku to deploy, manage, and scale modern apps. Our platform is elegant, flexible, and easy to use, offering developers the simplest path to getting their apps to market.


##### Run the project in the terminal by doing:  
*	Mvn clean install
*	Mvn spring-boot:run
*	Then go to the browser and write: http://localhost:8080

##### Maven CMD command: 
1.	Mvn clean install
2.	Mvn -pl frontend -am spring-boot:run
3.	Going to browser: http://localhost:8080 – press enter
4.	The Login page appear 
5.	Let us Enjoy.

##### Or by running: 
* LocalApplicationRunner from the frontend, and under the test folder. 

##### Did requirements: 
R1, R2, R3

##### Did not do requirements: 
R4, R5

I have fulfilled the requirement for the testing on the backend and have more than 80% code coverage there. 
The code coverage can you find under target folder under the backend folder, and after opening up the index.html file. 

