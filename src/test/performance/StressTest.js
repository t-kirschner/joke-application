import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '1m', target: 50 },
    { duration: '5m', target: 50 },
    { duration: '1m', target: 0 },
  ],
};

export default function () {
  const data = Math.random() < 0.5 ? '1' : '2';
  const res = http.post('http://localhost:8080/', data);
  check(res, { 'status was 200': (r) => r.status == 200 });
  sleep(1);
}

