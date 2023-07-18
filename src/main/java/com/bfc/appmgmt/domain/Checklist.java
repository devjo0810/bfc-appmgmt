package com.bfc.appmgmt.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.bfc.appmgmt.domain
 * fileName       : Checklist
 * author         : joyousang
 * date           : 2023/06/01
 * description    :
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@SequenceGenerator(
        name = "CHECKLIST_SEQ_GENERATOR"
        , sequenceName = "CHECKLIST_SEQ"
        , initialValue = 1
        , allocationSize = 1
)
public class Checklist extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHECKLIST_SEQ_GENERATOR")
    @Column(name = "checklist_id")
    private Long id;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToMany(mappedBy = "checklist", cascade = CascadeType.ALL)
    private List<ChecklistItem> checklistItems = new ArrayList<>();

    @Builder
    public Checklist(String title, Member member) {
        this.title = title;
        this.member =member;
    }

    public void updateTitle(String title) {
        this.title = title;
    }
}
