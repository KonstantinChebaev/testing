import java.util.Arrays;
import java.util.List;

/**
 * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
 * from left to right). All vacant positions in the array are zeros.
 *
 * @param /inputNumbers to be used in the pyramid
 * @return 2d array with pyramid inside
 * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
 */

public class PyramidBuilder {
//    public static void main(String[] args) {
//
//        List<Integer> numbers = Arrays.asList(4, 6, 7, 5, 3, 2, 1, 8, 9,10);
//        int [][] pyramid =buildPyramid(numbers);
//        for (int [] aLine : pyramid ){
//            for(int num : aLine){
//                System.out.print(num);
//            }
//            System.out.print("\n");
//        }
//    }

    public int[][] buildPyramid(List<Integer> inputNumbers) {
        int[] list;
        try {
            list = inputNumbers.stream().mapToInt(i -> i).toArray();
        } catch (NullPointerException e ){
            throw new CannotBuildPyramidException();
        } catch (OutOfMemoryError E){
            throw new CannotBuildPyramidException();
        }
        Arrays.sort(list);
        int test = list.length;
        int x = 1;
        while (test > 0) {
            test -= x;
            x++;
        }
        if (test<0){
            throw new CannotBuildPyramidException();
        }
        int primeNum = x-1;


        int [][] pyramid = new int [primeNum][primeNum*2-1];
        for (int [] aLine : pyramid ){
            for(int num : aLine){
                num=0;
            }
        }
        int totalAmount = list.length-1;
        int bottom = 0;
        int roof = primeNum*2-2;
        for(int a = primeNum-1;a>=0;a--){

            for(int b = roof;b>=bottom;b-=2){
                if(totalAmount==-1)break;
                pyramid[a][b]=list[totalAmount];
                System.out.println(totalAmount);
                totalAmount-=1;
            }
            roof--;
            bottom++;
        }

        return pyramid;
    }
}
