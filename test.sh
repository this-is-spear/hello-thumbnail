#!/bin/bash
# run_k6_test.sh

# K6 스크립트 파일 이름을 변수에 저장합니다.
K6_SCRIPT="k6_load_test.js"

# 스크립트 실행 권한을 확인하고, k6 스크립트를 실행합니다.
if [ -x "$(command -v k6)" ]; then
    if [ -f "$K6_SCRIPT" ]; then
        echo "Running k6 test script: $K6_SCRIPT"
        k6 run $K6_SCRIPT
    else
        echo "Error: Script file not found: $K6_SCRIPT"
        exit 1
    fi
else
    echo "Error: k6 is not installed or not in the PATH."
    exit 1
fi
