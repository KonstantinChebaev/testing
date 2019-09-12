import java.util.List;

public class Subsequence {

    public boolean find(List x, List y) {
        if(x==null||y==null)throw new IllegalArgumentException();

        int start = 0;
        int coinNum = x.size();
        for(Object xObject : x){
            for (int a = start;a<y.size();a++){
                if(xObject.equals(y.get(a))){
                    coinNum--;
                    start = a;
                    break;
                }
            }
        }
        if(coinNum==0) return true;
        else  return false;
    }
}
