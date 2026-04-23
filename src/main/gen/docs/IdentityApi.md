# IdentityApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**assignCollectivityIdentity**](IdentityApi.md#assignCollectivityIdentity) | **PUT** /collectivities/{id}/identity | Assign unique number and name to a collectivity |


<a id="assignCollectivityIdentity"></a>
# **assignCollectivityIdentity**
> Collectivity assignCollectivityIdentity(id, assignIdentityRequest)

Assign unique number and name to a collectivity

The federation assigns a unique number and a unique name to a collectivity. Once assigned, the number and name CANNOT be changed. This operation can only be performed once per collectivity. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.IdentityApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    IdentityApi apiInstance = new IdentityApi(defaultClient);
    String id = "123"; // String | Collectivity ID
    AssignIdentityRequest assignIdentityRequest = new AssignIdentityRequest(); // AssignIdentityRequest | 
    try {
      Collectivity result = apiInstance.assignCollectivityIdentity(id, assignIdentityRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IdentityApi#assignCollectivityIdentity");
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
| **id** | **String**| Collectivity ID | |
| **assignIdentityRequest** | [**AssignIdentityRequest**](AssignIdentityRequest.md)|  | |

### Return type

[**Collectivity**](Collectivity.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Identity assigned successfully |  -  |
| **400** | Invalid request - missing number or name |  -  |
| **404** | Collectivity not found |  -  |
| **409** | Conflict - Number or name already exists, or identity already assigned |  -  |

