package com.laluna.laluna.service;

import com.laluna.laluna.domain.entity.Member;
import com.laluna.laluna.domain.entity.Photo;
import com.laluna.laluna.repository.MemberRepository;
import com.laluna.laluna.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhotoService {

    private final MemberRepository memberRepository;
    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoService(MemberRepository memberRepository, PhotoRepository photoRepository) {
        this.memberRepository = memberRepository;
        this.photoRepository = photoRepository;
    }

    @Transactional
    public void savePhotoUrlToDB(String link, Long mid) {
        // ID를 기반으로 Member 및 Board 정보를 추가로 가져오는 로직
        Member member = memberRepository.findById(mid)
                .orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 회원을 찾을 수 없습니다: " + mid));

        // Board 정보는 필요에 따라 가져오거나, 해당 메서드를 사용하지 않을 수 있습니다.

        // 데이터베이스에 사진 URL 저장
        savePhotoUrl(member, link);
    }

    private void savePhotoUrl(Member member, String link) {
        // Photo 엔티티 생성 및 저장
        Photo photo = Photo.builder()
                .member(member)
                .link(link)
                .build();

        photoRepository.save(photo);
    }
}
