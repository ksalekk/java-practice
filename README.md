# Java practice programs
The repository contains few small Java programs that touch various branches of Java programming, e.g. multithreading, i/o operations, network communication, collections.

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

The following solution may not prevent starvation because it relies on the notifyAll() method to notify threads about resources free so that may depends on the JVM implementation. For a relatively small number of philosophers (up to 10), no starvation was observed.

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
Another well-known problem of multithreaded programs where one resource is shared between 2 types of processes – those that do not make changes to this resource (readers) and those that make changes (writers).

The program is parameterized with three positional arguments:
```
W  number of writers creating books
R  number of readers reading books 
B  total number of books to be written (i.e. in the entire system, not per one writer)
```

It is assumed that every reader must read all the books created in the system. A book can be read by only one reader at once and readers need to start reading books as soon as these books become available.

### Architecture
![Readers and writers UML diagram](./src/main/resources/readers-writers-uml.png)

### Run
```
javac -d bins ./src/main/java/edu/ksalekk/readerwriter/*.java
cd ./bins
java edu/ksalekk/readerwriter/ReadersWritersProblemSimulation [writers] [readers] [books]
```
***

## Client Server 
Small phone book application with client-server communication. Protocol is text-based, the transmission unit is a line of text, ending with a newline character (\n).

#### C -> S
```
put [name] [number]
get [name]
```
[number] is the phone number assigned to the lastname [name], and the put message places this assignment on the server. If there is an assignment on the server, it is replaced by a new one. 

#### S -> C
Both for get and put statements server responses with:
```
[name] [number]
```

If there is no assignment, the server returns an error line:
```
--- no name: [name] ---
```

The client program allows to enter pairs (name, phone number) into the server. Lowercase and uppercase letters are not distinguished. Each client is served in a separate thread of the server program.


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
***

## Set Calculator
A program that compute simple logical operations on two user specified sets of non negative int numbers. Input data has the form:
```
[first, set, data] * | + | – [second, set, data]
where * is the intersection of sets, + is the sum of sets, and – is the difference of sets

For example:
[5,3,8] * [1,0,3] == [3]
[5,3,8] + [1,0,3] == [0,1,3,5,8]
```

### Run
```
javac -d bins ./src/main/java/edu/ksalekk/setcalculator/SetCalculator.java
cd ./bins
java edu/ksalekk/setcalculator/SetCalculator
```

***
