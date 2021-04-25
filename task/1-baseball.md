# 숫자야구게임 수행 과정 및 기능 목록

## 기능 목록

### 숫자를 콘솔에서 입력하는 기능
  - 입력 받은 숫자는 3자리의 숫자인지 Validation 필요.

### 랜덤 숫자를 생성하는 기능
 
### 입력한 숫자와 컴퓨터가 랜덤으로 생성한 숫자에 따라 출력값을 결정하는 기능
  - 1개도 없는 경우 -> Nothing
  - 1개가 있는 숫자인데 자리수가 틀린 경우 -> 1볼
  - 2개가 있는 숫자인데 자리수가 틀린 경우 -> 2볼
  - 1개가 자리수까지 맞고 1개가 있는 숫자인데 자리수가 틀린 경우 -> 1스 1볼
  - 1개가 자리수까지 맞고 2개가 있는 숫자인데 자리수가 틀린 경우 -> 1스 2볼
  - 1개가 자리수까지 맞는 경우 -> 1 스트라이크
  - 2개가 자리수가 맞는 경우 -> 2 스트라이크
  - 3개가 자리수가 맞는 경우 -> 3 스트라이크
  
### 3개의 숫자를 모두 맞혔을 때 게임 재실행 하는 기능
  - 게임 재실행은 1
  - 종료는 2

## 수행 과정

기능 단위로 커밋을 수행하는 것이 쉽지 않을 것으로 예상되기 때문에 기능을 구현하기 전에 먼저 커밋을 작성하는 것이 좋다고 판단했다.

그래서 먼저 작성한 커밋에 대해 이 곳에 정리한다.

수행 과정은 다음과 같은 규칙을 가진다.

- 커밋 메시지를 작성하고 커밋을 한다.

- 개발한 내용에 대한 커밋을 한다. (커밋 내용에 수정이 필요하면 이 문서도 수정한다.)

- 커밋이 너무 많아져서 미리 작성한 커밋과 기능 구현한 커밋을 분리하여 작성하지 않는다.

### 커밋 정리

#### docs(README, 1-baseball) : 숫자 야구 게임 문서 정리

```
정리하는 문서들은 다음과 같다.
- README.md : 과제를 시작하기 전에 1주차 과제에 수행 내용에 대한 링크를 추가한다.
- task/1-baseball.md : 숫자 야구 게임에 대한 수행 과정 및 기능 목록을 설명한다.
```

#### chore(gradle, buildSrc, 1-baseball, .gitignore) : 프로젝트 초기화 작업

```
프로젝트를 수행하기 위한 작업들을 진행한다.

사용할 프레임워크 및 라이브러리 추가:
- junit5 : 테스트를 하기 위한 프레임워크
- assertj : 테스트할 때 사용할 서드 파티 matcher 라이브러리
- jacoco : 테스트 커버리지를 확인하기 위한 라이브러리 

gitignore 대상 추가:
- java, gradle, windows, macos, linux, jetbrain
```

#### test(StringClassTest) : junit5, assertj 학습 - 1

```
junit5, assertj에 대한 학습을 위해 다음과 같은 테스트를 추가한다.

String 클래스 테스트
- "1,2"를 split 했을 때, 1과 2로 분리되는지 테스트
- "1"를 split 했을 때, 1만 포함하는 배열이 되는지 테스트
- "(1,2)" 값이 주어졌을 때, substring을 활용하여 "1,2"를 반환하도록 테스트
- "abc" 값이 주어졌을 때, charAt() 메소드를 활용하여 특정 위치의 문자를 가져오도록 테스트
- charAt() 메소드를 활용하여 StringIndexOutOfBoundsException이 발생하도록 테스트
```

#### test(SetCollectionTest) : junit5, assertj 학습 - 2

```
junit5, assertj에 대한 학습을 위해 다음과 같은 테스트를 추가한다.

Set 콜렉션 테스트
- Set의 size() 메소드를 통해 set의 크기를 확인하는 테스트
- Set의 contains() 메소드를 통해 1, 2, 3의 값이 존재하는지 확인하는 테스트
- Set의 contains() 메소드가 false를 나오는게 하는 테스트
- @Parameterized를 활용한 테스트를 진행한다.
```

#### feat(BaseBall, BaseBallConsoleView, Launcher) - 숫자를 입력 받는 기능 구현

