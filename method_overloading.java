//Two or more methods can have same name but different parameters.
public class method_overloading {
    static void foo()
    {
        System.out.println("Hello");
    }
    static void foo(int x)
    {
        System.out.println(x);
    }
    public static void main(String[] args)
    {
        foo();
        foo(5);
    }
}
