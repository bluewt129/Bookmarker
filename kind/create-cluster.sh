#!/bin/sh
 echo "Kubernetes cluster 초기화..."
 kind create cluster --config kind-config.yml
 echo "\n-----------------------------------------------------\n"
 echo NGINX Ingress 설치..."
 kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml
 echo "\n-----------------------------------------------------\n"
 echo "NGINX Ingress가 실행이 준비중..."
 sleep 10
 kubectl wait --namespace ingress-nginx \
  --for=condition=ready pod \
  --selector=app.kubernetes.io/component=controller \
  --timeout=180s
 echo "\n"