import java.io.*;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.*;

import static java.time.LocalTime.now;

/**
 *
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        System.out.println(String.valueOf(LocalDateTime.now()).split("T")[0]);
    }

}