From alpine
RUN apk add openjdk17
ENV PATH $PATH:/usr/lib/jvm/java-17-openjdk/bin
WORKDIR /usr/share/udemy
ADD Test.java Test.java
ENTRYPOINT javac Test.java && java Test $NUMBER
#docker build -t=zarniaung7/squares .
#docker run -e NUMBER=${NUMBER} zarniaung7/squares