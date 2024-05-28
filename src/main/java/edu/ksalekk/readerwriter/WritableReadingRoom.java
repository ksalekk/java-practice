package edu.ksalekk.readerwriter;

public interface WritableReadingRoom {
    Book takeBookToWrite();
    void putWrittenBook(Book writtenBook);
    int getBooksLimit();
    int getReservedBooksToWriteCount();
}
