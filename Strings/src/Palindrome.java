
public class Palindrome {
    public static void main(String[] args) {
        String word = "abcba";
        System.out.println(palindrome(word));
    }

    static boolean palindrome(String word) {
        String str = word.toLowerCase();
        int start = 0;
        int end = str.length()-1;
        char[] arr = str.toCharArray();
        while(start <= end) {
            if(arr[start] == arr[end]) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }
}