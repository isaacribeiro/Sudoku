import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckSudoku {

    @Test
    public void validSingleRowOrColumn() {

//      Input structure
        int[] actRecord = {5, 3, 4, 6, 7, 8, 9, 1, 2};

        assertTrue(Sudoku.checkSingleRecord(actRecord));
    }

    @Test
    public void invalidSingleRowOrColumn() {

//      Input structure
        int[] actRecord = {5, 3, 4, 5, 7, 8, 9, 1, 2};

        assertFalse(Sudoku.checkSingleRecord(actRecord));

    }

    @Test
    public void validSingleBlock() {

//      Input Block
        int[][] block = {{5, 3, 4},
                {6, 7, 2},
                {1, 9, 8}};

        assertTrue(Sudoku.checkSingleBlock(block));

    }

    @Test
    public void invalidSingleBlock() {

//      Input Block
        int[][] block = {{5, 3, 4},
                {6, 5, 2},
                {1, 9, 8}};

        assertFalse(Sudoku.checkSingleBlock(block));

    }

    @Test
    public void checkCalculatedSudoku() {

//      Input Object
        int[][] actualSudoku = {{5, 3, 0, 0, 7, 0, 0, 0, 0},
                                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                                {0, 0, 0, 0, 8, 0, 0, 7, 9}};

        Sudoku actSudoku = new Sudoku(actualSudoku);

//      Expected Object
        int[][] expectedSudoku = {  {5, 3, 4, 6, 7, 8, 9, 1, 2},
                                    {6, 7, 2, 1, 9, 5, 3, 4, 8},
                                    {1, 9, 8, 3, 4, 2, 5, 6, 7},
                                    {8, 5, 9, 7, 6, 1, 4, 2, 3},
                                    {4, 2, 6, 8, 5, 3, 7, 9, 1},
                                    {7, 1, 3, 9, 2, 4, 8, 5, 6},
                                    {9, 6, 1, 5, 3, 7, 2, 8, 4},
                                    {2, 8, 7, 4, 1, 9, 6, 3, 5},
                                    {3, 4, 5, 2, 8, 6, 1, 7, 9} };

        Sudoku expSudoku = new Sudoku(expectedSudoku);

        assertThat(actSudoku.calculateSudoku(), is(expSudoku));

    }

    @Test
    public void extractBlocks() {

//      Input Structure
        int[][] input = {{5, 3, 4, 6, 7, 8, 9, 1, 2},
                         {6, 7, 2, 1, 9, 5, 3, 4, 8},
                         {1, 9, 8, 3, 4, 2, 5, 6, 7},
                         {8, 5, 9, 7, 6, 1, 4, 2, 3},
                         {4, 2, 6, 8, 5, 3, 7, 9, 1},
                         {7, 1, 3, 9, 2, 4, 8, 5, 6},
                         {9, 6, 1, 5, 3, 7, 2, 8, 4},
                         {2, 8, 7, 4, 1, 9, 6, 3, 5},
                         {3, 4, 5, 2, 8, 6, 1, 7, 9}};

        Sudoku sudoku = new Sudoku(input);

        //      #1 Block
        int[][] expBlock_0 = {{5, 3, 4},
                {6, 7, 2},
                {1, 9, 8}};

        //      #2 Block
        int[][] expBlock_1 = {{6, 7, 8},
                {1, 9, 5},
                {3, 4, 2}};

        //      #3 Block
        int[][] expBlock_2 = {{9, 1, 2},
                {3, 4, 8},
                {5, 6, 7}};

        //      #4 Block
        int[][] expBlock_3 = {{8, 5, 9},
                {4, 2, 6},
                {7, 1, 3}};

        //      #5 Block
        int[][] expBlock_4 = {{7, 6, 1},
                {8, 5, 3},
                {9, 2, 4}};

        //      #6 Block
        int[][] expBlock_5 = {{4, 2, 3},
                {7, 9, 1},
                {8, 5, 6}};

        //      #7 Block
        int[][] expBlock_6 = {{9, 6, 1},
                {2, 8, 7},
                {3, 4, 5}};

        //      #8 Block
        int[][] expBlock_7 = {{5, 3, 7},
                {4, 1, 9},
                {2, 8, 6}};

        //      #9 Block
        int[][] expBlock_8 = {{2, 8, 4},
                {6, 3, 5},
                {1, 7, 9}};

        //      Sudoky2Block
        assertThat(sudoku.getBlock(0), is(expBlock_0));
        assertThat(sudoku.getBlock(1), is(expBlock_1));
        assertThat(sudoku.getBlock(2), is(expBlock_2));
        assertThat(sudoku.getBlock(3), is(expBlock_3));
        assertThat(sudoku.getBlock(4), is(expBlock_4));
        assertThat(sudoku.getBlock(5), is(expBlock_5));
        assertThat(sudoku.getBlock(6), is(expBlock_6));
        assertThat(sudoku.getBlock(7), is(expBlock_7));
        assertThat(sudoku.getBlock(8), is(expBlock_8));

    }
}