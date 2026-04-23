# CollectivitiesApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**assignCollectivityIdentity**](CollectivitiesApi.md#assignCollectivityIdentity) | **PUT** /collectivities/{id}/identity | Assign unique number and name to a collectivity |
| [**createCollectivities**](CollectivitiesApi.md#createCollectivities) | **POST** /collectivities | Create collectivities |


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
import org.openapitools.client.api.CollectivitiesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    CollectivitiesApi apiInstance = new CollectivitiesApi(defaultClient);
    String id = "123"; // String | Collectivity ID
    AssignIdentityRequest assignIdentityRequest = new AssignIdentityRequest(); // AssignIdentityRequest | 
    try {
      Collectivity result = apiInstance.assignCollectivityIdentity(id, assignIdentityRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectivitiesApi#assignCollectivityIdentity");
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

<a id="createCollectivities"></a>
# **createCollectivities**
> List&lt;Collectivity&gt; createCollectivities(createCollectivity)

Create collectivities

Creates one or more collectivities. During collectivity creation, member IDs are provided to identify existing members. The response includes full member information, not only their IDs. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CollectivitiesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    CollectivitiesApi apiInstance = new CollectivitiesApi(defaultClient);
    List<CreateCollectivity> createCollectivity = Arrays.asList(); // List<CreateCollectivity> | 
    try {
      List<Collectivity> result = apiInstance.createCollectivities(createCollectivity);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectivitiesApi#createCollectivities");
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
| **createCollectivity** | [**List&lt;CreateCollectivity&gt;**](CreateCollectivity.md)|  | |

### Return type

[**List&lt;Collectivity&gt;**](Collectivity.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Collectivities created successfully |  -  |
| **400** | Invalid request - missing federation approval or structure |  -  |
| **404** | One or more members not found |  -  |

