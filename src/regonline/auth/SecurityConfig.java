package regonline.auth;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
 
public class SecurityConfig {
 
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
 
    // String: Role
    // List<String>: urlPatterns.
    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();
 
    static {
        init();
    }
 
    private static void init() {
 
        // Configure For "USER" Role.
        List<String> userUrls = new ArrayList<String>();
 
        userUrls.add("/courses/");
        userUrls.add("/programs/");
 
        mapConfig.put(ROLE_USER, userUrls);
 
        // Configure For "ADMIN" Role.
        List<String> adminUrls = new ArrayList<String>();
 
        adminUrls.add("/faculties/");
//        adminUrls.add("/users/");
 
        mapConfig.put(ROLE_ADMIN, adminUrls);
    }
 
    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }
 
    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
 
}