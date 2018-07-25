import java.util.*;

public class Sudoku {

    private int[][] elements = new int[9][9];

    public Sudoku( int[][] elements){
        this.elements = elements;
    }

//  Check if a single record fits the base Sudoku condition of having no zeros, repeated values or numbers out of 1..9 interval
    public static boolean checkSingleRecord(int[] record){
        return hasNonRepeatedValues(record);
    }

//  Check if a single block fits the base Sudoku condition of having no zeros, repeated values or number out of 1..9 interval
    public static boolean checkSingleBlock(int[][] block){
        return hasNonRepeatedValues(serializesMultimensionalArray(block));
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

//  Check if there is no repeated values for a specific set
    private static boolean hasNonRepeatedValues(int[] entries){

//      Boxed list for Wrapper's Auxiliary Methods Support
        Integer[] boxedList = Arrays.stream( entries )
                .boxed()
                .toArray(Integer[]::new);

        List<Integer> aList = Arrays.asList(boxedList);

//      A set doesn't allow non-unique values
        Set<Integer> adjustedSet = new HashSet<Integer>();

        aList.stream()
                .filter(x -> x >= 1 && x <= 9)
                .filter(x -> !adjustedSet.contains(x))
                .forEach(adjustedSet::add);

        return adjustedSet.size() == 9 ? true : false;

    }

//  Serializes an multidimensional Array
    private static int[] serializesMultimensionalArray(int[][] input){

//      Generic Model for Multidimensional Array Serialization Process
//      int[] to Integer[] -> Boxing Process
        Integer[] boxedList;

//      Creates an ArrayList<Integer> to support multidimensional appending process
        List<Integer> aList = new ArrayList<Integer>();

//      Gets the content of each line and append to the Integer Boxed List
        for(int aRow = 0; aRow < input.length; aRow++) {

//          Cast the int[] row for a Boxed format
            boxedList = Arrays.stream(input[aRow])
                    .boxed()
                    .toArray(Integer[]::new);
            aList.addAll(Arrays.asList(boxedList));

        }

//      Auxiliary swapping process.
//      Before casting into an int[], creates an Integer[]
        Integer[] aReturn = aList.toArray(new Integer[aList.size()]);

//      Cast to primitive int[] type
        return Arrays.stream(aReturn)
                .mapToInt(Integer::intValue)
                .toArray();
    }

}
