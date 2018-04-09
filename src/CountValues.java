import grid.InfoGrid;

import java.util.HashSet;

/**
 * Created by po917265 on 4/9/18.
 */
public class CountValues {
    public static void main(String[] args) {
        HashSet<InfoGrid> set = new HashSet<InfoGrid>();
        int radix = 2;
        for(int n = 1; n < 6; n++) {
            for(int i = 0; i < (int)Math.pow(2, n*n); i++) {
                if(i % 100000 == 0) System.out.println(i + ": " + set.size());
                InfoGrid ig = new InfoGrid(n);
                ig.set(i, radix);
                set.add(ig);
            }
            System.out.println(set.size());
            set.clear();
        }
    }
}
