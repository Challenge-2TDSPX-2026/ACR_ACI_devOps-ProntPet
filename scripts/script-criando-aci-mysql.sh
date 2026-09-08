```bash
#!/bin/bash

# ==========================================
# VARIÁVEIS
# ==========================================

# ALTERE PARA SEU RM
rm=rm566526

resourceGroup="rg-prontpet"
location="mexicocentral"

# ACR
acrName="prontpetrm566526"

# ACI
aciName="mysql-prontpet"

# Imagem do MySQL
imageName="prontpet-db"
tag="v2"

# Storage Account
storageAccountName="volumeprontpetdata$rm"
file_share_name="mysql-prontpet-volume"

# Key Vault
keyVaultName="keyvault-rm566526"

# Chave do Storage Account
storage_key=$(az storage account keys list \
  --resource-group $resourceGroup \
  --account-name $storageAccountName \
  --query "[0].value" \
  --output tsv)


# ==========================================
# REGISTRA O SERVIÇO DE ACI NA ASSINATURA
# ==========================================

az provider register \
  --namespace Microsoft.ContainerInstance


# ==========================================
# DEPLOY DO CONTAINER MYSQL
# ==========================================

az container create \
  --resource-group $resourceGroup \
  --name $aciName \
  --image $acrName.azurecr.io/$imageName:$tag \
  --location $location \
  --cpu 1 \
  --memory 1.5 \
  --os-type Linux \
  --dns-name-label mysql-prontpet-container-$rm \
  --ports 3306 \
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
  --azure-file-volume-account-name $storageAccountName \
  --azure-file-volume-account-key $storage_key \
  --azure-file-volume-share-name $file_share_name \
  --azure-file-volume-mount-path /var/lib/mysql \
  --environment-variables \
    MYSQL_ROOT_PASSWORD=$(az keyvault secret show \
      --vault-name $keyVaultName \
      --name mysql-root-password \
      --query value \
      -o tsv) \
    MYSQL_DATABASE=$(az keyvault secret show \
      --vault-name $keyVaultName \
      --name mysql-database \
      --query value \
      -o tsv) \
    MYSQL_USER=$(az keyvault secret show \
      --vault-name $keyVaultName \
      --name mysql-user \
      --query value \
      -o tsv) \
    MYSQL_PASSWORD=$(az keyvault secret show \
      --vault-name $keyVaultName \
      --name mysql-password \
      --query value \
      -o tsv) \
  --restart-policy Always


# ==========================================
# MOSTRAR INFORMAÇÕES DO MYSQL
# ==========================================

mysqlIP=$(az container show \
  --resource-group $resourceGroup \
  --name $aciName \
  --query ipAddress.ip \
  --output tsv)

mysqlFQDN=$(az container show \
  --resource-group $resourceGroup \
  --name $aciName \
  --query ipAddress.fqdn \
  --output tsv)

echo ""
echo "=========================================="
echo "MYSQL PRONTPET CRIADO"
echo "=========================================="
echo "IP: $mysqlIP"
echo "FQDN: $mysqlFQDN"
echo "Porta: 3306"
echo "=========================================="
```
