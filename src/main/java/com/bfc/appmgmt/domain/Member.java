package com.bfc.appmgmt.domain;

import com.bfc.appmgmt.api.auth.MeApiController;
import lombok.*;

import javax.persistence.*;

import static com.bfc.appmgmt.api.auth.MeApiController.*;

/**
 * packageName    : com.bfc.appmgmt.domain
 * fileName       : Member
 * author         : joyousang
 * date           : 2023/06/01
 * description    :
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Member extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String username;
    private String password;
    private String email;

    @Builder
    public Member(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public MeResponse toMeResponse() {
        return MeResponse.builder()
                .memberId(id)
                .name(username)
                .email(email)
                .build();
    }
}
