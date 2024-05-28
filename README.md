# Java practice programs
This repository contains small Java programming exercises that touch different branches, e.g. i/o operations, network communication, multithreading.

## Run Java program from command line (Linux / git bash)
0. Make sure you have installed JDK ```javac -version```
1. Go to your working directory, e.g. ```cd ./java-practice ```
2. Compile specified .java files to the specified directory, e.g. <br/> ```javac -d bins ./src/main/java/edu/ksalekk/clientserver/PhoneBookServer.java```
3. Go to the root directory for compiled files ```cd ./bins```
4. Run compiled files (remember not to type .class extension) ```java edu/ksalekk/clientserver/PhoneBookServer```

You can also run all these programs in your IDE.
***

# Dining Philosophers (Five Philosophers)
One of the most famous synchronization issues in concurrent (https://en.wikipedia.org/wiki/Dining_philosophers_problem). This solution uses arbitrator (waiter) that is proxy for picking up / putting down forks.

### Run
```
javac -d bins ./src/main/java/edu/ksalekk/fivephilosophers/*.java
cd ./bins
```
You can specify the number of philosophers and forks (default 5):
```
java edu/ksalekk/fivephilosophers/FivePhilosophersTest 10
```

***

# Reader Writer
Next well known concurrency problem where one resource is shared for reading and writing processes. In this varation we defined *W* writers that write in total *B* books (i.e. in all system, not per one writer). There is *R* readers that have to read all *B* books. Each book can be read by at most one reader at once and readers start to reading book as soon as they become available.

## Architecture


## Run
```
javac -d bins ./src/main/java/edu/ksalekk/readerwriter/*.java
cd ./bins
```
You can specify the number of writers (first positional argument), readers (second one) and books (third one):
```
java edu/ksalekk/readerwriter/ReadersWritersProblemSimulation [writers] [readers] [books]
```

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
