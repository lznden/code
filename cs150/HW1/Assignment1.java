
import java.util.ArrayList;

public class Assignment1 {
    public static void main(String[] args) {
        //test code for IntSinglyLinkedList.java
        IntSinglyLinkedList list = new IntSinglyLinkedList();
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        System.out.println("List after adding elements to the front: " + list);

        list.removeValue(20);
        System.out.println("List after removing value 20: " + list);

        int removedValue = list.removeFirst();
        System.out.println("Removed value from front: " + removedValue);
        System.out.println("List after removing front element: " + list);

        System.out.println("Size of the list: " + list.size());
        System.out.println("Is the list empty? " + list.isEmpty());
        //test code for Library.java
        Book book1 = new Book("123", "Java Programming", "John Doe");
        Book book2 = new Book("456", "Data Structures", "Jane Smith");
        Book book3 = new Book("789", "Algorithms", "Alice Johnson");
        
        ArrayList<Book> books = new ArrayList<>();

        books.add(book1);
        books.add(book2);
        books.add(book3);

        ArrayList<Borrower> borrowers = new ArrayList<>();
        
        Library library = new Library(books, borrowers);

        Borrower borrower1 = new Borrower("B001", "Bob Brown");
        Borrower borrower2 = new Borrower("B002", "Sara White");

        library.registerBorrower(borrower1);
        library.registerBorrower(borrower2);

        // Availablility Check
        System.out.println("\n\nIs book with ISBN 123 available? " + library.isAvailable("123"));
        System.out.println("Is book with ISBN 456 available? " + library.isAvailable("456"));
        System.out.println("Is book with ISBN 789 available? " + library.isAvailable("789"));

        // Borrowing Books
        System.out.print("Bob Brown checks out Java Programming: ");
        System.out.println("Checkout Success = " + library.checkout("B001", "123"));
        System.out.println("Java Programming available? " + library.isAvailable("123"));

        //Borrower Book Lists
        System.out.println("Books borrowed by Bob Brown: " + library.getBorrowerBooks("B001"));
        System.out.println("Books borrowed by Sara White: " + library.getBorrowerBooks("B002"));
        System.out.println();

        //Checking in
        System.out.println("Bob Brown returns Java Programming: ");
        System.out.println("Checkin Success: " + library.checkin("123"));
        System.out.println("Java Programming available? " + library.isAvailable("123"));

        //Final Borrower Book Lists
        System.out.println("Books borrowed by Bob Brown: " + library.getBorrowerBooks("B001"));
        System.out.println("Books borrowed by Sara White: " + library.getBorrowerBooks("B002"));
    }
}
