package pl.ewqa.rest.testSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import pl.ewqa.rest.test.NotesTests;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        NotesTests.class,
})
public class AllApiTests {
}
