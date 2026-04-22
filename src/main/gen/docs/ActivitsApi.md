# ActivitsApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createActiviteCollectivite**](ActivitsApi.md#createActiviteCollectivite) | **POST** /collectivites/{collectiviteId}/activites | Créer une activité pour une collectivité |
| [**createActiviteFederation**](ActivitsApi.md#createActiviteFederation) | **POST** /federation/activites | Créer une activité de la fédération (invitation des collectivités) |
| [**getActiviteCollectivite**](ActivitsApi.md#getActiviteCollectivite) | **GET** /collectivites/{collectiviteId}/activites/{activiteId} | Obtenir les détails d&#39;une activité |
| [**getActiviteFederation**](ActivitsApi.md#getActiviteFederation) | **GET** /federation/activites/{activiteId} | Obtenir les détails d&#39;une activité de la fédération |
| [**listActivitesCollectivite**](ActivitsApi.md#listActivitesCollectivite) | **GET** /collectivites/{collectiviteId}/activites | Lister les activités d&#39;une collectivité |
| [**listActivitesFederation**](ActivitsApi.md#listActivitesFederation) | **GET** /federation/activites | Lister les activités de la fédération |
| [**updateActiviteCollectivite**](ActivitsApi.md#updateActiviteCollectivite) | **PUT** /collectivites/{collectiviteId}/activites/{activiteId} | Mettre à jour une activité |


<a id="createActiviteCollectivite"></a>
# **createActiviteCollectivite**
> Activite createActiviteCollectivite(collectiviteId, activiteRequest)

Créer une activité pour une collectivité

Types d&#39;activités : - ASSEMBLEE_GENERALE_MENSUELLE (obligatoire, date fixée annuellement) - FORMATION_JUNIORS (obligatoire pour les juniors, date fixée annuellement) - EXCEPTIONNELLE (présence définie par la collectivité) 

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
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    ActiviteRequest activiteRequest = new ActiviteRequest(); // ActiviteRequest | 
    try {
      Activite result = apiInstance.createActiviteCollectivite(collectiviteId, activiteRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ActivitsApi#createActiviteCollectivite");
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
| **activiteRequest** | [**ActiviteRequest**](ActiviteRequest.md)|  | |

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
| **404** |  |  -  |

<a id="createActiviteFederation"></a>
# **createActiviteFederation**
> Activite createActiviteFederation(activiteFederationRequest)

Créer une activité de la fédération (invitation des collectivités)

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
    ActiviteFederationRequest activiteFederationRequest = new ActiviteFederationRequest(); // ActiviteFederationRequest | 
    try {
      Activite result = apiInstance.createActiviteFederation(activiteFederationRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ActivitsApi#createActiviteFederation");
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
| **activiteFederationRequest** | [**ActiviteFederationRequest**](ActiviteFederationRequest.md)|  | |

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

<a id="getActiviteCollectivite"></a>
# **getActiviteCollectivite**
> Activite getActiviteCollectivite(collectiviteId, activiteId)

Obtenir les détails d&#39;une activité

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
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    UUID activiteId = UUID.randomUUID(); // UUID | Identifiant unique de l'activité
    try {
      Activite result = apiInstance.getActiviteCollectivite(collectiviteId, activiteId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ActivitsApi#getActiviteCollectivite");
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

[**Activite**](Activite.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Détails de l&#39;activité |  -  |
| **404** |  |  -  |

<a id="getActiviteFederation"></a>
# **getActiviteFederation**
> Activite getActiviteFederation(activiteId)

Obtenir les détails d&#39;une activité de la fédération

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
    UUID activiteId = UUID.randomUUID(); // UUID | Identifiant unique de l'activité
    try {
      Activite result = apiInstance.getActiviteFederation(activiteId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ActivitsApi#getActiviteFederation");
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
| **activiteId** | **UUID**| Identifiant unique de l&#39;activité | |

### Return type

[**Activite**](Activite.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Détails de l&#39;activité |  -  |
| **404** |  |  -  |

<a id="listActivitesCollectivite"></a>
# **listActivitesCollectivite**
> ActivitePage listActivitesCollectivite(collectiviteId, page, size, type, dateDebut, dateFin)

Lister les activités d&#39;une collectivité

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
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    Integer page = 0; // Integer | 
    Integer size = 20; // Integer | 
    TypeActiviteEnum type = TypeActiviteEnum.fromValue("ASSEMBLEE_GENERALE_MENSUELLE"); // TypeActiviteEnum | 
    LocalDate dateDebut = LocalDate.now(); // LocalDate | 
    LocalDate dateFin = LocalDate.now(); // LocalDate | 
    try {
      ActivitePage result = apiInstance.listActivitesCollectivite(collectiviteId, page, size, type, dateDebut, dateFin);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ActivitsApi#listActivitesCollectivite");
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
| **page** | **Integer**|  | [optional] [default to 0] |
| **size** | **Integer**|  | [optional] [default to 20] |
| **type** | [**TypeActiviteEnum**](.md)|  | [optional] [enum: ASSEMBLEE_GENERALE_MENSUELLE, FORMATION_JUNIORS, EXCEPTIONNELLE] |
| **dateDebut** | **LocalDate**|  | [optional] |
| **dateFin** | **LocalDate**|  | [optional] |

### Return type

[**ActivitePage**](ActivitePage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des activités |  -  |
| **404** |  |  -  |

<a id="listActivitesFederation"></a>
# **listActivitesFederation**
> ActivitePage listActivitesFederation(page, size, dateDebut, dateFin, obligatoire)

Lister les activités de la fédération

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
    Integer page = 0; // Integer | 
    Integer size = 20; // Integer | 
    LocalDate dateDebut = LocalDate.now(); // LocalDate | 
    LocalDate dateFin = LocalDate.now(); // LocalDate | 
    Boolean obligatoire = true; // Boolean | 
    try {
      ActivitePage result = apiInstance.listActivitesFederation(page, size, dateDebut, dateFin, obligatoire);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ActivitsApi#listActivitesFederation");
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
| **dateDebut** | **LocalDate**|  | [optional] |
| **dateFin** | **LocalDate**|  | [optional] |
| **obligatoire** | **Boolean**|  | [optional] |

### Return type

[**ActivitePage**](ActivitePage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des activités de la fédération |  -  |

<a id="updateActiviteCollectivite"></a>
# **updateActiviteCollectivite**
> Activite updateActiviteCollectivite(collectiviteId, activiteId, activiteRequest)

Mettre à jour une activité

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
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    UUID activiteId = UUID.randomUUID(); // UUID | Identifiant unique de l'activité
    ActiviteRequest activiteRequest = new ActiviteRequest(); // ActiviteRequest | 
    try {
      Activite result = apiInstance.updateActiviteCollectivite(collectiviteId, activiteId, activiteRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ActivitsApi#updateActiviteCollectivite");
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
| **activiteRequest** | [**ActiviteRequest**](ActiviteRequest.md)|  | |

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
| **200** | Activité mise à jour |  -  |
| **404** |  |  -  |

