package pl.ewqa.rest.testXml;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pl.ewqa.rest.notes.Note;
import pl.ewqa.rest.utils.BaseMethods;
import pl.ewqa.rest.utils.EndPoint;
import pl.ewqa.rest.utils.TestConfig_Xml;

import static io.restassured.RestAssured.given;

public class NotesTestsXml extends TestConfig_Xml {

    @Test
    public void getAllNotes() {
        given().when().get(EndPoint.NOTES).then().assertThat().statusCode(200);
    }

    @Test
    public void createNewNoteByXML() {
        String suffix = BaseMethods.randomAlphaNumeric(8);
        String noteName = "name_ " + suffix;
        String description = "description_ " + suffix;

        //create note
        Note note = new Note(noteName, description);

        //create response and post
        Response response = given().body(note.toXml()).
                when().post(EndPoint.NOTES);
        XmlPath xmlPathEvaluator = response.xmlPath();

        //check status code
        response.then().assertThat().statusCode(201);

        //check note from response
        Assert.assertEquals(xmlPathEvaluator.get("message.name"), note.getName());
        Assert.assertEquals(xmlPathEvaluator.get("message.description"), note.getDescription());

        //get created note
        Note createdNote = getSingleNote(xmlPathEvaluator.get("message.id").toString());

        //check created note
        Assert.assertEquals(createdNote.getName(), note.getName());
        Assert.assertEquals(createdNote.getDescription(), note.getDescription());
    }
//    @Test
//    public void updateNote() {
//        String body = "{\n" +
//                "  \"name\": \"UpdateNote\",\n" +
//                "  \"description\": \"desc\",\n" +
//                "}";
//        given().body(body).
//                when().put("/notes/11").
//                then();
//    }
//
//    @Test
//    public void deleteNote() {
//        given().
//                when().delete("notes/11").
//                then();
//    }

    private Note getSingleNote(String noteId) {
        Response response = given().pathParam("noteId", noteId).get(EndPoint.SINGLE_NOTE);
        XmlPath xmlPathEvaluator = response.xmlPath();
        return new Note(xmlPathEvaluator.get("message.name").toString(), xmlPathEvaluator.get("message.description").toString());
    }

}
