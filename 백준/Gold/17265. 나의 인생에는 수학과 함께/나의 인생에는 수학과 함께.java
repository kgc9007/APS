
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ NO_17265 나의 인생에는 수학과 함께
 * G5
 * https://www.acmicpc.net/problem/17265
 */
public class Main {

    static int N;
    static String[][] map;
    static int max;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());        // 3 <= N <= 5
        map = new String[N][N];
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken();
            }
        }

        dfs(0, 0, Integer.parseInt(map[0][0]), "");

        // 결과 출력
        System.out.println(max + " " + min);

    }

    // dfs 메인 메소드
    // 지정된 범위 내에서
    // 현재 위치가 숫자인지 연산자인지 구분하여
    // 점수 계산 후 기존 최대/최소값 갱신
    // 도착지 도달 전이라면 dfs 진행
    // 도착지 도달시 return;
    public static void dfs(int cr, int cc, int score, String optr) {
        if (cr >= N || cc >= N) {
            return;
        }

        if ((cr + cc) % 2 == 0) {
            score = calc(score, Integer.parseInt(map[cr][cc]), optr);

            if (cr == N - 1 && cc == N - 1) {
                max = Integer.max(max, score);
                min = Integer.min(min, score);

                return;
            }
            dfs(cr, cc + 1, score, "");
            dfs(cr + 1, cc, score, "");
        } else {
            dfs(cr, cc + 1, score, map[cr][cc]);
            dfs(cr + 1, cc, score, map[cr][cc]);
        }

    }

    public static int calc(int num1, int num2, String optr) {
        if ("+".equals(optr)) {
            return num1 + num2;
        }

        if ("-".equals(optr)) {
            return num1 - num2;
        }

        if ("*".equals(optr)) {
            return num1 * num2;
        }

        return num1;
    }
}
