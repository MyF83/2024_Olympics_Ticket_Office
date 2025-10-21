import java.security.MessageDigest;
import java.util.Random;

public class GenerateKeys {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();
    
    public static String generateUserKeyHash(Long user_id, String baseDate) {
        try {
            // Use specific timestamps for reproducible keys based on creation dates
            long timestamp;
            switch (user_id.intValue()) {
                case 1: timestamp = 1640995200000L; break; // 2022-01-01 approx
                case 2: timestamp = 1696147200000L; break; // 2023-10-01 approx  
                case 3: timestamp = 1696492800000L; break; // 2023-10-05 approx
                case 4: timestamp = 1709625600000L; break; // 2024-03-05 approx
                default: timestamp = System.currentTimeMillis();
            }
            
            // Generate 256 deterministic "random" characters based on user_id
            StringBuilder randomChars = new StringBuilder(256);
            Random userRandom = new Random(user_id * 12345); // Seed based on user_id for reproducibility
            for (int i = 0; i < 256; i++) {
                randomChars.append(CHARACTERS.charAt(userRandom.nextInt(CHARACTERS.length())));
            }
            
            // Combine: timestamp + user_id + random_characters
            String input = timestamp + user_id.toString() + randomChars.toString();
            
            // Generate SHA-256 hash
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
            
            // Convert to hex string (64 characters)
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generating user key hash", e);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Generated SHA-256 keys for users:");
        System.out.println("User 1 (Superad-Min): " + generateUserKeyHash(1L, "2022-01-01"));
        System.out.println("User 2 (Fournier-Myriam): " + generateUserKeyHash(2L, "2023-10-01"));
        System.out.println("User 3 (Bond-James): " + generateUserKeyHash(3L, "2023-10-05"));
        System.out.println("User 4 (Empl-Oyee): " + generateUserKeyHash(4L, "2024-03-05"));
    }
}