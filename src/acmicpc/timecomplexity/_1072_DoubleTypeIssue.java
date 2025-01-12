package acmicpc.timecomplexity;

/*
    1072 풀 때 double 부동 소수점 이슈
 */
public class _1072_DoubleTypeIssue {
    public static void main(String[] args) {
        double funds = 1.00;
        int itemsBought = 0;
        for (double price = 0.10; funds >= price; price += 0.10) {
            funds -= price;
            itemsBought++;
        }

        System.out.println(itemsBought + "개 구입"); // itemsBought : 3
        System.out.println("잔돈(달러) : " + funds); // funds : 0.3999999999999999

        /*
            => 기대한 결과값이 아님
            float와 double은 과학과 공학 계산용으로 설계됨. 이진 부동소수점 연산에 쓰이며, 넓은 범위의 수를 빠르게 정밀한 '근사치'로 계산하도록 설계됨
            -> 정확한 결과가 필요할 때는 사용하면 안됨!!!
                * 이진 부동 소수점 연산이란?
                    : 숫자를 컴퓨터 메모리에 저장할 때 유효숫자(significand)와 지수(exponent)를 이용해 표현
                    : 1. 이진수로 소수 표현의 한계 <- 0.1 은 이진수로 표현하면 끝없이 반복되는 값이 되기 때문에 유한한 비트로 잘라내야 하므로 정확한 값이 저장되지 않음
                    : 2. 정밀도의 한계 <- double은 64비트로 데이터를 저장하며, 약 15~16자리의 유효숫자를 보장, 하지만 유효숫자를 초과하는 값은 반올림하거나 버려져 정밀도가 손실됨
                    : 3. 연산 누적 오차 <- 정밀도 손실 누적됨
                    : 4. 비교 연산의 문제 <- 정밀도 문제 때문에 동등 비교 성립 X


            정확한 계산 (금융 계산) 에서는 BigDecimal, int, long을 사용해야함

            BigDecimal : 반올림 모드가 다양해 반올림을 완벽히 제어할 수 있지만, 기본 타입보다 쓰기가 훨씬 불편하고, 느림
            ```
            final int itemsBought = 0;
            BigDecimal funds = new BigDecimal("1.00");
            for (BigDecimal price = ".10"; funds.compareTo(price) >= 0; price = price.add(".10")){
                funds = funds.subtract(price);
                itemsBought++;
            }
            ```

            int, long : 다룰 수 있는 값의 크기가 제한되고, 소수점을 직접 관리해야하지만, 성능이 중요하고 숫자가 너무 크지 않을 때 사용
                        열여덟자리 십진수를 넘어가면 BigDecimal 사용하기
            => funds * 100 사용해서 정수값으로 수행하기 !
         */
    }

}
