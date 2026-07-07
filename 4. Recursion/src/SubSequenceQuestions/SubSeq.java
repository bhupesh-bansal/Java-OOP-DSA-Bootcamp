package SubSequenceQuestions;

import java.util.ArrayList;

public class SubSeq {
    static void main() {
        String s = "abc";
        //subSequence("", s);

        System.out.println(subSeqRet("", s));
    }

    static void subSequence(String p, String up) {
        if(up.isEmpty()) {
            System.out.println(p);
            return;
        }

        char ch = up.charAt(0);

        subSequence(p + ch, up.substring(1));
        subSequence(p, up.substring(1));
    }

    static ArrayList<String> subSeqRet(String p, String up) {
        if(up.isEmpty()) {
            ArrayList<String> list= new ArrayList<>();
            list.add(p);
            return list;
        }

        char ch = up.charAt(0);

        ArrayList<String> left = subSeqRet(p + ch, up.substring(1));
        ArrayList<String> right = subSeqRet(p, up.substring(1));

        left.addAll(right);

        return left;
    }
}