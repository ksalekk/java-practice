package edu.ksalekk.readerwriter;

public interface ReadableReadingRoom {
    Book takeBookToRead(BookReader reader);
    void putReadBook(Book readBook);
    int getBooksLimit();
}
