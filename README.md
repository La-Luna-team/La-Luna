<div align=center>
	<img src="https://capsule-render.vercel.app/api?type=waving&color=auto&height=200&section=header&text=La-Luna-team!&fontSize=90" />	
</div>

# 반려동물을 키우는 사람들을 위한 커뮤니티
--------------
## 프로젝트 계획 이유
> 반려동물 커뮤니티를 기획하게된 이유로는
> 여러분도 가정에서 반려동물과 함께 생활 하시는 분들이 꽤 있을신텐데요 ? 가장 사랑 하는 반려견 이름이 무엇이였나요? 
> 저희 팀은 반려동물과 생활 하면서 겪었던 부분을 생각해보면 
> 가장 필요한 부분이 반려견이 아프거나 전혀 모르던 증상이 생겼을때 당황 했던 경험이 있었습니다 
> 이럴때 갑자기 병원에 데려가지 못하고 빠른 조치가 필요한 순간이 있었을 텐데요 
> 반려동물 커뮤니티를 통해 
> 1. 반려견 건강 관련된 정확한 정보와 필요한 정보를 확인 할 수 있는 건강관리 게시글  
> 2. 일상 생활을 기록 할 수 있는 일상 게시글 
> 3. 반려견을 키우면서 전체 적인 팁을 얻을 수 있는 일상 게시글
>
> 이렇게 세가지를 목적으로 프로젝트를 진행 하였습니다. 

---------------

## La-luna 반려동물 커뮤니티 게시판의 주요 기능

- 커뮤니티 안에서 가입자의 반려동물 상태를 공유
- 반려동물 게시글 이용
    - 반려 동물의 건강 상태 및 이상 징후 문의 및 공유
    - 반려 동물의 일상 게시 보관 및 공유
    - 반려 동물의 다양한 팁 문의 및 공유
- 건강 등 반려 동물의 이상 징후를
커뮤니티 내 수의사 (인증된 가입자)의 댓글로
처치 방법등 정확한 정보 확인 및 공유  

 ----------------
 
