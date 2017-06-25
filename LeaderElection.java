package google.code.bootcamp;

import java.util.ArrayList;
import java.util.Arrays;
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
	Set<Character> set = new HashSet<>();
	Map<Integer,List<String>> topLeaders= new HashMap();
	//List<String> names =
	
	/*
	 * time complexity : o(n)
	 */
	public int getUniqueCharcterCount(String name){
		 set.clear();
		 for(int i = 0;i< name.length(); i++) {
			set.add(name.charAt(i)); 
		 }
		 return set.size();
	}
	
	public List<String> getTopLeaders(List<String> names) {
		Iterator<String> it = names.iterator();
		while(it.hasNext()) {
			String name = it.next();
			int charCount = getUniqueCharcterCount(name);
			if (charCount > highestCount) {
				// removing old entries for save space
				topLeaders.remove(new Integer(highestCount));
				highestCount = charCount;
				topLeaders.put(new Integer(highestCount), Arrays.asList(name));
			} else if (charCount == highestCount) {
				List<String> leaders = topLeaders.get(new Integer(highestCount));
				leaders.add(name);
			}
		}
		return names;
	}
	
	public static void main(String arg[]) {
		LeaderElection le = new LeaderElection();
		System.out.println(le.getUniqueCharcterCount("GOOGLE"));
		System.out.println(le.getUniqueCharcterCount("SUYOG"));
		List<String> names = new ArrayList();
		names.add("GOOGLE");
		names.add("SUYOG");
		names.add("ABCDE");
		List<String> leaders = le.getTopLeaders(names);
		System.out.println(leaders.size());
	}
}
