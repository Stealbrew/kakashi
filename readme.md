<p align="center">
    <strong>Kakashi, a backend built with Ktor.</strong>
</p>

---
# Kakashi
Kakashi is the backend service holding Endured Network together.

The primary goal of implementing this backend was to consolidate persistent data and handle communication efficiently and
allow developers to implement games onto Endured Network without having to worry about writing their own messy backend code
to integrate with our system.

If you want to contribute to our project read our [Contributing Guidelines](https://github.com/EnduredNetwork/kakashi/blob/main/CONTRIBUTING.md) beforehand.

# Technologies
Kakashi, uses [Ktor](https://ktor.io/) to handle routing and requests to our backend service, we use [MongoDB](https://www.mongodb.com/)
to manage persistent data and [KMongo](https://litote.org/kmongo/) to interface with that persisted data in our service.
