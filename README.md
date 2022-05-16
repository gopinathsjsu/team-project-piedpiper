# Team 8 – PiedPiper – Hotel Booking Application

## Team Members :
- Abhiteja Mandava
- Sai Kiran Madupu
- Sai Santhosh Yamsani
- Sandhya Shekhar

## Tech stack:
- Frontend: React JS
- Backend: Spring Boot
- Database: MongoDB
- Cloud: AWS EC2, Mongo Atlas
- Testing: Postman

## Component Ownership / Task Allocation
- Frontend: Abhiteja, Sai Kiran
- Backend: Sandhya, Santhosh
- Database: Abhiteja
- Cloud Deployment: Sai Kiran
- Integration: Santhosh, Sandhya
- Testing: Abhiteja, Sai Kiran, Sandhya, Santhosh
- Project Management: Sai Kiran
- Documentation: Abhiteja, Sai Kiran, Sandhya, Santhosh

## Scrum Meetings Schedule
- Every Saturday

## Project Board
https://github.com/gopinathsjsu/team-project-piedpiper/projects

## Sprint Sheet
https://docs.google.com/spreadsheets/d/1nsZ4NcqjTfaCyrLj8ynFxO0QwFw5A0jitlPwQG7a56E/edit#gid=0
https://github.com/gopinathsjsu/team-project-piedpiper/blob/main/Documentation/PiedPiper%20Sprint%20Task%20Sheet.xlsx

## Project Journals
| Team member  | Link |
| ------------- | ------------- |
| Abhiteja  | https://github.com/gopinathsjsu/team-project-piedpiper/blob/main/Project%20Journals/abhiteja_journal.md  |
| Sai Kiran  | https://github.com/gopinathsjsu/team-project-piedpiper/blob/main/Project%20Journals/sai_kiran_journal.md  |
| Santhosh | https://github.com/gopinathsjsu/team-project-piedpiper/blob/main/Project%20Journals/santhosh_journal.md  |
| Sandhya | https://github.com/gopinathsjsu/team-project-piedpiper/blob/main/Project%20Journals/sandhya_journal.md  |

## UML Diagrams
### Architecture Diagram
<img width="821" alt="arch" src="https://user-images.githubusercontent.com/18122083/168495457-39ff3fd2-701a-4901-88fe-6e293d877699.png">


## Feature Set
| Feature  | Implementation |
| ------------- | ------------- |
| Manage Hotel rewards Account  | Done  |
| Search for Hotels  | Done  |
| Book rooms of different categories  | Done  |
| Dynamic Pricing | Done  |
| Update/Cancel reservation  | Done  |
| Customer and Employee Signup | Done  |


## Design decisions
### 1. Choosing the frontend framework
React is a client-side JavaScript library that uses server-side rendering. Enhanced support for server-side rendering turns React into a robust performance-oriented solution perfect for creating content-focused applications, be it a web app or native-rendered apps for Android and iOS. React’s Virtual DOM is basically the prime cause of faster rendering and better performance of React apps. 
### 2. Choosing the backend framework
Java Spring is a multi-threaded which implies that many tasks are performed concurrently. Multi-threading helps applications to perform in a better way and is mostly recommended for large scale projects to handle multiple tasks simultaneously. For enterprise web applications, high concurrency is required. As Java Spring is multi-threaded, it requires a thread for each request, and it becomes expensive as it demands many threads to achieve full concurrency.
### 3. Choosing the database
The difference in the way data is represented and interpreted in each makes a significant difference. MongoDB stores data in JSON format with key and value pairs for each entity whereas SQL Databases stores data as a record in a row of the table. SQL Databases facilitate Vertical Scaling, that is Scaling Up. You can scale up the RAM, CPU, and SSD capacities to upscale and add functionalities to your setup. While this can add some significant factors, it cannot be used to alter the framework and is restricted on that front. It facilitates easy data insertion and uses Slave and Master data replication. MongoDB, on the other hand, would be better for Horizontal Scaling or Scaling Out. It is, therefore, preferred by those constantly looking to improve their frameworks, add more servers and expand their storage and set up. This process, known as Sharding, allows dealing with more flexible data and requires more detailed informational indexes and system capacities. Sharding is easily facilitated with MongoDB along with the use of Replica Sets for extending multiple copies of data for accessibility.
### 4. Choosing the cloud platform
AWS offers a seamless integration of the VM instance with load balancer. It also provides Auto-scaling which is an important non-functional requirement. For proper integration with other web services, we went with AWS. Mongo Atlas offers a multi-tenant architecture by default, which ensure the maximum availability of the system, by taking eliminating a single point of failure.

## Extreme Programming (XP) Core Values
### 1. Courage
Courage was an essential piece of our advancement interaction. We all were not extremely sure of our aptitude in the innovation stack, thus to stay aware of the advancement for each run and not dreading to request help or additional time assuming required was essential. It takes mental fortitude to take up overwhelming work and we were most certainly exceptionally bold all through our cycle.
### 2. Simplicity
It is generally great to keep the work basic, like that if any one else from the group needs to take up the work it will not comsume much time. We utilized code refactoring strategies to keep our code clean of any duplications and proficient to run. As there were various API and UI changes straightforwardness was a cruicial part for advancement of this venture.
### 3. Feedback
Taking gathering criticism in any project is essential. This assists the engineer with realizing how well he has advanced and in what ways might he at any point make the work he has improved. Henceforth we utilized Feedback widely all through our venture. We attempted to stick to the timetable and complete the work expected by us to do in the run.


