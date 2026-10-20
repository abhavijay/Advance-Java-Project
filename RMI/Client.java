import java.rmi.Naming;

public class Client {
    public static void main(String args[]) {
        try {
            Calculator obj =
                (Calculator) Naming.lookup("rmi://localhost/Calculator");

            System.out.println("Addition: " + obj.add(10, 5));
            System.out.println("Subtraction: " + obj.subtract(10, 5));

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}