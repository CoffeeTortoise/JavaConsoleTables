import java.util.ArrayList;


public class Table {

    private Cell[][] cells;

    private int cell_rows, cell_cols;

    private int table_rows, table_cols;

    private String as_string;

    public Table() {
        this.setDefaultParams();
    }

    public Table(
            int cell_rows,
            int cell_cols,
            int table_rows,
            int table_cols
    ) {
        if ((cell_rows < 1)
            || (cell_cols < 1)
            || (table_rows < 1)
            || (table_cols < 1)) {
            System.out.println("The table must be able to contain at least one cell 1x1!");
            this.setDefaultParams();
        }
        else {
            this.cell_rows = cell_rows;
            this.cell_cols = cell_cols;
            this.table_rows = table_rows;
            this.table_cols = table_cols;
            this.as_string = "";
            this.cells = new Cell[table_rows][table_cols];
        }
    }

    public int getCellRows() {return this.cell_rows;}

    public int getCellCols() {return this.cell_cols;}

    public int getTableRows() {return this.table_rows;}

    public int getTableCols() {return this.table_cols;}

    public String getSavedStringVariant() {return this.as_string;}

    public String saveStringVariant() {return this.toString();}

    public Cell getCell(
            int row,
            int col,
            boolean copy
    ) {
        if ((row < 0)
                || (row >= this.table_rows)) {
            System.out.println("Incorrect index of row!");
            System.out.printf("Table has %d rows!\n", this.table_rows);
            System.out.println("Return default cell!");
            return new Cell();
        } else if ((col < 0)
                    || (col >= this.table_cols)) {
            System.out.println("Incorrect index of column!");
            System.out.printf("Table has %d columns!\n", this.table_cols);
            System.out.println("Return default cell!");
            return new Cell();
        } else if (this.cells[row][col] == null) {
            System.out.printf("Cell at %d row and %d column is null!\n", row, col);
            System.out.println("Return default cell!");
            return new Cell();
        }
        else return copy ? this.cells[row][col].getCopy() : this.cells[row][col];
    }

    public void insertCell(
            Cell cell,
            int row,
            int col,
            boolean insert_if_null
    ) {
        if (((row < 0) || (row > this.table_rows))
            || ((col < 0) || (col > this.table_cols))) {
            System.out.printf("Cannot insert cell at %d row and %d column!\n", row, col);
            System.out.printf("Table has %d rows and %d columns!\n", this.table_rows, this.table_cols);
        }
        else {
            int c_rows = cell.getRows();
            int c_cols = cell.getCols();
            if ((c_rows != this.cell_rows)
                || (c_cols != this.cell_cols)) {
                System.out.printf("Cannot insert table %d rows and %d columns!\n", c_rows, c_cols);
                System.out.printf("This is a table for cells with %d rows and %d cols!\n",
                        this.cell_rows, this.cell_cols);
            }
            else {
                if (insert_if_null) {
                    if (this.cells[row][col] == null) {
                        this.cells[row][col] = cell.getCopy();
                    }
                    else {
                        System.out.printf("Cell at %d row and %d column is not null!\n", row, col);
                    }
                }
                else {
                    this.cells[row][col] = cell.getCopy();
                }
            }
        }
    }

    public void show() {
        if (this.as_string.isEmpty()) {
            System.out.println("Save as string first!");
        }
        else {
            System.out.print(this.as_string);
        }
    }

    public String toString() {
        boolean found_null = false;
        int mat_rows = this.cell_rows * this.table_rows;
        int mat_cols = this.cell_cols * this.table_cols;
        String[][] table = new String[mat_rows][mat_cols];
        int k = 0, v = 0;
        for (int i = 0; i < this.cells.length; i++) {
            for (int j = 0; j < this.cells[i].length; j++) {
                if (this.cells[i][j] == null) {
                    System.out.println("Stop converting!");
                    System.out.printf("Found null at %d row and %d column!\n", i, j);
                    found_null = true;
                    break;
                }
                ArrayList<String> clines = this.cells[i][j].getLines();
                for (String cline : clines) {
                    table[k][v] = cline.replace("\n", "");
                    k++;
                }
                if (k >= mat_rows) {
                    k = 0;
                    v++;
                }
            }
            if (found_null) {
                break;
            }
        }
        String[] res = new String[mat_rows];
        for (int i = 0; i < mat_rows; i++) {
            String[] sline = new String[mat_cols];
            for (int j = 0; j < mat_cols; j++) {
                if (table[i][j] != null) {
                    sline[j] = table[i][j];
                }
                else sline[j] = "";
            }
            String rline = String.join("", sline);
            if (i == mat_rows - 1) {
                res[i] = rline;
            }
            else {
                res[i] = rline + "\n";
            }
        }
        String string = String.join("", res);
        this.as_string = string;
        return string;
    }

    private void setDefaultParams() {
        System.out.println("Default parameters!");
        System.out.println("Table 1x1, that can contain cell 1x1!");
        this.cells = new Cell[1][1];
        this.cell_rows = 1;
        this.cell_cols = 1;
        this.table_rows = 1;
        this.table_cols = 1;
        this.as_string = "";
    }
}