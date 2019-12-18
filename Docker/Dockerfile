FROM microsoft/mssql-server-linux:latest

# create directory within SQL container for database files
RUN mkdir -p /opt/mssql-scripts

# copy the database files from host to container
COPY *.sql /opt/mssql-scripts/
COPY launch_inserts.sh /opt/mssql-scripts
RUN chmod +x  /opt/mssql-scripts/launch_inserts.sh

# set environment variables
ENV MSSQL_SA_PASSWORD=Admin123
ENV ACCEPT_EULA=Y
# expose port
EXPOSE 1433

# run initial scripts
RUN ( /opt/mssql/bin/sqlservr -t272 --accept-eula & ) | grep -q "Service Broker manager has started" \
    && /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'Admin123' -i /opt/mssql-scripts/setup.sql \
    && /opt/mssql-scripts/launch_inserts.sh \
    && pkill sqlservr 