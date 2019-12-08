import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TextAnalyzerTest {

    private TextAnalyzer analyzer;

    @Before
    public void init() throws IOException {
        String filePath = "/workspace/TextAnalysis/src/main/resources/berlin.txt";
        analyzer = new TextAnalyzer(filePath);
    }

    @Test
    public void getNumberOfSentencesTest() {
        assertEquals(8, analyzer.getSentences().size());
    }

    @Test
    public void getNumberOfWordsTest() {
        assertEquals(233, analyzer.getWords().size());
    }


    @Test
    public void getLongestWordTest() {
        assertEquals("celebrations", analyzer.getLongestWord());
    }

    @Test
    public void getLongestSentenceTest() {
        String expected = "Three decades, tens of thousands of pork sausages and boatloads of curry-flavored ketchup later, Konnopke’s, the sausage stand under a subway overpass in the heart of Prenzlauer Berg that started its life in 1930 is still there, a monument to a working-class Berlin that has been all but priced out of existence — the all-night bars replaced by banks, upscale kitchen stores and vegan restaurants.";
        assertEquals(expected, analyzer.getLongestSentence());
    }

    @Test
    public void getPopularWordTest() {
        assertEquals("the, and, a, in, to", analyzer.getPopularWord(5));
        assertEquals("the, and, a, in, to, of, I, sausage, that, Berlin", analyzer.getPopularWord(10));
    }

}
