#!/usr/bin/env bash
docker build . -t dockerregistry.dev.embl.org/PersonManagement:latest --build-arg JAR_PATH="build/libs" --no-cache
