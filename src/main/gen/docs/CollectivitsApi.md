# CollectivitsApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createCollectivite**](CollectivitsApi.md#createCollectivite) | **POST** /collectivites | Demander l&#39;ouverture d&#39;une nouvelle collectivité |
| [**decideAutorisationCollectivite**](CollectivitsApi.md#decideAutorisationCollectivite) | **POST** /collectivites/{collectiviteId}/autorisation | Accorder ou refuser l&#39;autorisation d&#39;ouverture d&#39;une collectivité |
| [**getCollectivite**](CollectivitsApi.md#getCollectivite) | **GET** /collectivites/{collectiviteId} | Obtenir les détails d&#39;une collectivité |
| [**listCollectivites**](CollectivitsApi.md#listCollectivites) | **GET** /collectivites | Lister toutes les collectivités |
| [**updateCollectivite**](CollectivitsApi.md#updateCollectivite) | **PUT** /collectivites/{collectiviteId} | Mettre à jour une collectivité |


<a id="createCollectivite"></a>
# **createCollectivite**
> Collectivite createCollectivite(collectiviteCreationRequest)

Demander l&#39;ouverture d&#39;une nouvelle collectivité

Soumet une demande d&#39;ouverture. Conditions requises : - Au moins 10 membres dont 5 avec plus de 6 mois d&#39;ancienneté dans la fédération - Postes spécifiques (président, président adjoint, trésorier, secrétaire) pourvus - Autorisation formelle de la fédération - Ville et spécialité agricole définies 

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
    CollectiviteCreationRequest collectiviteCreationRequest = new CollectiviteCreationRequest(); // CollectiviteCreationRequest | 
    try {
      Collectivite result = apiInstance.createCollectivite(collectiviteCreationRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectivitsApi#createCollectivite");
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
| **collectiviteCreationRequest** | [**CollectiviteCreationRequest**](CollectiviteCreationRequest.md)|  | |

### Return type

[**Collectivite**](Collectivite.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Collectivité créée avec succès |  -  |
| **400** |  |  -  |
| **422** |  |  -  |

<a id="decideAutorisationCollectivite"></a>
# **decideAutorisationCollectivite**
> Collectivite decideAutorisationCollectivite(collectiviteId, autorisationDecision)

Accorder ou refuser l&#39;autorisation d&#39;ouverture d&#39;une collectivité

Endpoint réservé à la fédération pour valider ou rejeter une demande d&#39;ouverture.

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
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    AutorisationDecision autorisationDecision = new AutorisationDecision(); // AutorisationDecision | 
    try {
      Collectivite result = apiInstance.decideAutorisationCollectivite(collectiviteId, autorisationDecision);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectivitsApi#decideAutorisationCollectivite");
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
| **autorisationDecision** | [**AutorisationDecision**](AutorisationDecision.md)|  | |

### Return type

[**Collectivite**](Collectivite.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Décision enregistrée |  -  |
| **404** |  |  -  |

<a id="getCollectivite"></a>
# **getCollectivite**
> Collectivite getCollectivite(collectiviteId)

Obtenir les détails d&#39;une collectivité

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
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    try {
      Collectivite result = apiInstance.getCollectivite(collectiviteId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectivitsApi#getCollectivite");
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

### Return type

[**Collectivite**](Collectivite.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Détails de la collectivité |  -  |
| **404** |  |  -  |

<a id="listCollectivites"></a>
# **listCollectivites**
> CollectivitePage listCollectivites(page, size, ville, specialite)

Lister toutes les collectivités

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
    Integer page = 0; // Integer | 
    Integer size = 20; // Integer | 
    String ville = "ville_example"; // String | Filtrer par ville
    String specialite = "specialite_example"; // String | Filtrer par spécialité agricole
    try {
      CollectivitePage result = apiInstance.listCollectivites(page, size, ville, specialite);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectivitsApi#listCollectivites");
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
| **page** | **Integer**|  | [optional] [default to 0] |
| **size** | **Integer**|  | [optional] [default to 20] |
| **ville** | **String**| Filtrer par ville | [optional] |
| **specialite** | **String**| Filtrer par spécialité agricole | [optional] |

### Return type

[**CollectivitePage**](CollectivitePage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des collectivités |  -  |

<a id="updateCollectivite"></a>
# **updateCollectivite**
> Collectivite updateCollectivite(collectiviteId, collectiviteUpdateRequest)

Mettre à jour une collectivité

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
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    CollectiviteUpdateRequest collectiviteUpdateRequest = new CollectiviteUpdateRequest(); // CollectiviteUpdateRequest | 
    try {
      Collectivite result = apiInstance.updateCollectivite(collectiviteId, collectiviteUpdateRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CollectivitsApi#updateCollectivite");
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
| **collectiviteUpdateRequest** | [**CollectiviteUpdateRequest**](CollectiviteUpdateRequest.md)|  | |

### Return type

[**Collectivite**](Collectivite.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Collectivité mise à jour |  -  |
| **404** |  |  -  |

