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

### MySQL setup
#### Creating the database
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

 - Once configured, you should be able to start the back-end server with the command listed in [Startup](https://github.com/johnhh2/spring-react-portfolio#startup). This should create all the necessary tables in the database.
 - Once the tables have been created, you will need to create a few roles in order for the back-end authentication system to work properly: `sudo mysql -c < create_roles.sql`
 - Your mysql database should now be ready to use.

#### Recreating the database
 - Should you ever need to reset the database, follow the below steps:
 - Drop the existing database and create a new one: `sudo mysql -c < recreate_db.sql`
 - Restart your backend server with [Startup](https://github.com/johnhh2/spring-react-portfolio#startup) instructions
 - Create the user roles: `sudo mysql -c < create_roles.sql`

### Startup
 - Launch the back-end:
   - Go into the spring repo: `cd spring-portfolio`
   - Start the server: `./mvnw spring-boot:run`
 - Launch the front-end: `yarn --cwd react-portfolio/ start`
 - Access the website (front-end) at `http://localhost:3000`
 - Access the back-end API at `http://localhost:8080/api`
