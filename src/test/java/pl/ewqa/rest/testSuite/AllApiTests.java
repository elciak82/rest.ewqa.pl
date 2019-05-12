package pl.ewqa.rest.testSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pl.ewqa.rest.testJson.NotesTestsJson;
import pl.ewqa.rest.testXml.NotesTestsXml;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        NotesTestsJson.class,
        NotesTestsXml.class,
})
public class AllApiTests {
}
