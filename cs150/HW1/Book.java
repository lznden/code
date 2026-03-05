
public class Book {
    //Fields
    private final String isbn;
    private final String title;
    private final String author;
    //Constructor
    public Book(String isbn, String title, String author) {
    
        if (isbn == null || isbn.isEmpty() || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("isbn cannot be null or blank!");

        }
        if (title == null || title.isEmpty() || title.trim().isEmpty()) {
            throw new IllegalArgumentException("title cannot be null!");

        }
        if (author == null || author.isEmpty() || author.trim().isEmpty()) {
            throw new IllegalArgumentException("author cannot be null!");
        
        }
        //Initialize Fields
        this.isbn = isbn;
        this.title = title;
        this.author = author;

        }
        @Override
        public String toString() {
            return "isbn = "+ isbn + ", title = " + title + ", author = " + author;
        }
        @Override
        public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book)) return false;
        Book other = (Book) obj;
        return this.isbn.equals(other.isbn);
        }
        @Override
        public int hashCode() { 
            return isbn.hashCode();
        }
        //Getters
        public String getIsbn() {
        return isbn;
        }
        public String getTitle() {
        return title;
        }
        public String getAuthor() {
        return author;
        }

}