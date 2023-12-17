package Klausur_2_Part2.ListEx;

/**
 * A List with ListElement of type List
 * {1, 2, 3} -> {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}
 * @param <DataType>
 */
public class List2D<DataType> {
    private List<List<DataType>> list;
    private int size;

    /*
    ====================================================================================================================
                                               Constructor and Reset
    ====================================================================================================================
     */
    public List2D(){
        this.list = new List<>();
        this.size = 0;
    }

    public void reset(){
        this.list.reset();
        this.size = 0;
    }

    /*
    ====================================================================================================================
                                              Add - Remove Methods
    ====================================================================================================================
     */
    public void add(List<DataType> listObject){
        list.add(listObject);
        size++;
    }

    /*
    ====================================================================================================================
                                                Setter and Getter
    ====================================================================================================================
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if(list != null){
            stringBuilder.append(list);
        }
        return String.valueOf(stringBuilder);
    }

    public List<List<DataType>> getList() {
        return list;
    }

    public int getSize() {
        return size;
    }

    public void setList(List<List<DataType>> list) {
        this.list = list;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
