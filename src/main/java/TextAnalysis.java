public class TextAnalysis {

    public static void main(String[] args) throws Exception {

        String filePath = "/workspace/TextAnalysis/src/main/resources/berlin.txt";
        TextAnalyzer analyzer = new TextAnalyzer(filePath);

        System.out.println("Number of sentences in the text is: " + analyzer.getSentences().size());
        System.out.println("Number of words in the text is: " + analyzer.getWords().size());
        System.out.println("The longest word is : " + analyzer.getLongestWord());
        System.out.println("The longest sentence is : " + analyzer.getLongestSentence());
        System.out.println("The most popular words are : " + analyzer.getPopularWord(5));
    }
}
