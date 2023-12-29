## Minimal Viable Product: Financial Records API v1

This first iteration of an MVP API that is designed to help a client store financial data in AWS cloud. 
 
### Technology 
<ul>
   <li>Java</li>
   <li>Spring Boot</li>
   <li>AWS</li>
   <li>Github Action CI/CD</li>
   <li>JWT</li>
</ul>

### Architecture

This has been kept simple in order to achieve the clients primary objective. 

![Alt text](aws.png)

The ECS service will hold the application in a fargate container. The advantage are not requiring to maintain EC2 instances and controlled scalability. For the backend databse DynmoDB due to its advantages like being serverless and fully managed. The increasing data complexity over time led to decision to use a NoSQL database over relational database for its high performance.

