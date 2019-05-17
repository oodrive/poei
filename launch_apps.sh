#!/bin/bash

set -e

function build() {
    local path=$1
    pushd $path
    mvn clean package
    popd
}

build poei-file-storage &
build poei-password-manager &
build poei-secured-box &
build poei-sample &
wait

docker-compose up --build
