import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TinyUrl {

    Queue<String> tinyUrlQueue = new LinkedList<>();
    Map<String,String> tinyToURL = new HashMap<String,String>();
    Map<String,String> urlToTiny = new HashMap<String,String>();

    public static void main(String str[]){
        System.out.println("testing..");

        TinyUrl tinyUrl = new TinyUrl();

        // [0-9][a-z][A-Z] added randomly to String
        //String charList = "0bDzPcX1dAmB3QaEeGnS4tFoO";
        //String charList = "0bDzPc1";
        String charList = "0123456";
        System.out.println(tinyUrl.generateTinyUrlList(charList, 3, 0, 1, 0));

        String tinyUrlPrefix = "";
        String url1 = "www.aaaa.com";
        String url2 = "www.bbbb.com";

        String code1 = tinyUrl.generateTinyUrlForURL(url1);
        String code2 = tinyUrl.generateTinyUrlForURL(url2);

        System.out.println(" url for " + code1 +": " + tinyUrl.tinyToURL.get(code1));
        System.out.println(" url for " + code2 +": " + tinyUrl.tinyToURL.get(code2));

    }

    public Queue<String> generateTinyUrlList(String list, int tinyUrlSize, int startIndex, int nextIndex, int diff) {
        if (startIndex <= list.length() - tinyUrlSize) {
            if (nextIndex < list.length() - tinyUrlSize + 2) {
                while(diff <= tinyUrlSize) {
                    tinyUrlQueue.add(list.substring(startIndex, startIndex+diff+1) +":"+ list.substring(nextIndex+diff, nextIndex + tinyUrlSize - diff));
                    System.out.println(tinyUrlQueue);
                    diff++;
                }
                return generateTinyUrlList(list, tinyUrlSize, startIndex, nextIndex + 1, diff);
            } else {
                return generateTinyUrlList(list, tinyUrlSize, startIndex+1, startIndex + 2, diff);
            }
        }
        return tinyUrlQueue;
    }

    public String generateTinyUrlForURL(String url){
        String code = tinyUrlQueue.poll();
        tinyToURL.put(code,url);
        urlToTiny.put(url,code);
        return code;
    }
}
