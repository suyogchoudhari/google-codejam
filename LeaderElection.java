
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * https://code.google.com/codejam/contest/6304486/dashboard
 *
 */
public class LeaderElection {

    int highestCount = 0;
    Set<Character> uniqueCharSet = new HashSet<>(); // using to find out unique char count. fixed memory
    Map<Integer,List<String>> topLeaders= new HashMap(); // just storing top leader list

    /**
     *
     * naive approach
     * time complexity : m * log(n) where n : number of elements, m: avg length
     * considering log complexity because in every recurssion iteration reducing list size
     */
    public String findAlphabeticallyTopLeader(List<String> leaders) {
        int startIndex = 0;
        return findAlphabeticallyTopLeader (leaders,startIndex);
    }

    public String findAlphabeticallyTopLeader(List<String> leaders, int charIndex) {
        if(leaders.size() ==1) {    // base condition
            return leaders.get(0);
        } else {
            List<String> newLeaders = new ArrayList<String>();
            String leader = leaders.get(0);
            newLeaders.add(leader);
            for (int i = 1; i < leaders.size(); i++) {
                String nextLeader = leaders.get(i);
                if(leader.length() > charIndex && nextLeader.length() > charIndex) {
                    if (nextLeader.charAt(charIndex) < leader.charAt(charIndex)) {
                        newLeaders.clear();
                        newLeaders.add(leaders.get(i));
                        leader = nextLeader;
                    } else if (nextLeader.charAt(charIndex) == leader.charAt(charIndex)) {
                        newLeaders.add(nextLeader);
                    }
                } else {
                    if(leader.length() > charIndex) {
                        newLeaders.clear();
                        newLeaders.add(leader);
                    } else if(nextLeader.length() > charIndex) {
                        newLeaders.clear();
                        newLeaders.add(nextLeader);
                        leader = nextLeader;
                    }
                }
            }
            return findAlphabeticallyTopLeader(newLeaders, charIndex+1);
        }
    }

    /*
     * time complexity : o(n)
     */
    public int getUniqueCharcterCount(String name){
        uniqueCharSet.clear();
        for(int i = 0;i< name.length(); i++) {
            uniqueCharSet.add(name.charAt(i));
        }
        return uniqueCharSet.size();
    }

    /**
     * time complexity - O(n)
     * Need to walk through whole list to find out top leaders
     */
    public List<String> getTopLeaders(List<String> names) {
        Iterator<String> it = names.iterator();
        while(it.hasNext()) {
            String name = it.next();
            int charCount = getUniqueCharcterCount(name);
            if (charCount > highestCount) {
                // removing old entries for save space
                topLeaders.remove(new Integer(highestCount));
                highestCount = charCount;
                List<String> list = new ArrayList();
                list.add(name);
                topLeaders.put(new Integer(highestCount), list);
            } else if (charCount == highestCount) {
                List<String> leaders = topLeaders.get(new Integer(highestCount));
                leaders.add(name);
            }
        }
        return topLeaders.get(new Integer(highestCount));
    }

    public String getLeader(List<String> names) {
        // get list of leaders with ties
        List<String> leaders = getTopLeaders(names);
        return findAlphabeticallyTopLeader(leaders);
    }

    public static void main(String arg[]) {
        LeaderElection le = new LeaderElection();
        List<String> names = new ArrayList();
        names.add("GOOGLE");
        names.add("ASUYO");
        names.add("DAABDEABD");
        names.add("AABDEA");
        names.add("AABDEAB");
        names.add("PPPQQRST");
        String leader = le.getLeader(names);
        System.out.println(leader);
    }
}
