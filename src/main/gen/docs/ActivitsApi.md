# ActivitsApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**activitesPost**](ActivitsApi.md#activitesPost) | **POST** /activites | Créer une activité (obligatoire ou exceptionnelle) – Collectivité ou Fédération (Fonctionnalité E) |
| [**collectivitesCollectiviteIdActivitesGet**](ActivitsApi.md#collectivitesCollectiviteIdActivitesGet) | **GET** /collectivites/{collectiviteId}/activites | Lister les activités d’une collectivité (Fonctionnalité E) |


<a id="activitesPost"></a>
# **activitesPost**
> Activite activitesPost(activiteCreation)

Créer une activité (obligatoire ou exceptionnelle) – Collectivité ou Fédération (Fonctionnalité E)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ActivitsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    ActivitsApi apiInstance = new ActivitsApi(defaultClient);
    ActiviteCreation activiteCreation = new ActiviteCreation(); // ActiviteCreation | 
    try {
      Activite result = apiInstance.activitesPost(activiteCreation);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ActivitsApi#activitesPost");
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
| **activiteCreation** | [**ActiviteCreation**](ActiviteCreation.md)|  | |

### Return type

[**Activite**](Activite.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Activité créée |  -  |

<a id="collectivitesCollectiviteIdActivitesGet"></a>
# **collectivitesCollectiviteIdActivitesGet**
> List&lt;Activite&gt; collectivitesCollectiviteIdActivitesGet(collectiviteId)

Lister les activités d’une collectivité (Fonctionnalité E)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ActivitsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    ActivitsApi apiInstance = new ActivitsApi(defaultClient);
    Long collectiviteId = 56L; // Long | 
    try {
      List<Activite> result = apiInstance.collectivitesCollectiviteIdActivitesGet(collectiviteId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ActivitsApi#collectivitesCollectiviteIdActivitesGet");
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

### Return type

[**List&lt;Activite&gt;**](Activite.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des activités |  -  |

