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
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Checklist extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checklist_id")
    private Long id;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToMany(mappedBy = "checklist", cascade = CascadeType.ALL)
    private List<ChecklistItem> checklistItems = new ArrayList<>();

    @Builder
    public Checklist(String title) {
        this.title = title;
    }
}
