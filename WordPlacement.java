import java.util.*;

public class WordPlacement {
    static int maxCount = 0, totalWords, linesCount, lineLimit;
    static List<String> allWords = new ArrayList<>();

    static void placeWords(int idx, int[] lineLengths, int placedCount, List<String> filteredWords) {
        if (idx == filteredWords.size()) {
            maxCount = Math.max(maxCount, placedCount);
            return;
        }
        if (placedCount + filteredWords.size() - idx <= maxCount) return;

        for (int i = 0; i < linesCount; i++) {
            if (lineLengths[i] == 0) {
                lineLengths[i] = filteredWords.get(idx).length();
                placeWords(idx + 1, lineLengths, placedCount + 1, filteredWords);
                lineLengths[i] = 0;
                break;
            } else if (lineLengths[i] + 1 + filteredWords.get(idx).length() <= lineLimit) {
                lineLengths[i] += 1 + filteredWords.get(idx).length();
                placeWords(idx + 1, lineLengths, placedCount + 1, filteredWords);
                lineLengths[i] -= 1 + filteredWords.get(idx).length();
            }
        }
        placeWords(idx + 1, lineLengths, placedCount, filteredWords);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        totalWords = scanner.nextInt();
        for (int i = 0; i < totalWords; i++) allWords.add(scanner.next());
        linesCount = scanner.nextInt();
        lineLimit = scanner.nextInt();

        List<String> filteredWords = new ArrayList<>();
        for (String word : allWords) if (word.length() <= lineLimit) filteredWords.add(word);
        filteredWords.sort((a, b) -> a.length() != b.length() ? Integer.compare(b.length(), a.length()) : a.compareTo(b));

        placeWords(0, new int[linesCount], 0, filteredWords);
        System.out.print(maxCount);
        scanner.close();
    }
}
