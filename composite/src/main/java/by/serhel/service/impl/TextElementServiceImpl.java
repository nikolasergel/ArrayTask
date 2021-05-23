package by.serhel.service.impl;

import by.serhel.composite.AbstractElement;
import by.serhel.composite.SymbolElement;
import by.serhel.composite.TextElement;
import by.serhel.composite.TextElementType;
import by.serhel.exception.NotExpectedElementException;
import by.serhel.service.TextElementService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TextElementServiceImpl implements TextElementService {
    @Override
    public void sortParagraphsBySentenceCount(TextElement text) throws NotExpectedElementException {
        if (!text.getType().equals(TextElementType.TEXT)) {
            throw new NotExpectedElementException("TEXT type element expected, not " + text.getType().name());
        }
        Comparator<AbstractElement> comparator = (paragraph1, paragraph2) -> {
            long countSentences1 = paragraph1.getChild().size();
            long countSentences2 = paragraph2.getChild().size();
            return Long.compare(countSentences1, countSentences2);
        };
        text.getChild().sort(comparator);
    }

    @Override //TO DO, rewrite with recursion
    public List<AbstractElement> findSentencesWithLongestWord(TextElement text) throws NotExpectedElementException {
        if (!text.getType().equals(TextElementType.TEXT)) {
            throw new NotExpectedElementException("TEXT type element expected, not " + text.getType().name());
        }
        List<AbstractElement> sentences = new ArrayList<>();
        String longest = "";
        boolean flag;
        for (AbstractElement paragraph : text.getChild()) {
            for (AbstractElement sentence : paragraph.getChild()) {
                flag = false;
                for (AbstractElement lexeme : sentence.getChild()) {
                    if (lexeme.getType().equals(TextElementType.LEXEME)) {
                        for (AbstractElement word : lexeme.getChild()) {
                            if (word.getType().equals(TextElementType.WORD)
                                    && word.toString().length() >= longest.length()) {
                                if (word.toString().length() > longest.length()) {
                                    longest = word.toString();
                                    sentences.clear();
                                }
                                flag = true;
                            }
                        }
                    }
                }
                if (flag) {
                    sentences.add(sentence);
                }
            }
        }
        return sentences;
    }

    @Override //TO DO, rewrite with recursion
    public void removeSentencesLessThenWordCount(TextElement text, int wordCount) throws NotExpectedElementException {
        if (!text.getType().equals(TextElementType.TEXT)) {
            throw new NotExpectedElementException("TEXT type element expected, not " + text.getType().name());
        }
        List<AbstractElement> sentences = new ArrayList<>();
        for (AbstractElement paragraph : text.getChild()) {
            sentences.clear();
            for (AbstractElement sentence : paragraph.getChild()) {
                int count = 0;
                for (AbstractElement lexeme : sentence.getChild()) {
                    if (lexeme instanceof TextElement) {
                        for (AbstractElement word : lexeme.getChild()) {
                            if (word.getType().equals(TextElementType.WORD)) {
                                count++;
                            }
                        }
                    }
                }
                if (count < wordCount) {
                    sentences.add(sentence);
                }
            }
            paragraph.getChild().removeAll(sentences);
        }
    }

    @Override
    public int countSameWordsInText(TextElement text) {
        List<String> words = new ArrayList<>();
        getWordsFromText(text, words);
        int count = 0;
        for (int i = 0; i < words.size() - 1; i++) {
            int buff = 0;
            for (int j = i + 1; j < words.size(); j++) {
                if (words.get(i).equals(words.get(j))) {
                    buff++;
                    words.remove(j);
                }
            }
            count += buff > 0 ? buff + 1 : 0;
        }
        return count;
    }

    @Override
    public int countConsonantsInSentence(AbstractElement sentence) {
        if (sentence instanceof SymbolElement) {
            return sentence.getType().equals(TextElementType.CONSONANT) ? 1 : 0;
        }
        int count = 0;
        for (AbstractElement element : sentence.getChild()) {
            count += countVowelsInSentence(element);
        }
        return count;
    }

    @Override
    public int countVowelsInSentence(AbstractElement text) {
        if (text instanceof SymbolElement) {
            return text.getType().equals(TextElementType.VOWEL) ? 1 : 0;
        }
        int count = 0;
        for (AbstractElement element : text.getChild()) {
            count += countVowelsInSentence(element);
        }
        return count;
    }

    private void getWordsFromText(AbstractElement element, List<String> words) {
        if (element instanceof SymbolElement) {
            return;
        }
        for (AbstractElement child : element.getChild()) {
            if (child.getType().equals(TextElementType.WORD)) {
                words.add(child.toString().toLowerCase());
            } else {
                getWordsFromText(child, words);
            }
        }
    }
}
