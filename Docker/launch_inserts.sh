/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'Admin123' -i /opt/mssql-scripts/02.insert_users.sql &&
/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'Admin123' -i /opt/mssql-scripts/03.insert_dictPermission.sql &&
/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'Admin123' -i /opt/mssql-scripts/04.insert_usersPermission.sql &&
/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'Admin123' -i /opt/mssql-scripts/05.MovieModuleInserts.sql