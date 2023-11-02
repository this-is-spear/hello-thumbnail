# hello-thumbnail
썸네일을 추출하고 헤더 값으로 크기에 맞는 썸네일 조회 기능 구현

## 실행 방법

```shell

클론

```shell
git clone --recurse-submodules https://github.com/this-is-spear/hello-thumbnail
```

루트 프로젝트 이동

```shell
cd /hello-thumbnail
```

빌드 파일 생성

```shell
./gradlew :thumbnail:clean :thumbnail:build
./gradlew :gateway:clean :gateway:build
```

도커 실행

```shell
docker compose -f docker-compose-local.yml -p hello-thumbnail up -d
```


## 제공하는 기능

🛠️ 수집 중
