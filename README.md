# Java practice programs
The repository contains few small Java programs on various issues, e.g. multithreading, i/o operations, network communication, collections.

### Run Java program from command line (Linux / git bash)
1. Make sure you have installed JDK
   ```
   javac -version
   ```
2. Clone the repository to your working directory
   ```
   git clone https://github.com/ksalekk/java-practice.git
   cd ./java-practice
   ```
3. Compile .java files
   ```
   javac -d bins ./src/main/java/edu/ksalekk/[project-directory]/*.java
   ```
4. Go to the root directory for compiled files and run specified file (remember not to type .class extension)
   ```
   cd ./bins
   java edu/ksalekk/[project-directory]/[test-class]
   ```

You can also run all programs in your IDE.
***

## Dining Philosophers (Five Philosophers)
One of the most famous problems of concurrent programming, in which multiple threads (philosophers) use shared resources (forks). The following solution uses an arbiter (waiter) which is asked to pick up and put away forks for philosohpers. This makes the waiter aware of which forks are currently in use and the solution prevents deadlock.

The following solution may not prevent starvation because it relies on the notifyAll() method to notify threads about resources free and that may depends on the JVM implementation. For a relatively small number of philosophers (up to 10), no starvation was observed.

### Run
```
javac -d bins ./src/main/java/edu/ksalekk/fivephilosophers/*.java
cd ./bins
```
The program is parameterized by the number of philosophers and forks (default 5):
```
java edu/ksalekk/fivephilosophers/FivePhilosophersTest [philosophers_cnt]
```
***


## Reader Writer
Another well-known problem of multithreaded programs, in which one resource is shared between 2 types of processes – those that do not make changes to this resource (reader) and those that make changes (writer).

The program is parameterized with positional arguments:
```
W  number of writers creating books
R  number of readers reading books 
B  total number of books to be written (i.e. in the entire system, not per one writer)
```

In this reader-writer problem variation it is assumed that every reader must read all the books created in the system. A book can be read by only one reader at once and readers need to start reading books as soon as these books become available.

## Architecture
![Readers and writers UML diagram](./src/main/resources/readers-writers-uml.jpg)

## Run
```
javac -d bins ./src/main/java/edu/ksalekk/readerwriter/*.java
cd ./bins
java edu/ksalekk/readerwriter/ReadersWritersProblemSimulation [writers] [readers] [books]
```
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
