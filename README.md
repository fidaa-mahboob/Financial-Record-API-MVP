## Financial Records API v1

A REST API for storing financial data which came about at the request of my close family member who was having an issue with money management. This API is used by a front end dashboard like application to enable data entry.  

### Technology 
<ul>
   <li>Java</li>
   <li>Spring Boot</li>
   <li>AWS</li>
   <li>Github Action CI/CD</li>
   <li>Terraform</li>
</ul>

### Development

To start the dockerised springboot application use the following commands:
```
gradle build 
docker build --build-arg JAR_FILE=/build/libs/*.jar -t financial-api:latest .
docker run -p 8080:8080 financial-api:latest

```

### Testing 

Run tests using following command:

```
./gradlew test

```

### Road Map

- Implement JWT Token security feature for REST API
- Build the AWS Infra using Terraform






