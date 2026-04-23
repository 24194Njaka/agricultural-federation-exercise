

# CreateAccountRequest


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**entityType** | [**EntityTypeEnum**](#EntityTypeEnum) | Type of entity owning the account |  |
|**entityId** | **Integer** | ID of the entity |  |
|**accountType** | [**AccountTypeEnum**](#AccountTypeEnum) | Type of account |  |
|**accountName** | **String** | Name of the account |  |
|**accountHolderName** | **String** | Name of the account holder |  |
|**bankName** | [**BankNameEnum**](#BankNameEnum) | Bank name (for BANK accounts) |  [optional] |
|**bankCode** | **String** | Bank code (5 digits) |  [optional] |
|**branchCode** | **String** | Branch code (5 digits) |  [optional] |
|**accountNumber** | **String** | Account number (11 digits) |  [optional] |
|**ribKey** | **String** | RIB key (2 digits) |  [optional] |
|**mobileMoneyService** | [**MobileMoneyServiceEnum**](#MobileMoneyServiceEnum) | Mobile money service (for MOBILE_MONEY accounts) |  [optional] |
|**phoneNumber** | **String** | Phone number (10 digits) |  [optional] |



## Enum: EntityTypeEnum

| Name | Value |
|---- | -----|
| COLLECTIVITY | &quot;COLLECTIVITY&quot; |
| FEDERATION | &quot;FEDERATION&quot; |



## Enum: AccountTypeEnum

| Name | Value |
|---- | -----|
| CASH | &quot;CASH&quot; |
| BANK | &quot;BANK&quot; |
| MOBILE_MONEY | &quot;MOBILE_MONEY&quot; |



## Enum: BankNameEnum

| Name | Value |
|---- | -----|
| BRED | &quot;BRED&quot; |
| MCB | &quot;MCB&quot; |
| BMOI | &quot;BMOI&quot; |
| BOA | &quot;BOA&quot; |
| BGFI | &quot;BGFI&quot; |
| AFG | &quot;AFG&quot; |
| ACCES_BANQUE | &quot;ACCES_BANQUE&quot; |
| BAOBAB | &quot;BAOBAB&quot; |
| SIPEM | &quot;SIPEM&quot; |



## Enum: MobileMoneyServiceEnum

| Name | Value |
|---- | -----|
| ORANGE_MONEY | &quot;ORANGE_MONEY&quot; |
| MVOLA | &quot;MVOLA&quot; |
| AIRTEL_MONEY | &quot;AIRTEL_MONEY&quot; |



