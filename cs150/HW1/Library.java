
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Library {
    //Fields
        //Array List is used here because we only need to store the names of the books for the catalog
        private final ArrayList<Book> catalog;
        //HashMap is used for fast lookup of books by ISBN, not requiring you to know the name of the books.
        private final HashMap<String, Book> isbnMap;
        //HashSet is used for fast lookup of available books, as you can have empty slots in the library.
        private final HashSet<Book> availableBooks;
        //HashMap is used because we need 2 sets of data, the borrower and the list of books they have borrowed.
        private final HashMap<Borrower, LinkedList<Book>> borrowerHistory;
  
    //Constructor
   public Library(ArrayList<Book> books, ArrayList<Borrower> borrowers) {
        
        catalog = new ArrayList<>();
        isbnMap = new HashMap<>();
        availableBooks = new HashSet<>();
        borrowerHistory = new HashMap<>();

        for (Book b : books) {
            catalog.add(b);
            isbnMap.put(b.getIsbn(), b);
            availableBooks.add(b);

        }
        for (Borrower bt : borrowers) {
            borrowerHistory.put(bt, new LinkedList<Book>());

        }
    }
    //Helper method to find borrower by ID
    private Borrower findBorrowerById(String borrowerId) {
        for (Borrower b : borrowerHistory.keySet()) {
            if (b.getId().equals(borrowerId)) {
                return b;
            }
        }
            throw new IllegalArgumentException("Borrower with id " + borrowerId + " not found.");
    }
    //Methods
    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("book cannot be null!");

        }
        if (isbnMap.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException("Book with isbn already exists.");
        }
        catalog.add(book);
        isbnMap.put(book.getIsbn(), book);
        availableBooks.add(book);
    }
    public Book findByIsbn(String isbn) {
        Book b = isbnMap.get(isbn);
        if (b == null) {
            throw new IllegalArgumentException("Book with isbn " + isbn + " not found.");
        }
        return b;
    }
    public boolean isAvailable(String isbn) {
        Book b = isbnMap.get(isbn);
        if (b == null) {
            throw new IllegalArgumentException("Book with isbn " + isbn + " not found.");
        }
        return availableBooks.contains(b);
    }
        @SuppressWarnings("Convert2Diamond")
    public void registerBorrower(Borrower b) {
        if (b == null) {
            throw new IllegalArgumentException("Borrower cannot be null!");
        }
        if (borrowerHistory.containsKey(b)) {
            throw new IllegalArgumentException("Borrower with id " + b.getId() + " is already registered.");
        }
        borrowerHistory.put(b, new LinkedList<Book>());

    }

    public boolean checkout(String borrowerId, String isbn) {

        Borrower borrower = findBorrowerById(borrowerId);
        Book book = findByIsbn(isbn);
        if (!availableBooks.contains(book)) {
            return false;
        }
    
    availableBooks.remove(book);
    borrowerHistory.get(borrower).add(book);
    return true;
    }
    public boolean checkin(String isbn) {
        Book book = findByIsbn(isbn);
        if (availableBooks.contains(book)) {
            return false;
        }
        Borrower currentBorrower = null;
        for (Borrower b : borrowerHistory.keySet()) {
            LinkedList<Book> books = borrowerHistory.get(b);
            if (books.contains(book)) {
                currentBorrower = b;
                books.remove(book);
                break;
            }
        }
        if (currentBorrower == null) {
            return false;
        }
        availableBooks.add(book);
        return true;
        
    }
    public LinkedList<Book> getBorrowerBooks(String borrowerId) {
        Borrower borrower = findBorrowerById(borrowerId);
        LinkedList<Book> internalList = borrowerHistory.get(borrower);
        return new LinkedList<>(internalList);
    }

}