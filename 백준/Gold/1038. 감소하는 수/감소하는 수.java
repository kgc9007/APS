import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * BOJ NO_1038 감소하는수
 * G5
 * https://www.acmicpc.net/problem/1038
 */
public class Main {

    static ArrayList<Long> list;        // 감소하는 수 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());        // 0 <= N <= 1,000,000
        list = new ArrayList<>();                       // 감소하는 수 리스트 초기화

        for (int i = 0; i < 10; i++) {
            calc(i, 1);             // 재귀를 통해 감소하는 수 리스트 저장
        }
        Collections.sort(list);         // 리스트 정렬

        if (N >= list.size()) {
            // N번째 감소하는 수가 없는 경우 = 모든 감소하는 수의 개수보다 N이 큰 경우
            // => -1 출력
            System.out.println(-1);
        } else {
            // N번째 감소하는 수 출력
            System.out.println(list.get(N));
        }

    }


    private static void calc(long num, int idx) {
        if (idx > 10) {
            return;
        }

        list.add(num);
        for (int i = 0; i < num % 10; i++) {
            calc((num * 10) + i, idx + 1);
        }
    }
}
