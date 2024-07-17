import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int hour, minute;
    static int cook;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        hour = Integer.parseInt(st.nextToken());
        minute = Integer.parseInt(st.nextToken());

        cook = Integer.parseInt(br.readLine());

        if (cook / 60 > 0) {
            hour += cook / 60;
            cook = cook % 60;
        }

        if (minute + cook >= 60) {
            hour++;
            minute = minute + cook - 60;
        } else {
            minute = minute + cook;
        }
        
        if (hour >= 24) {
            hour -= 24;
        }

        System.out.println(hour + " " + minute);
    }
}