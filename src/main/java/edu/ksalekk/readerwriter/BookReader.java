package edu.ksalekk.readerwriter;

import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class BookReader implements Runnable {
    private static int readersCount = 0;

    private final String name;
    private final ReadableReadingRoom readingRoom;
    private final HashSet<Book> readBooks;
    private final int targetReadBooksCount;

    public BookReader(ReadableReadingRoom readingRoom) {
        this.name = "\t\t\t[R] Reader_" + (++readersCount);
        this.readingRoom = readingRoom;
        this.readBooks = new HashSet<>();
        this.targetReadBooksCount = readingRoom.getBooksLimit();
    }

    public void read(Book currentReadBook) {
        System.out.println(name + " is reading " + currentReadBook.getId() + "... ");
        try {
            Thread.sleep(ThreadLocalRandom.current().nextLong(0, 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        readBooks.add(currentReadBook);
        readingRoom.putReadBook(currentReadBook);
        System.out.println(name + " has read " + currentReadBook.getId() + " [" + readBooks.size() + "/" + targetReadBooksCount + "]");
    }

    @Override
    public void run() {
        while(readBooks.size() < targetReadBooksCount) {
            Book bookToRead = readingRoom.takeBookToRead(this);
            boolean bookHasBeenAlreadyRead = readBooks.contains(bookToRead);

            while(bookHasBeenAlreadyRead) {
                readingRoom.putReadBook(bookToRead);
                bookToRead = readingRoom.takeBookToRead(this);
                bookHasBeenAlreadyRead = readBooks.contains(bookToRead);
            }
            read(bookToRead);
        }
        System.out.println("\t\t\t" + name + " HAS READ ALL BOOKS: " + "[" + readBooks.size() + "/" + targetReadBooksCount + "]");
    }
}
