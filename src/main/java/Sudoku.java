public class Sudoku {

    private int[][] elements = new int[9][9];

    public Sudoku( int[][] elements){
        this.elements = elements;
    }

//  Check if a single record fits the base Sudoku condition of having no zeros, repeated values or numbers out of 1..9 interval
    public static boolean checkSingleRecord(int[] record){
        return false;
    }

//  Check if a single block fits the base Sudoku condition of having no zeros, repeated values or number out of 1..9 interval
    public static boolean checkSingleBlock(int[][] block){
        return false;
    }

//  Receives a incomplete Sudoku and tries to solve it
    public Sudoku calculateSudoku(){
        return null;
    }

//  Receives a Sudoku and extracts its values to a specific requested block, in accordance with the following rule:
//  0 | 1 | 2
//  3 | 4 | 5
//  6 | 7 | 8
    public int[][] getBlock(int block){

        int[][] result = new int[3][3];

        for(int i = 0; i < result.length; i++){
            for(int j = 0; j < result[i].length; j++){
                result[i][j] = elements[((block / 3) * 3) + i][((block % 3) * 3) + j];
            }
        }

        return result;

    }

}
