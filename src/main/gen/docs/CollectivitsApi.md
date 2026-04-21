# CollectivitsApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**collectivitesPost**](CollectivitsApi.md#collectivitesPost) | **POST** /collectivites | Créer une nouvelle collectivité (Fonctionnalité A) |


<a id="collectivitesPost"></a>
# **collectivitesPost**
> Collectivite collectivitesPost(collectiviteCreation)

Créer une nouvelle collectivité (Fonctionnalité A)

Vérifie automatiquement les conditions (≥10 membres avec 6 mois d’ancienneté, postes occupés, etc.)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CollectivitsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    CollectivitsApi apiInstance = new CollectivitsApi(defaultClient);
    CollectiviteCreation collectiviteCreation = new CollectiviteCreation(); // CollectiviteCreation | 
    try {
      Collectivite result = apiInstance.collectivitesPost(collectiviteCreation);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectivitsApi#collectivitesPost");
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
| **collectiviteCreation** | [**CollectiviteCreation**](CollectiviteCreation.md)|  | |

### Return type

[**Collectivite**](Collectivite.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Collectivité créée avec succès |  -  |
| **400** | Conditions d’ouverture non remplies |  -  |

