

# TransactionResponse


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **Integer** |  |  [optional] |
|**accountId** | **Integer** |  |  [optional] |
|**memberId** | **Integer** |  |  [optional] |
|**memberName** | **String** |  |  [optional] |
|**transactionType** | [**TransactionTypeEnum**](#TransactionTypeEnum) |  |  [optional] |
|**amount** | **BigDecimal** |  |  [optional] |
|**paymentMethod** | [**PaymentMethodEnum**](#PaymentMethodEnum) |  |  [optional] |
|**transactionDate** | **LocalDate** |  |  [optional] |
|**description** | **String** |  |  [optional] |



## Enum: TransactionTypeEnum

| Name | Value |
|---- | -----|
| CONTRIBUTION | &quot;CONTRIBUTION&quot; |
| PAYMENT | &quot;PAYMENT&quot; |
| REFUND | &quot;REFUND&quot; |



## Enum: PaymentMethodEnum

| Name | Value |
|---- | -----|
| CASH | &quot;CASH&quot; |
| BANK_TRANSFER | &quot;BANK_TRANSFER&quot; |
| MOBILE_MONEY | &quot;MOBILE_MONEY&quot; |



