# Spring React Portfolio
A custom portfolio website used to display projects and related information

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


### Startup
- Launch the back-end: `./spring-portfolio/mvnw spring-boot:run`
- Launch the front-end: `yarn --cwd react-portfolio/ start`
- Access the website (front-end) at `http://localhost:3000`
- Access the backend at `http://localhost:8080`
