public class ConvertFloatToString {

    public static void main(String[] args) {

        // Convert float to Float
        float f = 10.56f;
        Float fObj = Float.valueOf(f);

        // Convert Float to String
        String fStr = fObj.toString();

        // Convert String to float
        float f1 = Float.parseFloat(fStr);

        // Convert float to String
        String fStr1 = String.valueOf(f1);

        // Convert String to Float
        Float fObj1 = Float.valueOf(fStr1);

        // Convert Float to float
        float f2 = fObj1.floatValue();

        // Print the converted values
        System.out.println("float to Float: " + fObj);
        System.out.println("Float to String: " + fStr);
        System.out.println("String to float: " + f1);
        System.out.println("float to String: " + fStr1);
        System.out.println("String to Float: " + fObj1);
        System.out.println("Float to float: " + f2);
    }
}