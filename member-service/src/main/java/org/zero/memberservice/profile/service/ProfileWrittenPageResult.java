package org.zero.memberservice.profile.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zero.memberservice.profile.dto.ProfileWrittenListResponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileWrittenPageResult {
    private int total;
    private List<ProfileWrittenListResponse> list;
}

