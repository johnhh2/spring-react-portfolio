# Spring React Portfolio
A custom portfolio website used to display projects and related information

## Major Dependencies
 - Spring 2.7.3
 - React.js 18.2

## Setup
### React setup
#### NPM
 - [Windows/Mac](https://nodejs.org/en/download/)
 - Linux:
```bash
npm install -g npm
```
#### Yarn
 - Install yarn: `npm install -g yarn`
 - User yarn to download frontend dependencies: `yarn install`

### React startup
```bash
yarn start
```

### Spring setup
#### Java
You will need [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17). Make sure to set your JAVA\_HOME environment variable with the location of the folder that you extract from the download.
#### Maven
Additionally, download and extract [Maven 3.8.6](https://maven.apache.org/download.cgi).

### Spring startup
Run the following command to start a test server displaying the Spring boilerplate test page at [localhost:8080](localhost:8080):
```bash
backend/mvnw spring-boot:run
```
