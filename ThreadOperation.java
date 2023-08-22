// Name: Aashutosh Dahal
// Class: 2251: A01
// Assignment: Matrix Addition - Part2.
// Purpose: To create a ThreadOperation Class that takes int[][] matrix's and String quadrant.
// Filename: "ThreadOperation.java"
public class ThreadOperation extends Thread{
    private final int[][] A;
    private final int[][] B;
    private final int[][] C;
    private final String quadrant;

    public ThreadOperation(int[][] matrix1, int[][]matrix2, int[][]result, String quadrant){
        this.A= matrix1;
        this.B=matrix2;
        this.C=result;
        this.quadrant=quadrant;
    }
    public static int[] getQuadrantIndexes(int row, int column, String quadrant){
        int[] indexes = new int[4]; //since it store : row start, row end, column start, column end
        switch (quadrant) {
            case "UpperLeft" -> {
                indexes[0] = 0; //since the upperLeft starts with 0; row start
                indexes[1] = (row / 2) - 1; //row end
                indexes[2] = 0; //column start
                indexes[3] = (column / 2) - 1;
            }
            case "UpperRight" -> {
                indexes[0] = 0; //since the upperLeft starts with 0; row start
                indexes[1] = (row / 2) - 1; //row end
                indexes[2] = column / 2; //column start
                indexes[3] = column - 1;
            }
            case "LowerLeft" -> {
                indexes[0] = row / 2;
                indexes[1] = row - 1;
                indexes[2] = 0;
                indexes[3] = (column / 2) - 1;
            }
            default -> {
                indexes[0] = row / 2;
                indexes[1] = row - 1;
                indexes[2] = column / 2;
                indexes[3] = column - 1;
            }
        }
        return indexes;
    }

    @Override
    public void run(){
        int rows = A.length;
        int cols = A[0].length;
        int[] indexes = getQuadrantIndexes(rows,cols,quadrant);
        for(int i = indexes[0]; i<=indexes[1];i++){
            for(int j = indexes[2]; j<=indexes[3]; j++){
                C[i][j] = A[i][j] + B[i][j]; //matrix addition.
            }
        }
    }
}
