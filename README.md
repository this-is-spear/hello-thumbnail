# hello-thumbnail
썸네일을 추출하고 헤더 값으로 크기에 맞는 썸네일 조회 기능 구현

## 실행 방법

클론

```shell
git clone --recurse-submodules https://github.com/this-is-spear/hello-thumbnail
```

실행

```shell
./run.sh
```

pinpoint web 접속

```http request
GET http://localhost:8080
```

> pinpoint는 실행하고 최소 30초 동안 초기화하는 시간이 존재합니다.

zipkin web 접속

```http request
GET http://localhost:9411
```

## 테스트 방법

k6를 설치하면 원활하게 테스트 결과를 확인 할 수 있습니다.

```shell
brew install k6
```

```shell
./test.sh
```

## 제공하는 기능

🛠️ 수집 중
