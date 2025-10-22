#!/bin/bash

# Script de test pour l'API LogConsoleItem
# Usage: ./test_api.sh

BASE_URL="http://localhost:8080/api/log-console-items"

echo "=========================================="
echo "Test de l'API LogConsoleItem"
echo "=========================================="
echo ""

# Couleurs pour l'affichage
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Test 1: Créer un LogConsoleItem simple
echo -e "${YELLOW}Test 1: Création d'un LogConsoleItem simple${NC}"
RESPONSE=$(curl -s -w "\n%{http_code}" -X POST $BASE_URL \
  -H "Content-Type: application/json" \
  -d @create_simple.json)

HTTP_CODE=$(echo "$RESPONSE" | tail -n1)
BODY=$(echo "$RESPONSE" | sed '$d')

if [ "$HTTP_CODE" -eq 201 ]; then
    echo -e "${GREEN}✓ Création réussie (HTTP $HTTP_CODE)${NC}"
    ITEM_ID=$(echo $BODY | grep -o '"id":[0-9]*' | grep -o '[0-9]*' | head -1)
    echo "ID créé: $ITEM_ID"
else
    echo -e "${RED}✗ Échec de la création (HTTP $HTTP_CODE)${NC}"
fi
echo ""

# Test 2: Récupérer tous les items
echo -e "${YELLOW}Test 2: Récupération de tous les items${NC}"
RESPONSE=$(curl -s -w "\n%{http_code}" -X GET $BASE_URL)
HTTP_CODE=$(echo "$RESPONSE" | tail -n1)

if [ "$HTTP_CODE" -eq 200 ] || [ "$HTTP_CODE" -eq 204 ]; then
    echo -e "${GREEN}✓ Récupération réussie (HTTP $HTTP_CODE)${NC}"
else
    echo -e "${RED}✗ Échec de la récupération (HTTP $HTTP_CODE)${NC}"
fi
echo ""

# Test 3: Récupérer l'item créé par ID
if [ ! -z "$ITEM_ID" ]; then
    echo -e "${YELLOW}Test 3: Récupération de l'item ID=$ITEM_ID${NC}"
    RESPONSE=$(curl -s -w "\n%{http_code}" -X GET $BASE_URL/$ITEM_ID)
    HTTP_CODE=$(echo "$RESPONSE" | tail -n1)
    
    if [ "$HTTP_CODE" -eq 200 ]; then
        echo -e "${GREEN}✓ Récupération réussie (HTTP $HTTP_CODE)${NC}"
    else
        echo -e "${RED}✗ Échec de la récupération (HTTP $HTTP_CODE)${NC}"
    fi
    echo ""
fi

# Test 4: Créer un LogConsoleItem complet avec relations
echo -e "${YELLOW}Test 4: Création d'un LogConsoleItem complet${NC}"
RESPONSE=$(curl -s -w "\n%{http_code}" -X POST $BASE_URL \
  -H "Content-Type: application/json" \
  -d @create_complete.json)

HTTP_CODE=$(echo "$RESPONSE" | tail -n1)
BODY=$(echo "$RESPONSE" | sed '$d')

if [ "$HTTP_CODE" -eq 201 ]; then
    echo -e "${GREEN}✓ Création complète réussie (HTTP $HTTP_CODE)${NC}"
    ITEM_ID_2=$(echo $BODY | grep -o '"id":[0-9]*' | grep -o '[0-9]*' | head -1)
    echo "ID créé: $ITEM_ID_2"
else
    echo -e "${RED}✗ Échec de la création complète (HTTP $HTTP_CODE)${NC}"
fi
echo ""

# Test 5: Recherche par type d'entité
echo -e "${YELLOW}Test 5: Recherche par type d'entité (QUEU_IN)${NC}"
RESPONSE=$(curl -s -w "\n%{http_code}" -X GET "$BASE_URL/search/by-entity-type?type=QUEU_IN")
HTTP_CODE=$(echo "$RESPONSE" | tail -n1)

if [ "$HTTP_CODE" -eq 200 ] || [ "$HTTP_CODE" -eq 204 ]; then
    echo -e "${GREEN}✓ Recherche réussie (HTTP $HTTP_CODE)${NC}"
else
    echo -e "${RED}✗ Échec de la recherche (HTTP $HTTP_CODE)${NC}"
fi
echo ""

# Test 6: Recherche par référence de message
echo -e "${YELLOW}Test 6: Recherche par référence (MSG20241022001)${NC}"
RESPONSE=$(curl -s -w "\n%{http_code}" -X GET "$BASE_URL/search/by-reference?reference=MSG20241022001")
HTTP_CODE=$(echo "$RESPONSE" | tail -n1)

if [ "$HTTP_CODE" -eq 200 ] || [ "$HTTP_CODE" -eq 204 ]; then
    echo -e "${GREEN}✓ Recherche réussie (HTTP $HTTP_CODE)${NC}"
else
    echo -e "${RED}✗ Échec de la recherche (HTTP $HTTP_CODE)${NC}"
fi
echo ""

# Test 7: Recherche par type de message
echo -e "${YELLOW}Test 7: Recherche par type de message (PACS008)${NC}"
RESPONSE=$(curl -s -w "\n%{http_code}" -X GET "$BASE_URL/search/by-type-message?type=PACS008")
HTTP_CODE=$(echo "$RESPONSE" | tail -n1)

