FROM mysql:8.4


ENV MYSQL_DATABASE=prontpet

COPY script_bd.sql /docker-entrypoint-initdb.d/script_bd.sql
COPY db.cnf /etc/mysql/conf.d/db.cnf

EXPOSE 3306
