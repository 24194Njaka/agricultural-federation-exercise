# StatistiquesCollectivitsApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getRapportMensuelCollectivite**](StatistiquesCollectivitsApi.md#getRapportMensuelCollectivite) | **GET** /collectivites/{collectiviteId}/statistiques/rapport-mensuel | Rapport mensuel d&#39;une collectivité (pour la fédération) |
| [**getStatsMembreById**](StatistiquesCollectivitsApi.md#getStatsMembreById) | **GET** /collectivites/{collectiviteId}/statistiques/membres/{membreId} | Statistiques détaillées d&#39;un membre actif |
| [**getStatsMembres**](StatistiquesCollectivitsApi.md#getStatsMembres) | **GET** /collectivites/{collectiviteId}/statistiques/membres | Statistiques détaillées par membre actif |


<a id="getRapportMensuelCollectivite"></a>
# **getRapportMensuelCollectivite**
> RapportMensuelCollectivite getRapportMensuelCollectivite(collectiviteId, annee, mois)

Rapport mensuel d&#39;une collectivité (pour la fédération)

Taux d&#39;assiduité global et nombre de membres inscrits, transmis à la fédération.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.StatistiquesCollectivitsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    StatistiquesCollectivitsApi apiInstance = new StatistiquesCollectivitsApi(defaultClient);
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    Integer annee = 2026; // Integer | 
    Integer mois = 4; // Integer | 
    try {
      RapportMensuelCollectivite result = apiInstance.getRapportMensuelCollectivite(collectiviteId, annee, mois);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatistiquesCollectivitsApi#getRapportMensuelCollectivite");
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
| **annee** | **Integer**|  | |
| **mois** | **Integer**|  | |

### Return type

[**RapportMensuelCollectivite**](RapportMensuelCollectivite.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Rapport mensuel |  -  |
| **404** |  |  -  |

<a id="getStatsMembreById"></a>
# **getStatsMembreById**
> StatsMembre getStatsMembreById(collectiviteId, membreId, dateDebut, dateFin)

Statistiques détaillées d&#39;un membre actif

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.StatistiquesCollectivitsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    StatistiquesCollectivitsApi apiInstance = new StatistiquesCollectivitsApi(defaultClient);
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    UUID membreId = UUID.randomUUID(); // UUID | Identifiant unique du membre
    LocalDate dateDebut = LocalDate.now(); // LocalDate | 
    LocalDate dateFin = LocalDate.now(); // LocalDate | 
    try {
      StatsMembre result = apiInstance.getStatsMembreById(collectiviteId, membreId, dateDebut, dateFin);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatistiquesCollectivitsApi#getStatsMembreById");
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
| **membreId** | **UUID**| Identifiant unique du membre | |
| **dateDebut** | **LocalDate**|  | |
| **dateFin** | **LocalDate**|  | |

### Return type

[**StatsMembre**](StatsMembre.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Statistiques du membre |  -  |
| **404** |  |  -  |

<a id="getStatsMembres"></a>
# **getStatsMembres**
> List&lt;StatsMembre&gt; getStatsMembres(collectiviteId, dateDebut, dateFin)

Statistiques détaillées par membre actif

Pour chaque membre actif (non démissionnaire) : - Taux d&#39;assiduité sur une période donnée - Montant d&#39;encaissement sur une période donnée - Montant impayé potentiel sur une période donnée 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.StatistiquesCollectivitsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    StatistiquesCollectivitsApi apiInstance = new StatistiquesCollectivitsApi(defaultClient);
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    LocalDate dateDebut = LocalDate.now(); // LocalDate | 
    LocalDate dateFin = LocalDate.now(); // LocalDate | 
    try {
      List<StatsMembre> result = apiInstance.getStatsMembres(collectiviteId, dateDebut, dateFin);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling StatistiquesCollectivitsApi#getStatsMembres");
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
| **dateDebut** | **LocalDate**|  | |
| **dateFin** | **LocalDate**|  | |

### Return type

[**List&lt;StatsMembre&gt;**](StatsMembre.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Statistiques détaillées par membre |  -  |
| **404** |  |  -  |

