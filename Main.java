

public class Main {

    public static void main(String[] args) {
        String content = "turtle";
        String left = "|", right = "|", top = "-", bottom = "-";
        int rows = 5, cols = content.length() + 2;
        int trows = 3, tcols = 3;
        Table table = new Table(rows, cols, trows, tcols);
        for (int i = 0; i < trows; i++) {
            for (int j = 0; j < tcols; j++) {
                Cell cell = new Cell(
                        rows,
                        cols,
                        right,
                        left,
                        top,
                        bottom,
                        content
                );
                table.insertCell(cell, i, j, true);
            }
        }
        String cont = "coffee";
        Cell unique_cell = new Cell(
                rows,
                cols,
                right,
                left,
                top,
                bottom,
                cont
        );
        table.insertCell(unique_cell, 1, 1, false);
        System.out.print(table);
        System.out.println("Very well!");
    }
}