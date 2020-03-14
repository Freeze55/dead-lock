public class Main {

    public static void main(String[] args)  {
/*
        double[][] a = MatrixHelper.generateMatrix(500);
        double[][] b = MatrixHelper.generateMatrix(500);

        double[][] c = MatrixHelper.calculateInTwoThreads(a,b);
        */

        WatermarkService watermarkService = new WatermarkService();



        watermarkService.processImage();


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
