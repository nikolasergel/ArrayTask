package by.serhel.service;

import by.serhel.composite.AbstractElement;
import by.serhel.composite.TextElement;
import by.serhel.exception.NotExpectedElementException;

import java.util.List;

public interface TextElementService {
    void sortParagraphsBySentenceCount(TextElement text) throws NotExpectedElementException;
    List<AbstractElement> findSentencesWithLongestWord(TextElement text) throws NotExpectedElementException;
    void removeSentencesLessThenWordCount(TextElement text, int wordCount) throws NotExpectedElementException;
    int countSameWordsInText(TextElement text);
    int countVowelsInSentence(AbstractElement sentence);
    int countConsonantsInSentence(AbstractElement sentence);
}
