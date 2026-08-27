@echo off
setlocal

set "PROJECT_DIR=%~dp0"

echo Starting PostgreSQL...
docker compose -f "%PROJECT_DIR%docker-compose.yml" up -d postgres
if errorlevel 1 (
    echo Failed to start PostgreSQL. Make sure Docker Desktop is running.
    exit /b 1
)

echo Starting backend...
start "JobTracker Backend" cmd /k "cd /d "%PROJECT_DIR%backend" ^&^& mvn spring-boot:run"

echo Starting frontend...
start "JobTracker Frontend" cmd /k "cd /d "%PROJECT_DIR%frontend" ^&^& npm run dev"

echo.
echo JobTracker is starting.
echo Backend:  http://localhost:8080
echo Frontend: http://localhost:5173
echo API:      http://localhost:8080/api/applications
echo.
echo Close the backend and frontend windows to stop those services.
echo Run "docker compose down" from this folder to stop PostgreSQL.

endlocal