```
숫자를 입력 받는 기능을 구현한다.

숫자를 입력 받을 때 다음과 같은 validation을 가진다.
- 입력 받은 글자의 길이는 3글자다.
- 입력 받은 글자는 모두 숫자다.
```

#### feat(BaseBall, Constants, BaseBallTest) : 랜덤 숫자를 생성하는 기능 구현

```
랜덤 숫자를 생성하는 기능을 구현한다.

BaseBall의 기본 생성자를 호출하면 value 값이 랜덤으로 생성된다.
랜덤으로 생성된 값은 100 이상 999 이하의 값을 가진다.
```

### feat(BaseBall, BaseBallResult, BaseBallTest) : 같은 숫자, 같은 자리수가 없는 경우 기능 구현

```
두 개의 BaseBall을 비교하는데 같은 숫자, 같은 자리수가 없는 경우에 대해 구현한다.

첫 번째 구현 테스트이기 때문에 간단하게 구현한다.
```

### feat(BaseBall, BaseBallResult, BaseBallTest) : 1, 2, 3개의 자리수가 같은 경우 기능 구현

```
2개의 BaseBall을 비교하는데 3개의 자리수가 같은 경우를 구현한다.

자리수 비교를 위해서 BaseBall의 value는 int 값이 아닌 String으로 변환해서 사용한다.
```

### feat(BaseBall, BaseBallResult, BaseBallTest) : 1, 2개의 같은 수가 있는 경우 기능 구현

```
2개의 BaseBall을 비교하는데 2개의 같은 수가 있는 경우를 구현한다.

첫 설계와 달리 3개의 볼이 나올 경우는 없다. 
그러므로 1, 2볼이 나오는 경우만 기능을 구현한다.
```

### feat(BaseBall, BaseBallResult, BaseBallTest) : 스트라이크와 볼이 섞여 있는 경우 기능 구현

```
2개의 BaseBall을 비교하는데 스트라이크와 볼이 섞여 있는 경우 기능을 구현한다.

다음의 경우에 대해 구현한다.
- 1 스트라이크 1볼
- 1 스트라이크 2볼
```

### refactor(BaseBall, BaseBallTest) : BaseBall의 prepare() 함수 분할

```
prepare() 함수를 분할한다.

분할하는 기준은 다음과 같다.
- 스트라이크 넘버만 있는 경우
- 볼 넘버만 있는 경우
- 스트라이크 넘버와 볼 넘버만 있는 경우
```

### fix(BaseBall, BaseBallTest) : 중복되는 수로 BaseBall을 생성 못하도록 로직 구성

```
요구사항에 BaseBall의 수는 중복이 안되다는 조건이 있다.
그에 따라 중복되는 수가 존재하지 않도록 로직을 구성한다.
```

### refactor(BaseBall, StringUtil) : 문자열에서 특정 문자가 있는지 확인하는 함수 분할

```
문자열에서 특정 문자가 있는지 확인하는 함수를 분할하여 가독성을 더 높인다.
```

### feat : 게임에서 실행 기능 구현

```
게임을 실행하는 기능을 구현한다.

문제를 틀리는 경우 : 계속 입력할 수 있게 한다.
문제를 맞추는 경우 : 1을 누르면 다시 시작, 2를 누르면 종료하게 한다.
```

### refactor(Launcher, RandomGenerator, BaseBallConsoleView, BaseBallViewModel) : MVVM 패턴으로 변경

```
MVC 패턴에서 MVVM 패턴으로 변경한다.

이 애플리케이션은 웹 애플리케이션이 아니기 때문에 MVC 패턴보다 MVVM 패턴을 따라가는 것이 더
적합하다고 판단이 된다. 이에 따라 MVVM 패턴으로 구조를 변경한다.

그에 따라 다음 클래스를 변경한다.
- BaseBallRepository -> RandomGenerator
- BaseBallApplicationService -> BaseBallViewModel
```

### feat(BaseBallViewModel, BaseBallViewTest) : 게임 상태에 대해 BaseBallViewModel에서 관리하도록 기능 변경

```
게임 상태에 대해 BaseBallViewModel에서 관리하도록 기능 변경한다.

게임 상태는 다음과 같이 변경된다.
- 게임 상태에 대한 값은 completed다.
- 처음 게임이 시작하면 false다.
- 숫자를 맞추면 true다.
```
