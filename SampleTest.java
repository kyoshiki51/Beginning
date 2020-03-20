public class SampleTest {
    public static void main (String[] args) {
        System.out.println("テストだよ");
        for (int i = 0; i < args.length; i++) {
            System.out.print("入力" + i + "件目：");
            System.out.println(args[i]);
        }
    }
}