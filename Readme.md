# 

## *ThingsFlow pre-assignment (Android newcomer)*

---

## [폴더구조]

- common
    - 기타 Constants, util 클래스, Ext, SingleLiveData 를 담았습니다.
- data
    - 데이터소스를 담았습니다.
    - remote 와 local 로 나뉘고 repository 구현체가 있습니다.
- di
  힐트 모듈을 담았습니다.
- domain
    - model 에 Entity 를 담았습니다.
    - repository 에 repository 인터페이스를 담았습니다.
    - use_case
- presentation
    - view 단과 관련된 것을 담았습니다.

## [기술스택]

- MVVM Clean Architecture
  규모가 작은 프로젝트엔 MVC 가 적절하다고 생각했습니다.
  하지만 프로젝트의 규모가 커진다는 가정하게 MVVM 이 적절하다는 점과
  채용공고 우대사항에 ‘MVVM 에 대한 이해’ 가 있다는 점을 생각해서 선정했습니다.
- Retrofit2
- Coroutine
- AAC (ViewModel, LiveData)
- Glide
- Groupie
  일반적으로 ViewType 을 활용해야하는 상황에서, 보일러플레이트 코드를 줄이기 위해 사용하였습니다.
- Room

## [아쉬운 점]

- 서비스와 쉘 명령어를 구현하지 못했습니다.
- dataBinding 을 활용하여 View Controller 의 코드 부담을 줄이면 더 좋겠다고 생각합니다.
- 힐트 모듈을 기능별로 분리하면 하면 더 좋을 것 같습니다.
- UseCase 방식을 통일하여 더 가독성 있는 코드를 작성하고 싶습니다.
- 로딩 뷰를 마지막에 넣고 싶었는데 못 넣었습니다.

## [FYI]

뷰에 전달된 Issue 가 5개 미만인 경우,  있는 개수만큼 먼저 넣고 [https://s3.ap-northeast-2.amazonaws.com/hellobot-kr-test/image/main_logo.png](https://s3.ap-northeast-2.amazonaws.com/hellobot-kr-test/image/main_logo.png) 이미지를 넣었습니다.