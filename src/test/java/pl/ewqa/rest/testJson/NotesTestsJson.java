package pl.ewqa.rest.testJson;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pl.ewqa.rest.notes.Note;
import pl.ewqa.rest.utils.BaseMethods;
import pl.ewqa.rest.utils.EndPoint;
import pl.ewqa.rest.utils.TestConfig_Json;

import static io.restassured.RestAssured.given;

public class NotesTestsJson extends TestConfig_Json {

    @Test
    public void getAllNotes() {
        given().when().get(EndPoint.NOTES).then().assertThat().statusCode(200);
    }

    @Test
    public void createNewNoteByJson() {
        String suffix = BaseMethods.randomAlphaNumeric(8);
        String noteName = "name_ " + suffix;
        String description = "description_ " + suffix;

        //create note
        Note note = new Note(noteName, description);

        //create response and post
        Response response = given().body(note.toJson()).
                when().post(EndPoint.NOTES);
        JsonPath jsonPathEvaluator = response.jsonPath();

        //check status code
        response.then().assertThat().statusCode(201);

        //check note from response
        Assert.assertEquals(jsonPathEvaluator.get("message.name"), note.getName());
        Assert.assertEquals(jsonPathEvaluator.get("message.description"), note.getDescription());

        //get created note
        Note createdNote = getSingleNote(jsonPathEvaluator.get("message.id").toString());

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
        JsonPath jsonPathEvaluator = response.jsonPath();
        return new Note(jsonPathEvaluator.get("message.name").toString(), jsonPathEvaluator.get("message.description").toString());
    }

}
