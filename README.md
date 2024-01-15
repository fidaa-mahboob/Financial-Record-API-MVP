## Minimal Viable Product: Financial Records API v1

This first iteration of an MVP API is designed to help a client store financial data in AWS cloud. There will be more features added later in the following API versions after successful introduction and implementation in business envrionment.  
 
### Technology 
<ul>
   <li>Java</li>
   <li>Spring Boot</li>
   <li>AWS</li>
   <li>Github Action CI/CD</li>
   <li>JWT</li>
   <li>Terraform</li>
</ul>

### Architecture

This has been kept simple in order to fulfil the basic function of the API which is to store financial data. 

![Alt text](aws.png)

The ECS service will hold the application in a fargate container which is serverless compute service provided by AWS, this means no need to maintain servers. For the backend databse DynmoDB was picked due to its advantages like being serverless and fully managed. The increasing data complexity over time led to decision to use a NoSQL database over relational database for its high performance. This architecture may change over the course of this project.

### Challenges and Thought process

These are the steps I have taken so far in the project:

<ul>
 <li>Build the basic Sping Boot application and get it to work with dummy data which I managed to get working. The challenges here was trying to get springboot REST API to return data without Null Pointer Exceptions which I resolved by reading and understanding solutions to these problems on Stack Overflow.</li>
 <li>Build a basic CI/CD pipeline job using a third party vendor. In my case, I used github actions because of the easy of use and convinience.</li> 
 <li>Then write a dockerfile to create an image for use in later steps. After the successful functioning of the docker container, I began working on the CI/CD pipeline job to build docker image which required lots of research which paid off after building a successful pipeline that built a docker image and pushed it to AWS</li>
 <li>Write Terraform code to build infrastructure in AWS and setup a pipeline job to automate infrastructure deployment. This step is where I am at when it comes to the challenges I have faced these include finding out how to write pipeline scripts that does that, pipeline syntax and debugging pipeline job. Currently working on a way to speed up the terraform plan phase of the pipeline.</li>
<ul>



