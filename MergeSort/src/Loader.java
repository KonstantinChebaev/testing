public class Loader {  //сортировка слиянием
    static int [] primeArr = {3,6,1,2,3,3,7,9,34,12,97,6,23,346,665,34,12,2,9,0,65,34,1521,7,7,82,3}; //изначальный массив

    public static void main(String[] args) {
        for(int x : sorting(primeArr)){   // вывод отсортированного массива
            System.out.print(x+" ");
        }
    }



    public static int[] sorting(int[] arr) {
        if (arr.length == 1)
            return arr;
        else {
            int halfOne[] = new int[arr.length/2];
            int halfTwo[] = new int[arr.length - arr.length/2];
            for (int i = 0; i < arr.length; i++) {
                if (i < arr.length/2) halfOne[i] = arr[i];
                else halfTwo[i - arr.length/2] = arr[i];
            }
            halfOne = sorting(halfOne);
            halfTwo = sorting(halfTwo);
            arr = merging(halfOne,halfTwo);
            return arr;
        }
    }

        public static int[] merging ( int[] x, int[] y) {
        int c[] = new int [x.length + y.length];
        int a = 0, b = 0;
        for (int i = 0; i < x.length + y.length; i++) {
            if (a == x.length) {
                c[i] = y[b];
                b++;
            }
            else if (b == y.length) {
                c[i] = x[a];
                a++;
            }
            else if (x[a] > y[b]) {
                c[i] = y[b];
                b++;
            }
            else {
                c[i] = x[a];
                a++;
            }
        }
        return c;
    }

}
