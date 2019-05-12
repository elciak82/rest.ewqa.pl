package pl.ewqa.rest.notes;

public class Note {
    private String name;
    private String description;

    public Note(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toJson(){
        return "{\n" +
                "  \"name\": \""+this.name+"\",\n" +
                "  \"description\": \""+this.description+"\"\n" +
                "}";
    }

    public String toXml(){
        return "<root>\n" +
                "    <name>\""+this.name+"\"</name>\n" +
                "    <description>\""+this.description+"\"</description>\n" +
                "</root>";
    }
}
