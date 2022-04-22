package mx.edu.utez.neighborhoodcommitte.security;

public class BlacklistController {
    
    private static String[] blacklist = {"<",">","script","SELECT", "FROM","DELETE","INSERT","UPDATE","WHERE","=","&","+","-","AND","OR"};

    public static boolean checkBlacklistedWords(String word) {
        for (int i = 0; i < blacklist.length; i++) {
            if (word.contains(blacklist[i])) {
                return true;
            }
        }
        return false;
    }

}
