import java.util.ArrayList;


public class Cell {

    private final ArrayList<String> lines = new ArrayList<>();

    private final String
            right_sym,
            left_sym,
            up_sym,
            bottom_sym;

    private String
            content,
            as_string;

    public Cell() {
        System.out.println("Default cell. Border sym is *, 1 row, 1 cols, empty content");
        String border_sym = "*";
        int rows = 1, cols = 1;
        this.right_sym = border_sym;
        this.left_sym = border_sym;
        this.up_sym = border_sym;
        this.bottom_sym = border_sym;
        this.content = " ";
        this.as_string = "";
        this.makeCell(rows, cols);
    }

    public Cell(
            int rows,
            int cols,
            String border_sym
    ) {
        if ((rows <= 0)
            || (cols <= 0)) {
            System.out.println("Cell should at least have 1 row and 1 column!");
            System.out.println("Setting rows and cols to 1!");
            rows = 1;
            cols = 1;
        }
        this.right_sym = border_sym;
        this.left_sym = border_sym;
        this.up_sym = border_sym;
        this.bottom_sym = border_sym;
        this.content = " ";
        this.as_string = "";
        this.makeCell(rows, cols);
    }

    public Cell(
            int rows,
            int cols,
            String right_sym,
            String left_sym,
            String up_sym,
            String bottom_sym,
            String content
    ) {
        if ((rows <= 0)
                || (cols <= 0)) {
            System.out.println("Cell should at least have 1 row and 1 column!");
            System.out.println("Setting rows and cols to 1!");
            rows = 1;
            cols = 1;
        }
        this.right_sym = right_sym;
        this.left_sym = left_sym;
        this.up_sym = up_sym;
        this.bottom_sym = bottom_sym;
        this.content = content;
        this.as_string = "";
        this.makeCell(rows, cols);
    }

    public Cell getNew(
            int rows,
            int cols,
            String right_sym,
            String left_sym,
            String up_sym,
            String bottom_sym,
            String content
    ) {
        /**
         * Set integers to -1, if you don't want change them.
         * Set strings to "" if you don't want change them.
         */
        int r = (rows != -1) ? rows : this.lines.size();
        int c = (cols != -1) ? cols : this.lines.getFirst().length();
        String right = right_sym.isEmpty() ? this.right_sym : right_sym;
        String left = left_sym.isEmpty() ? this.left_sym : left_sym;
        String up = up_sym.isEmpty() ? this.up_sym : up_sym;
        String bottom = bottom_sym.isEmpty() ? this.bottom_sym : bottom_sym;
        String cont = content.isEmpty() ? this.content : content;
        return new Cell(r, c, right, left, up, bottom, cont);
    }

    public Cell getCopy() {
        return new Cell(
                this.lines.size(),
                this.lines.getFirst().length(),
                this.right_sym,
                this.left_sym,
                this.up_sym,
                this.bottom_sym,
                this.content);
    }

    public boolean canPlaceContent(String content) {
        int rows = this.lines.size();
        int cols = this.lines.getFirst().length();
        if ((rows < 3)
            || ((cols - content.length() - 2) < 0)) {
            System.out.println("To place content, cell should have at least 3 rows!");
            System.out.printf("To place this content, cell should have at least %d columns!\n",
                    content.length() + 2);
            return false;
        }
        else return true;
    }

    public boolean canPlaceContent(
            String content,
            int rows,
            int cols) {
        if ((rows < 3)
                || ((cols - content.length() - 2) < 0)) {
            System.out.println("To place content, cell should have at least 3 rows!");
            System.out.printf("To place this content, cell should have at least %d columns!\n",
                    content.length() + 2);
            return false;
        }
        else return true;
    }

    public void show() {
        if (this.as_string.isEmpty()) {
            System.out.println("Save as string first!");
        }
        else {
            System.out.print(this.as_string);
        }
    }

    public void saveStringVariant() {
        this.as_string = this.toString();
    }

    public String getSavedStringVariant() {
        return this.as_string;
    }

    public String getContent() {return this.content;}

    public String getRight_sym() {return this.right_sym;}

    public String getLeft_sym() {return this.left_sym;}

    public String getUp_sym() {return this.up_sym;}

    public String getBottom_sym() {return this.bottom_sym;}

    public int getRows() {return this.lines.size();}

    public int getCols() {return this.lines.getFirst().length();}

    public ArrayList<String> getLines() {return this.lines;}

    private void makeCell(
            int rows,
            int cols
    ) {
        if (
                !this.canPlaceContent(this.content, rows, cols)
        ) {
            System.out.println("Cannot place this content!");
            System.out.println("Setting content to an empty string!");
            this.content = "";
        }
        int cont_len = this.content.length();
        int start_col = Math.abs(
                (cols - cont_len) / 2
        );
        int cont_rows = cont_len - this.content.replace("\n", "").length();
        int start_row = Math.abs(
                (rows - cont_rows) / 2
        );
        int k = 0;
        for (int i = 0; i < rows; i++) {
            String[] lines = new String[cols];
            for (int j = 0; j < cols; j++) {
                if ((i > 0)
                        && (i < rows - 1)
                        && (j == 0)) {
                    lines[j] = this.left_sym;
                } else if ((i > 0)
                        && (i < rows - 1)
                        && (j == cols - 1)) {
                    lines[j] = this.right_sym;
                } else if ((i == 0)
                        && (j > 0)
                        && (j < cols - 1)) {
                    lines[j] = this.up_sym;
                } else if ((i == rows - 1)
                        && (j > 0)
                        && (j < cols - 1)) {
                    lines[j] = this.bottom_sym;
                } else if ((i >= start_row)
                        && (j >= start_col)
                        && (k < cont_len)) {
                    lines[j] = this.content.substring(k, k + 1);
                    k++;
                }
                else {
                    lines[j] = " ";
                }
            }
            String res = String.join("", lines);
            this.lines.add(res);
        }
    }

    public String toString() {
        int len = this.lines.size();
        String[] lines = new String[len];
        for (int i = 0; i < len; i++) {
            lines[i] = this.lines.get(i) + '\n';
        }
        String string =  String.join("", lines);
        this.as_string = string;
        return string;
    }
}