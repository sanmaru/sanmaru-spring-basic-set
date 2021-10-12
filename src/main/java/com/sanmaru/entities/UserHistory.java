package com.sanmaru.entities;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Columns;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Entity
public class UserHistory {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, //사용할 전략을 시퀀스로  선택
            generator = "SeqGeneratorName" //식별자 생성기를 설정해놓은  USER_SEQ_GEN으로 설정
    )
    @SequenceGenerator(
            name = "SeqGeneratorName",
            sequenceName = "userHistorySeq", // camel 형식으로 작성해야 된다.
            allocationSize = 1
    )
    private Long id;

    @Getter @Setter
    private String accessIP;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String eventName;

    @Getter @Setter
    private Long eventTime;

    public UserHistory() {}

    public UserHistory(HttpServletRequest request, String userName, String eventName){
        this.accessIP = request.getRemoteAddr();
        this.username = userName;
        this.eventName = eventName;
        this.eventTime = new Date().getTime();
    }
}
