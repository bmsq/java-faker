package com.github.javafaker;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.join;

public class Lorem {
    private final Faker faker;

    Lorem(Faker faker) {
        this.faker = faker;
    }
    
    public char character() {
        return character(false);
    }

    public char character(boolean includeUppercase) {
        return characters(1).charAt(0);
    }

    public String characters() {
        return characters(255, false);
    }

    public String characters(boolean includeUppercase) {
        return characters(255, false);
    }

    public String characters(int minimumLength, int maximumLength) {
        return characters(faker.random().nextInt(maximumLength - minimumLength) + minimumLength, false);
    }

    public String characters(int minimumLength, int maximumLength, boolean includeUppercase) {
        return characters(faker.random().nextInt(maximumLength - minimumLength) + minimumLength, includeUppercase);
    }

    public String characters(int fixedNumberOfCharacters) {
        return characters(fixedNumberOfCharacters, false);
    }

    public String characters(int fixedNumberOfCharacters, boolean includeUppercase) {
        if (fixedNumberOfCharacters < 1) {
            return "";
        }
        char[] buffer = new char[fixedNumberOfCharacters];
        for (int i = 0; i < buffer.length; i++) {
            char randomCharacter = characters[faker.random().nextInt(characters.length)];
            if (includeUppercase && faker.bool().bool()) {
                randomCharacter = Character.toUpperCase(randomCharacter);
            }
            buffer[i] = randomCharacter;
        }
        return new String(buffer);
    }

    public List<String> words(int num) {
        List<String> returnList = new ArrayList<String>();
        for (int i = 0; i < num; i++) {
            returnList.add(word());
        }
        return returnList;
    }

    public List<String> words() {
        return words(3);
    }

    public String word() {
        return faker.fakeValuesService().resolve("lorem.words", this, faker);
    }

    public String sentence(int wordCount) {
        return capitalize(join(words(wordCount + faker.random().nextInt(6)), " ") + ".");
    }

    public String sentence() {
        return sentence(3);
    }

    public List<String> sentences(int sentenceCount) {
        List<String> sentences = new ArrayList<String>(sentenceCount);
        for (int i = 0; i < sentenceCount; i++) {
            sentences.add(sentence());
        }
        return sentences;
    }

    public String paragraph(int sentenceCount) {
        return join(sentences(sentenceCount + faker.random().nextInt(3)), " ");
    }

    public String paragraph() {
        return paragraph(3);
    }

    public List<String> paragraphs(int paragraphCount) {
        List<String> paragraphs = new ArrayList<String>(paragraphCount);
        for (int i = 0; i < paragraphCount; i++) {
            paragraphs.add(paragraph());
        }
        return paragraphs;
    }

    /**
     * Create a string with a fixed size. Can be useful for testing
     * validator based on length string for example
     *
     * @param numberOfLetters size of the expected String
     * @return a string with a fixed size
     */
    public String fixedString(int numberOfLetters) {
        StringBuilder builder = new StringBuilder();
        while (builder.length() < numberOfLetters) {
            builder.append(sentence());
        }
        return StringUtils.substring(builder.toString(), 0, numberOfLetters);
    }

    static {
        StringBuilder builder = new StringBuilder(36);
        for (char number = '0'; number <= '9'; number++) {
            builder.append(number);
        }
        for (char character = 'a'; character <= 'z'; character++) {
            builder.append(character);
        }
        characters = builder.toString().toCharArray();
    }

    private static final char[] characters;

}