## 프로젝트 개발 환경
![2024-03-13 08 59 46](https://github.com/La-Luna-team/La-Luna/assets/159854114/6ee91447-e486-4c34-84d5-03bd89dd963c)

![2024-03-13 09 00 00](https://github.com/La-Luna-team/La-Luna/assets/159854114/cd310f78-d2cf-45de-9567-6d74dcd562dd)

----------------

## 팀원 소개
![2024-03-13 09 02 52](https://github.com/La-Luna-team/La-Luna/assets/159854114/a4dea237-4915-4de6-8225-83b204986083)

--------------


## 프로젝트 진행목차

첫째.
- 주제 정하기
- 피그마를 통해 화면 구성
- DB 구성하기
- 역할 정하기

둘째.
- 역할에 맞는 프론트 제작
- 역할에 맞는 백엔드 제작
- 제작 후 깃허브에 저장

셋째.
- 서로 맞춘 후 추가와 수정, 삭제
- 오류 수정
- 작동하여 기능들 확인

-------------


## 프로젝트 전략

- github-flow 전략으로 메인 에서 각 기능들을 이슈 브런치로 만들어 만든 기능들을 PR로 코드리뷰와
  피드백으르 받고 테스트 후 마지 하여 새로운 기능 추가를 위해 테스트까지 완료된 최종마지에 브런치를 생성하여 작업한다.
- 각 팀원들이 맡은 파트를 성공적으로 완수하기 위해 서로 상호적인 관계로 다른 파트 코드도 내 코드라는
  마음가짐으로 임하여 하나는 모두를 위하여 모두는 하나를 위한 다대일, 일대다 마음가짐으로 프로젝트에 임한다.
	

-------------

## 프로젝트 구조
``` JAVA
├─.github
│  └─ISSUE_TEMPLATE
├─.gradle
│  ├─8.5
│  │  ├─checksums
│  │  ├─dependencies-accessors   
│  │  ├─executionHistory
│  │  ├─fileChanges
│  │  ├─fileHashes
│  │  └─vcsMetadata
│  ├─buildOutputCleanup
│  └─vcs-1
├─.idea
│  └─dataSources
│      └─ac9dd5fb-e2fe-426c-8f35-6e318de53fb3
│          └─storage_v2
│              └─_src_
│                  └─schema
├─gradle
│  └─wrapper
├─out
│  └─production
│      ├─classes
│      │  └─com
│      │      └─laluna
│      │          └─laluna
│      │              ├─config
│      │              ├─controller
│      │              ├─domain
│      │              │  ├─dto
│      │              │  │  ├─board
│      │              │  │  ├─join
│      │              │  │  ├─member
│      │              │  │  ├─pet
│      │              │  │  └─reply
│      │              │  └─entity
│      │              ├─repository
│      │              └─service
│      └─resources
│          ├─static
│          │  ├─css
│          │  └─images
│          └─templates
│              ├─boards
│              ├─fragments
│              ├─layout
│              └─view
└─src
    ├─main
    │  ├─generated
    │  ├─java
    │  │  └─com
    │  │      └─laluna
    │  │          └─laluna
    │  │              ├─config
    │  │              ├─controller
    │  │              ├─domain
    │  │              │  ├─dto
    │  │              │  │  ├─board
    │  │              │  │  ├─join
    │  │              │  │  ├─member
    │  │              │  │  ├─pet
    │  │              │  │  ├─photo
    │  │              │  │  └─reply
    │  │              │  └─entity
    │  │              ├─repository
    │  │              └─service
    │  └─resources
    │      ├─static
    │      │  ├─css
    │      │  └─images
    │      └─templates
    │          ├─boards
    │          ├─fragments
    │          ├─layout
    │          └─view
    └─test
        └─java
            └─com
                └─laluna
                    └─laluna
                        ├─controller
                        └─service

```



-----------

## 개발기간 

개발 기간 : 2023-02-20 ~ 2023-03-12

주제정하기 요구사항 명세서 작성 : 2023.2.20 ~ 2023.2.21

데이터베이스 ERD다이어그램 제작 피그마 학습 및 작업 : 2023.2.22 ~ 2023.2.26

프론트 작업, 역할 배분 : 2023.02.26 ~ 2023.02.29

기능 업무, 마무리작업 : 2023.03.01 ~ 2023.03.11


-----------

## 실행화면

![home](https://github.com/La-Luna-team/La-Luna/assets/159854114/c5fa7c31-37f0-4200-a3ed-70c63e1f8891)
![join us](https://github.com/La-Luna-team/La-Luna/assets/159854114/deed13bf-f8aa-4f0a-b06b-95cdfa603025)
![login](https://github.com/La-Luna-team/La-Luna/assets/159854114/1ad44d25-2cca-4c38-be4d-f609a99db0e9)
![게시글(수정,삭제)](https://github.com/La-Luna-team/La-Luna/assets/159854114/7ab31e40-990e-477f-8e3c-b364474155f5)
![게시물 등록](https://github.com/La-Luna-team/La-Luna/assets/159854114/7326bf57-8089-4bc5-b284-2c21e2394e07)
![댓글(등록,수정,삭제)](https://github.com/La-Luna-team/La-Luna/assets/159854114/a5feab7d-0ae0-4c21-b037-28a36802a0d6)
![작성자만(수정,삭제)](https://github.com/La-Luna-team/La-Luna/assets/159854114/41f9fa56-d67c-4f21-b684-4ad0b79c70f7)
![작성자만(수정,삭제)댓글](https://github.com/La-Luna-team/La-Luna/assets/159854114/1cb5bf76-14f4-4536-a048-b43ceabc5fe0)
![회원탈퇴-min](https://github.com/La-Luna-team/La-Luna/assets/159854114/c55de273-9224-426c-bfb5-e3116b921a27)

-----------




## 프로젝트 하면서 부족했던점


- 프로젝트 초반에 이슈발행 및 브랜치 활용을 못해서 pull request 과정에서
브랜치당 작업물이 너무 많아 충돌한 코드를 수정하는것이 힘들었음
나중에 큰 프로젝트를 진행할 때 문제가 될것으로 생각됨
    - 개선 방법 : 사소한 기능일지라도 이슈발행 및 브랜치 생성을 통해 업무 별로 작업(커밋, 푸시)후에 main 코드에
    팀원끼리 코드리뷰 후에 mergy를 하는것이 좋다고 느껴진다.
- 프로젝트 주제에 대한 방향성, 차별성에 대한 불명확함
    - 개선 방법 : La-luna 만의 방향성을 뚜렷히 가져서 상용화 될수있을만한 차별성을 생각해본다.
- 각자 맡은일에 시간 엄수 및 뚜렷한 규칙 부족
    - 개선 방법 : 규칙을 정하고 맡은 기능이나 업무에 대해서 시간을 엄수하고 부족한 부분을 서로 채워준다.
- 코드 설명이 부족
    - 개선 방법 : 협업이기 때문에 코드마다 주석으로 설명을 달아주면 각자 알아볼 수 있어 협엽에 용이하다는 생각이든다.
    
---------------------------------


## 프로젝트 후기
- 백승용 :
- 유윤경 :
- 이강권 :
- 배정현 :
