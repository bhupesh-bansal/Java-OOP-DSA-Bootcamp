public class FindUnique {
    static void main() {
        int[] arr = {2,3,4,8,4,3,2};

        int unique = 0;
        for(int ele : arr) {
            unique ^= ele;
        }

        System.out.println(unique);
    }
}
