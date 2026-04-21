# AssiduiteApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**activitesActiviteIdPresencesPost**](AssiduiteApi.md#activitesActiviteIdPresencesPost) | **POST** /activites/{activiteId}/presences | Enregistrer les présences pour une activité (Fonctionnalité F) |
| [**membresMembreIdAssiduiteGet**](AssiduiteApi.md#membresMembreIdAssiduiteGet) | **GET** /membres/{membreId}/assiduite | Taux d’assiduité d’un membre sur une période (Fonctionnalité F) |


<a id="activitesActiviteIdPresencesPost"></a>
# **activitesActiviteIdPresencesPost**
> activitesActiviteIdPresencesPost(activiteId, presence)

Enregistrer les présences pour une activité (Fonctionnalité F)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AssiduiteApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AssiduiteApi apiInstance = new AssiduiteApi(defaultClient);
    Long activiteId = 56L; // Long | 
    List<Presence> presence = Arrays.asList(); // List<Presence> | 
    try {
      apiInstance.activitesActiviteIdPresencesPost(activiteId, presence);
    } catch (ApiException e) {
      System.err.println("Exception when calling AssiduiteApi#activitesActiviteIdPresencesPost");
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
| **activiteId** | **Long**|  | |
| **presence** | [**List&lt;Presence&gt;**](Presence.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Présences enregistrées |  -  |

<a id="membresMembreIdAssiduiteGet"></a>
# **membresMembreIdAssiduiteGet**
> TauxAssiduite membresMembreIdAssiduiteGet(membreId, du, au)

Taux d’assiduité d’un membre sur une période (Fonctionnalité F)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AssiduiteApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AssiduiteApi apiInstance = new AssiduiteApi(defaultClient);
    Long membreId = 56L; // Long | 
    LocalDate du = LocalDate.now(); // LocalDate | 
    LocalDate au = LocalDate.now(); // LocalDate | 
    try {
      TauxAssiduite result = apiInstance.membresMembreIdAssiduiteGet(membreId, du, au);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AssiduiteApi#membresMembreIdAssiduiteGet");
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
| **membreId** | **Long**|  | |
| **du** | **LocalDate**|  | |
| **au** | **LocalDate**|  | |

### Return type

[**TauxAssiduite**](TauxAssiduite.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Taux d’assiduité |  -  |

