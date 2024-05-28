package edu.ksalekk.readerwriter;

import java.util.concurrent.ThreadLocalRandom;

public class BookWriter implements Runnable {
    private static int writersCount = 0;

    private final String name;
    private final WritableReadingRoom readingRoom;

    public BookWriter(WritableReadingRoom readingRoom) {
        this.name = "\t[W] Writer_" + (++writersCount);
        this.readingRoom = readingRoom;
    }

    public void write(Book bookToWrite) {
        System.out.println(name + " is writing " + bookToWrite.getId() + "...");
        try {
            Thread.sleep(ThreadLocalRandom.current().nextLong(0, 2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(name + " has written " + bookToWrite.getId());
        readingRoom.putWrittenBook(bookToWrite);
    }

    @Override
    public void run() {
        while(readingRoom.getReservedBooksToWriteCount() != readingRoom.getBooksLimit()) {
            Book bookToWrite = readingRoom.takeBookToWrite();
            write(bookToWrite);
        }
    }
}
