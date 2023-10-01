FROM bellsoft/liberica-openjdk-alpine:17.0.8

# workspace
WORKDIR /home/selenium-docker

# Add the required files
ADD target/docker-resources ./

# Run the tests
ENTRYPOINT java -cp 'libs/*' \
           -Dselenium.grid.enabled=true \
           -Dselenium.grid.hubHost=${HUB_HOST} \
           -Dbrowser=${BROWSER} \
           org.testng.TestNG \
           -threadcount ${THREAD_COUNT} \
           test-suites/${TEST_SUITE}
            


# "run this for docker image build" docker build -t=zarniag/selenium .
# "after upper" java -cp 'libs/*' org.testng.TestNG 
# java -cp 'libs/*' org.testng.TestNG test-suites/vendor-portal.xml
# docker run -it -v ${PWD}/result:/home/selenium-docker/test-output zarniag/selenium



#  mvn clean package -DskipTests 
# "run this for docker image build"  docker build -t=zarniag/selenium .
# docker run -it -v ${PWD}/result:/home/selenium-docker/test-output zarniag/selenium
# docker run -e BROWSER=firefox -e HUB_HOST=192.168.1.3 -e TEST_SUITE=flight-reservation.xml -e THREAD_COUNT=4 zarniag/selenium