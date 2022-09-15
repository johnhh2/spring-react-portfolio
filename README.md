# Spring React Portfolio
A custom portfolio website used to display projects and related information

## Authors
Created by Matthew Purnell and John-Hugh Hedrick

## Major Dependencies
 - Spring 2.7.3
 - React.js 18.2

## Setup
### React setup
 - Install npm:
   - [Windows/Mac](https://nodejs.org/en/download/)
   - Linux: `sudo npm install -g npm`
 - Install yarn: `sudo npm install -g yarn`
 - Go into the react repo: `cd react-portfolio/`
 - Use yarn to download frontend dependencies: `yarn install`

### Spring setup
#### Java
You will need [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17). Make sure to set your JAVA\_HOME environment variable with the location of the folder that you extract from the download.
#### Maven
Additionally, download and extract [Maven 3.8.6](https://maven.apache.org/download.cgi).

### Database setup
 - Install mysql: `sudo apt install mysql-server`
 - Start the mysql service: `sudo service mysql start`
 - Configure the database:
   - Open a mysql prompt: `sudo mysql --password`
   - Create the database:

        ```sql
        CREATE DATABASE portfolio; -- Creates the new database
        CREATE USER 'user1'@'%' IDENTIFIED BY 'pass'; -- Creates the user
        GRANT ALL ON portfolio.* TO 'user1'@'%'; -- Gives all privileges to the new user on the newly created database
        \q
        ```

   - Create the file _spring-portfolio/src/main/resources/application.properties_ and add the following lines, or ensure they already exist there:

        ```
        spring.jpa.hibernate.ddl-auto=update
        spring.datasource.url=jdbc:mysql://localhost:3306/portfolio
        spring.datasource.username=user1
        spring.datasource.password=pass
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        ```

 - Once configured, you should be able to start the back-end server with the command listed in [Startup](https://github.com/johnhh2/spring-react-portfolio#startup). This should create all the necessary tables in the database.
 - Once the tables have been created, you will need to create a few roles in order for the back-end authentication system to work properly.
   - Open a mysql prompt: `sudo mysql`
   - Create roles:

        ```sql
        USE portfolio;
        INSERT INTO role(name) VALUES('ROLE_USER');
        INSERT INTO role(name) VALUES('ROLE_MODERATOR');
        INSERT INTO role(name) VALUES('ROLE_ADMIN');
        \q
        ```

  - Your mysql database should now be ready to use.

### Startup
 - Launch the back-end:
   - Go into the spring repo: `cd spring-portfolio`
   - Start the server: `./mvnw spring-boot:run`
 - Launch the front-end: `yarn --cwd react-portfolio/ start`
 - Access the website (front-end) at `http://localhost:3000`
 - Access the back-end API at `http://localhost:8080/api`
