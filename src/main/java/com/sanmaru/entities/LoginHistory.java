package com.sanmaru.entities;


import javax.persistence.*;

@Entity
@Table
public class LoginHistory {

    @Id
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE, //사용할 전략을 시퀀스로  선택
            generator="SeqGeneratorName" //식별자 생성기를 설정해놓은  USER_SEQ_GEN으로 설정
    )
    @SequenceGenerator(
            name = "SeqGeneratorName",
            sequenceName = "loginSeq", // camel 형식으로 작성해야 된다.
            allocationSize= 1
    )
    private Long id;

    private String username;
    private Long inconntime;
    private Long disconntime;

    public LoginHistory(String userName, Long inConnectionTime, Long disConnectionTime){
        this.username = userName;
        this.inconntime = inConnectionTime;
        this.disconntime = disConnectionTime;
    }

    public LoginHistory() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getInconntime() {
        return inconntime;
    }

    public void setInconntime(Long inconntime) {
        this.inconntime = inconntime;
    }

    public Long getDisconntime() {
        return disconntime;
    }

    public void setDisconntime(Long disconntime) {
        this.disconntime = disconntime;
    }
}
