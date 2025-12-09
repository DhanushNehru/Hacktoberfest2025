import java.util.Random;

public class OTPGenerator {
    public static String generateNumericOTP(int length) {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10)); // 0-9
        }
        return otp.toString();
    }

    public static void main(String[] args) {
        System.out.println("6-digit OTP: " + generateNumericOTP(6));
    }
}   
