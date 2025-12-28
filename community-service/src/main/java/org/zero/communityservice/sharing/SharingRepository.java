package org.zero.communityservice.sharing;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SharingRepository extends JpaRepository<Sharing, Long> {

    Optional<Sharing> findByIdAndDelFlagIsNull(Long sharingId);

    boolean existsByIdAndMemberIdAndDelFlagIsNull(Long sharingId, Long memberId);

    int countByMemberIdAndStatusAndDelFlagIsNull(Long memberId, String status);

    @Modifying
    @Query("""
        update Sharing s
        set s.delFlag = CURRENT_TIMESTAMP
        where s.id = :sharingId
    """)
    void softDelete(@Param("sharingId") Long sharingId);
}
