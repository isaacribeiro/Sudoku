import org.junit.Test;
import org.junit.Assert;

public class CheckSudoku {

    @Test
    public void validSingleRowOrColumn(){

//      Input structure
        int[] actRecord = new int[5, 3, 4, 6, 7, 8, 9, 1, 2];

        assertTrue(Sudoku.checkSingleRecord(actRecord));
    }

    @Test
    public void invalidSingleRowOrColumn(){

//      Input structure
        int[] actRecord = new int[5, 3, 4, 5, 7, 8, 9, 1, 2];

        assertFalse(Sudoku.checkSingleRecord(actRecord));

    }

    @Test
    public void validSingleBlock(){

//      Input Block
        Block actBlock = new Block( [ 5, 3, 4],
                                    [ 6, 7, 2],
                                    [ 1, 9, 8]);

        assertTrue(Block.checkSingleBlock(actBlock));

    }

    @Test
    public void invalidSingleBlock(){

//      Input Block
        Block actBlock = new Block( [ 5, 3, 4],
                                    [ 6, 5, 2],
                                    [ 1, 9, 8]);

        assertFalse(Block.checkSingleBlock(actBlock));

    }

    @Test
    public void checkCalculatedSudoku(){

//      Input Object
        Sudoku actSudoku = new Sudoku( [ 5, 3,  ,  , 7,  ,  ,  ,  ],
                                       [ 6,  ,  , 1, 9, 5,  ,  ,  ],
                                       [  , 9, 8,  ,  ,  ,  , 6,  ],
                                       [ 8,  ,  ,  , 6,  ,  ,  , 3],
                                       [ 4,  ,  , 8,  , 3,  ,  , 1],
                                       [ 7,  ,  ,  , 2,  ,  ,  , 6],
                                       [  , 6,  ,  ,  ,  , 2, 8,  ],
                                       [  ,  ,  , 4, 1, 9,  ,  , 5],
                                       [  ,  ,  ,  , 8,  ,  , 7, 9] );


//      Expected Object
        Sudoku expSudoku = new Sudoku( [ 5, 3, 4, 6, 7, 8, 9, 1, 2],
                                       [ 6, 7, 2, 1, 9, 5, 3, 4, 8],
                                       [ 1, 9, 8, 3, 4, 2, 5, 6, 7],
                                       [ 8, 5, 9, 7, 6, 1, 4, 2, 3],
                                       [ 4, 2, 6, 8, 5, 3, 7, 9, 1],
                                       [ 7, 1, 3, 9, 2, 4, 8, 5, 6],
                                       [ 9, 6, 1, 5, 3, 7, 2, 8, 4],
                                       [ 2, 8, 7, 4, 1, 9, 6, 3, 5],
                                       [ 3, 4, 5, 2, 8, 6, 1, 7, 9] );

        assertEquals(expSudoku, calculateSudoku(actSudoku));

    }

    @Test
    public void extractBlocks(){

//      Input Structure
        Sudoku inputSudoku = new Sudoku( [ 5, 3, 4, 6, 7, 8, 9, 1, 2],
                                         [ 6, 7, 2, 1, 9, 5, 3, 4, 8],
                                         [ 1, 9, 8, 3, 4, 2, 5, 6, 7],
                                         [ 8, 5, 9, 7, 6, 1, 4, 2, 3],
                                         [ 4, 2, 6, 8, 5, 3, 7, 9, 1],
                                         [ 7, 1, 3, 9, 2, 4, 8, 5, 6],
                                         [ 9, 6, 1, 5, 3, 7, 2, 8, 4],
                                         [ 2, 8, 7, 4, 1, 9, 6, 3, 5],
                                         [ 3, 4, 5, 2, 8, 6, 1, 7, 9] );

//      #1 Block
        Block expBlock_1 = new Block( [ 5, 3, 4],
                                      [ 6, 5, 2],
                                      [ 1, 9, 8]);

//      #2 Block
        Block expBlock_2 = new Block( [ 6, 7, 8],
                                      [ 1, 9, 5],
                                      [ 3, 4, 2]);

//      #3 Block
        Block expBlock_3 = new Block( [ 9, 1, 2],
                                      [ 3, 4, 8],
                                      [ 5, 6, 7]);

//      #4 Block
        Block expBlock_4 = new Block( [ 8, 5, 9],
                                      [ 4, 2, 6],
                                      [ 7, 1, 3]);

//      #5 Block
        Block expBlock_5 = new Block( [ 7, 6, 1],
                                      [ 8, 5, 3],
                                      [ 9, 2, 4]);

//      #6 Block
        Block expBlock_6 = new Block( [ 4, 2, 3],
                                      [ 7, 9, 1],
                                      [ 8, 5, 6]);

//      #7 Block
        Block expBlock_7 = new Block( [ 9, 6, 1],
                                      [ 2, 8, 7],
                                      [ 3, 4, 5]);
//      #8 Block
        Block expBlock_8 = new Block( [ 5, 3, 7],
                                      [ 4, 1, 9],
                                      [ 2, 8, 6]);

//      #9 Block
        Block expBlock_9 = new Block( [ 2, 8, 4],
                                      [ 6, 3, 5],
                                      [ 1, 7, 9]);

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

    }
}