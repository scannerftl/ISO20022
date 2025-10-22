@echo off
echo ========================================
echo Construction du projet Console Log API
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

echo [INFO] Nettoyage et compilation du projet...
echo.

REM Compiler le projet
mvn clean package -DskipTests

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo [SUCCES] Build termine avec succes!
    echo ========================================
    echo Le fichier JAR est disponible dans: target\console-log-api-1.0.0.jar
    echo.
    echo Pour demarrer l'application:
    echo   java -jar target\console-log-api-1.0.0.jar
    echo.
) else (
    echo.
    echo ========================================
    echo [ERREUR] Echec du build
    echo ========================================
)

pause
