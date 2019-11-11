# Dockerfile
Docker setup for database
* Username: SA
* Password: Admin123
## How to build
Run 
```bash
docker build -t mssqlcinema .
```
## How to run
```bash
docker run --name mssqlcinema -p 1433:1433 -d mssqlcinema
```