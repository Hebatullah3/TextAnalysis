import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TextAnalyzer {
    private List<String> sentences;
    private List<String> words;

    public TextAnalyzer(String filePath) throws IOException {
        String text = readFile(filePath);
        sentences = splitTextIntoSentences(text);
        words = splitSentencesIntoWords(sentences);
    }

    public List<String> getSentences() {
        return sentences;
    }

    public List<String> getWords() {
        return words;
    }

    /**
     * get longest (length) word in text
     *
     * @return longest word
     */
    public String getLongestWord() {
        int counter = 0;
        String longestWord = "";
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).length() >= counter) {
                longestWord = words.get(i);
                counter = words.get(i).length();
            }
        }
        return longestWord;
    }

    /**
     * get longest (length) sentence in text
     *
     * @return longest sentence
     */
    public String getLongestSentence() {
        int counter = 0;
        String longestSentence = "";
        for (int i = 0; i < sentences.size(); i++) {
            if (sentences.get(i).length() >= counter) {
                longestSentence = sentences.get(i);
                counter = sentences.get(i).length();
            }
        }
        return longestSentence;
    }

    /**
     * get most popular word in text
     *
     * @return popular word
     */
    public String getPopularWord(Integer topNumber) {
        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) {
            if (!wordsMap.containsKey(word)) {
                wordsMap.put(word, 1);
            } else {
                wordsMap.put(word, wordsMap.get(word) + 1);
            }
        }
        Map<String, Integer> sortedWordMap = sortWordsMapByWordCount(wordsMap);
        List<String> topFiveList = sortedWordMap.keySet().stream().limit(topNumber).collect(Collectors.toList());

        return String.join(", ", topFiveList);
    }

    /**
     * split text to sentences
     *
     * @param line
     * @return list of sentences
     */
    private List<String> splitTextIntoSentences(String line) {
        String sentenceRegex = "(?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=\\.|\\?)\\s";
        return Arrays.asList(line.split(sentenceRegex));
    }

    /**
     * Split sentence into words
     *
     * @param sentence
     * @return list of words
     */
    private List<String> splitSentenceIntoWords(String sentence) {
        String wordRegex = "([^a-zA-Z']+)'*\\1*";
        return Arrays.asList(sentence.split(wordRegex));
    }

    /**
     * Collects words from multiple sentences
     *
     * @param sentences
     * @return list of words
     * @throws IOException
     */
    private List<String> splitSentencesIntoWords(List<String> sentences) throws IOException {
        List<String> words = new ArrayList<>();
        for (String sentence : sentences) {
            words.addAll(splitSentenceIntoWords(sentence));
        }
        return words;
    }

    /**
     * read file text
     *
     * @param filePath
     * @return text
     * @throws IOException
     */
    private String readFile(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        return sb.toString();
    }

    /**
     * Sort words map by word count
     *
     * @param wordsMap
     * @return sorted word map
     */
    private Map<String, Integer> sortWordsMapByWordCount(Map<String, Integer> wordsMap) {
        return wordsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
    }
}
