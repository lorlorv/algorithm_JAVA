package DAY02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortTest {
    public static void main(String[] args ){
        Item item1 = new Item(1, 3, 1);
        Item item2 = new Item(1, 2, 3);
        Item item3 = new Item(1, 1, 2);

        List<Item> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);
        list.add(item3);

        System.out.println("정렬 전 : " + list);

        Collections.sort(list);
        //정렬
        //1. Comparable : b 기준 오름차순 / a 기준 내림차순
        System.out.println("Comparable 정렬 후 : " + list);

        //2. Comparator 중간에 바꿔야하는 경우
        Comparator<Item> comp = new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.c - o2.c;
            }
        };

        Collections.sort(list, comp); //comparable 무시 comparator을 가지고 정렬!
        System.out.println("Comparator 정렬 후 : " + list);

        //3. getter 이용 정렬
        Collections.sort(list, Comparator.comparingInt(Item::getB)); //Item::getB 자바의
//        Collections.sort(list,Comparator.comparingInt(Item::getB).reversed());//내림차순
//        Collections.sort(list,Comparator.comparingInt(Item::getB).reversed().thenComparingInt(Item::getC));//다중 조건 복잡한 경우에는 추천 X
        System.out.println("getter 정렬 후 : " + list);

    }
}
class Item implements Comparable<Item>{
    int a;
    int b;
    int c;

    public Item(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return "{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }

    @Override
    public int compareTo(Item o) {
        //음수 -> 원래 순서
        //0 -> 동일
        //양수 -> SWAP

        //ex Item의 b를 기준으로 오름차순 정렬
//        (1)
//        if(b < o.b){
//            return -1;
//        }else if(b == o.b){
//            return 0;
//        }else{
//            return 1;
//        }

//        (2)
//        return b - o.b; //b가 크다면 양수 -> swap

//        (3) 기본 Compartor이용
//        return Integer.compare(b, o.b); //기본 : 오름차순, 내림차순 -> 순서 바꾸기

//        (4) a기준 내림차순 * 동률 발생, b는 오름차순 *
        if(a > o.a){
            return -1;
        }else if(a == o.a){
            return b - o.b;
        }else{
            return 1;
        }
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }
}
