# NET4005 - Assignment 3 - SOAP
## Requirements:
- Servers
- Service
- API Server
- Client

## Server:
### Args: 
Port, Server Number
### Methods: 
info - returns info, port, name, id, etc
amIConnected - simple test

## Services:
### Args: 
service port, server ports
### Methods: 
getServerInfo - get server info method
getServerAmIConnected - test connection to server
amIConnected - simple test

## API Gateway:
### Args: 
API Gateway port, service port
Logs all querries
### Methods: 
apiStatus - returns logs
getServerInfo - get server information
amIConnected - simple test

## Client:
### Args: 
API Gateway port
### Methods:
autoTest - Runs through 1000 tests.

