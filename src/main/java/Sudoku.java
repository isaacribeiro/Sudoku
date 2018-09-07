import java.util.*;

public class Sudoku{

    private Integer[][] elements = new Integer[9][9];

    public Sudoku( Integer[][] elements){
        this.elements = elements;
    }

//  Receives a incomplete Sudoku and tries to solve it
    public Sudoku calculateSudoku(){

//      Optimization solves easy puzzles
//      The ones which is possible to derive a best match
        Sudoku optimizedSudoku = optimizeResolution();

        Integer numberOfMissingSlots = getMissingSlots().size();

//      It means we were trying to solve an easy puzzle, so it's just to return its optimized version
        if (numberOfMissingSlots == 0)
            return optimizeResolution();

//      Otherwise, we are still looking for the latest solution
        return null;
    }

//  Check if there is no repeated values for a specific set
    private static boolean hasNonRepeatedValues(Integer[] entries){

//      Boxed list for Wrapper's Auxiliary Methods Support
        Integer[] boxedList = Arrays.stream( entries )
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
    private static Integer[] serializesMultimensionalArray(Integer[][] input){

//      Generic Model for Multidimensional Array Serialization Process
//      int[] to Integer[] -> Boxing Process
        Integer[] boxedList;

//      Creates an ArrayList<Integer> to support multidimensional appending process
        List<Integer> aList = new ArrayList<Integer>();

//      Gets the content of each line and append to the Integer Boxed List
        for(int aRow = 0; aRow < input.length; aRow++) {

//          Cast the int[] row for a Boxed format
            boxedList = Arrays.stream(input[aRow])
                    .toArray(Integer[]::new);
            aList.addAll(Arrays.asList(boxedList));

        }

//      Auxiliary swapping process.
//      Before casting into an int[], creates an Integer[]
        return aList.toArray(new Integer[aList.size()]);
    }

//  Find the best position to fill a value
//  By "Best Position", I mean the one whose surrounding values brings a higher expectation of
//  success. Then, it returns an array with the x, y coordinates to play
    private Sudoku optimizeResolution(){
        
//      Looks for missing slots. The ones equal to '0'
        List<Integer[]> openPositions = getMissingSlots();

//      Auxiliar Integer var to support game progress measurement
        Integer numberOfMissingSlots = openPositions.size();
        Integer numberOfMissingSlotsWhenFieldsHaveBeenRecalculated = numberOfMissingSlots;

//      HashMap for matching best next steps in game
        Map<Integer[], Integer[]> gameMap = new HashMap<>();

//      Based on th openPositions list, for each position, looks for the best possibility to play
//      In other words, searches for the slot surrounded by non-initial values
//      1) Rows
//      2) Columns
//      3) Group
//      Build the GameMap composed by the Coordinates of the missing slots as well the possible values
//      for them
        for(Integer[] openPosition : openPositions){
            gameMap.put(openPosition, getPossibleValues(openPosition));
        }

//      Sort gameMap by the size of Integer[] ASCENDINGLY
        Map<Integer[], Integer[]> sortedGameMap = sortByNumberOfPossibleMatches(gameMap);

        for (Map.Entry<Integer[], Integer[]> sortedMap : sortedGameMap.entrySet()) {
            if (sortedMap.getValue().length == 1) { //Best Match! When there is only one option to play!
                Integer[] correctValue = sortedMap.getValue();
                Integer[] position = sortedMap.getKey();
                elements[position[0]][position[1]] = correctValue[0];
                numberOfMissingSlots--;
            } else {
//              If the game is progressing, we can still try to optimized things
                if (isGameProgressing(numberOfMissingSlots, numberOfMissingSlotsWhenFieldsHaveBeenRecalculated))
                    optimizeResolution();
            }
        }

        return new Sudoku(elements);
    }

    private Sudoku neighborhoodEvaluation(Sudoku sudoku){
        return null;
    }

    private boolean isGameProgressing(Integer currentNumberOfMissingSlots, Integer previousNumberOfMissingSlots){
        return (currentNumberOfMissingSlots < previousNumberOfMissingSlots) ? true : false;
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

        Integer[] row = getRow(coordinates[0]);
        Integer[] column = getColumn(coordinates[1]);
        Integer[] block = serializesMultimensionalArray(getBlock(getBlockId(coordinates)));

        Arrays.stream(row)
                .forEach(possibleValues::remove);

        Arrays.stream(column)
                .forEach(possibleValues::remove);

        Arrays.stream(block)
                .forEach(possibleValues::remove);

        return possibleValues.toArray(new Integer[possibleValues.size()]);
    }

    private Integer getBlockId(Integer[] coordinates){

        int y = coordinates[0];
        int x = coordinates[1];

        int gColumn = x / 3;
        int gRow = (y / 3) * 3;

        return gRow + gColumn;
    }

    //  Returns the specified column from elements multidimensional vector
    private Integer[] getColumn(int index){

        ArrayList<Integer> column = new ArrayList<>();

        for(Integer[] aRow : elements){
            column.add(aRow[index]);
        }

        return (Integer[]) column.toArray(new Integer[column.size()]);
    }

    //  Returns the specified column from multidimensional vector
    private Integer[] getRow(int index){

        Integer[] row = elements[index];
        return row;
    }

//  Receives a Sudoku and extracts its values to a specific requested block, in accordance with the following rule:
//  0 | 1 | 2
//  3 | 4 | 5
//  6 | 7 | 8
    public Integer[][] getBlock(int block){

        Integer[][] result = new Integer[3][3];

        for(int i = 0; i < result.length; i++){
            for(int j = 0; j < result[i].length; j++){
                result[i][j] = elements[((block / 3) * 3) + i][((block % 3) * 3) + j];
            }
        }
        return result;
    }

    //  Check if a single record fits the base Sudoku condition of having no zeros, repeated values or numbers out of 1..9 interval
    public static boolean checkSingleRecord(Integer[] record){
        return hasNonRepeatedValues(record);
    }

    //  Check if a single block fits the base Sudoku condition of having no zeros, repeated values or number out of 1..9 interval
    public static boolean checkSingleBlock(Integer[][] block){
        return hasNonRepeatedValues(serializesMultimensionalArray(block));
    }

    //Sorts HashMap by Value
    private static Map<Integer[], Integer[]> sortByNumberOfPossibleMatches(Map<Integer[], Integer[]> map) {

        Map<Integer[],Integer[]> unsortedMap = new HashMap<>();

        unsortedMap.putAll(map);

//        for(Map.Entry<Integer[], Integer[]> entry : map.entrySet()){
//            unsortedMap.put(entry.getKey(), entry.getValue().length);
//        }

        // 1. Convert Map to List of Map
        List<Map.Entry<Integer[], Integer[]>> list =
                new LinkedList<>(unsortedMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<Integer[], Integer[]>>() {
            public int compare(Map.Entry<Integer[], Integer[]> p1,
                               Map.Entry<Integer[], Integer[]> p2) {
                return (Integer.compare(p1.getValue().length, p2.getValue().length));
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<Integer[], Integer[]> sortedMap = new LinkedHashMap<>();
//        sortedMap.putAll((Map<Integer[], Integer[]>) list);

        for (Map.Entry<Integer[], Integer[]> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

//    private static Map<Integer[], Integer[]> sortByValue(Map<Integer[], Integer[]> unsortedMap) {
//
//        // 1. Convert Map to List of Map
//        List<Map.Entry<Integer[], Integer[]>> list =
//                new LinkedList<>(unsortedMap.entrySet());
//
//        // 2. Sort list with Collections.sort(), provide a custom Comparator
//        //    Try switch the o1 o2 position for a different order
//        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
//            public int compare(Map.Entry<Integer, Integer> p1,
//                               Map.Entry<Integer, Integer> p2) {
//                return (Integer.compare(p1.getValue(),p2.getValue()));
//            }
//        });
//
//        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
//        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
//        for (Map.Entry<Integer, Integer> entry : list) {
//            sortedMap.put(entry.getKey(), entry.getValue());
//        }
//
//        return sortedMap;
//    }

    @Override
    public boolean equals(Object obj) {

        Sudoku sudoku = (Sudoku) obj;
        boolean result = true;

        for (int i = 0; i < elements.length; i++){
            for (int j = 0; j < elements[i].length; j++) {
                if (elements[i][j] != sudoku.getElements()[i][j])
                    result = false;
            }
        }
        return result;
    }

    public Integer[][] getElements(){
        return elements;
    }
}