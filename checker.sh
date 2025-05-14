#!/bin/bash

# URL base de tu servidor (ajusta el puerto si es necesario)
BASE_URL="http://localhost:8080"

# Lista de endpoints a testear
declare -A endpoints=(
["Admin ID 1"]="/admins/show/1"
["Patient ID 1"]="/patients/show/1"
["Schedule ID 1"]="/schedules/show/1"
)

echo "=== Testing endpoints ==="

for name in "${!endpoints[@]}"; do
	url="$BASE_URL${endpoints[$name]}"
	echo "-> Testing $name ($url)"

	http_code=$(curl -s -o /dev/null -w "%{http_code}" "$url")

	if [ "$http_code" -eq 200 ]; then
		echo "   ✅ OK (HTTP $http_code)"
	else
		echo "   ❌ FAIL (HTTP $http_code)"
	fi
done

curl -X POST \
	http://localhost:8080/users/login \
	-H 'Content-Type: application/json' \
	-d '{
	"name": "username",
	"password": "password",
	"user": "username"
}'

curl -X POST \
	http://localhost:8080/admins/register \
	-H 'Content-Type: application/json' \
	-d '{
	"name": "Admin Usuario",
	"user": "admin2",
	"password": "admin123"
}'

curl -X POST \
	http://localhost:8080/users/admin/login \
	-H 'Content-Type: application/json' \
	-d '{
	"user": "admin7",
	"password": "admin123"
}'


echo "=== Finished ==="

