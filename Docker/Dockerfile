FROM microsoft/mssql-server-linux:latest

# create directory within SQL container for database files
RUN mkdir -p /opt/mssql-scripts

# copy the database files from host to container
COPY setup.sql /opt/mssql-scripts

# set environment variables
ENV MSSQL_SA_PASSWORD=Admin123
ENV ACCEPT_EULA=Y

EXPOSE 1433

# run initial scripts
RUN ( /opt/mssql/bin/sqlservr --accept-eula & ) | grep -q "Service Broker manager has started" \
    && /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'Admin123' -i /opt/mssql-scripts/setup.sql \
    && pkill sqlservr 