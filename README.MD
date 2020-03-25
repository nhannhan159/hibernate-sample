Step to start
- Step 1: Run alibaba-broker-server from https://github.com/alibaba/alibaba-rsocket-broker . Do not run by docker-compose because it's outdated.
- Step 2 (Optional): Start openzipkin for tracing.
- Step 3: Start weather-app-data
- Step 4: Start weather-app-web

Endpoint to test: http://localhost:7001/api/cities