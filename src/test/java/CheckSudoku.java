import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckSudoku {

    @Test
    public void validSingleRowOrColumn(){

//      Input structure
        int[] actRecord = {5, 3, 4, 6, 7, 8, 9, 1, 2};

        assertTrue(Sudoku.checkSingleRecord(actRecord));
    }

    @Test
    public void invalidSingleRowOrColumn(){

//      Input structure
        int[] actRecord = {5, 3, 4, 5, 7, 8, 9, 1, 2};

        assertFalse(Sudoku.checkSingleRecord(actRecord));

    }

    @Test
    public void validSingleBlock(){

//      Input Block
        int[][] block = {{5, 3, 4},
                         {6, 7, 2},
                         {1, 9, 8}};

        Block actBlock = new Block( block );

        assertTrue(Block.checkSingleBlock(actBlock));

    }

    @Test
    public void invalidSingleBlock(){

//      Input Block
        int[][] block = {{5, 3, 4},
                         {6, 5, 2},
                         {1, 9, 8}};

        Block actBlock = new Block( block );

        assertFalse(Block.checkSingleBlock(actBlock));

    }

    @Test
    public void checkCalculatedSudoku(){

//      Input Object
        int[][] actualSudoku = {  { 5, 3, 0, 0, 7, 0, 0, 0, 0},
                                  { 6, 0, 0, 1, 9, 5, 0, 0, 0},
                                  { 0, 9, 8, 0, 0, 0, 0, 6, 0},
                                  { 8, 0, 0, 0, 6, 0, 0, 0, 3},
                                  { 4, 0, 0, 8, 0, 3, 0, 0, 1},
                                  { 7, 0, 0, 0, 2, 0, 0, 0, 6},
                                  { 0, 6, 0, 0, 0, 0, 2, 8, 0},
                                  { 0, 0, 0, 4, 1, 9, 0, 0, 5},
                                  { 0, 0, 0, 0, 8, 0, 0, 7, 9}  };

        Sudoku actSudoku = new Sudoku( actualSudoku );

//      Expected Object
        int[][] expectedSudoku = {  { 5, 3, 4, 6, 7, 8, 9, 1, 2},
                                    { 6, 7, 2, 1, 9, 5, 3, 4, 8},
                                    { 1, 9, 8, 3, 4, 2, 5, 6, 7},
                                    { 8, 5, 9, 7, 6, 1, 4, 2, 3},
                                    { 4, 2, 6, 8, 5, 3, 7, 9, 1},
                                    { 7, 1, 3, 9, 2, 4, 8, 5, 6},
                                    { 9, 6, 1, 5, 3, 7, 2, 8, 4},
                                    { 2, 8, 7, 4, 1, 9, 6, 3, 5},
                                    { 3, 4, 5, 2, 8, 6, 1, 7, 9}  };

        Sudoku expSudoku = new Sudoku( expectedSudoku );

        assertEquals(expSudoku, Sudoku.calculateSudoku(actSudoku));

    }


   /* TODO: create test for Block Extraction
    @Test
    public void extractBlocks(){

//      Input Structure
        Sudoku inputSudoku = new Sudoku( { 5, 3, 4, 6, 7, 8, 9, 1, 2},
                                         { 6, 7, 2, 1, 9, 5, 3, 4, 8},
                                         { 1, 9, 8, 3, 4, 2, 5, 6, 7},
                                         { 8, 5, 9, 7, 6, 1, 4, 2, 3},
                                         { 4, 2, 6, 8, 5, 3, 7, 9, 1},
                                         { 7, 1, 3, 9, 2, 4, 8, 5, 6},
                                         { 9, 6, 1, 5, 3, 7, 2, 8, 4},
                                         { 2, 8, 7, 4, 1, 9, 6, 3, 5},
                                         { 3, 4, 5, 2, 8, 6, 1, 7, 9} );

//      #1 Block
        Block expBlock_1 = new Block( { 5, 3, 4},
                                      { 6, 5, 2},
                                      { 1, 9, 8});

//      #2 Block
        Block expBlock_2 = new Block( { 6, 7, 8},
                                      { 1, 9, 5},
                                      { 3, 4, 2});

//      #3 Block
        Block expBlock_3 = new Block( { 9, 1, 2},
                                      { 3, 4, 8},
                                      { 5, 6, 7});

//      #4 Block
        Block expBlock_4 = new Block( { 8, 5, 9},
                                      { 4, 2, 6},
                                      { 7, 1, 3});

//      #5 Block
        Block expBlock_5 = new Block( { 7, 6, 1},
                                      { 8, 5, 3},
                                      { 9, 2, 4});

//      #6 Block
        Block expBlock_6 = new Block( { 4, 2, 3},
                                      { 7, 9, 1},
                                      { 8, 5, 6});

//      #7 Block
        Block expBlock_7 = new Block( { 9, 6, 1},
                                      { 2, 8, 7},
                                      { 3, 4, 5});

//      #8 Block
        Block expBlock_8 = new Block( { 5, 3, 7},
                                      { 4, 1, 9},
                                      { 2, 8, 6});

//      #9 Block
        Block expBlock_9 = new Block( { 2, 8, 4},
                                      { 6, 3, 5},
                                      { 1, 7, 9});

//      Sudoky2Block
        assertEquals(expBlock_1, Sudoku.getBlock(1));
        assertEquals(expBlock_2, Sudoku.getBlock(2));
        assertEquals(expBlock_3, Sudoku.getBlock(3));
        assertEquals(expBlock_4, Sudoku.getBlock(4));
        assertEquals(expBlock_5, Sudoku.getBlock(5));
        assertEquals(expBlock_6, Sudoku.getBlock(6));
        assertEquals(expBlock_7, Sudoku.getBlock(7));
        assertEquals(expBlock_8, Sudoku.getBlock(8));
        assertEquals(expBlock_9, Sudoku.getBlock(9));

    }*/
}