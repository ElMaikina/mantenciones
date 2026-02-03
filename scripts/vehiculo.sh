curl -X POST http://localhost:8080/vehiculos -H "Content-Type: application/json" -d '{"kilometros":1000,"patente":"BANA74","tipo":"Minibus"}' | jq '.'
