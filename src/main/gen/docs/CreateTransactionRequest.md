

# CreateTransactionRequest


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**accountId** | **Integer** | Account ID |  |
|**memberId** | **Integer** | Member ID making the transaction |  |
|**transactionType** | [**TransactionTypeEnum**](#TransactionTypeEnum) | Type of transaction |  |
|**amount** | **BigDecimal** | Amount in MGA |  |
|**paymentMethod** | [**PaymentMethodEnum**](#PaymentMethodEnum) | Payment method used |  |
|**transactionDate** | **LocalDate** | Date of the transaction |  |
|**description** | **String** | Optional description of the transaction |  [optional] |



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



