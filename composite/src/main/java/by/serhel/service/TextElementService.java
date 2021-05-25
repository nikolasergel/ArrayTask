package by.serhel.service;

import by.serhel.composite.AbstractElement;
import by.serhel.composite.TextElement;
import by.serhel.exception.NotExpectedElementException;

import java.util.Set;

public interface TextElementService {
    void sortParagraphsBySentenceCount(TextElement text) throws NotExpectedElementException;
    Set<AbstractElement> findSentencesWithLongestWord(TextElement text);
    void removeSentencesLessThenWordCount(TextElement text, int wordCount) throws NotExpectedElementException;
    int countSameWordsInText(TextElement text);
    int countVowelsInSentence(AbstractElement sentence);
    int countConsonantsInSentence(AbstractElement sentence);
}
