@echo off
echo ========================================
echo Demarrage de Console Log API
echo ========================================
echo.

REM Verifier si Maven est installe
where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo [ERREUR] Maven n'est pas installe ou n'est pas dans le PATH
    echo Veuillez installer Maven: https://maven.apache.org/download.cgi
    pause
    exit /b 1
)

echo [INFO] Compilation et demarrage de l'application...
echo.

REM Demarrer l'application Spring Boot
mvn spring-boot:run

pause
