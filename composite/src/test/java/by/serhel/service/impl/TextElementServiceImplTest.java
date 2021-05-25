package by.serhel.service.impl;

import by.serhel.composite.TextElement;
import by.serhel.exception.NotExpectedElementException;
import by.serhel.parser.*;
import by.serhel.service.TextElementService;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class TextElementServiceImplTest {
    private TextElementService service = new TextElementServiceImpl();
    private AbstractParser parser = new ParagraphParser(new SentenceParser(
            new LexemeParser(new WordParser(new SymbolParser(null)))));

    @Test
    public void testFindSentencesWithLongestWord() {
        AbstractParser parser = new ParagraphParser(new SentenceParser(new LexemeParser(new WordParser(null))));
        Set<String> expected = new HashSet<>() {
            {
                add("\tIt was great!");
                add("\n\tHello world lol. ");
            }
        };
        TextElement element = new TextElement();
        parser.parse("\tIt was great!\n\tI want you!!!\n\tHello world lol. I am not your.", element);
        Set<String> actual = service.findSentencesWithLongestWord(element)
                .stream()
                .map(e -> e.toString())
                .collect(Collectors.toSet());
        assertEquals(actual, expected);
    }

    @Test
    public void testRemoveSentencesLessThenWordCount() throws NotExpectedElementException {
        TextElement expected = new TextElement();
        parser.parse("\n\tHello world lol kek. I am not your.", expected);
        TextElement actual = new TextElement();
        parser.parse("\tIt was great!\n\tI want you!!!\n\tHello world lol kek. I am not your.", actual);
        service.removeSentencesLessThenWordCount(actual, 4);
        assertEquals(actual.toString(), expected.toString());
    }

    @Test
    public void testCountSameWordsInText() throws NotExpectedElementException {
        TextElement text = new TextElement();
        AbstractParser parser = new SentenceParser(new LexemeParser(new WordParser(null)));
        parser.parse("It it, it it it - it.", text);
        int expected = 6;
        int actual = service.countSameWordsInText(text);
        assertEquals(actual, expected);
    }

    @Test
    public void testCountVowelsInSentence() {
        AbstractParser parser = new SentenceParser(new LexemeParser(new WordParser(new SymbolParser(null))));
        int expected = 5;
        TextElement element = new TextElement();
        parser.parse("\n\tHello world lol kek.", element);
        int actual = service.countVowelsInSentence(element);
        assertEquals(actual, expected);
    }

    @Test
    public void testCountConsonantsInSentence() {
        AbstractParser parser = new SentenceParser(new LexemeParser(new WordParser(new SymbolParser(null))));
        int expected = 11;
        TextElement element = new TextElement();
        parser.parse("\n\tHello world lol kek.", element);
        int actual = service.countConsonantsInSentence(element);
        assertEquals(actual, expected);
    }
}