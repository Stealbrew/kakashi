# Kakashi
Kakashi is a backend service built with (Ktor)[https://ktor.io] that serves as the foundation for Endured Network.

### Features
 • Consolidates persistent data using (MongoDB)[https://mongodb.com] and the (KMongo)[https://litote.org/kmongo/] driver
 • Handles communication efficiently between clients and servers
 • Allows developers to focus on creating their game without having to worry about writing their own backend code to integrate with the Endured Network system
 
### Requirements
 • Java 17 or higher
 • MongoDB 4.2 or higher

### Installation
 1. Install MongoDB and start the server
 2. Clone the Kakashi repository: `git clone https://github.com/EnduredNetwork/kakashi.git`
 3. Navigate to the project directory: `cd kakashi`
 4. Build the project: `./gradlew build`
 5. Start the Kakashi service: `./gradlew run`

### Contribution
We welcome contributions to Kakashi. To contribute, please see the (Contributation Guidelines)[https://github.com/EnduredNetwork/kakashi/blob/main/contributing.md] for more information.

### License
Kakashi is licensed under the MIT License. See (LICENSE)[https://github.com/EnduredNetwork/kakashi/blob/main/license.md] for more information.
