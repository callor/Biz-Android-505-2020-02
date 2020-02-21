package com.biz.img.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

// Serializble
// spring web에서 보내는 데이터는 모두가 문자열로 CharSequence 방식으로
//      일렬 종대로 전달이 된다.
//      ModelAndAttribute로 받을때는 Seirailzable로 인터페이스를 implements한
//      vo로 받아야 한다.
//      sprint web에서는 선택사항으로 설정하면되고,
//
// 안드로이드에서는 반드시 vo 클래스에 Serializable을 impleements 해주어야만
// Intent간에 객체에 값을 담아서 전달할수 있다
public class Addresses implements Serializable {

    private String a_name;
    private String a_tel;
    private String a_addr;

}
