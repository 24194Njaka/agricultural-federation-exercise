# AssiduitApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createPresence**](AssiduitApi.md#createPresence) | **POST** /collectivites/{collectiviteId}/activites/{activiteId}/presences | Enregistrer la présence à une activité |
| [**getPresences**](AssiduitApi.md#getPresences) | **GET** /collectivites/{collectiviteId}/activites/{activiteId}/presences | Obtenir la fiche de présence d&#39;une activité |
| [**updatePresence**](AssiduitApi.md#updatePresence) | **PUT** /collectivites/{collectiviteId}/activites/{activiteId}/presences/{membreId} | Mettre à jour la présence d&#39;un membre pour une activité |


<a id="createPresence"></a>
# **createPresence**
> FichePresence createPresence(collectiviteId, activiteId, presenceRequest)

Enregistrer la présence à une activité

Le secrétaire effectue la présence. - Un membre peut être marqué présent, absent ou excusé. - Les membres d&#39;autres collectivités peuvent être inscrits mais leur présence   n&#39;est pas comptabilisée dans leur taux d&#39;assiduité annuel. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AssiduitApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AssiduitApi apiInstance = new AssiduitApi(defaultClient);
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    UUID activiteId = UUID.randomUUID(); // UUID | Identifiant unique de l'activité
    PresenceRequest presenceRequest = new PresenceRequest(); // PresenceRequest | 
    try {
      FichePresence result = apiInstance.createPresence(collectiviteId, activiteId, presenceRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AssiduitApi#createPresence");
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
| **collectiviteId** | **UUID**| Identifiant unique de la collectivité | |
| **activiteId** | **UUID**| Identifiant unique de l&#39;activité | |
| **presenceRequest** | [**PresenceRequest**](PresenceRequest.md)|  | |

### Return type

[**FichePresence**](FichePresence.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Présence enregistrée |  -  |
| **404** |  |  -  |
| **422** |  |  -  |

<a id="getPresences"></a>
# **getPresences**
> FichePresence getPresences(collectiviteId, activiteId)

Obtenir la fiche de présence d&#39;une activité

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AssiduitApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AssiduitApi apiInstance = new AssiduitApi(defaultClient);
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    UUID activiteId = UUID.randomUUID(); // UUID | Identifiant unique de l'activité
    try {
      FichePresence result = apiInstance.getPresences(collectiviteId, activiteId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AssiduitApi#getPresences");
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
| **collectiviteId** | **UUID**| Identifiant unique de la collectivité | |
| **activiteId** | **UUID**| Identifiant unique de l&#39;activité | |

### Return type

[**FichePresence**](FichePresence.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Fiche de présence |  -  |
| **404** |  |  -  |

<a id="updatePresence"></a>
# **updatePresence**
> PresenceDetail updatePresence(collectiviteId, activiteId, membreId, presenceUpdateRequest)

Mettre à jour la présence d&#39;un membre pour une activité

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AssiduitApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    AssiduitApi apiInstance = new AssiduitApi(defaultClient);
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    UUID activiteId = UUID.randomUUID(); // UUID | Identifiant unique de l'activité
    UUID membreId = UUID.randomUUID(); // UUID | Identifiant unique du membre
    PresenceUpdateRequest presenceUpdateRequest = new PresenceUpdateRequest(); // PresenceUpdateRequest | 
    try {
      PresenceDetail result = apiInstance.updatePresence(collectiviteId, activiteId, membreId, presenceUpdateRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AssiduitApi#updatePresence");
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
| **collectiviteId** | **UUID**| Identifiant unique de la collectivité | |
| **activiteId** | **UUID**| Identifiant unique de l&#39;activité | |
| **membreId** | **UUID**| Identifiant unique du membre | |
| **presenceUpdateRequest** | [**PresenceUpdateRequest**](PresenceUpdateRequest.md)|  | |

### Return type

[**PresenceDetail**](PresenceDetail.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Présence mise à jour |  -  |
| **404** |  |  -  |