if [ "$HTTP_CODE" -eq 200 ] || [ "$HTTP_CODE" -eq 204 ]; then
    echo -e "${GREEN}✓ Recherche réussie (HTTP $HTTP_CODE)${NC}"
else
    echo -e "${RED}✗ Échec de la recherche (HTTP $HTTP_CODE)${NC}"
fi
echo ""

# Test 8: Recherche par date de session
echo -e "${YELLOW}Test 8: Recherche par date de session (2024-10-22)${NC}"
RESPONSE=$(curl -s -w "\n%{http_code}" -X GET "$BASE_URL/search/by-date-session?date=2024-10-22")
HTTP_CODE=$(echo "$RESPONSE" | tail -n1)

if [ "$HTTP_CODE" -eq 200 ] || [ "$HTTP_CODE" -eq 204 ]; then
    echo -e "${GREEN}✓ Recherche réussie (HTTP $HTTP_CODE)${NC}"
else
    echo -e "${RED}✗ Échec de la recherche (HTTP $HTTP_CODE)${NC}"
fi
echo ""

# Test 9: Recherche par code BIC
echo -e "${YELLOW}Test 9: Recherche par code BIC (BICFRCM01)${NC}"
RESPONSE=$(curl -s -w "\n%{http_code}" -X GET "$BASE_URL/search/by-bic?code=BICFRCM01")
HTTP_CODE=$(echo "$RESPONSE" | tail -n1)

if [ "$HTTP_CODE" -eq 200 ] || [ "$HTTP_CODE" -eq 204 ]; then
    echo -e "${GREEN}✓ Recherche réussie (HTTP $HTTP_CODE)${NC}"
else
    echo -e "${RED}✗ Échec de la recherche (HTTP $HTTP_CODE)${NC}"
fi
echo ""

# Test 10: Mettre à jour un item
if [ ! -z "$ITEM_ID" ]; then
    echo -e "${YELLOW}Test 10: Mise à jour de l'item ID=$ITEM_ID${NC}"
    RESPONSE=$(curl -s -w "\n%{http_code}" -X PUT $BASE_URL/$ITEM_ID \
      -H "Content-Type: application/json" \
      -d @update_example.json)
    HTTP_CODE=$(echo "$RESPONSE" | tail -n1)
    
    if [ "$HTTP_CODE" -eq 200 ]; then
        echo -e "${GREEN}✓ Mise à jour réussie (HTTP $HTTP_CODE)${NC}"
    else
        echo -e "${RED}✗ Échec de la mise à jour (HTTP $HTTP_CODE)${NC}"
    fi
    echo ""
fi

# Test 11: Créer un item avec erreur
echo -e "${YELLOW}Test 11: Création d'un item avec erreur${NC}"
RESPONSE=$(curl -s -w "\n%{http_code}" -X POST $BASE_URL \
  -H "Content-Type: application/json" \
  -d @create_error_example.json)

HTTP_CODE=$(echo "$RESPONSE" | tail -n1)
BODY=$(echo "$RESPONSE" | sed '$d')

if [ "$HTTP_CODE" -eq 201 ]; then
    echo -e "${GREEN}✓ Création d'erreur réussie (HTTP $HTTP_CODE)${NC}"
    ITEM_ID_3=$(echo $BODY | grep -o '"id":[0-9]*' | grep -o '[0-9]*' | head -1)
    echo "ID créé: $ITEM_ID_3"
else
    echo -e "${RED}✗ Échec de la création d'erreur (HTTP $HTTP_CODE)${NC}"
fi
echo ""

# Test 12: Supprimer un item
if [ ! -z "$ITEM_ID" ]; then
    echo -e "${YELLOW}Test 12: Suppression de l'item ID=$ITEM_ID${NC}"
    RESPONSE=$(curl -s -w "\n%{http_code}" -X DELETE $BASE_URL/$ITEM_ID)
    HTTP_CODE=$(echo "$RESPONSE" | tail -n1)
    
    if [ "$HTTP_CODE" -eq 204 ]; then
        echo -e "${GREEN}✓ Suppression réussie (HTTP $HTTP_CODE)${NC}"
    else
        echo -e "${RED}✗ Échec de la suppression (HTTP $HTTP_CODE)${NC}"
    fi
    echo ""
fi

# Test 13: Vérifier que l'item supprimé n'existe plus
if [ ! -z "$ITEM_ID" ]; then
    echo -e "${YELLOW}Test 13: Vérification de la suppression${NC}"
    RESPONSE=$(curl -s -w "\n%{http_code}" -X GET $BASE_URL/$ITEM_ID)
    HTTP_CODE=$(echo "$RESPONSE" | tail -n1)
    
    if [ "$HTTP_CODE" -eq 404 ]; then
        echo -e "${GREEN}✓ Item correctement supprimé (HTTP $HTTP_CODE)${NC}"
    else
        echo -e "${RED}✗ L'item existe encore (HTTP $HTTP_CODE)${NC}"
    fi
    echo ""
fi

echo "=========================================="
echo "Tests terminés"
echo "=========================================="
