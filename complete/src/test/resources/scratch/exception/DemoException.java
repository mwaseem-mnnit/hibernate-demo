public class DemoException {
    public static void main(String[] args) {
        try{
            fun();
        } catch (NumberFormatException e) {
            System.out.println("main exception");
        } finally {
            System.out.println("main finally");
        }
    }
    private static void fun() {
        try{
            String s = "abc";
            System.out.println(Integer.parseInt(s));
        } catch (NullPointerException e) {
            System.out.println("null pointer");
        } catch (NumberFormatException ex) {
            System.out.println("number format");
        }
        finally {
            System.out.println("finally");
        }
    }
}
