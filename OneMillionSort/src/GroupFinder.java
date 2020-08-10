import java.util.*;

public class Cat {
    private static HashMap<Integer, Integer> firstNums = new HashMap<>();
    private static HashMap<Integer, Integer> secNums = new HashMap<>();
    private static HashMap<Integer, Integer> therdNums = new HashMap<>();

    private static ArrayList<String> allRawLines = new ArrayList<>(1000000);

    private static ArrayList<ArrayList<Integer>> allGroups = new ArrayList<>();


    private static int currentId = 0;

    public static int getTotalGroupCount() {
        return allGroups.size();
    }

    public static ArrayList<ArrayList<Integer>> getSortedGroups() {
        Collections.sort(allGroups, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> lhs, ArrayList<Integer> rhs) {
                return Integer.compare(rhs.size(), lhs.size());
            }
        });
        return allGroups;
    }


    public static void addNums(String line) {
//        if(currentId == 510050){
//            System.out.println(line);
//            System.exit(0);
//        }
        allRawLines.add(line);
        String[] lines = line.split(";");
        if (lines.length < 3) {
            System.out.println(line);
            currentId++;
            return;
        }
        if (lines[0].length() == 13) {
            int x = Integer.parseInt(lines[0].substring(3, 12));
            if (firstNums.containsKey(x)) {
                group(currentId, firstNums.get(x));
            } else {
                firstNums.put(x, currentId);
            }
        }
        if (lines[1].length() == 17) {
            int x = Integer.parseInt(lines[1].substring(6, 16));
            if (secNums.containsKey(x)) {
                group(currentId, secNums.get(x));
            } else {
                secNums.put(x, currentId);
            }
        }
        if (lines[2].length() == 17) {
            int x = Integer.parseInt(lines[2].substring(6, 16));
            if (therdNums.containsKey(x)) {
                group(currentId, therdNums.get(x));
            } else {
                therdNums.put(x, currentId);
            }
        }
        currentId++;
    }

    private static void group(int newMember, int oldMember) {
        ArrayList<Integer> oldMemberGroup = null;
        ArrayList<Integer> newMemberGroup = null;
        for (ArrayList<Integer> list : allGroups) {
            if (list.contains(oldMember)) {
                oldMemberGroup = list;
            }
            if (list.contains(newMember)) {
                newMemberGroup = list;
            }
        }
        if (oldMemberGroup == null && newMemberGroup == null) {
            ArrayList<Integer> newGroup = new ArrayList<>();
            newGroup.add(oldMember);
            newGroup.add(newMember);
            allGroups.add(newGroup);
            return;
        }
        if (oldMemberGroup != null) {
            if (newMemberGroup == null) {
                oldMemberGroup.add(newMember);
            } else {
                if(oldMemberGroup.equals(newMemberGroup)){
                    return;
                }
                oldMemberGroup.addAll(newMemberGroup);
                allGroups.remove(newMemberGroup);
            }
        }
    }

    public static ArrayList<String> getAllRawLines() {
        return allRawLines;
    }
}