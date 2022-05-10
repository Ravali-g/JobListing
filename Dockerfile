FROM ubuntu:22.04
RUN apt update && apt install -y openjdk-18-jdk
COPY ./job-listing-service/build/libs/job-listing-service-1.0-SNAPSHOT.jar joblistingtarget.jar
ENTRYPOINT ["java","-jar","/joblistingtarget.jar", "JobListingApplicationHome"]
