+---------- NET 4005 - Assignment 2 ----------+
+-------- Alexandru Viman - 100967379 --------+
+------- Georges Ankenmann - 100935237 -------+
+---------------- README.TXT -----------------+
+---------------------------------------------+

-- REQUIREMENTS --
Java 8

-- HOW TO --

BUILD:   javac Publisher.java
RUN:     java  Publisher

BUILD:   javac Client.java
RUN:     java  Client <Service_Address1> <Service_Address2>
RUN:     java  Client
EXAMPLE: java  http://localhost:8000/ServiceA?wsdl http://localhost:9000/ServiceB?wsdl

-- FILES --
- Publisher.java
- Client.java
- ServiceA.java
- ServiceA_Impl.java
- ServiceB.java
- ServiceB_Impl.java

Publisher.java
- The SOAP API publishes service endpoints that allow Clients to interface with back-end server functions.
- This application supports two services: ServiceA and ServiceB
- Each Service has a front-end that listens for requests
- The front-end dispatches requests to one of two back-end servers in a Round Robin manner (load-balancing)

Client.java
- The Client interfaces with the SOAP API (Publisher) and sends it requests for both services
- The console output is for SOAP server replies
  - Replies are formatted as: Service# = server_ip:server_port
- Supports two modes: Static and Dynamic
  - Static: No command line arguments required
  - Dynamic: User must specify two SOAP service addresses

ServiceA.java
- A simple Interface for the service's supported functions

ServiceA_Impl.java
- This service listens for requests on port 8000 and dispatches them to back-end servers on ports 8001 or 8002
- Returns the server's response to the Client

ServiceB.java
- A simple Interface for the service's supported functions

ServiceB_Impl.java
- This service listens for requests on port 9000 and dispatches them to back-end servers on ports 9001 or 9002
- Returns the server's response to the Client

-- SOAP FUNCTIONS --
ServiceA.getSvcName()
ServiceB.getSvcName()
