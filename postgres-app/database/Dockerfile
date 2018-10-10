FROM postgres

ENV POSTGRES_USER root
ENV POSTGRES_PASSWORD root
ENV POSTGRES_DB database

COPY tabela.sql /docker-entrypoint-initdb.d/
