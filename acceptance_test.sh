#!/bin/bash
#test $(curl localhost:8765/sum?a=1\&b=2) -eq 3
CALCULATOR_PORT=$(sudo docker-compose port calculator 8080 | cut -d: -f2)
test $(curl localhost:$CALCULATOR_PORT/sum?a=1\&b=2) -eq 3
