# Vert.x 

Eclipse Vert.x is an event-driven and non-blocking **toolkit** for developing reactive applications on JVM. It was designed for asynchronous communications.

### Core Concepts

Vert.x has core concepts: Verticle; Event Loop; Event Bus

#### Verticle

A verticle is a unit of deployment. Verticle allows to encapsulate code for different needs and run completely independently of each other. Verticles in Vert.x are: Standard Verticles; Worker Verticles; Multi-threaded Worker Verticles.

#### Event Loop

The event loop of Vert.x similar to that existing in asynchronous programming models. Vert.x has a golden role: **Don’t block the event loop.** But, Vert.x has a solution(*executeBlocking method*) for blocking and inevitable situation.

#### Event Bus

The event bus is the nervous system of Vert.x. Verticles communication goes through asynchronous message passing provided over the event bus. The event bus supports the following communication modes: point to point; request-response; publish/subscribe for broadcasting;