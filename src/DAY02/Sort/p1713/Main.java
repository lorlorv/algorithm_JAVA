package DAY02.Sort.p1713;

import java.io.*;
import java.util.*;

public class Main {
    static int N; //사진틀의 개수
    static int recoCount; //전체 학생의 총 추천 횟수
    static Nominee[] nominees;

    static class Nominee implements Comparable<Nominee> {
        int count; //추천받은 사람의 추천받은 횟수
        int stuNum; //추천받은 사람의 학생 번호
        int timeStamp; //게시일자
        boolean isIn; //사진틀에 있는지

        public Nominee(int count, int stuNum, int date, boolean isIn) {
            this.count = count;
            this.stuNum = stuNum;
            this.timeStamp = date;
            this.isIn = isIn;
        }

        //1. 추천수 2. 시간 -> 표도 적고 가장 최근의 후보 (*내림차순 삭제로 코드 효율 높여보기*!)
        @Override
        public int compareTo(Nominee o) {
            int comp = Integer.compare(count, o.count);
            if(comp == 0){ //추천 수 동률 발생!
                return Integer.compare(timeStamp, o.timeStamp);
            }else{
                return comp;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY02/Sort/p1713/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        recoCount = Integer.parseInt(br.readLine());

        nominees = new Nominee[101];

        List<Nominee> list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < recoCount; i++) {
            int num = Integer.parseInt(st.nextToken());

            //1. 해당 후보가 최초 호출 시
            if(nominees[num] == null){
                nominees[num] = new Nominee(0, num, 0, false);
            }
            //2. 해당 후보가 사진틀에 있을 경우
            if(nominees[num].isIn == true){
                nominees[num].count++;
            }else {
                //3. 해당 후보가 사진틀에 없을 경우
                //3-1. 사진 틀이 가득 찬 경우
                if(list.size() == N){
                    //정렬, 지울 후보 선정, 제거
                    Collections.sort(list);
                    Nominee nominee = list.remove(0); //내림차순으로 정렬하는 것이 효과적! 오름차순이라면 가장 앞에걸 지우고 다시 땡겨오는 작업이 필요함
                    nominee.isIn = false;
                }
                //3-2. 사진 틀에 여유가 있는 경우
                nominees[num].count = 1;
                nominees[num].isIn = true;
                nominees[num].timeStamp = i;
                list.add(nominees[num]);
            }
        }

        Collections.sort(list, new Comparator<Nominee>() {
            @Override
            public int compare(Nominee o1, Nominee o2) {
                return Integer.compare(o1.stuNum, o2.stuNum);
            }
        });

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).stuNum + " ");
        }


    }
}
