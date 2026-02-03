curl -X POST http://localhost:8080/vehiculos/3/mantenciones -H "Content-Type: application/json" -d '{"fecha":"2026-01-01","kilometros":69}' | jq '.'
