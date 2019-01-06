#!/bin/bash
cd app && npm install
cd ..
cd app && npm run-script build --prod

echo "==APP:OK"