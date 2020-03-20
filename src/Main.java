import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        //deadLock();

        /*double[][] a = MatrixHelper.generateMatrix(500);
        double[][] b = MatrixHelper.generateMatrix(500);

        double[][] c = MatrixHelper.calculateInTwoThreads(a,b);

      MatrixHelper.printMatrix(c);



        WatermarkService watermarkService = new WatermarkService();



        watermarkService.processImage();
        */

        int[] input = new int[50];
        for (int i = 1; i< input.length; i++)
            input[i] = (int) (Math.random()*10000);


        System.out.println("Input array " + Arrays.toString(input));

        System.out.println("Result array " + Arrays.toString(BubbleSort.run(input)));


    }






    public static void deadLock(){

        final String s1 = "lock";
        final String s2 = "lock2";

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s1){
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread 1 locks s1");
                    synchronized (s2)
                    {
                        System.out.println("done from thread 1");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s2){
                    System.out.println("thread 2 locks s2");
                    synchronized (s1)
                    {
                        System.out.println("done from thread 2");
                    }
                }
            }
        }).start();
    }
}
