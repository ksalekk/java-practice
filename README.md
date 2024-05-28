# Java practice programs
This repository contains small Java programming exercises that touch different branches, e.g. i/o operations, network communication, multithreading.

## Run Java programm from command line (Linux / git bash)
0. Make sure you have installed JDK ```javac -version```
1. Go to your working directory, e.g. ```cd ./java-practice ```
2. Compile specified .java files to the specified directory, e.g. <br/> ```javac -d bins ./src/main/java/edu/ksalekk/clientserver/PhoneBookServer.java```
3. Go to the root directory for compiled files ```cd ./bins```
4. Run compiled files (remember not to type .class extension) ```java edu/ksalekk/clientserver/PhoneBookServer```
***

# Five Philosophers
***

# Reader Writer
***

# Elevator Simulator
***

# Client Server 
Phone book appliaction with client-server communication. 

### Run
1. Run server program
```
javac -d bins ./src/main/java/edu/ksalekk/clientserver/PhoneBookServer.java
cd ./bins
java edu/ksalekk/clientserver/PhoneBookServer
```
2. Run client program in separated terminal
```
cd ./bins
java edu/ksalekk/clientserver/PhoneBookClient
```
3. Every client request requires new connection (new program running)
```
java edu/ksalekk/clientserver/PhoneBookClient
```

### Architecture
Communication is implemented with text-based protocol and single transmission unit is represented by single text line:
C -> S:
```
put [name] [number]
get [name]
```
[number] is phone number assigned to lastname [name] and 'put' statement causes put such an assignment on the server (if such an assignment exists, it will be replaced with new one).

Both for get and put statements server responses with:
S -> C
```
[name] [number]
```
If a client request resource that does not exist, then the server will response with the statement:
```
--- no name: [name] ---
```
Each client is served in a separated server thread.
***

# Set Calculator
***
