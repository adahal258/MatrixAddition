// Name: Aashutosh Dahal
// Class: 2251: A01
// Assignment: Matrix Addition - Part3.
// Purpose: To modify ThreadOperation Class that takes matrix's and quadrant in its constructor. Instantiate it in main and
// run the thread. Each thread are for matrix addition for upper left, upper right , lower left and lower right respectively.
/*
While awaiting the completion of input/output, blocking on I/O activities enables the CPU to move to other threads,
maximizing CPU utilization. On multicore processors, several threads can operate concurrently on several cores,
enabling parallel processing and effective use of resources. by utilizing the capabilities of contemporary hardware,
multi-threading improves responsiveness and scalability while decreasing general sluggishness brought on by user interaction or other blocking tasks.
 */
// Filename: "Main.java"
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Path path = Paths.get(args[0]); //taking input from terminal
        Scanner fileIn = null;
        // Initialize scanner and check for invalid path
        try {
            fileIn = new Scanner(path);
        } catch (IOException e) {
            System.out.println("error IO exception: " + e.getMessage());
            System.exit(1);
        }

        String rowColumn = fileIn.nextLine();
        int row = Integer.parseInt(rowColumn.substring(0,1));
        int column = Integer.parseInt(rowColumn.substring(2)); //since index 1 contains space.

        int[][] matrix1FromFile;
        int[][] matrix2FromFile;

        matrix1FromFile = matrixFromFile(row,column,fileIn);
        matrix2FromFile = matrixFromFile(row,column,fileIn);
        int[][] resultMatrix3 = new int[row][column];


        ThreadOperation threadOne = new ThreadOperation(matrix1FromFile, matrix2FromFile, resultMatrix3,"UpperLeft");
        ThreadOperation threadTwo = new ThreadOperation(matrix1FromFile, matrix2FromFile, resultMatrix3,"UpperRight");
        ThreadOperation threadThree = new ThreadOperation(matrix1FromFile, matrix2FromFile, resultMatrix3,"LowerLeft");
        ThreadOperation threadFour = new ThreadOperation(matrix1FromFile, matrix2FromFile, resultMatrix3,"LowerRight");

        threadOne.start();
        threadTwo.start();
        threadThree.start();
        threadFour.start();

        try {
            threadOne.join();
            threadTwo.join();
            threadThree.join();
            threadFour.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The matrix A is: ");
        print2dArray(matrix1FromFile);
        System.out.println();
        System.out.println("The matrix B is: ");
        print2dArray(matrix2FromFile);
        System.out.println();
        System.out.println("The resulting matrix is: ");
        print2dArray(resultMatrix3);
    }
    public static int[][] matrixFromFile(int rows, int columns, Scanner file_reader) {
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            String[] matrixData = file_reader.nextLine().split(" ");
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = Integer.parseInt(matrixData[j]);
            }
        }
        return matrix;
    }

    public static void print2dArray(int[][] matrix){
        for (int[] rows : matrix) {
            for (int data : rows) {
                System.out.printf("%-4d",data);
            }
            System.out.println();  //separating row and column.
        }
    }
}