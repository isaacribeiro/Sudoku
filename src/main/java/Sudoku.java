import java.util.*;
import java.util.Map.Entry;

public class Sudoku {

    private int[][] elements = new int[9][9];

    public Sudoku( int[][] elements){
        this.elements = elements;
    }

//  Receives a incomplete Sudoku and tries to solve it
    public Sudoku calculateSudoku(){

        int[] nextPositionToPlay = findBestPositionToPlay();
        return null;
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

//  Find the best position to fill a value
//  By "Best Position", I mean the one whose surrounding values brings a higher expectation of
//  success. Then, it returns an array with the x, y coordinates to play
    private int[] findBestPositionToPlay(){

//      Looks for missing slots. The ones equal to '0'
        List<Integer[]> openPositions = getMissingSlots();

//      HashMap for matching best next steps in game
        Map<Integer, Integer[]> gameMap = new HashMap<Integer, Integer[]>();

//      Based on th openPositions list, for each position, looks for the best possibility to play
//      In other words, searches for the slot surrounded by non-initial values
//      1) Rows
//      2) Columns
//      3) Group
        for(int i = 0; i < openPositions.size(); i++){
            Integer[] openPosition = openPositions.get(i);
            gameMap.put(i, getPossibleValues(openPosition));
        }

//      Sort gameMap by the size of Integer[] ASCENDINGLY
        Map<Integer, Integer[]> sortedGameMap = sortByValueSize((HashMap<Integer, Integer[]>) gameMap);

        return null;
    }

//  Creates a List with the coordinates for the slots with null values
    private List<Integer[]> getMissingSlots(){

        List<Integer[]> missingSlots = new ArrayList<Integer[]>();

        for(int i = 0; i < elements.length; i++){
            for(int j = 0; j < elements[i].length; j++){
                if(elements[i][j] == 0){
                    Integer[] coordinates = new Integer[2];
                    coordinates[0] = i;
                    coordinates[1] = j;
                    missingSlots.add(coordinates);
                }
            }
        }

        return missingSlots;
    }

    private Integer[] getPossibleValues(Integer[] coordinates){

        Set<Integer> possibleValues = new HashSet<Integer>();
        Integer[] initialSet = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        possibleValues.addAll(Arrays.asList(initialSet));

        int[] row = getRow(coordinates[0]);
        int[] column = getColumn(coordinates[1]);
        int[] block = serializesMultimensionalArray(getBlock(getBlockId(coordinates)));

        Arrays.stream(row)
                .forEach(possibleValues::remove);

        Arrays.stream(column)
                .forEach(possibleValues::remove);

        Arrays.stream(block)
                .forEach(possibleValues::remove);

        return possibleValues.toArray(new Integer[possibleValues.size()]);
    }

    private int getBlockId(Integer[] coordinates){

        int y = coordinates[0];
        int x = coordinates[1];

        int gColumn = x / 3;
        int gRow = (y / 3) * 3;

        return gRow + gColumn;
    }

    //  Returns the specified column from elements multidimensional vector
    private int[] getColumn(int index){

        int[] column = new int[9];

        for(int aRow = 0; aRow < elements.length; aRow++){
            column[aRow] = elements[aRow][index];
        }

        return column;
    }

    //  Returns the specified column from elements multidimensional vector
    private int[] getRow(int index){

        int[] row = elements[index];
        return row;
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

    //  Check if a single record fits the base Sudoku condition of having no zeros, repeated values or numbers out of 1..9 interval
    public static boolean checkSingleRecord(int[] record){
        return hasNonRepeatedValues(record);
    }

    //  Check if a single block fits the base Sudoku condition of having no zeros, repeated values or number out of 1..9 interval
    public static boolean checkSingleBlock(int[][] block){
        return hasNonRepeatedValues(serializesMultimensionalArray(block));
    }

    //Sorts HashMap by Value
    private static Map<Integer, Integer[]> sortByValueSize(HashMap<Integer, Integer[]> map) {

    }
}