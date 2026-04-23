

# AccountResponse


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **Integer** | Account ID |  [optional] |
|**entityType** | [**EntityTypeEnum**](#EntityTypeEnum) |  |  [optional] |
|**entityId** | **Integer** |  |  [optional] |
|**accountType** | [**AccountTypeEnum**](#AccountTypeEnum) |  |  [optional] |
|**accountName** | **String** |  |  [optional] |
|**accountHolderName** | **String** |  |  [optional] |
|**bankName** | **String** |  |  [optional] |
|**mobileMoneyService** | **String** |  |  [optional] |
|**phoneNumber** | **String** |  |  [optional] |
|**balance** | **BigDecimal** | Current balance in MGA |  [optional] |
|**currency** | **String** |  |  [optional] |



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



