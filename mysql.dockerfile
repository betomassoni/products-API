FROM mysql:5.7
MAINTAINER Roberto Massoni
COPY ./database/ /docker-entrypoint-initdb.d/