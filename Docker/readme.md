# Dockerfile
Docker setup for database
* Username: SA
* Password: Admin123
## How to build
Run 
```bash
docker build -t mssqlcinema . --no-cache
```
## How to run
```bash
docker run --name mssqlcinema -p 1433:1433 -d mssqlcinema
```

## Configure hibernate

Copy hibernate.cfg.xml to src\main\resources or run in root of project

Windows
```cmd
copy Docker\hibernate.cfg.xml Cinema\src\main\resources
```

Linux
```bash
cp Docker/hibernate.cfg.xml Cinema/src/main/resources
```