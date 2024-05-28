package edu.ksalekk.readerwriter;

public class ReadersWritersProblemSimulation {
    private static Thread[] createReaders(int readersCount, ReadableReadingRoom readingRoom) {
        Thread[] readers = new Thread[readersCount];
        for(int i=0; i<readersCount; i++) {
            readers[i] = new Thread(new BookReader(readingRoom));
        }
        return readers;
    }

    private static Thread[] createWriters(int writersCount, WritableReadingRoom readingRoom) {
        Thread[] writers = new Thread[writersCount];
        for(int i=0; i<writersCount; i++) {
            writers[i] = new Thread(new BookWriter(readingRoom));
        }
        return writers;
    }

    public static void main(String[] args) {
        int w, r, b;

        if(args.length == 3) {
            w = Integer.parseInt(args[0]);
            r = Integer.parseInt(args[1]);
            b = Integer.parseInt(args[2]);
        } else {
            w = 2;
            r = 10;
            b = 10;
        }

        ReadingRoom readingRoom = new ReadingRoom(b);
        Thread[] bookWriters = createWriters(w, readingRoom);
        Thread[] bookReaders = createReaders(r, readingRoom);

        System.out.println("READERS: " + r);
        System.out.println("WRITERS: " + w);
        System.out.println("BOOKS: " + b + "\n");

        for(Thread writer: bookReaders) {
            writer.start();
        }

        for(Thread reader: bookWriters) {
            reader.start();
        }
    }
}
