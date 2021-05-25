package by.serhel.service.impl;

import by.serhel.composite.AbstractElement;
import by.serhel.composite.SymbolElement;
import by.serhel.composite.TextElement;
import by.serhel.composite.TextElementType;
import by.serhel.exception.NotExpectedElementException;
import by.serhel.service.TextElementService;

import java.util.*;

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

    @Override
    public Set<AbstractElement> findSentencesWithLongestWord(TextElement text) {
        Set<AbstractElement> sentences = new HashSet<>();
        findSentences(text, sentences, 0);
        return sentences;
    }

    @Override
    public void removeSentencesLessThenWordCount(TextElement text, int wordCount) throws NotExpectedElementException {
        ArrayList<AbstractElement> sentences = new ArrayList<>();
        for (AbstractElement element : text.getChild()) {
            if (element instanceof TextElement) {
                TextElement sentence = (TextElement) element;
                if (sentence.getType() == TextElementType.SENTENCE) {
                    if (countWordsInSentence(sentence, 0) < wordCount) {
                        sentences.add(sentence);
                    }
                } else {
                    removeSentencesLessThenWordCount(sentence, wordCount);
                }
            }
        }
        text.removeAll(sentences);
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
        return countConsonant(sentence, 0);
    }

    @Override
    public int countVowelsInSentence(AbstractElement text) {
        return countVowels(text, 0);
    }

    private int countConsonant(AbstractElement text, int count) {
        if (text instanceof SymbolElement) {
            return text.getType() == TextElementType.CONSONANT ? 1 : 0;
        }
        for (AbstractElement element : text.getChild()) {
            count += countConsonant(element, 0);
        }
        return count;
    }

    private int countVowels(AbstractElement text, int count) {
        if (text instanceof SymbolElement) {
            return text.getType() == TextElementType.VOWEL ? 1 : 0;
        }
        for (AbstractElement element : text.getChild()) {
            count += countVowels(element, 0);
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

    private int findSentences(AbstractElement element, Set<AbstractElement> sentences, int maxWordLength) {
        if (element instanceof SymbolElement) {
            return maxWordLength;
        }
        if (element.getType().equals(TextElementType.SENTENCE)) {
            String longestWord = element.getChild().stream()
                    .filter(e -> e.getType().equals(TextElementType.LEXEME))
                    .flatMap(e -> e.getChild().stream().filter(el -> el.getType().equals(TextElementType.WORD)))
                    .map(Object::toString)
                    .max(Comparator.comparingInt(String::length))
                    .get();
            if (longestWord.length() > maxWordLength) {
                sentences.clear();
                maxWordLength = longestWord.length();
            }
            if (longestWord.length() == maxWordLength) {
                sentences.add(element);
            }
        } else {
            for (AbstractElement child : element.getChild()) {
                maxWordLength = findSentences(child, sentences, maxWordLength);
            }
        }
        return maxWordLength;
    }

    private int countWordsInSentence(TextElement element, int count) {
        if (element.getType() == TextElementType.WORD) {
            count++;
        } else {
            for (AbstractElement child : element.getChild()) {
                if (child instanceof TextElement) {
                    TextElement buff = (TextElement) child;
                    count = countWordsInSentence(buff, count);
                }
            }
        }
        return count;
    }
}
