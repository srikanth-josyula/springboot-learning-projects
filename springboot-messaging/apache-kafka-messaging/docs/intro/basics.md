### Why Apache Kafka :

#### Introduction
- Messaging is used for sharing data between the applications/systems real time	
- There are two popular legacy messaging solutions
	* Publish-Subscribe(Topic)
	* Queue
- There is always a limit on the size of the message, bcz large msg may end up breaking the message broker.
- 

* **Publish-Subscribe(Topic)** :: 
	* Messages are published to a Message Broker and messages will be distributed to all Consumers.
	* Topics retain messages only as long as it takes to distribute them to current subscribers
	* Subscribers must continue to be active for it to consume the messages
* **Time Efficiency (Execution Efficiency)** :: 
	* Messages are published to a Queue and the consumer will read from it. Limitation is you have only one consumer per queue
		
### Advantages of Kafka
   * Messages are not removed from the topic as soon as the consumer consumes it. 
   * Kafka is Horizontally Scalable.
   * Kafka has stronger ordering guarantees than a traditional messaging system
   * Kafka can handle high volume, and it has very high throughput 
   * Kafka supports loosely coupled producers and consumers. 
   * Kafka can also be used as a storage system
   * Kafka provides a zero **Fault-Tolerance**, as it has the messaging cluster kafka stores in cruster and consumer can read the message using various techinques
   
### Architecture of Kafka
   * The component that feeds the message is called **Producer** and that holds the message are called **Topic** and that consumes are called **Consumers**.. 
   * We need to create these topics in Kafka. Each Topics will have a unique name to which producers and consumers publish and consume msgs
   * The topics leave in a broker (**Kafka Broker**). this is nothing but a server which can hold topics
   * Kafka achieves its very high throughput, using **Kafka Cluster**, Which is nothing but *A Group of Kafka Brokers*
   * Cluster distributes the load and process it simultaneously 
   * To achieve simultaneously processing the load needs to be distributed across the cluster and there needs to be cooradinate mechanism 
   * In a world of distributed systems they are achieved using a protocol called **Gossip Protocol**
   * There needs to be a system in place in order to monitor the health and metadata information about the brokers. This is where **Zoo Keeper** comes in.

### Role of Zoo Keeper
   * Zoo keeper is a centralized service for maintaining configuration information, naming, providing distributed sync, and group services.
   * This is responsible for maintaining cluster metadata, over all health of cluster and balancing the broker assignments and re assignments.

