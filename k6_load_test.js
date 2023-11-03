import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
    duration: '2m', // 2분 동안 테스트 실행
    vus: 1, // 동시에 실행할 가상 사용자의 수
};

let counter = 0; // 요청 횟수를 세는 카운터

function getRandomString(length) {
    const charset = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    let res = '';
    while (length--) res += charset[Math.floor(Math.random() * charset.length)];
    return res;
}

export default function () {
    const params = {
        timeout: '2000ms', // 2초 타임아웃 설정
    };

    counter++; // 카운터를 증가시킵니다.

    if (counter % 5 === 0) {
        const addRes = http.get(`http://localhost:9000/thumbnail/add?message=${getRandomString(10)}`, params);
        check(addRes, { 'add status was 200': (r) => r.status === 200 });
    }

    const findRes = http.get('http://localhost:9000/thumbnail/find', params);
    check(findRes, { 'find status was 200': (r) => r.status === 200 });

    const slowHelloRes = http.get('http://localhost:9000/slow-hello', params);
    check(slowHelloRes, { 'slow-hello status was 200': (r) => r.status === 200 });

    const noRes = http.get('http://localhost:9000/no', params);
    check(noRes, { 'no status was 200': (r) => r.status === 200 });

    const helloRes = http.get('http://localhost:9000/hello', params);
    check(helloRes, { 'hello status was 200': (r) => r.status === 200 });

    sleep(1); // 다른 요청 사이에 휴식
}
