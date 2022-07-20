package DAY03.p1927;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class p1927 {
    static int X;
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/DAY03/p1927/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        X = Integer.parseInt(br.readLine());


        MinHeap mh = new MinHeap();

        for (int i = 0; i < X; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num > 0){
                //배열에 추가 *insert*
                mh.insert(num);
            }else{
                //배열에서 제거 *remove*
                System.out.println(mh.delete());
            }
        }

    }
}
class MinHeap{
    List<Integer> list;

    public MinHeap(){
        list = new ArrayList<>();
        list.add(0); //처음에 0 넣어주기 -> index를 1부터 잡기 위함
    }

    public void insert(int val){
        //1. leaf 마지막에 삽입
        list.add(val);

        //2. 부모와 비교 후 조건에 맞지 않으면 Swap
        //3. 조건이 만족되거나 root 까지 반복
        int current = list.size() - 1; //가장 마지막 노드
        int parent = current / 2;

        while(true){
            if(list.get(parent) <= list.get(current) || parent == 0){ //root에 도착 하거나 부모가 더 작거나 같을때 탈출
                break;
            }
            //swap
            int temp = list.get(parent);
            list.set(parent, list.get(current));
            list.set(current, temp);

            //부모 설정
            current = parent;
            parent = current / 2;
        }
    }

    public int delete(){
        //heap이 비었을 때
        if(list.size() == 1){ // list[0]만 존재
            return 0;
        }
        //1. Root에 leaf 마지막 데이터 가져옴
        int top = list.get(1);
        list.set(1, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        //2. 자식과 비교 후 조건이 맞지 않으면 swap
        //3. 조건이 만족되거나 leaf 까지 반복 //leaf인지 -> 자식이 있는지 없는지
        int currentPos = 1;
        while(true){
            int leftPos = currentPos * 2;
            int rightPos = currentPos * 2 + 1;
            // 왼쪽 자식 먼저 확인
            if(leftPos >= list.size()){ //자식이 없다는 소리
                break;
            }

            int minValue = list.get(leftPos);
            int minPos = leftPos;

            // 오른쪽 자식 확인
            if(rightPos < list.size() && minValue > list.get(rightPos)){
                minValue = list.get(rightPos);
                minPos = rightPos;
            }

            // swap
            if(list.get(currentPos) > minValue){
                int temp = list.get(currentPos);
                list.set(currentPos, list.get(minPos));
                list.set(minPos, temp);
                currentPos = minPos;
            }else{
                break;
            }

        }

        return top;
    }
}
