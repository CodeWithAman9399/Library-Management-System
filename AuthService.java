public class AuthService {

    public static boolean isAdmin(User user) {
        return user.getRole().equalsIgnoreCase("ADMIN");
    }
}