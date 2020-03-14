public class MatrixHelper {



    public synchronized  static double[][] calculateInTwoThreads(double[][] a, double[][] b) throws InterruptedException {

        double[][] res = new double[a.length][a.length];
        synchronized (res) {

            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {

                    for (int i = 0; i < a.length; i++) {
                        for (int j = 0; j < a.length; j++) {
                            res[i][j] = 0;
                            for (int k = 0; k < a.length; k++) {
                                res[i][j] = res[i][j] + a[i][k] * b[k][j];
                            }
                        }

                    }
                }
            });

            Thread second = new Thread(new Runnable() {
                @Override
                public void run() {

                    for (int i = 1; i < a.length; i += 2) {
                        for (int j = 0; j < a.length; j++) {
                            res[i][j] = 0;
                            for (int k = 0; k < a.length; k++) {
                                res[i][j] = res[i][j] + a[i][k] * b[k][j];
                            }
                        }

                    }
                }
            });
            one.start();
            second.start();
        }


        return res;
    }



    public static double[][] calculate(double[][] a, double[][] b){

        double[][] res = new double[a.length][a.length];

        for (int i = 0; i < a.length; i++){
            for (int j = 0; j < a.length; j++){
                res[i][j] = 0;
                for (int k=0; k<a.length; k++){
                    res[i][j] =res[i][j] + a[i][k]*b[k][j];
                }
            }

        }

        return res;
    }






    public static double[][] generateMatrix(int size){
        double[][] res = new double[size][size];

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                res[i][j] = Math.random()*1000;
            }
        }
        return res;
    }

    public static void printMatrix(double[][] matrix){
        System.out.println("++++++++++++++++++++++++++++++++++++");
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("++++++++++++++++++++++++++++++++++++");
    }
}
