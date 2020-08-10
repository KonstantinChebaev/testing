import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {
    public static void printAllGroups (){
        ArrayList<ArrayList<Integer>> groups = GroupFinder.getSortedGroups();
        ArrayList<String> lines = GroupFinder.getAllRawLines();
        File file = new File("res/result2.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (BufferedWriter out = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true))) {
                for(int i=0;i<groups.size();i++){
                    ArrayList<Integer> groupList = groups.get(i);
                    out.write("Группа "+(i+1));
                    out.newLine();
                    for(int id : groupList){
                        final String nextLine = lines.get(id);
                        out.write(id+nextLine);
                        out.newLine();
                    }
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
