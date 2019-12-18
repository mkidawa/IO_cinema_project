/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'Admin123' -i /opt/mssql-scripts/02.insert_users.sql &&
/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'Admin123' -i /opt/mssql-scripts/03.insert_dictPermission.sql &&
/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'Admin123' -i /opt/mssql-scripts/04.insert_usersPermission.sql &&
/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'Admin123' -i /opt/mssql-scripts/05.MovieModuleInserts.sql &&
/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'Admin123' -i /opt/mssql-scripts/06.insert_HallData.sql &&
/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'Admin123' -i /opt/mssql-scripts/07.insert_DICTReservationStatus.sql &&
/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'Admin123' -i /opt/mssql-scripts/08.insert_performance_timetable_data.sql &&
/opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P 'Admin123' -i /opt/mssql-scripts/09.insert_products.sql