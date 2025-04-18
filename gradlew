#!/bin/bash
export JAVA_HOME=${JAVA_HOME:-/usr/lib/jvm/java-11-openjdk-amd64}
DIR="$(cd "$(dirname "$0")" && pwd)"
exec "${DIR}/gradle/wrapper/gradle-wrapper.jar" "$@"