#!/bin/sh

echo "Maven build"
echo ""
./mvnw clean install

echo ""
echo "Unpack fat jar"
echo ""
mkdir target/dependency
(cd target/dependency; jar -xf ../*.jar)

echo ""
echo "Docker build"
echo ""
docker build --no-cache -t io.geovaneshimizu/whatstune .

echo ""
echo "Build finished"
