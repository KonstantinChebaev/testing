
public class Main {
    public static void main(String[] args) {
        long start  = System.currentTimeMillis();
        Reader.readFile();
        long finish  = System.currentTimeMillis();
        System.out.println(GroupFinder.getTotalGroupCount());
        Writer.printAllGroups();
        System.out.println((finish-start)/1000);
    }
}
