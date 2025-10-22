@echo off
REM Script de test pour l'API LogConsoleItem (Windows)
REM Usage: test_api.bat

setlocal enabledelayedexpansion

set BASE_URL=http://localhost:8080/api/log-console-items

echo ==========================================
echo Test de l'API LogConsoleItem
echo ==========================================
echo.

REM Test 1: Créer un LogConsoleItem simple
echo Test 1: Création d'un LogConsoleItem simple
curl -X POST %BASE_URL% -H "Content-Type: application/json" -d @create_simple.json
echo.
echo.

REM Test 2: Récupérer tous les items
echo Test 2: Récupération de tous les items
curl -X GET %BASE_URL%
echo.
echo.

REM Test 3: Créer un LogConsoleItem complet avec relations
echo Test 3: Création d'un LogConsoleItem complet
curl -X POST %BASE_URL% -H "Content-Type: application/json" -d @create_complete.json
echo.
echo.

REM Test 4: Récupérer un item par ID (remplacer 1 par l'ID réel)
echo Test 4: Récupération de l'item ID=1
curl -X GET %BASE_URL%/1
echo.
echo.

REM Test 5: Recherche par type d'entité
echo Test 5: Recherche par type d'entité (QUEU_IN)
curl -X GET "%BASE_URL%/search/by-entity-type?type=QUEU_IN"
echo.
echo.

REM Test 6: Recherche par référence de message
echo Test 6: Recherche par référence (MSG20241022001)
curl -X GET "%BASE_URL%/search/by-reference?reference=MSG20241022001"
echo.
echo.

REM Test 7: Recherche par type de message
echo Test 7: Recherche par type de message (PACS008)
curl -X GET "%BASE_URL%/search/by-type-message?type=PACS008"
echo.
echo.

REM Test 8: Recherche par date de session
echo Test 8: Recherche par date de session (2024-10-22)
curl -X GET "%BASE_URL%/search/by-date-session?date=2024-10-22"
echo.
echo.

REM Test 9: Recherche par code BIC
echo Test 9: Recherche par code BIC (BICFRCM01)
curl -X GET "%BASE_URL%/search/by-bic?code=BICFRCM01"
echo.
echo.

REM Test 10: Mettre à jour un item (remplacer 1 par l'ID réel)
echo Test 10: Mise à jour de l'item ID=1
curl -X PUT %BASE_URL%/1 -H "Content-Type: application/json" -d @update_example.json
echo.
echo.

REM Test 11: Créer un item avec erreur
echo Test 11: Création d'un item avec erreur
curl -X POST %BASE_URL% -H "Content-Type: application/json" -d @create_error_example.json
echo.
echo.

REM Test 12: Supprimer un item (remplacer 1 par l'ID réel)
echo Test 12: Suppression de l'item ID=1
curl -X DELETE %BASE_URL%/1
echo.
echo.

echo ==========================================
echo Tests terminés
echo ==========================================
pause
