# AccountsApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createAccount**](AccountsApi.md#createAccount) | **POST** /accounts | Create a financial account |


<a id="createAccount"></a>
# **createAccount**
> AccountResponse createAccount(createAccountRequest)

Create a financial account

Creates a new account (CASH, BANK, or MOBILE_MONEY) for a collectivity or the federation. - A collectivity can have only one CASH account - Bank accounts must have a valid RIB format (23 digits) - Mobile money accounts require a valid phone number 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AccountsApi apiInstance = new AccountsApi(defaultClient);
    CreateAccountRequest createAccountRequest = new CreateAccountRequest(); // CreateAccountRequest | 
    try {
      AccountResponse result = apiInstance.createAccount(createAccountRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AccountsApi#createAccount");
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
| **createAccountRequest** | [**CreateAccountRequest**](CreateAccountRequest.md)|  | |

### Return type

[**AccountResponse**](AccountResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Account created successfully |  -  |
| **400** | Invalid request - validation error |  -  |
| **409** | Conflict - CASH account already exists for this entity |  -  |

