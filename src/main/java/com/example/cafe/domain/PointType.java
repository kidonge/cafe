package com.example.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PointType {
    POINT_PAYMENT("결제"),
    POINT_CHARGE("충전");

    private String name;


}

/* 조금만 더 고민하고 바꾸기
ORDINAL로 설정 후 Gender enum 타입이 변경된다면 예기치 못한 문제가 발생할 수 있기도 하고, 숫자가 만약 바뀌면 이전에 있던 데이터들이 뭔지 모른다
STRING 설정은 문자열 자체가 저장되기 때문에 DB 공간 낭비가 발생한다.
이를 대체할 수 있는 좋은 방법은 converter 를 사용하는 것이다.

하지만 충전과 결제 두 개 이외에 추가되거나 수정될 내용이 없기 때문에 ordinal ok?
 */