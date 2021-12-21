![header](https://capsule-render.vercel.app/api?type=Waving&color=auto&height=250&section=header&text=DAYZ%20&fontSize=90&animation=blinking&fontAlignY=35&desc=내%20손%20안의%20클래스&descAlignY=51&descAlign=62)

# DAYZ
## __내 손안의 클래스! DAYZ__

</br>

## 🛠 Tech Stack
</br>
<div align="center">
<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=ffffff&logoWidth=25"/>&nbsp;&nbsp;
<img src="https://img.shields.io/badge/-JPA-orange?style=flat-square&logoColor=ffffff"/>&nbsp;&nbsp;
<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=SpringBoot&logoColor=ffffff&logoWidth=20"/>&nbsp;&nbsp;
<img src="https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=Gradle&logoColor=ffffff&logoWidth=25"/>&nbsp;&nbsp;
<img src="https://img.shields.io/badge/Lombok-CC071E?style=flat-square"/>&nbsp;&nbsp;
<img src="https://img.shields.io/badge/Mockito-609540?style=flat-square"/>&nbsp;&nbsp;
<br>
<img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=GitHub&logoColor=ffffff&logoWidth=20"/>
<img src="https://img.shields.io/badge/GitHub%20Actions-2088FF?style=flat-square&logo=GitHubActions&logoColor=ffffff&logoWidth=20"/>
<img src="https://img.shields.io/badge/Spring%20Security-6DB33F?style=flat-square&logo=SpringSecurity&logoColor=ffffff&logoWidth=20"/>
<img src="https://img.shields.io/badge/Amazon%20S3-1572B6?style=flat-square&logo=AmazonS3&logoColor=ffffff&logoWidth=20"/>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=ffffff&logoWidth=20"/>
</div>
<div align="center">
<img src="https://img.shields.io/badge/pull%20request-92%20open-lightgrey">&nbsp;&nbsp;&nbsp;
<img src="https://img.shields.io/badge/issues-91%20open-lightgrey">
</div>
</br></br>

* Programming Language : Java 14
* Framework & Libraries : 
    * Spring Boot
    * Spring Security
    * Gradle
    * Lombok
    * Mockito
    * QueryDSL
* IDE : Intellij
* DataBase : MySQL
* SCM & Issues : Git Hub
* CI / CD : GitHub Actions

</br>

---
</br>

## 👨🏻‍💻 Contributors & Team Conventions
</br>

|Team Leader|Developer|Scrum Master|
|:---:|:---:|:---:|
|[Young-BLUE](https://github.com/Young-BLUE)|[DevRunner21](https://github.com/DevRunner21)|[yhh1056](https://github.com/yhh1056)|
|<img src="https://user-images.githubusercontent.com/81351244/140481418-842f52b3-7a6f-44e7-97c6-f1add14cde85.jpeg" width="400" />|<img src="https://user-images.githubusercontent.com/81351244/140481423-6a286f38-3047-43b4-ab43-8fd39620fdba.JPG" width="400" />|<img src="https://user-images.githubusercontent.com/81351244/146935475-cea1c381-f878-44a9-90d8-a71352526ff5.JPG" width="300" height="400" />|


</br>

 * 프로젝트 관리용 협업툴 - [Notion](https://www.notion.so/backend-devcourse/9-DAYZ-83cc78a4918c4118ba958638578f0dbc)
 * 문서 공유용 협업툴 - [Notion](https://www.notion.so/backend-devcourse/9-DAYZ-83cc78a4918c4118ba958638578f0dbc)
 * 메신저 - Slack
 * 온라인 화이트보드 - 게더타운
 * 이슈관리 - [Git Hub](https://github.com/prgrms-web-devcourse/Team_DAYZ_DAYZ_BE)
 * CI/CD - GitHub Actions

</br>

---
</br>

## 💻 Project Description

* 목표 : 이용자들이 검색을 통해 공방과 클래스를 찾아 예약할 수 있고 공방 주인은 SNS 형태의 플랫폼을 통해 홍보와 소통을 할 수 있다.

* 목표 도달 정도 : MOSCOW 기준 Must Have 부분 100% 개발 완료

* 구현된 주요기능 :
    - OAuth2 KaKao 계정으로 회원가입
    - 카테고리 검색
    - 카테고리를 통한 공방, 클래스 검색
    - 공방 상세조회
    - 공방 게시글 작성 및 팔로우 하는 공방의 게시글을 피드형태로 확인
    - 이용자가 공방을 팔로우 또는 언팔로우
    - 원데이 클래스 예약
    - 예약 내역 조회
    - 리뷰 작성 및 조회
    - 댓글 작성 및 조회
    - 공방 유저 회원 가입 (OAuth2 KaKao)
    - 공방 예약 현황 조회
    - 신규공방 추천
    - 이번주 인기 공방 추천

</br>

<details>
<summary style="font-size: 18px">📁 디렉토리 구조보기</summary>

```
├── README.md
├── appspec.yml
├── build
│   ├── generated
│   │   ├── querydsl
│   │   └── sources
│   │       ├── annotationProcessor
│   │       │   └── java
│   │       │       ├── main
│   │       │       └── test
│   │       └── headers
│   │           └── java
│   │               ├── main
│   │               └── test
│   ├── generated-snippets
│   ├── resources
│   │   ├── main
│   │   │   ├── application-prod.yml
│   │   │   ├── application.yml
│   │   │   └── dayz-data.sql
│   └── tmp
│       ├── compileJava
│       │   └── source-classes-mapping.txt
│       ├── compileQuerydsl
│       └── compileTestJava
│           └── source-classes-mapping.txt
│       
├── build.gradle
├── deploy.sh
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
└── src
    ├── docs
    │   └── asciidoc
    │       └── api-guide.adoc
    └── main
        ├── java
        │   └── com
        │       └── dayz
        │           ├── DataSettingRunner.java
        │           ├── DayzApplication.java
        │           ├── atelier
        │           │   ├── controller
        │           │   │   └── AtelierController.java
        │           │   ├── converter
        │           │   │   └── AtelierConverter.java
        │           │   ├── domain
        │           │   │   ├── Atelier.java
        │           │   │   ├── AtelierRepository.java
        │           │   │   └── WorkTime.java
        │           │   ├── dto
        │           │   │   ├── SaveAtelierRequest.java
        │           │   │   └── SaveAtelierResponse.java
        │           │   └── service
        │           │       └── AtelierService.java
        │           ├── category
        │           │   ├── controller
        │           │   │   └── CategoryController.java
        │           │   ├── converter
        │           │   │   └── CategoryConverter.java
        │           │   ├── domain
        │           │   │   ├── Category.java
        │           │   │   └── CategoryRepository.java
        │           │   ├── dto
        │           │   │   └── ReadAllCategoriesResponse.java
        │           │   └── service
        │           │       └── CategoryService.java
        │           ├── comment
        │           │   └── domain
        │           │       ├── Comment.java
        │           │       └── CommentRepository.java
        │           ├── common
        │           │   ├── aop
        │           │   │   ├── JwtAuthenticationArgumentResolver.java
        │           │   │   └── LoginMember.java
        │           │   ├── aws
        │           │   │   ├── AwsS3Service.java
        │           │   │   └── S3Controller.java
        │           │   ├── dto
        │           │   │   ├── ApiError.java
        │           │   │   ├── ApiResponse.java
        │           │   │   ├── CustomPageRequest.java
        │           │   │   ├── CustomPageResponse.java
        │           │   │   └── CustomSort.java
        │           │   ├── entity
        │           │   │   ├── BaseEntity.java
        │           │   │   └── BaseTimeEntity.java
        │           │   ├── enums
        │           │   │   ├── Auth.java
        │           │   │   ├── ErrorInfo.java
        │           │   │   ├── ReservationStatus.java
        │           │   │   └── TimeStatus.java
        │           │   ├── exception
        │           │   │   ├── BusinessException.java
        │           │   │   └── GlobalExceptionHandler.java
        │           │   ├── jwt
        │           │   │   ├── Jwt.java
        │           │   │   ├── JwtAuthentication.java
        │           │   │   ├── JwtAuthenticationFilter.java
        │           │   │   ├── JwtAuthenticationToken.java
        │           │   │   └── JwtUtil.java
        │           │   ├── oauth2
        │           │   │   ├── HttpCookieOAuth2AuthorizationRequestRepository.java
        │           │   │   └── OAuth2AuthenticationSuccessHandler.java
        │           │   └── util
        │           │       ├── ImageUrlUtil.java
        │           │       └── TimeUtil.java
        │           ├── config
        │           │   ├── AopConfig.java
        │           │   ├── AwsS3Config.java
        │           │   ├── CorsProperties.java
        │           │   ├── JpaAuditingConfig.java
        │           │   ├── JwtConfigure.java
        │           │   ├── QuerydslConfig.java
        │           │   ├── WebMvcConfig.java
        │           │   └── WebSecurityConfigure.java
        │           ├── follow
        │           │   └── domain
        │           │       └── Follow.java
        │           ├── member
        │           │   ├── controller
        │           │   │   ├── AddressController.java
        │           │   │   └── MemberController.java
        │           │   ├── converter
        │           │   │   ├── AddressConverter.java
        │           │   │   └── MemberConverter.java
        │           │   ├── domain
        │           │   │   ├── Address.java
        │           │   │   ├── AddressRepository.java
        │           │   │   ├── Member.java
        │           │   │   ├── MemberRepository.java
        │           │   │   ├── Permission.java
        │           │   │   └── PermissionRepository.java
        │           │   ├── dto
        │           │   │   ├── EditMemberAddressRequest.java
        │           │   │   ├── EditMemberAddressResponse.java
        │           │   │   ├── ReadAllAddressResponse.java
        │           │   │   └── ReadMemberInfoResponse.java
        │           │   └── service
        │           │       ├── AddressService.java
        │           │       └── MemberService.java
        │           ├── onedayclass
        │           │   ├── controller
        │           │   │   └── OneDayClassController.java
        │           │   ├── converter
        │           │   │   └── OneDayClassConverter.java
        │           │   ├── domain
        │           │   │   ├── Curriculum.java
        │           │   │   ├── OneDayClass.java
        │           │   │   ├── OneDayClassImage.java
        │           │   │   ├── OneDayClassRepository.java
        │           │   │   └── OneDayClassTime.java
        │           │   ├── dto
        │           │   │   ├── ReadOneDayClassDetailResponse.java
        │           │   │   └── ReadOneDayClassesByCategoryResult.java
        │           │   └── service
        │           │       └── OneDayClassService.java
        │           ├── post
        │           │   ├── controller
        │           │   │   └── PostController.java
        │           │   ├── converter
        │           │   │   └── PostConverter.java
        │           │   ├── domain
        │           │   │   ├── Post.java
        │           │   │   ├── PostImage.java
        │           │   │   ├── PostImageRepository.java
        │           │   │   └── PostRepository.java
        │           │   ├── dto
        │           │   │   └── PostCreateRequest.java
        │           │   └── service
        │           │       └── PostService.java
        │           ├── reservation
        │           │   └── domain
        │           │       └── Reservation.java
        │           └── review
        │               ├── controller
        │               │   └── ReviewController.java
        │               ├── converter
        │               │   └── ReviewConverter.java
        │               ├── domain
        │               │   ├── CustomRepository.java
        │               │   ├── Review.java
        │               │   ├── ReviewImage.java
        │               │   ├── ReviewImageRepository.java
        │               │   ├── ReviewRepository.java
        │               │   └── ReviewRepositoryImpl.java
        │               ├── dto
        │               │   ├── ReadAllAtelierReviewsResponse.java
        │               │   └── ReadAllMyReviewsResponse.java
        │               └── service
        │                   └── ReviewService.java
        └── resources
            ├── application-prod.yml
            ├── application.yml
            └── dayz-data.sql
    
```
</details>

</br>

<details>
<summary style="font-size: 18px">🗄 ERD</summary>
<img src="https://user-images.githubusercontent.com/81351244/146935364-ccbe5022-08c9-4608-b769-e150cd7bb01d.png">
</details>

