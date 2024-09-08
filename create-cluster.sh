#!/bin/sh

# Kubernetes 클러스터 초기화
echo "Kubernetes cluster 초기화 중..."
kind create cluster --config kind-config.yml

# 구분선 출력
echo -e "\n-----------------------------------------------------\n"

# NGINX Ingress 설치
echo "NGINX Ingress 설치 중..."
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml

# 구분선 출력
echo -e "\n-----------------------------------------------------\n"

# NGINX Ingress 준비 대기
echo "NGINX Ingress가 실행 준비 중..."
sleep 10

# NGINX Ingress 준비 상태 확인
kubectl wait --namespace ingress-nginx \
  --for=condition=ready pod \
  --selector=app.kubernetes.io/component=controller \
  --timeout=180s

# 완료 메시지
echo -e "\n"
echo "Happy Day!"
