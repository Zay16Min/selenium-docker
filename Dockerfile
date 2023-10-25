FROM bellsoft/liberica-openjdk-alpine:17.0.8

# Install curl jq
RUN apk add curl jq

# workspace
WORKDIR /home/selenium-docker

# Add the required files
ADD target/docker-resources     ./
ADD runner.sh                   runner.sh

# Start the runner.sh
ENTRYPOINT sh runner.sh

# maven clean before docker build 'mvn clean package -DskipTests'
# firstly create docker image by 'docker build -t=zarniaung7/selenium .'