import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ NO_16974 레벨 햄버거
 * G5
 * https://www.acmicpc.net/problem/16974
 */
public class Main {

    static long[] burgerSize;       // 레벨-N 버거의 크기(레이어 수)
    static long[] pattiesCnt;       // 레벨-N 버거의 패티의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());        // 1 ≤ N ≤ 50
        long X = Long.parseLong(st.nextToken());

        burgerSize = new long[51];
        pattiesCnt = new long[51];
        burgerSize[0] = 1;
        pattiesCnt[0] = 1;
        for (int i = 1; i < 51; i++) {
            burgerSize[i] = 2 * burgerSize[i - 1] + 3;      // 레벨-N 버거의 크기(레이어 수)
            pattiesCnt[i] = 2 * pattiesCnt[i - 1] + 1;      // 레벨-N 버거의 패티의 수
        }

        // 결과 출력
        System.out.println(calc(N, X));

    }

    public static long calc(int level, long pos) {
        long cnt = 0L;
        long mid = (burgerSize[level] / 2) + 1;

        // 더이상 작은 크기로 쪼갤수 없을때 또는 즉시 반환이 가능한 경우
        // 1. 레벨이 0이면 1 반환 (레벨-0 버거는 패티만 존재)
        // 2. 먹은 레이어의 수가 1이면 0 반환 (레벨-0 버거외에는 첫번째 레이어는 항상 번)
        // 3. 먹은 레이어의 수가 버거 전체이면 해당 레벨 버거의 패티의 수 반환
        if (level == 0) return 1;
        if (pos == 1) return 0;
        if (pos == burgerSize[level]) return pattiesCnt[level];

        // 먹은 레이어의 수를 전체 버거의 범위로 구분하여 재귀로 계산
        // 1. 중간아래
        // 2. 중간위
        // 3. 중간
        if (pos < mid) {
            cnt = calc(level - 1, pos - 1);
        } else if (pos > mid) {
            cnt = pattiesCnt[level - 1] + 1 + calc(level - 1, pos - mid);
        } else {
            cnt = pattiesCnt[level - 1] + 1;
        }

        return cnt;
    }
}
