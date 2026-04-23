# TransactionsApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createTransaction**](TransactionsApi.md#createTransaction) | **POST** /transactions | Record a transaction |
| [**getCashFlow**](TransactionsApi.md#getCashFlow) | **GET** /transactions/cashflow/{collectivityId} | Get cash flow statement |


<a id="createTransaction"></a>
# **createTransaction**
> TransactionResponse createTransaction(createTransactionRequest)

Record a transaction

Records a financial transaction (contribution, payment, or refund). Updates the account balance automatically. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TransactionsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    TransactionsApi apiInstance = new TransactionsApi(defaultClient);
    CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest(); // CreateTransactionRequest | 
    try {
      TransactionResponse result = apiInstance.createTransaction(createTransactionRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TransactionsApi#createTransaction");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **createTransactionRequest** | [**CreateTransactionRequest**](CreateTransactionRequest.md)|  | |

### Return type

[**TransactionResponse**](TransactionResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Transaction recorded successfully |  -  |
| **400** | Invalid request |  -  |
| **404** | Account or member not found |  -  |

<a id="getCashFlow"></a>
# **getCashFlow**
> CashFlowResponse getCashFlow(collectivityId, from, to)

Get cash flow statement

Returns the cash flow statement for a collectivity over a period. Includes total income, total expenses, balance, and all transactions. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TransactionsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    TransactionsApi apiInstance = new TransactionsApi(defaultClient);
    Integer collectivityId = 56; // Integer | Collectivity ID
    LocalDate from = LocalDate.now(); // LocalDate | Start date (YYYY-MM-DD)
    LocalDate to = LocalDate.now(); // LocalDate | End date (YYYY-MM-DD)
    try {
      CashFlowResponse result = apiInstance.getCashFlow(collectivityId, from, to);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TransactionsApi#getCashFlow");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **collectivityId** | **Integer**| Collectivity ID | |
| **from** | **LocalDate**| Start date (YYYY-MM-DD) | |
| **to** | **LocalDate**| End date (YYYY-MM-DD) | |

### Return type

[**CashFlowResponse**](CashFlowResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Cash flow statement retrieved successfully |  -  |
| **404** | Collectivity not found |  -  |

