package grid;

/**
 * Created by po917265 on 4/9/18.
 */
public class InfoGrid {
    private int[][] grid;
    private Integer stashedHash = null;

    public InfoGrid(int size) {
        grid = new int[size][size];
    }

    public void set(int row, int col, int val) {
        grid[row][col] = val;
    }

    public int get(int row, int col) {
        return grid[row][col];
    }

    public void set(int value, int radix) {
        String x = Integer.toString(value, radix);
        for(int i = 0; i < grid.length*grid.length - x.length(); i++) {
            x = "0" + x;
        }
        for(int i = 0; i < x.length(); i++) {
            grid[i / grid.length][i % grid.length] = Integer.parseInt("" + x.charAt(i), radix);
        }
    }

    public int get(int radix) {
        String toParse = "";
        for(int i = 0; i < grid.length*grid.length; i++) {
            toParse += Integer.toString(grid[i / grid.length][i % grid.length], radix);
        }
        return Integer.parseInt(toParse, radix);
    }

    public void orientCanonically(int radix) {
        InfoGrid canonical = null;
        Integer minV = null;
        for(int i = 0; i < 4; i++) {
            int v = get(radix);
            if(minV == null || minV > v) {
                canonical = duplicate();
                minV = v;
            }
            mirror();
            v = get(radix);
            if(minV == null || minV > v) {
                canonical = duplicate();
                minV = v;
            }
            mirror();
            rotate();
        }
        grid = canonical.grid;  //put in canonical orientation.
    }

    public boolean identical(InfoGrid other) {
        if(other.grid.length != grid.length) return false;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid.length; j++) {
                if(grid[i][j] != other.grid[i][j]) return false;
            }
        }
        return true;
    }

    public boolean equals(Object other) {
        if(other instanceof InfoGrid) {
            InfoGrid x = (InfoGrid)other;
            for(int i = 0; i < 4; i++) {
                if(x.identical(this)) return true;
                x.mirror();
                if(x.identical(this)) return true;
                x.mirror();
                x.rotate();
            }
        }
        return false;
    }

    public InfoGrid duplicate() {
        InfoGrid ans = new InfoGrid(grid.length);
        copy(grid, ans.grid);
        return ans;
    }

    public void transpose() {
        int[][] temp = new int[grid.length][grid.length];
        copy(grid, temp);
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid.length; j++) {
                temp[i][j] = grid[j][i];
            }
        }
        grid = temp;
    }

    public void mirror() {
        int[][] temp = new int[grid.length][grid.length];
        copy(grid, temp);
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid.length; j++) {
                temp[i][j] = grid[i][grid.length - 1 - j];
            }
        }
        grid = temp;
    }

    public void rotate() {
        mirror();
        transpose();
    }

    public String toString() {
        String ans = "";
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid.length; j++) {
                ans += grid[i][j] + " ";
            }
            ans += "\n";
        }
        return ans;
    }

    private void copy(int[][] grid, int[][] temp) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid.length; j++) {
                temp[i][j] = grid[i][j];
            }
        }
    }

    public int hashCode() {
        if(stashedHash != null) return stashedHash;
        int maxEntry = maxEntry();
        if(maxEntry == 0) maxEntry = 2;
        orientCanonically(maxEntry + 1);
        int ans = get(maxEntry + 1);
        stashedHash = ans;
        return ans;
    }

    private int maxEntry() {
        Integer max = null;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid.length; j++) {
                if(max == null || max < grid[i][j]) max = grid[i][j];
            }
        }
        return max;
    }
}
