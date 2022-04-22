package mx.edu.utez.neighborhoodcommitte.security;

public class BlacklistController {
    
    private static String[] blacklist = {"<",">","SCRIPT","SELECT", "FROM","DELETE","INSERT","UPDATE","WHERE","=","&","+","-","AND","OR","/SCRIPT","HREF","SRC","TH:","%","*","/"};

    public static boolean checkBlacklistedWords(String word) {
        for (int i = 0; i < blacklist.length; i++) {
            if (word.toUpperCase().contains(blacklist[i])) {
                return true;
            }
        }
        return false;
    }

}
