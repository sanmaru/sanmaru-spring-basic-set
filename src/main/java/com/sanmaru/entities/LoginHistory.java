package com.sanmaru.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class LoginHistory {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, //사용할 전략을 시퀀스로  선택
            generator = "SeqGeneratorName" //식별자 생성기를 설정해놓은  USER_SEQ_GEN으로 설정
    )
    @SequenceGenerator(
            name = "SeqGeneratorName",
            sequenceName = "loginSeq", // camel 형식으로 작성해야 된다.
            allocationSize = 1
    )
    private Long id;

    @Getter @Setter
    private String username;
    @Getter @Setter
    private Long inconntime;
    @Getter @Setter
    private Long disconntime;

    public LoginHistory() {}

    public LoginHistory(String userName, Long inConnectionTime, Long disConnectionTime){
        this.username = userName;
        this.inconntime = inConnectionTime;
        this.disconntime = disConnectionTime;
    }
}
