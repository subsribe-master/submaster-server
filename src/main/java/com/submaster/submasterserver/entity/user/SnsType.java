package com.submaster.submasterserver.entity.user;

import com.submaster.submasterserver.api.exception.SubMasterException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum SnsType {
    NORMAL("일반"),
    KAKAO("카카오"),
    GOOGLE("구글");

    private final String text;

    public void checkSnsType(){
        Optional<SnsType> snsType = Arrays.stream(values())
            .filter(type -> type.getText().equals(text))
            .findFirst();
        snsType.ifPresent(type -> {
            throw new SubMasterException(type.name());
        });
    }
}
