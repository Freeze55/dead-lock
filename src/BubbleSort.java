import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class BubbleSort {


    public static int[] run(int[] input) throws InterruptedException {


        int middle = input.length / 2;

        int[] left = new int[middle];
        int[] right = new int[input.length - middle];

        System.arraycopy(input, 0, left, 0, middle);
        System.arraycopy(input, middle, right, 0, input.length - middle);


        final CountDownLatch latch = new CountDownLatch(2);

        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {

                bubbleSort(left);
                latch.countDown();
            }
        });

        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {

                bubbleSort(right);
                latch.countDown();
            }
        });
        one.start();
        second.start();


        latch.await();

        return merge(left, right);

    }

    private static void bubbleSort(int[] input) {

        int n = input.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (input[j - 1] > input[j]) {

                    temp = input[j - 1];
                    input[j - 1] = input[j];
                    input[j] = temp;
                }

            }
        }
    }


    private static int[] merge(int[] leftPart, int[] rightPart) {
        int l = 0, r = 0, counter = 0;
        int[] merged = new int[leftPart.length + rightPart.length];

        while (l < leftPart.length && r < rightPart.length) {
            if (leftPart[l] <= rightPart[r]) {
                merged[counter] = leftPart[l];
                l++;
            } else {
                merged[counter] = rightPart[r];
                r++;
            }
            counter++;
        }

        if (l < leftPart.length) {
            System.arraycopy(leftPart, l, merged, counter, merged.length - counter);
        }
        if (r < rightPart.length) {
            System.arraycopy(rightPart, r, merged, counter, merged.length - counter);
        }

        return merged;
    }
}
