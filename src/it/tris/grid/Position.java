package it.tris.grid;

public final class Position {
    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setValues(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean equals(Object o){
        return ((Position)o).getRow() == row && ((Position)o).getColumn() == column;
    }


    @Override
    public String toString() {
        return row + ", " + column;
    }
}