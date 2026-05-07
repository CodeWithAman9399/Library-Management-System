import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // Add a new book
    public void addBook(Book book) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO books(title, author, total_copies, available_copies) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getTotalCopies());
            ps.setInt(4, book.getAvailableCopies());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Issue a book to a user
    public boolean issueBook(int bookId, String username) {
        try (Connection con = DBConnection.getConnection()) {
            // Check if copies are available
            String check = "SELECT available_copies FROM books WHERE id=?";
            PreparedStatement ps1 = con.prepareStatement(check);
            ps1.setInt(1, bookId);
            ResultSet rs = ps1.executeQuery();

            if (rs.next() && rs.getInt("available_copies") > 0) {
                // Decrease available copies
                String sql = "UPDATE books SET available_copies=available_copies-1 WHERE id=?";
                PreparedStatement ps2 = con.prepareStatement(sql);
                ps2.setInt(1, bookId);
                ps2.executeUpdate();

                // Track which user issued it
                String track = "INSERT INTO issued_books(book_id, username, issue_date) VALUES(?, ?, NOW())";
                PreparedStatement ps3 = con.prepareStatement(track);
                ps3.setInt(1, bookId);
                ps3.setString(2, username);
                ps3.executeUpdate();

                return true;
            } else {
                return false; // No copies available
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Return a book
    public boolean returnBook(int bookId, String username) {
        try (Connection con = DBConnection.getConnection()) {
            // Remove from issued_books
            String track = "DELETE FROM issued_books WHERE book_id=? AND username=?";
            PreparedStatement ps = con.prepareStatement(track);
            ps.setInt(1, bookId);
            ps.setString(2, username);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                // Increase available copies
                String sql = "UPDATE books SET available_copies=available_copies+1 WHERE id=?";
                PreparedStatement ps2 = con.prepareStatement(sql);
                ps2.setInt(1, bookId);
                ps2.executeUpdate();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all books (admin)
    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM books");
            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt("id"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setTotalCopies(rs.getInt("total_copies"));
                b.setAvailableCopies(rs.getInt("available_copies"));
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get available books (for user)
    public List<Book> getAvailableBooks() {
        List<Book> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM books WHERE available_copies > 0");
            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt("id"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setTotalCopies(rs.getInt("total_copies"));
                b.setAvailableCopies(rs.getInt("available_copies"));
                list.add(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get issued books for a specific user
    public List<IssuedBook> getIssuedBooksForUser(String username) {
        List<IssuedBook> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT ib.book_id, b.title, b.author, ib.issue_date " +
                    "FROM issued_books ib " +
                    "JOIN books b ON ib.book_id = b.id " +
                    "WHERE ib.username = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                IssuedBook ib = new IssuedBook();
                ib.setBookId(rs.getInt("book_id"));
                ib.setTitle(rs.getString("title"));
                ib.setAuthor(rs.getString("author"));
                ib.setIssueDate(rs.getTimestamp("issue_date"));
                list.add(ib);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}