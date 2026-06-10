//If all elements except one is repeated 3 times in the array, find the unique element

public class FindUnique2 {
    static void main() {
        int[] arr = {2,2,3,2,3,55,3,8,8,8,9,9,9};
        System.out.println(unique(arr));
    }

    static int unique(int[] arr) {
        int unique = 0;
        // Iterate through each of the 32 bit positions
        for(int i = 0; i < 32; i++) {
            int sum = 0;
            for(int ele: arr) {
                // sum of bits of all nums at i-th position
                sum += (ele >> i) & 1;
            }
            // If sum is not divisible by 3, the unique number has this bit set
            if(sum % 3 != 0) {
                unique |= (1 << i);
            }
        }
        return unique;
    }
}