#!/usr/bin/env bash

echo "
    Running.
    Application will be available in ~10 seconds at:

    http://127.0.0.1:8080/

    To cancel, press Ctrl-C.
"

java -jar build/libs/shopchallenge-1.0.jar > ./boot.log 2>&1
