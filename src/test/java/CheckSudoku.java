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

//      Expected Object
        Sudoky actBlock = new Sudoku( [ 5, 3, 4],
                                      [ 6, 7, 2],
                                      [ 1, 9, 8]);

        assertTrue(Sudoku.checkSingleBlock(actBlock));

    }

    @Test
    public void invalidSingleBlock(){

//      Expected Object
        Sudoky expSudoku = new Sudoku( [ 5, 3, 4],
                                       [ 6, 5, 2],
                                       [ 1, 9, 8]);

        assertFalse(Sudoku.checkSingleBlock(actBlock));

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


//       Expected Object
        Sudoky expSudoku = new Sudoku( [ 5, 3, 4, 6, 7, 8, 9, 1, 2],
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
}