# StatistiquesApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**collectivitesCollectiviteIdStatistiquesGet**](StatistiquesApi.md#collectivitesCollectiviteIdStatistiquesGet) | **GET** /collectivites/{collectiviteId}/statistiques | Rapport mensuel complet (Fonctionnalité G) |


<a id="collectivitesCollectiviteIdStatistiquesGet"></a>
# **collectivitesCollectiviteIdStatistiquesGet**
> StatistiquesCollectivite collectivitesCollectiviteIdStatistiquesGet(collectiviteId, mois)

Rapport mensuel complet (Fonctionnalité G)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.StatistiquesApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    StatistiquesApi apiInstance = new StatistiquesApi(defaultClient);
    Long collectiviteId = 56L; // Long | 
    LocalDate mois = LocalDate.parse("2026-04"); // LocalDate | 
    try {
      StatistiquesCollectivite result = apiInstance.collectivitesCollectiviteIdStatistiquesGet(collectiviteId, mois);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatistiquesApi#collectivitesCollectiviteIdStatistiquesGet");
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
| **collectiviteId** | **Long**|  | |
| **mois** | **LocalDate**|  | |

### Return type

[**StatistiquesCollectivite**](StatistiquesCollectivite.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Statistiques globales et par membre |  -  |

