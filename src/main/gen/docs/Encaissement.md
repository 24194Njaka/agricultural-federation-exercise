

# Encaissement


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **Long** |  |  [optional] |
|**membreId** | **Long** |  |  |
|**collectiviteId** | **Long** |  |  |
|**montant** | **BigDecimal** |  |  |
|**dateEncaissement** | **OffsetDateTime** |  |  |
|**modePaiement** | [**ModePaiementEnum**](#ModePaiementEnum) |  |  |
|**type** | [**TypeEnum**](#TypeEnum) |  |  [optional] |



## Enum: ModePaiementEnum

| Name | Value |
|---- | -----|
| ESPECES | &quot;ESPECES&quot; |
| VIREMENT_BANCAIRE | &quot;VIREMENT_BANCAIRE&quot; |
| MOBILE_MONEY | &quot;MOBILE_MONEY&quot; |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| PERIODIQUE | &quot;PERIODIQUE&quot; |
| PONCTUELLE | &quot;PONCTUELLE&quot; |



