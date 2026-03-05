public class Borrower {
    //Fields
    private String id;
    private String name;
    //Constructor
    public Borrower(String id, String name) {
        if (id == null || id.isEmpty() || id.trim().isEmpty()) {
            throw new IllegalArgumentException("id cannot be null!");
        }
        if (name == null || name.isEmpty() || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name cannot be null!");
        }
        this.id = id;
        this.name = name;
    }
    //Getters
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    //Checks for existing borrower based on ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Borrower)) return false;
        Borrower other = (Borrower) obj;
        return this.id.equals(other.id);
    }
    //Returns hashcode based on ID
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}