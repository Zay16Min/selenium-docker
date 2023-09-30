FROM bellsoft/liberica-openjdk-alpine:17.0.8

# workspace
WORKDIR /home/selenium-docker

# Add the required files
ADD target/docker-resources ./

# Run the tests
# "run this for docker image build" docker build -t=zarniag/selenium .
# "after upper" java -cp 'libs/*' org.testng.TestNG 
# java -cp 'libs/*' org.testng.TestNG test-suites/vendor-portal.xml
# docker run -it -v ${PWD}/result:/home/selenium-docker/test-output zarniag/selenium