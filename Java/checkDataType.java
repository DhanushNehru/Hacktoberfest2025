public class checkDataType {
    public checkDataType() {
        System.out.println("DataTypeChecker object created.");
    }

    public static void checkDataType(Object obj) {
        System.out.println("The type is: " + (obj == null ? "null" : obj.getClass().getSimpleName()));
    }
}
