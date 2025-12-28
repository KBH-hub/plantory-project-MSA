package org.zero.communityservice.repository;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "sharing")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sharing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sharing_id")
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "target_member_id")
    private Long targetMemberId;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "plant_type", nullable = false, length = 50)
    private String plantType;

    @Column(name = "management_level", length = 30)
    private String managementLevel;

    @Column(name = "management_needs", length = 30)
    private String managementNeeds;

    @Column(name = "interest_num")
    private Integer interestNum;

    @Column(length = 20)
    private String status;

    @Column(name = "review_flag")
    private LocalDateTime reviewFlag;

    @Column(name = "target_member_review_flag")
    private LocalDateTime targetMemberReviewFlag;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "del_flag")
    private LocalDateTime delFlag;

    public Sharing(
            Long memberId,
            String title,
            String content,
            String plantType,
            String managementLevel,
            String managementNeeds
    ) {
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.plantType = plantType;
        this.managementLevel = managementLevel;
        this.managementNeeds = managementNeeds;
        this.interestNum = 0;
        this.status = "false";
        this.createdAt = LocalDateTime.now();
    }


    public void update(String title, String content,
                       String managementLevel, String managementNeeds) {
        this.title = title;
        this.content = content;
        this.managementLevel = managementLevel;
        this.managementNeeds = managementNeeds;
        this.updatedAt = LocalDateTime.now();
    }

    public void softDelete() {
        this.delFlag = LocalDateTime.now();
    }

    public void increaseInterest() {
        this.interestNum = (this.interestNum == null ? 1 : this.interestNum + 1);
    }

    public void decreaseInterest() {
        if (this.interestNum != null && this.interestNum > 0) {
            this.interestNum--;
        }
    }

    public void selectTargetMember(Long targetMemberId) {
        this.targetMemberId = targetMemberId;
        this.status = "true";
    }

}
