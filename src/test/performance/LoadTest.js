import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '1m', target: 20 },
    { duration: '4m', target: 30 },
    { duration: '20s', target: 0 },
  ],
};

export default function () {
  const res = http.get('http://localhost:8080/');
  check(res, { 'status was 200': (r) => r.status == 200 });
  sleep(1);
}
