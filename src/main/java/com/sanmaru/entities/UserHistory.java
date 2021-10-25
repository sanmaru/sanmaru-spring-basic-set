package com.sanmaru.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    private String requestURI;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String eventName;

    @Getter @Setter
    private Long eventTime;

    @Getter @Setter
    private String eventTimeKr;

    @Getter @Setter
    private String userAgent;

    public UserHistory() {}

    public UserHistory(HttpServletRequest request, String userName, String eventName){
        Date now = new Date();
        this.accessIP = request.getRemoteHost();
        this.requestURI = request.getRequestURI();
        this.userAgent = request.getHeader("user-agent");
        this.username = userName;
        this.eventName = eventName;
        this.eventTime = now.getTime();
        this.eventTimeKr = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(now);
    }
}
