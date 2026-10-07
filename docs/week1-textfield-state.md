# Compose TextFieldState를 사용한 이유

## 기존 `value`, `onValueChange` 방식

기존 로그인·회원가입 화면은 문자열을 `mutableStateOf("")`로 보관하고, 입력할 때마다 `onValueChange`에서 새 문자열을 대입했다. 이 방식은 짧은 입력창에 쓸 수 있지만, 텍스트·커서 위치·한글 조합 상태가 서로 맞게 갱신되도록 개발자가 신경 써야 한다. 특히 입력을 콜백에서 가공하거나 비동기 상태로 왕복시키면 소프트 키보드와 화면 사이의 동기화 문제가 생길 수 있다.

## `TextFieldState`로 변경

각 입력창에 `rememberTextFieldState()`를 만들고 `OutlinedTextField(state = ...)`에 전달했다. 비밀번호에는 같은 상태를 받는 `OutlinedSecureTextField`를 사용했다. `TextFieldState`는 텍스트, 선택 범위, 입력 중인 조합 상태를 함께 관리한다. 입력을 다시 콜백으로 돌려주지 않으므로 입력 흐름이 단순해진다. `rememberTextFieldState()`는 저장·복원 기능도 포함한다.

```kotlin
val emailState = rememberTextFieldState()

OutlinedTextField(
    state = emailState,
    lineLimits = TextFieldLineLimits.SingleLine
)

val email = emailState.text.toString()
```

검증은 `emailState.text`를 읽어 처리한다. 입력 자체를 제한해야 한다면 `onValueChange`에서 문자열을 다시 쓰는 대신 `InputTransformation`을 사용한다. 이 변환은 사용자 입력 직후 적용되어 키보드와 입력창 사이의 동기화 문제를 줄인다.

## 이번 과제에 적용한 내용

- 로그인 2개, 회원가입 4개 입력창을 `TextFieldState`로 전환했다.
- 일반 입력창은 `TextFieldLineLimits.SingleLine`, 비밀번호 입력창은 한 줄 전용 `OutlinedSecureTextField`를 썼다.
- 각 칸의 IME 동작을 다음 또는 완료로 지정하고, 완료 시 포커스를 해제하고 키보드를 닫았다.
- 오류 문구는 `AnimatedContent`로 나타나고 사라지게 했다.

## 참고 자료

- [Android Developers: Configure text fields](https://developer.android.com/develop/ui/compose/text/user-input)
- [Android Developers: Migrate to state-based text fields](https://developer.android.com/develop/ui/compose/text/migrate-state-based)
