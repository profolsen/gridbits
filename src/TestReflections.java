import grid.InfoGrid;

/**
 * Created by po917265 on 4/9/18.
 */
public class TestReflections {
    public static void main(String[] args) {
        InfoGrid ig = new InfoGrid(3);
        ig.set(0, 0, 1);
        ig.set(0, 1, 2);
        ig.set(0, 2, 3);
        ig.set(1, 0, 4);
        ig.set(1, 1, 5);
        ig.set(1, 2, 6);
        ig.set(2, 0, 7);
        ig.set(2, 1, 8);
        ig.set(2, 2, 9);
        InfoGrid ig2 = ig.duplicate();
        for(int i = 0; i < 4; i++) {
            System.out.println(ig2.equals(ig));
            ig2.mirror();
            System.out.println(ig2.equals(ig));
            ig2.mirror();
            ig2.rotate();
        }

        System.out.println(ig);
        ig.mirror();
        System.out.println(ig);
        ig.mirror();
        ig.rotate();
        System.out.println(ig);
        ig.mirror();
        System.out.println(ig);
        ig.mirror();
        ig.rotate();
        System.out.println(ig);
        ig.mirror();
        System.out.println(ig);
        ig.mirror();
        ig.rotate();
        System.out.println(ig);
        ig.mirror();
        System.out.println(ig);
        ig.mirror();
        ig.rotate();
        System.out.println(ig);

        InfoGrid h = new InfoGrid(3);
        h.set(234, 2);
        System.out.println(h);

    }
}
