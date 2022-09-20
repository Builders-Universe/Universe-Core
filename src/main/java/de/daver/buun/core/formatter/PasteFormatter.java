package de.daver.buun.core.formatter;


public class PasteFormatter {

    //Column-Name und Type
    //Objekt-Attribute -> Value

    private String leftMiddleBorder;
    private final PasteColumn<?>[] columns;

    public PasteFormatter(int columns){
        this.columns = new PasteColumn[columns];
    }

    public <T> void setColumn(int index, String name, Formatter<T> transformer){
        columns[index] = new PasteColumn<>(name, transformer);
    }

    public String formatLine(Object...values){
        StringBuilder builder = new StringBuilder().append(leftMiddleBorder);
        for(Object value : values){

        }
    }

    static class PasteColumn<T>{
        private final String name;
        private final Formatter<T> transformer;

        public PasteColumn(String name, Formatter<T> transformer){
            this.name = name;
            this.transformer = transformer;
        }

        public String getName(){
            return this.name;
        }

        public Formatter<T> getTransformer(){
            return this.transformer;
        }
    }
}
