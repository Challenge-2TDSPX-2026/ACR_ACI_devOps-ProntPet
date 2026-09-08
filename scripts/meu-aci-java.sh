# ==========================================
# VARIÁVEIS
# ==========================================

# ALTERE PARA SEU RM
rm=rm566526

location="mexicocentral"
resourceGroup="rg-prontpet"

# ACR
acrName="prontpetrm566526"

# ACI
aciName="api-prontpet"
aciNameMysql="mysql-prontpet"

# Imagem da API
imageName="prontpet-api"
tag="v2"

# Key Vault
keyVaultName="keyvault-rm566526"

# IP público do MySQL
mysqlPublicIP=$(az container show \
  --resource-group $resourceGroup \
  --name $aciNameMysql \
  --query ipAddress.ip \
  --output tsv)

echo "MySQL IP: $mysqlPublicIP"


# ==========================================
# REGISTRA O SERVIÇO DE ACI NA ASSINATURA
# ==========================================

az provider register \
  --namespace Microsoft.ContainerInstance


# ==========================================
# DEPLOY DO CONTAINER API JAVA
# ==========================================

az container create \
  --resource-group $resourceGroup \
  --name $aciName \
  --image $acrName.azurecr.io/$imageName:$tag \
  --location $location \
  --cpu 1 \
  --memory 1 \
  --os-type Linux \
  --dns-name-label api-prontpet-container-$rm \
  --ports 8080 \
  --registry-login-server $acrName.azurecr.io \
  --registry-username $(az keyvault secret show \
    --vault-name $keyVaultName \
    --name acr-username \
    --query value \
    -o tsv) \
  --registry-password $(az keyvault secret show \
    --vault-name $keyVaultName \
    --name acr-password \
    --query value \
    -o tsv) \
  --environment-variables \
  DB_URL=$(az keyvault secret show \
    --name spring-datasource-url \
    --vault-name $keyVaultName \
    --query value \
    -o tsv | sed "s/mysql-prontpet/$mysqlPublicIP/") \
  DB_USERNAME=$(az keyvault secret show \
    --name spring-datasource-username \
    --vault-name $keyVaultName \
    --query value \
    -o tsv) \
  DB_PASSWORD=$(az keyvault secret show \
    --name spring-datasource-password \
    --vault-name $keyVaultName \
    --query value \
    -o tsv) \
  --restart-policy Always


# ==========================================
# TESTAR API
# ==========================================

fqdnJava=$(az container show \
  --resource-group $resourceGroup \
  --name $aciName \
  --query ipAddress.fqdn \
  --output tsv)

echo ""
echo "=========================================="
echo "API PRONTPET CRIADA"
echo "=========================================="
echo "FQDN: $fqdnJava"
echo "URL: http://$fqdnJava:8080"
echo "=========================================="