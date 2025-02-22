import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Word implements Comparable<Word> {
        int weight;
        char letter;

        Word (int weight, char letter) {
            this.weight = weight;
            this.letter = letter;
        }

        public int compareTo(Word o1) {
            if (this.weight > o1.weight)
                return -1;
            else if (this.weight < o1.weight)
                return 1;
            else {
                if (this.letter < o1.letter)
                    return -1;
                else if (this.letter > o1.letter)
                    return 1;
            }

            return 0;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int wordCnt;
    static Map<Character, Integer> init;
    static PriorityQueue<Word> pq;

    public static void main(String[] args) throws IOException {
        init();

        int size = pq.size();
        int answer = 0;
        for (int num = 9; num > 9 - size; num--) {
            Word word = pq.poll();
            answer += word.weight * num;
        }
        System.out.println(answer);
    }

    public static void init() throws IOException {
        wordCnt = Integer.parseInt(br.readLine().trim());

        init = new HashMap<>();
        for (int idx = 0; idx < wordCnt; idx++) {
            String input = br.readLine().trim();
            int inputLeng = input.length();
            for (int word = 0; word < inputLeng; word++) {
                char ch = input.charAt(word);
                if (init.containsKey(ch)) {
                    int tmp = (int) (init.get(ch) + Math.pow(10, inputLeng - word - 1));
                    init.put(ch, tmp);
                } else
                    init.put(ch, (int) Math.pow(10, inputLeng - word - 1));
            }
        }

        pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : init.entrySet()) {
            int weight = entry.getValue();
            char word = entry.getKey();
            pq.add(new Word(weight, word));
        }
    }
}