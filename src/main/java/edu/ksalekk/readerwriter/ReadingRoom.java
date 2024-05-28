package edu.ksalekk.readerwriter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ReadingRoom implements WritableReadingRoom, ReadableReadingRoom {
    private final BlockingQueue<Book> readableBooks;
    private final BlockingQueue<Book> writableBooks;

    private final int booksLimit;
    private final AtomicInteger writtenBooksCount;
    private final AtomicInteger reservedBooksToWriteCount;


    public ReadingRoom(int booksLimit) {
        this.readableBooks = new ArrayBlockingQueue<>(100);
        this.writableBooks = new ArrayBlockingQueue<>(100);

        for(int i = 0; i<booksLimit; i++) {
            try {
                this.writableBooks.put(new Book(i));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        this.booksLimit = booksLimit;
        this.writtenBooksCount = new AtomicInteger(0);
        this.reservedBooksToWriteCount = new AtomicInteger(0);
    }

    @Override
    public int getBooksLimit() {
        return booksLimit;
    }

    @Override
    public int getReservedBooksToWriteCount() {
        return reservedBooksToWriteCount.get();
    }

    @Override
    public Book takeBookToWrite() {
        try {
            reservedBooksToWriteCount.incrementAndGet();
            return writableBooks.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void putWrittenBook(Book writtenBook) {
        try {
            readableBooks.put(writtenBook);
            writtenBooksCount.incrementAndGet();
            System.out.println("WRITTEN BOOKS: [" + writtenBooksCount + "/" + booksLimit + "]");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book takeBookToRead(BookReader reader) {
        try {
            return readableBooks.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void putReadBook(Book readBook) {
        try {
            readableBooks.put(readBook);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
